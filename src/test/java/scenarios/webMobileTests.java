package scenarios;

import data.GoogleSearchData;
import data.TestDataProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;

import static constants.PageObjectsConstants.*;
import static constants.ReflectionWebElements.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "This test search EPAM in google",
            dataProvider = "googleSearchData", dataProviderClass = TestDataProvider.class)
    public void googleSearchTest(GoogleSearchData data) throws NoSuchFieldException, IllegalAccessException {
        getDriver().get(GOOGLE_URL);
        pageObjects.get(SEARCH_PO).getElement(SEARCH_BAR).sendKeys(data.getSearchText());
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
        assertThat(pageObjects.get(RESULTS_PO).getElements(CONTENT_LIST).size(), not(equalTo(0)));
    }

}
