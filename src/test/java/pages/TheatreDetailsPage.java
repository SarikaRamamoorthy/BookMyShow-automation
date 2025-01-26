package pages;

import locators.TheatreDetailsPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import stepDefinitions.MovieBookingStepDef;

import java.util.List;

public class TheatreDetailsPage {

    private RemoteWebDriver webDriver = MovieBookingStepDef.webDriver;

    @FindBy(xpath = TheatreDetailsPageLocators.MOVIE_NAME)
    WebElement movieName;
    @FindBy(xpath = TheatreDetailsPageLocators.FAST_FILLING_SLOT)
    List<WebElement> fastFillingSlots;
    @FindBy(xpath = TheatreDetailsPageLocators.AVAILABLE_SLOT)
    List<WebElement> availableSlots;

    By venue = By.xpath("ancestor::li//div[@class='__name ']//a[@class='__venue-name']");

    public TheatreDetailsPage() {
        PageFactory.initElements(webDriver, this);
    }

    public String getMovieName() {
        return movieName.getText().split("-")[0].trim();
    }

    public List<WebElement> getFastFillingSlots() {
        return fastFillingSlots;
    }

    public List<WebElement> getAvailableSlots() {
        return availableSlots;
    }

    public String getTheatreName(WebElement movieElement) {
        WebElement theatreNameElement = movieElement.findElement(venue);
        return theatreNameElement.getText();
    }
}
