import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.example.userData.models.*;
import org.example.userData.userController.UserController;

public class BaseTest {
    long timestamp = System.currentTimeMillis();

    private static final String ADMIN_EMAIL = "admin@practicesoftwaretesting.com";
    private static final String ADMIN_PASSWORD = "welcome01";
    protected static final String USER_PASSWORD = "jimiJIMI27!";

    public final String userEmail = "hendrixxx" + timestamp + "@gmail.com";

    static {
        configureRestAssured();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    private static void configureRestAssured() {
        var objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        RestAssured.config = RestAssured.config()
                .objectMapperConfig(
                        RestAssured.config()
                                .getObjectMapperConfig()
                                .jackson2ObjectMapperFactory((cls, charset) -> objectMapper)
                );
    }

    public void registerUser(String email, String password) {
        var userController = new UserController();
        var registerUserRequest = buildUserForRequest(email,password);
        userController.registerUser(registerUserRequest).as();
    }

    public String loginUser(String userEmail, String password) {
        var userController = new UserController();
        var userLoginResponse = userController.loginUser(new LoginRequest(userEmail, password))
                .as();
        return userLoginResponse.getAccessToken();
    }

    public String registerAndLoginUser() {
        registerUser(userEmail, USER_PASSWORD);
        return loginUser(userEmail, USER_PASSWORD);
    }

    public String loginAsAdmin() {
        return loginUser(ADMIN_EMAIL, ADMIN_PASSWORD);
    }

    public RegisterUserRequest buildUserForRequest(String email, String password) {
        return RegisterUserRequest.builder()
                .firstName("Jimi")
                .lastName("Hendrix")
                .phone("0987654321")
                .email(email)
                .password(password)
                .country("USA")
                .state("Washington")
                .city("Seattle")
                .address("Hendrix ave. 27")
                .postcode("11223344")
                .dob("1941-01-01")
                .build();
    }
}