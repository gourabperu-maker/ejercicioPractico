package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;


    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public enum WaitType { VISIBLE, CLICKABLE }
    protected void waitExplicit(WebElement element, WaitType type) {
        switch (type) {
            case VISIBLE:
                wait.until(ExpectedConditions.visibilityOf(element));
                break;
            case CLICKABLE:
                wait.until(ExpectedConditions.elementToBeClickable(element));
                break;
            default:
                throw new IllegalArgumentException("Tipo de espera no válido");
        }
    }


    protected <V> V waitUntil(Function<? super WebDriver, V> condition) {
        return wait.until(condition);
    }


    protected void shortSleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
