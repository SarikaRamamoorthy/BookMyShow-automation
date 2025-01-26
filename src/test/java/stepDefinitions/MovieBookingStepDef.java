package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import pages.*;

import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import io.cucumber.java.en.Given;

public class MovieBookingStepDef {

    public static final RemoteWebDriver webDriver = new ChromeDriver();
    public static final Properties properties = new Properties();

    private HomePage homePage;
    private LocationPopUp locationPopUp;
    private MovieDetailsPage movieDetailsPage;
    private TheatreDetailsPage theatreDetailsPage;
    private SeatPopUp seatPopUp;

    private String location;
    private String movieName;
    private String theatreName;

    @Before
    public void initialize() {
        webDriver.manage().window().maximize();

        try {
            FileInputStream propertyFile = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
            properties.load(propertyFile);
        } catch (FileNotFoundException e) {
            System.out.println("Exception found in reading property file");
        } catch (IOException e) {
            System.out.println("Exception found in loading properties into property file");
        }

        homePage = new HomePage();
        locationPopUp = new LocationPopUp();
        movieDetailsPage = new MovieDetailsPage();
        seatPopUp = new SeatPopUp();

        location = properties.getProperty("location");
    }

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        homePage.goToHomePage(properties.getProperty("base.uri"));
    }

    @When("the user searches for the location")
    public void theUserSearchesForTheLocation() {
        locationPopUp.searchLocation(location);
    }

    @Then("the relevant location should be displayed and selected")
    public void theRelevantLocationShouldBeDisplayedAndSelected() {
        List<WebElement> suggestedCitiesWebElements = locationPopUp.getCitiesSuggestion();

        boolean isLocationPresent = false;
        for (WebElement city : suggestedCitiesWebElements) {
            if (city.getText().equalsIgnoreCase(location)) {
                locationPopUp.selectLocation(city);
                isLocationPresent = true;
                break;
            }
        }
        Assert.assertTrue(isLocationPresent, "Location not found in the suggestions");
    }

    @And("verify the selected location")
    public void verifyTheSelectedLocation() {
        String actualLocation = homePage.getActualLocation().getText();
        Assert.assertEquals(actualLocation, location, "Location mismatch");
    }

    @When("user selects the movie")
    public void userSelectsTheMovie() {
        movieName = homePage.selectMovie();
    }

    @Then("verify the selected movie")
    public void verifyTheSelectedMovie() {
        String actualMovieName = movieDetailsPage.getMovieName();
        Assert.assertEquals(actualMovieName, movieName, "Movie name doesn't match in movie details page");
    }

    @And("user selects book ticket")
    public void userSelectsBookTicket() {
        movieDetailsPage.selectBookTicket();
    }

    @And("user selects language and format")
    public void userSelectsLanguageAndFormat() {
        movieDetailsPage.selectLanguageAndFormat();
    }

    @Then("verify the selected movie in theatre details page")
    public void verifyTheSelectedMovieInTheatreDetailsPage() {
        String actualMovieName = theatreDetailsPage.getMovieName();
        Assert.assertEquals(actualMovieName, movieName, "Movie name doesn't match in theatre details page");
    }

    @When("the user selects a theatre and show timings, prioritizing fast filling or an available slot")
    public void theUserSelectsATheatreAndShowTimingsPrioritizingFastFillingOrAnAvailableSlot() {
        List<WebElement> fastFillingSlots = theatreDetailsPage.getFastFillingSlots();
        if (!fastFillingSlots.isEmpty()) {
            theatreName = theatreDetailsPage.getTheatreName(fastFillingSlots.getFirst());
            fastFillingSlots.getFirst().click();
        } else {
            List<WebElement> availableSlots = theatreDetailsPage.getAvailableSlots();
            theatreName = theatreDetailsPage.getTheatreName(availableSlots.getFirst());
            availableSlots.getFirst().click();
        }
    }

    @And("the user selects the number of seats")
    public void theUserSelectsTheNumberOfSeats() {
        seatPopUp.selectSeatCount();
    }

    @And("the clicks select seat button")
    public void theClicksSelectSeatButton() {
        seatPopUp.clickSelectSeats();
    }

    @After
    public void tearDown() {
//        webDriver.quit();
    }

}
