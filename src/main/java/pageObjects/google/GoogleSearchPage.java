package pageObjects.google;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {
    @FindBy(css = "input[type='Search']")
    WebElement searchBar;

    public GoogleSearchPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }
}
