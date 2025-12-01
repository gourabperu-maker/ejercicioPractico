package pages;
import hooks.Hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    private final String urlLogin = "https://the-internet.herokuapp.com/login";

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(id = "flash")
    private WebElement message;

    public LoginPage() {
        super(Hooks.driver);
        PageFactory.initElements(Hooks.driver, this);
    }

    public void openPage() {
        driver.get(urlLogin);
    }

    public void enterCredentials(String user, String pass) {
        waitExplicit(usernameInput, WaitType.VISIBLE);
        usernameInput.clear();
        usernameInput.sendKeys(user);

        passwordInput.clear();
        passwordInput.sendKeys(pass);
    }

    public void clickLogin() {
        waitExplicit(loginButton, WaitType.CLICKABLE);
        loginButton.click();
    }

    public String getMessage() {
        waitExplicit(message, WaitType.VISIBLE);
        return message.getText().trim();
    }

    public boolean isSuccess() {
        String msg = getMessage();
        return msg.contains("You logged into a secure area!");
    }
}
