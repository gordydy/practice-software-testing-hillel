import org.example.asserts.HomePageAsserts;
import org.example.asserts.ProductPageAsserts;
import org.example.pages.HomePage;
import org.example.pages.ProductPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class FilterProductTest {
    private final static String productId = "01J26MFEZ4T4M6HFCRFRQCXTJB";

    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    ProductPageAsserts productPageAsserts = new ProductPageAsserts();
    HomePageAsserts homePageAsserts = new HomePageAsserts();

    @Test
    public void filterProductsTest() {
        open("https://practicesoftwaretesting.com/");
        homePage.isLoaded();
        homePage.searchProductByName("Drawer Tool Cabinet");
        homePageAsserts.hasCorrectName("Drawer Tool Cabinet");
        String price = homePageAsserts.getThePrice();
        homePage.goOnProductPage();
        productPageAsserts.isRedirected(productId);
        productPage.comparePrice(price);
    }

}