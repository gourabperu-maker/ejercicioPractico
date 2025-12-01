package steps;

import io.cucumber.java.en.*;
import pages.TablesPage;

import static org.junit.Assert.*;

public class TablesSteps {

    private TablesPage page;
    private String currentTableId;

    @Given("the user is on the tables page for table {string}")
    public void theUserIsOnTheTablesPageForTable(String tableId) {
        this.currentTableId = tableId;
        page = new TablesPage(tableId);
        page.openPage();
    }

    @When("the user sorts the column {string} in descending order for table {string}")
    public void theUserSortsTheColumnInDescendingOrderForTable(String columnName, String tableId) {
        if (!tableId.equals(this.currentTableId)) {
            page = new TablesPage(tableId);
            page.openPage();
            this.currentTableId = tableId;
        }
        page.sortColumnDescending(columnName);
        System.out.println("----- DEBUG AFTER SORT (" + tableId + ") -----");
        System.out.println("Row1 Due: " + page.getDueFromRow(1));
        System.out.println("Row2 Due: " + page.getDueFromRow(2));
        System.out.println("Row3 Due: " + page.getDueFromRow(3));
        System.out.println("Row4 Due: " + page.getDueFromRow(4));
        System.out.println("---------------------------------------------");
    }

    @Then("the Due amount in row {int} of table {string} should be {string}")
    public void theDueAmountInRowOfTableShouldBe(int row, String tableId, String expected) {
        if (!tableId.equals(this.currentTableId)) {
            page = new TablesPage(tableId);
            page.openPage();
            this.currentTableId = tableId;
        }
        String actual = page.getDueFromRow(row);
        assertEquals(expected, actual);
    }
}
