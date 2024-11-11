package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.google.common.io.Files;

import pages.HomePage;
import utils.WebDriverListeners;
import utils.WindowManager;

// Version 130.0.6723.92 (Official Build) (64-bit)
public class BaseTests {

    private WebDriver driver;
    private WebDriverListener driverListener; // EventFiringWebDriver is deprecated
    protected HomePage homePage;

    @BeforeClass
    public void setup() {
        WebDriver webDriver;

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        // driver = new ChromeDriver(getChromeOptions());
        webDriver = new ChromeDriver(getChromeOptions());
        driverListener = new WebDriverListeners();
        driver = new EventFiringDecorator<>(driverListener).decorate(webDriver);
        // driver.get("https://the-internet.herokuapp.com/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        goHome();
        setCookie();
        homePage = new HomePage(driver);
        // driver.manage().window().setSize(new Dimension(375, 812));
        // WebElement inputsLink =  driver.findElement(By.linkText("Inputs"));
        // inputsLink.click();
        // List<WebElement> links =  driver.findElements(By.tagName("a"));
        // System.out.println(links.size());
        // System.out.println(driver.getTitle());
    }

    @BeforeMethod
    public void goHome() {
        driver.get("https://the-internet.herokuapp.com/");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        var camera = (TakesScreenshot)driver;
        if(ITestResult.FAILURE == result.getStatus()) {
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("C:\temp\screenshots\\" + result.getName() + ".png"));
            } catch(IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("Screenshot taken: " + screenshot.getAbsolutePath());
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.setEnableDownloads(true);
        return options;
    }

    private void setCookie() {
        Cookie cookie = new Cookie.Builder("tau", "123")
        .domain("the-internet.herokuapp.com")
        .build();
        driver.manage().addCookie(cookie);
    }

    // public static void main(String args[]) {
    //     BaseTests test = new BaseTests();
    //     test.setup();
    // }
}
