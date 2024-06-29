package org.example.cartData.cartController;

import org.example.cartData.models.CartDetails;
import org.example.cartData.models.CreateCartResponse;
import org.example.cartData.models.UpdateCartResponse;
import org.example.common.BaseController;
import org.example.cartData.models.AddCartItemRequest;
import org.example.common.ResponseDecorator;

public class CartController extends BaseController<CartController> {

    public ResponseDecorator<CreateCartResponse> createCart() {
        return new ResponseDecorator<>(
                baseClient().post("/carts"),
                CreateCartResponse.class
        );
    }

    public ResponseDecorator<UpdateCartResponse> addItemToCart(String cartId, AddCartItemRequest cartItem) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(cartItem)
                        .post("/carts/" + cartId),
                UpdateCartResponse.class
        );
    }

    public ResponseDecorator<CartDetails> getCart(String cartId) {
        return new ResponseDecorator<>(
                baseClient()
                        .get("/carts/" + cartId),
                CartDetails.class
        );
    }

    public ResponseDecorator<Void> deleteCart(String cartId) {
        return new ResponseDecorator<>(
                baseClient()
                        .delete("/carts/" + cartId),
                Void.class
        );
    }

    public ResponseDecorator<Void> deleteProductFromCart(String cartId, String productId) {
        return new ResponseDecorator<>(
                baseClient()
                        .delete("/carts/" + cartId + "/product/" + productId),
                Void.class
        );
    }

}