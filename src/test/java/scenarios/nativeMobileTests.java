package scenarios;

import data.EpamAppData;
import data.TestDataProvider;
import org.testng.annotations.Test;
import setup.BaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.PropertiesLoader.getProperty;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "This test register a new account and then sign in",
            dataProvider = "epamAppData", dataProviderClass = TestDataProvider.class)
    public void registerAndLogInTest(EpamAppData data) throws IllegalAccessException, NoSuchFieldException {
        po.getElement("regBtn").click();
        po.getElement("regEmailEditText")
                .sendKeys(data.getEmail());
        po.getElement("regUsernameEditText")
                .sendKeys(data.getUsername());
        po.getElement("regPwdEditText")
                .sendKeys(data.getPwd());
        po.getElement("regConfirmPwdEditText")
                .sendKeys(data.getPwd());
        getDriver().hideKeyboard();
        po.getElement("regNewAccBtn").click();
        po.getElement("loginEditText")
                .sendKeys(data.getEmail());
        po.getElement("passwordEditText")
                .sendKeys(data.getPwd());
        po.getElement("signInBtn").click();
        assertThat(po.getElement("budgetActivityTitle")
                .getText(), equalTo(getProperty("testRegBudgetText")));
    }

}
