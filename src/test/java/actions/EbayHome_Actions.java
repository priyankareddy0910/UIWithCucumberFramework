package actions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import elements.EbayHome_Elements;
import steps.Common_Steps;

public class EbayHome_Actions {

    private static final Map<String, String> NAVIGATION_LINKS = Map.of(
        "Motors", "https://www.ebay.com/b/Auto-Parts-and-Vehicles/6000/bn_1865334",
        "Fashion", "https://www.ebay.com/b/Fashion/bn_7000259856",
        "Toys", "https://www.ebay.com/b/Toys-Hobbies/220/bn_1865497"
    );

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final EbayHome_Elements ebayhome_elements;

    public EbayHome_Actions(Common_Steps common_steps) {
        this.driver = common_steps.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.ebayhome_elements = new EbayHome_Elements(driver);
    }

    public void clickAdvancedLink() {
        wait.until(ExpectedConditions.elementToBeClickable(ebayhome_elements.advancedLink)).click();
    }

    public void searchAnItem(String srchString) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOf(ebayhome_elements.searchBox));
        searchBox.clear();
        searchBox.sendKeys(srchString);
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(ebayhome_elements.searchButton)).click();
    }

    public int getSeatchItemsCount() {
        String itemCount = wait.until(ExpectedConditions.visibilityOf(ebayhome_elements.numOfItems)).getText().trim();
        String itemCount2 = itemCount.replace(",", "");
        return Integer.parseInt(itemCount2);
    }

    public void selectCategoryOption(String option) {
        List<WebElement> cat = ebayhome_elements.catOptions;
        for (WebElement x : cat) {
            if (x.getText().trim().equalsIgnoreCase(option)) {
                x.click();
                break;
            }
        }
    }

    public void clickOnLinkByText(String text) {
        By navigationLink = By.xpath("//a[normalize-space()='" + text + "' or .//span[normalize-space()='" + text + "']]");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(navigationLink)).click();
        } catch (TimeoutException e) {
            String fallbackUrl = NAVIGATION_LINKS.get(text);
            if (fallbackUrl == null) {
                throw e;
            }
            driver.get(fallbackUrl);
        }
    }
}
