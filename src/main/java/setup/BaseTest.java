package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static constants.PageObjectsConstants.*;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    protected static Map<String, IPageObject> pageObjects;
    protected static final String GOOGLE_URL = "https://www.google.com/";
    protected static String platform;

    @Override
    public AppiumDriver getDriver() { return appiumDriver; }

    @Parameters({"platformName","appType","deviceName","udid","browserName","app","appPackage","appActivity","bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName,
                      String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId
    ) throws Exception {
        System.out.println("Before: app type - " + appType);
        pageObjects = new HashMap<String, IPageObject>();
        setAppiumDriver(platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
        switch (appType) {
            case "web":
                setPageObject(appType, SEARCH_PO, appiumDriver);
                setPageObject(appType, RESULTS_PO, appiumDriver);
                break;
            case "native":
                setPageObject(appType, LOGIN_PO, appiumDriver);
                setPageObject(appType, REG_PO, appiumDriver);
                setPageObject(appType, BUDGET_PO, appiumDriver);
                break;
            default:
                break;
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String udid, String browserName,
                                 String app, String appPackage, String appActivity, String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName",platformName);
        capabilities.setCapability("deviceName",deviceName);
        capabilities.setCapability("udid",udid);

        if(app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck","true");

        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        capabilities.setCapability("bundleId", bundleId);

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        platform = platformName;
    }

    private void setPageObject(String appType, String pageType, AppiumDriver appiumDriver) throws Exception {
        pageObjects.put(pageType, new PageObject(appType, pageType, appiumDriver));
    }


}
