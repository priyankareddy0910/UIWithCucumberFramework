package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbayAdvancedSearch_Elements {

    WebDriver driver;

    @FindBy(css = "a.gh-logo, #gh-logo")
    public WebElement ebayLogo;

    @FindBy(xpath = "//input[@id='_nkw']")
    public WebElement searchString;

    @FindBy(xpath = "//input[@id='_ex_kw']")
    public WebElement excludeString;

    @FindBy(xpath = "//input[@name='_udlo']")
    public WebElement minPrice;

    @FindBy(xpath = "//input[@name='_udhi']")
    public WebElement maxPrice;

    public EbayAdvancedSearch_Elements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
