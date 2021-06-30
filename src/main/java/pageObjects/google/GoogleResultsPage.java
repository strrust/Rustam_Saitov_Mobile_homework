package pageObjects.google;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleResultsPage {
    @FindBy(css = "#rso > div")
    List<WebElement> searchResultsContent;

    public GoogleResultsPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }
}
