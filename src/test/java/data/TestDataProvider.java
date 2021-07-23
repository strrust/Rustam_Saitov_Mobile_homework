package data;

import org.testng.annotations.DataProvider;

import java.util.Random;
import java.util.stream.Collectors;

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
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "0123456789";
        String email = new Random().ints(5, 0, chars.length())
                .mapToObj(i -> "" + chars.charAt(i))
                .collect(Collectors.joining());
        data.setEmail(email + "@mail.ru");
        data.setUsername(getProperty("testRegUsername"));
        data.setPwd(getProperty("testRegPwd"));
        return new Object[][]{
                {data}
        };
    }
}
