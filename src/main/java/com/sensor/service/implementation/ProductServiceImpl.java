package com.sensor.service.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import com.sensor.dto.product.request.ProductDTO;
import com.sensor.entity.Product;
import com.sensor.utils.ProductTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensor.dao.IProductDao;
import com.sensor.security.dao.IUserDao;
import com.sensor.exception.GeneralException;
import com.sensor.utils.file.FileHelper;
import com.sensor.mapper.ProductMapper;
import com.sensor.security.entity.User;
import com.sensor.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private ProductMapper productMapper;

	@Value("${file.upload-dir}")
	private String FILE_DIRECTORY;

	private static final String DEFAULT_IMAGE = "default.png";

	@Override
	public List<ProductTransport> getAllEnabledProducts() {
		return productDao.getAllEnabledProducts().stream().map((product) -> new ProductTransport(product, getBase64Image(product))).collect(Collectors.toList());
	}

	@Override
	public ProductTransport getEnabledProductById(Long productId) {
		Product product = productDao.getEnabledProductById(productId).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el producto: " + productId));

		return new ProductTransport(product, getBase64Image(product));
	}

	//
	private String getBase64Image(Product p) {
		String image = null;
		String path = FILE_DIRECTORY + p.getImage();
		File file = new File(path);
		String encodeBase64 = null;
		try {
			String extension = FileHelper.getExtension(file.getName());
			FileInputStream fileInputStream = new FileInputStream(file);
			byte bytes[] = new byte[(int) file.length()];

			fileInputStream.read(bytes);

			encodeBase64 = Base64.getEncoder().encodeToString(bytes);
			image = "data:image/" + extension + ";base64," + encodeBase64;

			fileInputStream.close();

		} catch (IOException e1) {
			System.out.println(e1.getMessage());
			throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Problemas en el servidor");
		}

		return image;
	}

	@Override
	@Transactional
	public void saveProduct(String productDTOJSON, MultipartFile file) {
		System.out.println("LLege al service 1");

		try {

			System.out.println("LLege adentro del catch 1");
			ProductDTO productDTO = null;
			productDTO = JSONToProductoDTO(productDTOJSON);
			System.out.println(productDTO);

			Optional<com.sensor.entity.Product> optionalProduct = productDao.getProductByName(productDTO.getName());
			Optional<User> user = userDao.getUserById(productDTO.getIdUser());

			if (!optionalProduct.isEmpty()) {
				throw new GeneralException(HttpStatus.NOT_FOUND,
						"Ya existe el producto con nombre : " + productDTO.getName());
			}

			if (user.isEmpty()) {
				throw new GeneralException(HttpStatus.NOT_FOUND,
						"No se encontro el usuario con id : " + productDTO.getIdUser());
			}

			productDTO.setImage(DEFAULT_IMAGE);
			productDao.saveProduct(productMapper.toProduct(productDTO));

			Optional<com.sensor.entity.Product> getLastProduct = productDao.getLastProduct();
			com.sensor.entity.Product product;

			if (!getLastProduct.isEmpty()) {
				if (!file.isEmpty()) {
					product = getLastProduct.get();
					String saveDirectory = createDirectoryAndSaveFile(product, file);
					product.setImage(saveDirectory);
					product.setUpdated(Calendar.getInstance());
					productDao.saveProduct(product);
				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void deleteProductById(Long productId) {

		Optional<com.sensor.entity.Product> opt = productDao.getEnabledProductById(productId);

		if (opt.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el producto con id : " + productId);
		}

		productDao.deleteProductById(productId);
	}

	@Override
	public ProductTransport getProductByName(String name) {
		Product product = productDao.getProductByName(name).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el producto: " + name));

		return new ProductTransport(product, getBase64Image(product));
	}

	@Override
	public void modifyProductById(Long productId, ProductDTO productDTO) {

		com.sensor.entity.Product product = productDao.getEnabledProductById(productId).get();

		if (!product.getName().equals(productDTO.getName())) {
			Optional<com.sensor.entity.Product> existProductWithName = productDao.getProductByName(productDTO.getName());
			if (!existProductWithName.isEmpty()) {
				throw new GeneralException(HttpStatus.CONFLICT,
						"Ya existe un producto con nombre : " + productDTO.getName());
			}
		}

		Optional<User> user = userDao.getUserById(productDTO.getIdUser());

		if (user.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND,
					"No se encontro el usuario con id : " + productDTO.getIdUser());
		}

		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		product.setUserId(productDTO.getIdUser());
		product.setCreated(product.getCreated());
		product.setUpdated(Calendar.getInstance());

		productDao.saveProduct(product);
	}


	private ProductDTO JSONToProductoDTO(String productDTOJSON) throws JsonMappingException, JsonProcessingException {
		ProductDTO prodDTO = new ProductDTO();
		ObjectMapper objectMapper = new ObjectMapper();
		prodDTO = objectMapper.readValue(productDTOJSON, ProductDTO.class);
		return prodDTO;
	}

	private String createDirectoryAndSaveFile(com.sensor.entity.Product product, MultipartFile file) throws IOException {

		Long productId = product.getProductId();
		File directory = new File(FILE_DIRECTORY + productId);
		String newName = FileHelper.renameFile(file, productId);
		FileHelper.createDirectory(directory);
		FileHelper.saveFile(file, directory, newName);
		String saveDirectory = productId + File.separator + newName;
		return saveDirectory;
	}

}
