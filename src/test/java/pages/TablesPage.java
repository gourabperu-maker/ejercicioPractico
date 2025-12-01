package pages;

import hooks.Hooks;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TablesPage extends BasePage {


    @FindBy(xpath = "//table[@id='table1']")
    private WebElement table1;
    @FindBy(xpath = "//table[@id='table1']//thead//th")
    private List<WebElement> table1Headers;
    @FindBy(xpath = "//table[@id='table1']//tbody//tr")
    private List<WebElement> table1Rows;
    @FindBy(xpath = "//table[@id='table2']")
    private WebElement table2;
    @FindBy(xpath = "//table[@id='table2']//thead//th")
    private List<WebElement> table2Headers;
    @FindBy(xpath = "//table[@id='table2']//tbody//tr")
    private List<WebElement> table2Rows;

    private final String url = "https://the-internet.herokuapp.com/tables";
    private final String tableId; // "table1" o "table2"

    public TablesPage(String tableId) {
        super(Hooks.driver);
        this.tableId = tableId;
        PageFactory.initElements(Hooks.driver, this);
    }

    public void openPage() {
        driver.get(url);
        if ("table1".equalsIgnoreCase(tableId)) {
            waitExplicit(table1, WaitType.VISIBLE);
        } else {
            waitExplicit(table2, WaitType.VISIBLE);
        }
    }

    private List<WebElement> headersOf() {
        if ("table1".equalsIgnoreCase(tableId)) return table1Headers;
        if ("table2".equalsIgnoreCase(tableId)) return table2Headers;
        throw new RuntimeException("Tabla inválida: " + tableId);
    }

    private List<WebElement> rowsOf() {
        if ("table1".equalsIgnoreCase(tableId)) return table1Rows;
        if ("table2".equalsIgnoreCase(tableId)) return table2Rows;
        throw new RuntimeException("Tabla inválida: " + tableId);
    }

    private int columnIndexByName(String columnName) {
        List<WebElement> headers = headersOf();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(columnName.trim())) {
                return i;
            }
        }
        throw new RuntimeException("Columna no encontrada: " + columnName);
    }

    public void sortColumnDescending(String columnName) {
        int colIndex = columnIndexByName(columnName);
        WebElement header = headersOf().get(colIndex);
        String beforeText = "";
        List<WebElement> rowsBefore = rowsOf();
        if (!rowsBefore.isEmpty()) {
            List<WebElement> cells = rowsBefore.get(0).findElements(By.tagName("td"));
            if (cells.size() > colIndex) beforeText = cells.get(colIndex).getText().trim();
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitExplicit(header, WaitType.CLICKABLE);
        js.executeScript("arguments[0].click();", header); // primer click (asc?)
        shortSleep(200);
        js.executeScript("arguments[0].click();", header); // segundo click -> descending
        // esperar hasta que la celda 1, colIndex cambie su texto (o timeout)
        final String expectedBefore = beforeText;
        waitUntil(d -> {
            try {
                List<WebElement> rowsNow = rowsOf();
                if (rowsNow.isEmpty()) return false;
                List<WebElement> cellsNow = rowsNow.get(0).findElements(By.tagName("td"));
                if (cellsNow.size() <= colIndex) return false;
                String now = cellsNow.get(colIndex).getText().trim();
                return !now.equals(expectedBefore); // true cuando cambió
            } catch (StaleElementReferenceException e) {
                // si el elemento se volvió stale, eso también indica cambio
                return true;
            }
        });
        shortSleep(200);
    }

    public String getDueFromRow(int row) {
        WebElement targetRow = rowsOf().get(row - 1);
        return targetRow.findElements(By.tagName("td")).get(3).getText().trim();
    }


    public String getLastNameFromRow(int row) {
        WebElement targetRow = rowsOf().get(row - 1);
        return targetRow.findElements(By.tagName("td")).get(0).getText().trim();
    }


    public String getEmailFromRow(int row) {
        WebElement targetRow = rowsOf().get(row - 1);
        return targetRow.findElements(By.tagName("td")).get(2).getText().trim();
    }

    public int findRowIndexByLastName(String lastName) {
        List<WebElement> rows = rowsOf();
        for (int i = 0; i < rows.size(); i++) {
            String ln = rows.get(i).findElements(By.tagName("td")).get(0).getText().trim();
            if (ln.equalsIgnoreCase(lastName.trim())) return i + 1;
        }
        return -1;
    }
}
