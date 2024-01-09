import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class CawAssignment {

    public static void main(String[] args) {
    	
        WebDriver driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");

        // Find the element using XPath
        WebElement tableDataElement = driver.findElement(By.xpath("//summary[contains(text(),'Table Data')]"));
        tableDataElement.click();

        WebElement textBoxElement = driver.findElement(By.xpath("//textarea[@id='jsondata']"));
        textBoxElement.clear();
        textBoxElement.click();

        // JSON data
        String jsonTableData =	"[{\"name\": \"Bob\", \"age\": 20, \"gender\": \"male\"}, " +
        						"{\"name\": \"George\", \"age\": 42, \"gender\": \"male\"}, " +
        						"{\"name\": \"Sara\", \"age\": 42, \"gender\": \"female\"}, " +
        						"{\"name\": \"Conor\", \"age\": 40, \"gender\": \"male\"}, " +
        						"{\"name\": \"Jennifer\", \"age\": 42, \"gender\": \"female\"}]";
        textBoxElement.sendKeys(jsonTableData);
        
        WebElement refreshButtonElement = driver.findElement(By.xpath("//button[@id='refreshtable']"));
        refreshButtonElement.click();
        
        List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='dynamictable']//tr"));
        String actualJsonData = captureDataFromTable(tableRows);
                
        // Assert that the captured data matches the expected JSON data
        assert actualJsonData.equals(jsonTableData) : "Data in the UI table does not match the expected JSON data.";
        
        driver.quit();
    }
    
    private static String captureDataFromTable(List<WebElement> tableRows) {
        StringBuilder capturedData = new StringBuilder();
        System.out.println();
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));          
            for (WebElement cell : cells) {
                capturedData.append(cell.getText()).append(",");
            }
        }
        return capturedData.toString();
    }
}
