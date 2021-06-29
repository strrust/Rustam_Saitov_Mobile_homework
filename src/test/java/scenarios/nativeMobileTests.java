package scenarios;

import data.EpamAppData;
import data.TestDataProvider;
import org.testng.annotations.Test;
import setup.BaseTest;

import static constants.ReflectionWebElements.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.PropertiesLoader.getProperty;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "This test register a new account and then sign in",
            dataProvider = "epamAppData", dataProviderClass = TestDataProvider.class)
    public void registerAndLogInTest(EpamAppData data) throws IllegalAccessException, NoSuchFieldException {
        po.getElement(REGISTER_BUTTON).click();
        po.getElement(REG_EMAIL_TEXT).sendKeys(data.getEmail());
        po.getElement(REG_NAME_TEXT).sendKeys(data.getUsername());
        po.getElement(REG_PWD_TEXT).sendKeys(data.getPwd());
        po.getElement(REG_CONFIRM_PWD_TEXT).sendKeys(data.getPwd());
        getDriver().hideKeyboard();
        po.getElement(REG_CREATE_NEW_ACC_BUTTON).click();
        po.getElement(LOGIN_TEXT).sendKeys(data.getEmail());
        po.getElement(PWD_TEXT).sendKeys(data.getPwd());
        po.getElement(SIGN_IN_BUTTON).click();
        assertThat(po.getElement(BUDGET_ACTIVITY_TITLE).getText(), equalTo(getProperty("testRegBudgetText")));
    }

}
