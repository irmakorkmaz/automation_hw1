import org.testng.annotations.Test;

public class TestCase1 extends BaseTest {
    @Test
    public void testCase1() {
        homePage.navigatePage();
        homePage.selectDestinations(origin, destination);
        homePage.selectDates(departureDay, returnDay);
        homePage.goToSearch();
        searchPage.chooseTimeFilter();
        searchPage.TimeRangeCheck();
    }
}
