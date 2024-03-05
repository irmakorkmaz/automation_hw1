import flightPages.HomePage;
import flightPages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class BaseTest {

    WebDriver driver;
    HomePage homePage;

    SearchPage searchPage;

    String origin = "İstanbul";
    String destination = "Ankara";

    int departureDay = 5;
    int returnDay = 3;


    @BeforeTest
    public void before() {

        //sayfa full screen açılması için capability ekliyoruz

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);


    }

    public WebDriver getDriver() {
        return this.driver;
    }

    @AfterTest
    public void after() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();


    }

}
