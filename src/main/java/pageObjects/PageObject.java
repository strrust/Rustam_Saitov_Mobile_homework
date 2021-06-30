package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import pageObjects.epamApp.BudgetPageObject;
import pageObjects.epamApp.LoginPageObject;
import pageObjects.epamApp.RegPageObject;
import pageObjects.google.GoogleResultsPageObject;
import pageObjects.google.GoogleSearchPageObject;
import setup.IPageObject;

import java.lang.reflect.Field;
import java.util.List;

import static constants.PageObjectsConstants.*;

public class PageObject implements IPageObject {

    Object somePageObject; // it should be set of web page or EPAM Test App WebElements

    public PageObject(String appType, String pageType, AppiumDriver appiumDriver) throws Exception {
        switch (appType) {
            case "web":
                switch (pageType) {
                    case SEARCH_PO:
                        somePageObject = new GoogleSearchPageObject(appiumDriver);
                        break;
                    case RESULTS_PO:
                        somePageObject = new GoogleResultsPageObject(appiumDriver);
                        break;
                    default:
                        break;
                }
                break;
            case "native":
                switch (pageType) {
                    case LOGIN_PO:
                        somePageObject = new LoginPageObject(appiumDriver);
                        break;
                    case REG_PO:
                        somePageObject = new RegPageObject(appiumDriver);
                        break;
                    case BUDGET_PO:
                        somePageObject = new BudgetPageObject(appiumDriver);
                        break;
                    default:
                        break;
                }
                break;
            default:
                throw new Exception("Can't create a " + pageType + "page object for " + appType);
        }

    }

    @Override
    public WebElement getElement(String elementName) throws NoSuchFieldException, IllegalAccessException {
        // use reflection technique
        Field field = somePageObject.getClass().getDeclaredField(elementName);
        field.setAccessible(true);
        return (WebElement) field.get(somePageObject);
    }

    @Override
    public List<WebElement> getElements(String elementsListName) throws NoSuchFieldException, IllegalAccessException {
        Field field = somePageObject.getClass().getDeclaredField(elementsListName);
        field.setAccessible(true);
        return (List<WebElement>) field.get(somePageObject);
    }
}
