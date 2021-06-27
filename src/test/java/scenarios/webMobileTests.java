package scenarios;

import data.GoogleSearchData;
import data.TestDataProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "This test search EPAM in google",
            dataProvider = "googleSearchData", dataProviderClass = TestDataProvider.class)
    public void googleSearchTest(GoogleSearchData data) throws NoSuchFieldException, IllegalAccessException {
        getDriver().get(GOOGLE_URL);
        po.getElement("searchBar")
                .sendKeys(data.getSearchText());

        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        assertThat(po.getElements("searchResultsContent").size(),
                not(equalTo(0)));
    }

}
