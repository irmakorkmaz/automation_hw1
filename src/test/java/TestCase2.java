import org.testng.annotations.Test;

public class TestCase2 extends BaseTest {
    @Test
    public void testCase2() {
        homePage.navigatePage();
        homePage.selectDestinations(origin, destination);
        homePage.selectDates(departureDay, returnDay);
        homePage.goToSearch();
        searchPage.chooseTimeFilter();
        searchPage.chooseAirlineFilter();
        searchPage.priceController();

    }
}

