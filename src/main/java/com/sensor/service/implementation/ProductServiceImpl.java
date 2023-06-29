package com.sensor.service.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

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
import com.sensor.dto.product.request.ProductDTO;
import com.sensor.exception.BlogAppException;
import com.sensor.utils.file.FileHelper;
import com.sensor.mapper.ProductMapper;
import com.sensor.entity.Product;
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
	public List<ProductDTO> getAllEnabled() {
		return productDao.getAllEnabled().stream().map((product) -> getProductWithBase64Image(product)).collect(Collectors.toList());
	}

	@Override
	public ProductDTO getProductEnabled(Long productId) {
		Optional<Product> opt = productDao.getProductEnabled(productId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el producto: " + productId);
		}

		return getProductWithBase64Image(opt.get());
	}

	//
	private ProductDTO getProductWithBase64Image(Product p) {
		String image;

		String path = FILE_DIRECTORY + p.getImage();
		File file = new File(path);

		String encodeBase64 = null;
		ProductDTO pdto = null;
		try {
			String extension = FileHelper.getExtension(file.getName());
			FileInputStream fileInputStream = new FileInputStream(file);
			byte bytes[] = new byte[(int) file.length()];

			fileInputStream.read(bytes);

			encodeBase64 = Base64.getEncoder().encodeToString(bytes);
			image = "data:image/" + extension + ";base64," + encodeBase64;

			fileInputStream.close();

			pdto = productMapper.toProductDTO(p,image);
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}

		return pdto;
	}

	@Override
	@Transactional
	public void save(String productDTOJSON, MultipartFile file) {
		System.out.println("LLege al service 1");

		try {

			System.out.println("LLege adentro del catch 1");
			ProductDTO productDTO = null;
			productDTO = JSONToProductoDTO(productDTOJSON);
			System.out.println(productDTO);

			Optional<Product> optionalProduct = productDao.getProductByName(productDTO.getName());
			Optional<User> user = userDao.getUser(productDTO.getIdUser());

			if (!optionalProduct.isEmpty()) {
				throw new BlogAppException(HttpStatus.NOT_FOUND,
						"Ya existe el producto con nombre : " + productDTO.getName());
			}

			if (user.isEmpty()) {
				throw new BlogAppException(HttpStatus.NOT_FOUND,
						"No se encontro el usuario con id : " + productDTO.getIdUser());
			}

			productDTO.setImage(DEFAULT_IMAGE);
			productDao.save(productMapper.toProduct(productDTO));

			Optional<Product> getLastProduct = productDao.getLastProduct();
			Product product;

			if (!getLastProduct.isEmpty()) {
				if (!file.isEmpty()) {
					product = getLastProduct.get();
					String saveDirectory = createDirectoryAndSaveFile(product, file);
					product.setImage(saveDirectory);
					product.setUpdated(Calendar.getInstance());
					productDao.save(product);
				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void delete(Long productId) {

		Optional<Product> opt = productDao.getProductEnabled(productId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el producto con id : " + productId);
		}

		productDao.delete(productId);
	}

	@Override
	public ProductDTO getProductByName(String name) {
		Optional<Product> opt = productDao.getProductByName(name);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el producto con nombre : " + name);
		}

		return getProductWithBase64Image(opt.get());
	}

	@Override
	public void modify(Long productId, ProductDTO productDTO) {

		Product product = productDao.getProductEnabled(productId).get();

		if (!product.getName().equals(productDTO.getName())) {
			Optional<Product> existProductWithName = productDao.getProductByName(productDTO.getName());
			if (!existProductWithName.isEmpty()) {
				throw new BlogAppException(HttpStatus.CONFLICT,
						"Ya existe un producto con nombre : " + productDTO.getName());
			}
		}

		Optional<User> user = userDao.getUser(productDTO.getIdUser());

		if (user.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND,
					"No se encontro el usuario con id : " + productDTO.getIdUser());
		}

		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		product.setUserId(productDTO.getIdUser());
		product.setCreated(product.getCreated());
		product.setUpdated(Calendar.getInstance());

		productDao.save(product);
	}


	private ProductDTO JSONToProductoDTO(String productDTOJSON) throws JsonMappingException, JsonProcessingException {
		ProductDTO prodDTO = new ProductDTO();
		ObjectMapper objectMapper = new ObjectMapper();
		prodDTO = objectMapper.readValue(productDTOJSON, ProductDTO.class);
		return prodDTO;
	}

	private String createDirectoryAndSaveFile(Product product, MultipartFile file) throws IOException {

		Long productId = product.getProductId();
		File directory = new File(FILE_DIRECTORY + productId);
		String newName = FileHelper.renameFile(file, productId);
		FileHelper.createDirectory(directory);
		FileHelper.saveFile(file, directory, newName);
		String saveDirectory = productId + File.separator + newName;
		return saveDirectory;
	}

}
