package pages;
import hooks.Hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage{

    private WebDriver driver;
    private final String urlDropdown = "https://the-internet.herokuapp.com/dropdown";



    @FindBy(id = "dropdown")
    private WebElement dropdown;



    public DropdownPage(){
        super (Hooks.driver);
        this.driver = Hooks.driver;
        PageFactory.initElements(Hooks.driver,this);
    }


    public void openPage(){
        driver.get(urlDropdown);
    }

    public void seleccionarDropdownList(String value) {
        waitExplicit(dropdown, WaitType.VISIBLE);
        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
    }

    public String getSelectedOption() {
        waitExplicit(dropdown, WaitType.VISIBLE);
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }

}
