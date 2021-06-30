package scenarios;

import data.EpamAppData;
import data.TestDataProvider;
import org.testng.annotations.Test;
import setup.BaseTest;

import static constants.PageObjectsConstants.*;
import static constants.ReflectionWebElements.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.PropertiesLoader.getProperty;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "This test register a new account and then sign in",
            dataProvider = "epamAppData", dataProviderClass = TestDataProvider.class)
    public void registerAndLogInTest(EpamAppData data) throws IllegalAccessException, NoSuchFieldException {
        pageObjects.get(LOGIN_PO).getElement(REGISTER_BUTTON).click();
        pageObjects.get(REG_PO).getElement(REG_EMAIL_TEXT).sendKeys(data.getEmail());
        pageObjects.get(REG_PO).getElement(REG_NAME_TEXT).sendKeys(data.getUsername());
        pageObjects.get(REG_PO).getElement(REG_PWD_TEXT).sendKeys(data.getPwd());
        pageObjects.get(REG_PO).getElement(REG_CONFIRM_PWD_TEXT).sendKeys(data.getPwd());
        getDriver().hideKeyboard();
        pageObjects.get(REG_PO).getElement(REG_CREATE_NEW_ACC_BUTTON).click();
        pageObjects.get(LOGIN_PO).getElement(LOGIN_TEXT).sendKeys(data.getEmail());
        pageObjects.get(LOGIN_PO).getElement(PWD_TEXT).sendKeys(data.getPwd());
        pageObjects.get(LOGIN_PO).getElement(SIGN_IN_BUTTON).click();
        assertThat(pageObjects.get(BUDGET_PO).getElement(BUDGET_ACTIVITY_TITLE).getText(),
                equalTo(getProperty("testRegBudgetText")));
    }

}
