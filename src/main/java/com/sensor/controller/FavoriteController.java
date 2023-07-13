package com.sensor.controller;


import com.sensor.dto.favorite.response.FavoriteResponse;
import com.sensor.mapper.FavoriteMapper;
import com.sensor.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {

    @Autowired
    private IFavoriteService favoriteService;

    @Autowired
    private FavoriteMapper favoriteMapper;


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FavoriteResponse>> getAllFavoritesByUserLoggedIn() {
        return new ResponseEntity<>(this.favoriteService.getAllFavoritesByUserLoggedIn().stream().map( fav -> this.favoriteMapper.toFavoriteResponse(fav)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/products/{productId}")
    public ResponseEntity<Void> saveFavorite(@PathVariable("productId") Long productId) {
        this.favoriteService.saveFavorite(this.favoriteMapper.toFavoriteTransportToService(productId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<Void> deleteFavoriteByUserAndProduct(@PathVariable("productId") Long productId) {
        this.favoriteService.deleteFirstFavoriteByUserAndProduct(this.favoriteMapper.toFavoriteTransportToService(productId));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
