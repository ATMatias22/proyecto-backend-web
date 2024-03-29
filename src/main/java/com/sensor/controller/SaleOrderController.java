package com.sensor.controller;

import com.sensor.dto.sale.response.saleforadmin.SaleForAdminResponse;
import com.sensor.dto.sale.response.saleforuser.SaleForUserLoggedInResponse;
import com.sensor.enums.SaleOrderState;
import com.sensor.exception.GeneralException;
import com.sensor.mapper.SaleOrderMapper;
import com.sensor.service.ISaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/sale-orders")
@CrossOrigin(origins = "*")
public class SaleOrderController {

    @Autowired
    private ISaleOrderService saleOrderService;

    @Autowired
    private SaleOrderMapper saleOrderMapper;


    @PreAuthorize("isAuthenticated()")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/user")
    public ResponseEntity<List<SaleForUserLoggedInResponse>> getSaleOrderByUserLoggedInAndStateEntregarProductos(@RequestParam String state) {
        SaleOrderState stateFound = Arrays.stream(SaleOrderState.values())
                .filter(saleOrderState -> saleOrderState.name().equalsIgnoreCase(state))
                .findFirst()
                .orElseThrow(() ->new GeneralException(HttpStatus.BAD_REQUEST, "No se encuentra el estado que esta buscando"));

        return new ResponseEntity<>(this.saleOrderMapper.saleOrderToSaleForUserLoggedInResponse(this.saleOrderService.getSaleOrderByUserLoggedInAndState(stateFound)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/admin")
    public ResponseEntity<List<SaleForAdminResponse>> getSaleOrderByState(@RequestParam String state) {
        SaleOrderState stateFound = Arrays.stream(SaleOrderState.values())
                .filter(saleOrderState -> saleOrderState.name().equalsIgnoreCase(state))
                .findFirst()
                .orElseThrow(() ->new GeneralException(HttpStatus.BAD_REQUEST, "No se encuentra el estado que esta buscando"));

        return new ResponseEntity<>(this.saleOrderMapper.saleOrderToSaleForAdminResponse(this.saleOrderService.getSaleOrderByState(stateFound)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/admin/cancel/{saleOrderId}")
    public ResponseEntity<Void> cancelSaleOrder(@PathVariable("saleOrderId") Long saleOrderId) {
        this.saleOrderService.cancelSaleOrder(saleOrderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/admin/next-step/{saleOrderId}")
    public ResponseEntity<Void> nextStepSaleOrder(@PathVariable("saleOrderId") Long saleOrderId) {
        this.saleOrderService.nextStep(saleOrderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
