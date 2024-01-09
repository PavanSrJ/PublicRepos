//import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class CawAssignment {

    public static void main(String[] args) {
        // Set the property for the Chrome driver
        // Make sure to replace 'path/to/chromedriver' with the actual path to the chromedriver executable
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    	//webDriverManager.chromeDriver().setup()	
    	
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
        
        List<WebElement> allHeaders = driver.findElements(By.xpath("//table[@id='dynamictable']//tr"));
        
        System.out.println(allHeaders.size());
        
        driver.quit();
    }
}
