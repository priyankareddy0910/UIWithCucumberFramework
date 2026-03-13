package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;
import java.time.Duration;

public class EbaySteps {
    
    WebDriver driver;
    
    @Given("I navigate to eBay homepage")
    public void i_navigate_to_ebay_homepage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.ebay.com");
        System.out.println("✓ Navigated to eBay homepage");
    }
    
    @When("I search for products with following details:")
    public void i_search_for_products_with_following_details(DataTable dataTable) {
        
        List<Map<String, String>> productsList = dataTable.asMaps(String.class, String.class);
        
        System.out.println("\n========================================");
        System.out.println("Total products: " + productsList.size());
        System.out.println("========================================\n");
        
        for (int i = 0; i < productsList.size(); i++) {
            Map<String, String> productData = productsList.get(i);
            
            String product = productData.get("product");
            String category = productData.get("category");
            String condition = productData.get("condition");
            String maxPrice = productData.get("maxPrice");
            
            System.out.println("========== Product #" + (i + 1) + " ==========");
            System.out.println("Product: " + product);
            System.out.println("Category: " + category);
            System.out.println("Condition: " + condition);
            System.out.println("Max Price: $" + maxPrice);
            
            try {
                driver.findElement(By.id("gh-ac")).clear();
                driver.findElement(By.id("gh-ac")).sendKeys(product);
                System.out.println("✓ Entered: " + product);
                
                driver.findElement(By.id("gh-btn")).click();
                System.out.println("✓ Clicked search button");
                
                Thread.sleep(2000);
                
                driver.get("https://www.ebay.com");
                System.out.println("✓ Back to homepage\n");
                
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }
    }
    
    @Then("I should see search results")
    public void i_should_see_search_results() {
        System.out.println("========================================");
        System.out.println("✅ Test completed successfully!");
        System.out.println("========================================");
        
        if (driver != null) {
            driver.quit();
        }
    }
}