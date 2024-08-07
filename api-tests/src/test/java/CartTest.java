import org.example.cartData.cartController.CartController;
import org.example.cartData.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CartTest extends BaseTest {
    private String token;
    private static final String PRODUCT_ID = "01HHJC7RERZ0M3VDGS6X9HM33A";
    CartController cartController = new CartController();

    @BeforeEach
    void beforeEach() {
        token = registerAndLoginUser();
    }

    @Test
    void createUpdateAndDeleteCart() {
        var createdCart = cartController.withToken(token).createCart()
                .assertStatusCode(201)
                .as();
        assertNotNull(createdCart.getId());

        var cartId = createdCart.getId();
        var updateCartResponse = cartController
                .addItemToCart(cartId, new AddCartItemRequest(PRODUCT_ID, 1))
                .assertStatusCode(200)
                .as();
        assertNotNull(updateCartResponse.getMessage());

        var cartDetails = cartController.getCart(cartId)
                .assertStatusCode(200)
                .as();
        var productIds = cartDetails
                .getCartItems()
                .stream()
                .map(CartItem::getProductId).toList();
        assertTrue(productIds.contains(PRODUCT_ID));

        cartController.deleteCart(cartId)
                .assertStatusCode(204);

        cartController.deleteProductFromCart(cartId, PRODUCT_ID)
                .assertStatusCode(204);
    }

}