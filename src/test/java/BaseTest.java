import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

//    ChromeDriver driver;

    @BeforeAll
    static void setBeforeAll() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }



//    @AfterEach
//   public void closeAfterEach () {
//        driver.close();
//    }


}
