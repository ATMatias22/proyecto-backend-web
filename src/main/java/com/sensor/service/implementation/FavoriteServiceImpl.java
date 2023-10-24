package com.sensor.service.implementation;

import com.sensor.dao.IFavoriteDao;
import com.sensor.entity.Favorite;
import com.sensor.entity.Product;
import com.sensor.exception.GeneralException;
import com.sensor.security.MainUser;
import com.sensor.security.entity.User;
import com.sensor.security.service.IUserService;
import com.sensor.service.IFavoriteService;
import com.sensor.service.IProductService;
import com.sensor.utils.directory.DirectoryData;
import com.sensor.utils.file.FileHelper;
import com.sensor.utils.transport.favorite.FavoriteTransportToController;
import com.sensor.utils.transport.favorite.FavoriteTransportToService;
import com.sensor.utils.transport.product.ProductTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

    @Autowired
    private IFavoriteDao favoriteDao;

    @Autowired
    private IProductService productService;

    @Override
    public List<FavoriteTransportToController> getAllFavoritesByUserLoggedIn(User userLoggedIn) {

        return this.favoriteDao.getAllFavoritesByUser(userLoggedIn).stream().map(fav -> {
            String pathFile = fav.getProduct().getImage() != null ? DirectoryData.FILE_DIRECTORY_PRODUCT_IMAGES + fav.getProduct().getImage() : null;
            return new FavoriteTransportToController(new ProductTransportToController(fav.getProduct(), FileHelper.filePathToBase64String(pathFile, DirectoryData.PRODUCT_DEFAULT_IMAGE)), fav.getCreated());
        }).collect(Collectors.toList());
    }

    @Override
    public void saveFavorite(FavoriteTransportToService favoriteTransportToService, User userLoggedIn) {

        Product product = this.productService.getEnabledProductById(favoriteTransportToService.getProductId()).getProduct();
        boolean exists = this.favoriteDao.existsFavoriteByUserAndProduct(userLoggedIn, product);

        if(exists){
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Este producto ya fue agregado a favoritos");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(userLoggedIn);
        favorite.setProduct(product);

        this.favoriteDao.saveFavorite(favorite);

    }

    @Override
    @Transactional
    public void deleteFirstFavoriteByUserAndProduct(FavoriteTransportToService favoriteTransportToService, User userLoggedIn) {

        Product product = this.productService.getEnabledProductById(favoriteTransportToService.getProductId()).getProduct();
        boolean existsFavorite = this.favoriteDao.existsFavoriteByUserAndProduct(userLoggedIn, product);

        if(!existsFavorite){
            throw new GeneralException(HttpStatus.BAD_REQUEST, "El producto que quiere eliminar de favoritos, no esta en favoritos");
        }

        this.favoriteDao.deleteFirstFavoriteByUserAndProduct(userLoggedIn, product);
    }

    @Override
    public void deleteAllFavoritesByUser(User userLoggedIn) {
        this.favoriteDao.deleteAllFavoritesByUser(userLoggedIn);
    }
}
