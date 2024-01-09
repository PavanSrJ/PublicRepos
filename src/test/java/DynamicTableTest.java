import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class DynamicTableTest {

    public static void main(String[] args) {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the given URL
            driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");

            // Click on the "Table Data" button
            WebElement tableDataButton = driver.findElement(By.id("populate"));
            tableDataButton.click();

            // Wait for the input text box to be displayed
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement inputTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("data")));

            // Insert the JSON data into the input text box
            String jsonData = "[{\"name\": \"Bob\", \"age\": 20, \"gender\": \"male\"}, " +
                              "{\"name\": \"George\", \"age\": 42, \"gender\": \"male\"}, " +
                              "{\"name\": \"Sara\", \"age\": 42, \"gender\": \"female\"}, " +
                              "{\"name\": \"Conor\", \"age\": 40, \"gender\": \"male\"}, " +
                              "{\"name\": \"Jennifer\", \"age\": 42, \"gender\": \"female\"}]";
            inputTextBox.clear();
            inputTextBox.sendKeys(jsonData);

            // Click on the "Refresh Table" button
            WebElement refreshTableButton = driver.findElement(By.id("refresh"));
            refreshTableButton.click();

            // Verify the data in the table
            List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='records']/tbody/tr"));

            for (int i = 0; i < tableRows.size(); i++) {
                String[] rowData = tableRows.get(i).getText().split(" ");
                String name = rowData[0];
                int age = Integer.parseInt(rowData[1]);
                String gender = rowData[2];
                
                // Assertion: Compare the data with the expected values
                assertData(name, age, gender, jsonData, i);
            }

            System.out.println("Test passed!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser window
            driver.quit();
        }
    }

    private static void assertData(String expectedName, int expectedAge, String expectedGender, String jsonData, int index) {
        String[] jsonArray = jsonData.split(",");
        String[] expectedData = jsonArray[index].replaceAll("[{}\"]", "").split(":");
        String expectedNameInJson = expectedData[1].trim();
        int expectedAgeInJson = Integer.parseInt(expectedData[3].trim());
        String expectedGenderInJson = expectedData[5].trim();

        assert expectedName.equals(expectedNameInJson) : "Name mismatch at row " + (index + 1);
        assert expectedAge == expectedAgeInJson : "Age mismatch at row " + (index + 1);
        assert expectedGender.equals(expectedGenderInJson) : "Gender mismatch at row " + (index + 1);
    }
}
