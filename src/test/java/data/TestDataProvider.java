package data;

import org.testng.annotations.DataProvider;

import static utils.PropertiesLoader.getProperty;

public class TestDataProvider {
    @DataProvider
    public Object[][] googleSearchData() {
        GoogleSearchData data = new GoogleSearchData();
        data.setSearchText(getProperty("testGoogleSearchText") + "\n");
        return new Object[][]{
                {data}
        };
    }

    @DataProvider
    public Object[][] epamAppData() {
        EpamAppData data = new EpamAppData();
        data.setEmail(getProperty("testRegEmail"));
        data.setUsername(getProperty("testRegUsername"));
        data.setPwd(getProperty("testRegPwd"));
        return new Object[][]{
                {data}
        };
    }
}
