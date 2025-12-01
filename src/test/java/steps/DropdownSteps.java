package steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DropdownPage;

import static org.junit.Assert.assertEquals;

public class DropdownSteps {
    private DropdownPage dropdownPage;

    @Given("the user is on the dropdown page")
    public void navigateToDropdown() {
        dropdownPage = new DropdownPage();
        dropdownPage.openPage();
    }


    @When("the user selects {string} from the dropdown")
    public void theUserSelectsFromTheDropdown(String option) {
        dropdownPage.seleccionarDropdownList(option);
    }

    @Then("the selected option should be {string}")
    public void theSelectedOptionShouldBe(String expectedOption) {
        String actualOption = dropdownPage.getSelectedOption();
        assertEquals(expectedOption, actualOption);
    }
}
