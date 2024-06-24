import org.example.cartData.cartController.CartController;
import org.example.cartData.models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest{
        private static final String PRODUCT_ID = "01HHJC7RERZ0M3VDGS6X9HM33A";
        CartController cartController = new CartController();

        @Test
        void Cart() {
            var createdCart = cartController.createCart()
                    .as(CreateCartResponse.class);
            assertNotNull(createdCart.getId());

            var cartId = createdCart.getId();
            var updateCartResponse = cartController
                    .addItemToCart(cartId, new AddCartItemRequest(PRODUCT_ID, 1))
                    .as(UpdateCartResponse.class);
            assertNotNull(updateCartResponse.getMessage());

            var cartDetails = cartController.getCart(cartId)
                    .as(CartDetails.class);
            var productIds = cartDetails
                    .getCartItems()
                    .stream()
                    .map(CartItem::getProductId)
                    .toList();
            assertTrue(productIds.contains(PRODUCT_ID));

            cartController.deleteCart(cartId)
                    .then()
                    .statusCode(204);

            cartController.deleteProductFromCart(cartId, PRODUCT_ID)
                    .then()
                    .statusCode(204);
        }

}