package pages;

import locators.LocationPopUpLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.MovieBookingStepDef;

import java.time.Duration;
import java.util.List;

public class LocationPopUp {

    private RemoteWebDriver webDriver = MovieBookingStepDef.webDriver;

    @FindBy(tagName = LocationPopUpLocators.CITY_SEARCHBAR)
    WebElement citySearchBar;
    @FindBy(xpath = LocationPopUpLocators.SUGGESTED_CITIES)
    List<WebElement> citySuggestions;

    public LocationPopUp() {
        PageFactory.initElements(webDriver, this);
    }

    public void searchLocation(String location) {
        citySearchBar.sendKeys(location);
    }

    public List<WebElement> getCitiesSuggestion() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(citySuggestions));
        return citySuggestions;
    }

    public void selectLocation(WebElement city) {
        city.click();
    }
}
