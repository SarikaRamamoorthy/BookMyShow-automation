package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.HomePage;

import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import io.cucumber.java.en.Given;

public class MovieBookingStepDef {

    private ChromeDriver chromeDriver;
    private Properties properties;

    private HomePage homePage;
    private String location;

    @Before
    public void initialize() {
        chromeDriver = new ChromeDriver();

        properties = new Properties();
        try {
            FileInputStream propertyFile = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
            properties.load(propertyFile);
        } catch (FileNotFoundException e) {
            System.out.println("Exception found in reading property file");
        } catch (IOException e) {
            System.out.println("Exception found in loading properties into property file");
        }

        homePage = new HomePage(chromeDriver, properties);

        location = properties.getProperty("location");
    }

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        homePage.goToHomePage(properties.getProperty("base.uri"));
    }

    @When("the user searches the location")
    public void theUserSearchesTheLocation() {
        homePage.searchLocation(location);
    }

    @Then("the relevant location should be displayed and selected")
    public void theRelevantLocationShouldBeDisplayedAndSelected() {
        List<WebElement> suggestedCitiesWebElements = homePage.getCitiesSuggestion();

        boolean isLocationPresent = false;
        for (WebElement city : suggestedCitiesWebElements) {
            if (city.getText().equalsIgnoreCase(location)) {
                homePage.selectLocation(city);
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

    @When("the user searches the movie name")
    public void theUserSearchesTheMovieName() {
        homePage.searchMovie();
    }
}
