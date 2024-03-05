package flightPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(css = "input[id='OriginInput']")
    WebElement originInput;


    @FindBy(css = "input[id='DestinationInput']")
    WebElement destinationInput;

    @FindBy(id = "DepartureDate")
    WebElement departureDate;
    @FindBy(css = "td.CalendarDay[aria-disabled='false']")
    List<WebElement> activeDays;


    @FindBy(id = "ReturnDate")
    WebElement returnDate;

    @FindBy(css = "button[data-testid='formSubmitButton']")
    WebElement searchCheapTicket;

    @FindBy(xpath = "//li[contains(@class,'react-autosuggest__suggestion react-autosuggest__suggestion--first')]")
    WebElement firstSuggestion;

    public HomePage(WebDriver driver) {

        super(driver);
    }

    public void navigatePage() {
        driver.get("https://www.enuygun.com/ucak-bileti/");
        waiting(2);

    }

    public void selectDestinations(String origin, String destination) {
        originInput.sendKeys(origin);
        waitUntilVisible(firstSuggestion);
        firstSuggestion.click();
        destinationInput.sendKeys(destination);
        waitUntilVisible(firstSuggestion);
        firstSuggestion.click();

    }

    public void selectDates(int departureDay, int returnDay) {
        departureDate.click();
        activeDays.get(departureDay).click();
        returnDate.click();
        activeDays.get(returnDay).click();

    }

    public void goToSearch() {
        searchCheapTicket.click();

    }


}
