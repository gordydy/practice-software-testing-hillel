package org.example.cartData.cartController;

import io.restassured.response.Response;
import org.example.BaseController;
import org.example.cartData.models.AddCartItemRequest;

public class CartController extends BaseController {

    public Response createCart() {
        return baseClient()
                .post("/carts");
    }

    public Response addItemToCart(String cartId, AddCartItemRequest cartItem) {
        return baseClient()
                .body(cartItem)
                .post("/carts/" + cartId);
    }

    public Response getCart(String cartId) {

        return baseClient()
                .get("/carts/" + cartId);
    }

    public Response deleteCart(String cartId) {
        return baseClient()
                .delete("/carts/" + cartId);
    }

    public Response deleteProductFromCart(String cartId, String productId) {
        return baseClient()
                .delete("/carts/" + cartId+ "/product/" + productId);
    }
}