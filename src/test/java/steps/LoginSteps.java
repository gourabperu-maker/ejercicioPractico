package steps;

import io.cucumber.java.en.*;
import pages.LoginPage;
import static org.junit.Assert.*;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void theUserIsOnLoginPage() {
        loginPage = new LoginPage();
        loginPage.openPage();
    }

    @When("the user enters username {string} and password {string}")
    public void theUserEntersCredentials(String username, String password) {
        loginPage.enterCredentials(username, password);
    }

    @When("clicks the login button")
    public void clicksTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the user should see the message containing {string}")
    public void theUserShouldSeeMessage(String expectedMessage) {
        String actual = loginPage.getMessage();
        assertTrue(actual.contains(expectedMessage));
    }

    @Then("the login result should be {string}")
    public void theLoginResultShouldBe(String expectedSuccess) {
        boolean success = Boolean.parseBoolean(expectedSuccess);
        assertEquals(success, loginPage.isSuccess());
    }
}
