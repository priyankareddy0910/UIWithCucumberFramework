package actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import elements.EbayAdvancedSearch_Elements;
import steps.Common_Steps;

public class EbayAdvancedSearch_Actions {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final EbayAdvancedSearch_Elements ebayadvancedsearch_elements;

    public EbayAdvancedSearch_Actions(Common_Steps common_steps) {
        this.driver = common_steps.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.ebayadvancedsearch_elements = new EbayAdvancedSearch_Elements(driver);
    }

    public void clickOnEbayLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(ebayadvancedsearch_elements.ebayLogo)).click();
    }

    public void enterSearchString(String value) {
        wait.until(ExpectedConditions.visibilityOf(ebayadvancedsearch_elements.searchString)).sendKeys(value);
    }

    public void enterExcludeString(String value) {
        wait.until(ExpectedConditions.visibilityOf(ebayadvancedsearch_elements.excludeString)).sendKeys(value);
    }

    public void enterMinPrice(String value) {
        wait.until(ExpectedConditions.visibilityOf(ebayadvancedsearch_elements.minPrice)).sendKeys(value);
    }

    public void enterMaxPrice(String value) {
        wait.until(ExpectedConditions.visibilityOf(ebayadvancedsearch_elements.maxPrice)).sendKeys(value);
    }

    public void clickOnSearchBtn() {
        WebElement submitButton = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit'][data-track]"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        wait.until((ExpectedCondition<Boolean>) currentDriver ->
            currentDriver != null && currentDriver.getCurrentUrl().contains("_nkw=")
        );
    }
}
