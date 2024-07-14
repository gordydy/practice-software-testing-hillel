import org.example.pages.*;
import org.example.userData.models.RegisterUserRequest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class UserTest {

    HomePage homePage = new HomePage();
    Header header = new Header();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    AccountPage accountPage = new AccountPage();

    @Test
    public void registerNewUserAndLogin() {
        open("https://practicesoftwaretesting.com");
        homePage.isLoaded();
        header.clickSignInMenuItem();
        loginPage.isLoaded()
                .clickRegisterYourAccount();
        registerPage.isLoaded()
                .assertThat()
                .hasCorrectInfo();

        var user = getUser();
        registerPage.registerNewUser(user);

        loginPage.isLoaded()
                .login(user.getEmail(), user.getPassword());

        accountPage.isLoaded();
        header.assertThat().isSignedIn(user.getFirstName() + " " + user.getLastName());
    }

    private RegisterUserRequest getUser() {
        return RegisterUserRequest.builder()
                .firstName("David")
                .lastName("Bowie")
                .address("12 Liberty St")
                .city("Cambridge")
                .state("none")
                .country("United Kingdom of Great Britain and Northern Ireland (the)")
                .postcode("12345")
                .phone("09876589")
                .dob("06/03/1946")
                .email("BrilliantGuitarSolo@gmail.com")
                .password("gIlmour1243!")
                .build();
    }
}