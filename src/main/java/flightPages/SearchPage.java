package flightPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class SearchPage extends BasePage {


    @FindBy(css = "div.ctx-filter-departure-return-time.card-header")
    WebElement retDepTimeFilter;

    @FindBy(xpath = "//div[@data-testid='departureDepartureTimeSlider']//div[@aria-valuenow='0']")
    WebElement sourceRSlider;

    @FindBy(xpath = "//div[@data-testid='departureDepartureTimeSlider']//div[@aria-valuenow='600']")
    WebElement targetRSlider;

    @FindBy(xpath = "//div[@data-testid='departureDepartureTimeSlider']//div[@aria-valuenow='1440']")
    WebElement sourceLSlider;

    @FindBy(xpath = "//div[@data-testid='departureDepartureTimeSlider']//div[@aria-valuenow='1080']")
    WebElement targetLSlider;

    @FindBy(xpath = "//div[@data-testid='departureTime']")
    List<WebElement> timeElements;


    @FindBy(xpath = "//div[@class='ctx-filter-airline card-header']")
    WebElement airlineFilter;

    @FindBy(xpath = "//span[text()='Türk Hava Yolları']")
    WebElement thyFilter;

    @FindBy(xpath = "//div[@data-testid='flightInfoPrice']")
    List<WebElement> priceElements;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void chooseTimeFilter() {
        waitUntilVisible(retDepTimeFilter);
        retDepTimeFilter.click();
        actions.dragAndDropBy(sourceRSlider, 100, 0).perform();
        waiting(1);
        actions.dragAndDropBy(sourceLSlider, -60, 0).perform();
        waiting(1);
    }

    public void TimeRangeCheck() {
        // Saat aralığını kontrol etmek için başlangıç ve bitiş saatleri
        String startTime = "10:00";
        String endTime = "18:00";

        for (WebElement timeElement : timeElements) {
            String timeText = timeElement.getText(); // Örneğin, "15:30"
            boolean isInTimeRange = isTimeWithinRange(timeText, startTime, endTime);
            Assert.assertTrue(isInTimeRange);
            System.out.println("time: " + timeText + " | It is in the time range: " + isInTimeRange);
        }
    }

    private boolean isTimeWithinRange(String currentTime, String startTime, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime current = LocalTime.parse(currentTime, formatter);
        LocalTime start = LocalTime.parse(startTime, formatter);
        LocalTime end = LocalTime.parse(endTime, formatter);

        return !current.isBefore(start) && !current.isAfter(end);
    }

    public void chooseAirlineFilter() {
        wait.until(ExpectedConditions.visibilityOf(airlineFilter));
        actions.scrollToElement(airlineFilter).perform();
        airlineFilter.click();
        waiting(1);
        thyFilter.click();
        waiting(1);

    }

    public void priceController() {
        for (int i = 0; i < priceElements.size() - 1; i++) {
            double currentPrice = Double.parseDouble(priceElements.get(i).getAttribute("data-price"));
            double nextPrice = Double.parseDouble(priceElements.get(i + 1).getAttribute("data-price"));
            System.out.println("compared prices are: " + currentPrice + "and" + nextPrice);
            Assert.assertTrue(currentPrice <= nextPrice);
            System.out.println("comparison succeeded");
        }

    }
}


