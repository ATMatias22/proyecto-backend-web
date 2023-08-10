package com.sensor.mapper;

import com.sensor.dto.cart.response.cartterminadoforadmin.CartProductInCartTerminadoForAdminResponse;
import com.sensor.dto.cart.response.cartterminadoforadmin.FinishedCartTerminadoForAdminResponse;
import com.sensor.dto.cart.response.cartterminadoforadmin.ProductInCartTerminadoByAdminResponse;
import com.sensor.dto.cart.response.cartterminadoforadmin.UserInCartTerminadoByAdminResponse;
import com.sensor.dto.sale.response.saleforuser.*;
import com.sensor.entity.*;
import com.sensor.security.entity.User;
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
    //todos los carritos en estado terminado para que el usuario administrador pueda ver las ventas.
    @Mappings({
            @Mapping(source = "cart.cartId", target = "cartId"),
            @Mapping(target = "datePurchase", expression = "java(stdv.getString(cart.getUpdated()))"),
            @Mapping(source = "cart.user", target = "user"),
            @Mapping(source = "cart.cartProducts", target = "cartProducts"),
    })
    public abstract FinishedCartTerminadoForAdminResponse cartToFinishedCartTerminadoForAdminResponse(Cart cart);

    public abstract List<FinishedCartTerminadoForAdminResponse> cartToFinishedCartTerminadoForAdminResponse(List<Cart> cart);

    @Mappings({
            @Mapping(source = "user.userId", target = "userId"),
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.lastname", target = "lastname"),
            @Mapping(source = "user.email", target = "email"),
    })
    public abstract UserInCartTerminadoByAdminResponse userToUserInCartTerminadoByAdminResponse(User user);


    @Mappings({
            @Mapping(source = "cartProduct.product", target = "product"),
            @Mapping(source = "cartProduct.quantity", target = "quantity"),
    })
    public abstract CartProductInCartTerminadoForAdminResponse cartToCartProductInCartTerminadoForAdminResponse(CartProduct cartProduct);

    public abstract List<CartProductInCartTerminadoForAdminResponse> cartToCartProductInCartTerminadoForAdminResponse(List<CartProduct> cartProduct);

    @Mappings({
            @Mapping(source = "product.name", target = "name"),
            @Mapping(source = "product.price", target = "price"),
            @Mapping(source = "product.productId", target = "productId"),
    })
    public abstract ProductInCartTerminadoByAdminResponse productToProductInCartTerminadoByAdminResponse(Product product);

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
