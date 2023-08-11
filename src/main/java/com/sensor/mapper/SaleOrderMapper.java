package com.sensor.mapper;

import com.sensor.dto.sale.response.saleforadmin.*;
import com.sensor.dto.sale.response.saleforuser.*;
import com.sensor.entity.*;
import com.sensor.utils.date.StringToLocalDateTimeAndViceVersa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SaleOrderMapper {

    @Autowired
    protected StringToLocalDateTimeAndViceVersa stdv;


    //------------------------------------------------------------------------------------------------------------------------------------------
    //Estos metodos sirven para devolver una SaleOrder en cualquier estado para el administrador..
    @Mappings({
            @Mapping(source = "saleOrder.saleOrderId", target = "saleOrderId"),
            @Mapping(source = "saleOrder.state", target = "state"),
            @Mapping(source = "saleOrder.subtotal", target = "subtotal"),
            @Mapping(source = "saleOrder.total", target = "total"),
            @Mapping(source = "saleOrder.cartId", target = "cartId"),
            @Mapping(source = "saleOrder.created", target = "created"),
            @Mapping(source = "saleOrder.updated", target = "updated"),
            @Mapping(source = "saleOrder", target = "paymentMethod"),
            @Mapping(source = "saleOrder", target = "shippingMethod"),
            @Mapping(source = "saleOrder", target = "user"),
            @Mapping(source = "saleOrder.products", target = "products"),
            @Mapping(source = "saleOrder.addresses", target = "addresses"),
    })
    public abstract SaleForAdminResponse saleOrderToSaleForAdminResponse(SaleOrder saleOrder);
    public abstract List<SaleForAdminResponse> saleOrderToSaleForAdminResponse(List<SaleOrder> saleOrder);

    @Mappings({
            @Mapping(source = "saleOrder.userId", target = "userId"),
            @Mapping(source = "saleOrder.name", target = "name"),
            @Mapping(source = "saleOrder.lastname", target = "lastname"),
            @Mapping(source = "saleOrder.email", target = "email"),
    })
    public abstract UserInSaleForAdminResponse userToUserInSaleForAdminResponse(SaleOrder saleOrder);

    @Mappings({
            @Mapping(source = "saleAddress.street", target = "street"),
            @Mapping(source = "saleAddress.streetNumber", target = "streetNumber"),
            @Mapping(source = "saleAddress.floor", target = "floor"),
            @Mapping(source = "saleAddress.apartmentNumber", target = "apartmentNumber"),
            @Mapping(source = "saleAddress", target = "typeOfAddress"),
    })
    public abstract SaleAddressInSaleForAdminResponse saleAddressToSaleAddressInSaleForAdminResponse(SaleAddress saleAddress);
    public abstract List<SaleAddressInSaleForAdminResponse> saleAddressToSaleAddressInSaleForAdminResponse(List<SaleAddress> saleAddresses);


    @Mappings({
            @Mapping(source = "saleAddress.typeOfAddress", target = "name"),
    })
    public abstract TypeOfAddressInSaleForAdminResponse typeOfAddressToTypeOfAddressInSaleForAdminResponse(SaleAddress saleAddress);


    @Mappings({
            @Mapping(source = "saleProduct.productId", target = "productId"),
            @Mapping(source = "saleProduct.name", target = "name"),
            @Mapping(source = "saleProduct.price", target = "price"),
            @Mapping(source = "saleProduct.quantity", target = "quantity"),
            @Mapping(source = "saleProduct.description", target = "description"),
            @Mapping(source = "saleProduct.addedToCart", target = "addedToCart"),
    })
    public abstract SaleProductInSaleForAdminResponse saleProductToSaleProductInSaleForAdminResponse(SaleProduct saleProduct);


    @Mappings({
            @Mapping(source = "saleOrder.shippingMethodName", target = "name"),
    })
    public abstract ShippingMethodInSaleForAdminResponse shippingMethodToShippingMethodInSaleForAdminResponse(SaleOrder saleOrder);

    @Mappings({
            @Mapping(source = "saleOrder.paymentMethodName", target = "name"),
            @Mapping(source = "saleOrder.paymentMethodDiscount", target = "discount"),
    })
    public abstract PaymentMethodInSaleForAdminResponse paymentMethodToPaymentMethodInSaleForAdminResponse(SaleOrder saleOrder);

    //------------------------------------------------------------------------------------------------------------------------------------------
    //Estos metodos sirven para devolver una SaleOrder en cualquier estado.

    @Mappings({
            @Mapping(source = "saleOrder.saleOrderId", target = "saleOrderId"),
            @Mapping(source = "saleOrder.state", target = "state"),
            @Mapping(source = "saleOrder.subtotal", target = "subtotal"),
            @Mapping(source = "saleOrder.total", target = "total"),
            @Mapping(source = "saleOrder.cartId", target = "cartId"),
            @Mapping(source = "saleOrder.created", target = "created"),
            @Mapping(source = "saleOrder.updated", target = "updated"),
            @Mapping(source = "saleOrder", target = "paymentMethod"),
            @Mapping(source = "saleOrder", target = "shippingMethod"),
            @Mapping(source = "saleOrder.products", target = "products"),
            @Mapping(source = "saleOrder.addresses", target = "addresses"),
    })
    public abstract SaleForUserLoggedInResponse saleOrderToSaleForUserLoggedInResponse(SaleOrder saleOrder);
    public abstract List<SaleForUserLoggedInResponse> saleOrderToSaleForUserLoggedInResponse(List<SaleOrder> saleOrders);

    @Mappings({
            @Mapping(source = "saleAddress.street", target = "street"),
            @Mapping(source = "saleAddress.streetNumber", target = "streetNumber"),
            @Mapping(source = "saleAddress.floor", target = "floor"),
            @Mapping(source = "saleAddress.apartmentNumber", target = "apartmentNumber"),
            @Mapping(source = "saleAddress", target = "typeOfAddress"),
    })
    public abstract SaleAddressInSaleForUserLoggedInResponse saleAddressToSaleAddressInSaleForUserLoggedInResponse(SaleAddress saleAddress);
    public abstract List<SaleAddressInSaleForUserLoggedInResponse> saleAddressToSaleAddressInSaleForUserLoggedInResponse(List<SaleAddress> saleAddresses);

    @Mappings({
            @Mapping(source = "saleAddress.typeOfAddress", target = "name"),
    })
    public abstract TypeOfAddressInSaleForUserLoggedInResponse typeOfAddressToTypeOfAddressInSaleForUserLoggedInResponse(SaleAddress saleAddress);


    @Mappings({
            @Mapping(source = "saleProduct.productId", target = "productId"),
            @Mapping(source = "saleProduct.name", target = "name"),
            @Mapping(source = "saleProduct.price", target = "price"),
            @Mapping(source = "saleProduct.quantity", target = "quantity"),
            @Mapping(source = "saleProduct.description", target = "description"),
            @Mapping(source = "saleProduct.addedToCart", target = "addedToCart"),
    })
    public abstract SaleProductInSaleForUserLoggedInResponse saleProductToSaleProductInSaleForUserLoggedInResponse(SaleProduct saleProduct);

    @Mappings({
            @Mapping(source = "saleOrder.shippingMethodName", target = "name"),
    })
    public abstract ShippingMethodInSaleForUserLoggedInResponse shippingMethodToShippingMethodInSaleForUserLoggedInResponse(SaleOrder saleOrder);

    @Mappings({
            @Mapping(source = "saleOrder.paymentMethodName", target = "name"),
            @Mapping(source = "saleOrder.paymentMethodDiscount", target = "discount"),
    })
    public abstract PaymentMethodInSaleForUserLoggedInResponse paymentMethodToPaymentMethodInSaleForUserLoggedInResponse(SaleOrder saleOrder);

    //------------------------------------------------------------------------------------------------------------------------------------------


}
