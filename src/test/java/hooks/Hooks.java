package hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void openBrowser(){
        System.out.println("Opening a browser......");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After
    public void quitBrowser(){
        if (driver != null ) {
            System.out.println("Closing a Browser...!");
            driver.quit();
        }
    }
}
