package pages;

import locators.HomePageLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.MovieBookingStepDef;

import java.time.Duration;

public class HomePage {

    private RemoteWebDriver webDriver = MovieBookingStepDef.webDriver;

    @FindBy(xpath = HomePageLocators.LOCATION)
    WebElement location;
    @FindBy(xpath = HomePageLocators.MOVIE)
    WebElement movie;

    public HomePage() {
        PageFactory.initElements(webDriver, this);
    }

    public void goToHomePage(String baseURI) {
        webDriver.get(baseURI);
    }

    public WebElement getActualLocation() {
        return location;
    }

    public String selectMovie() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));;
        wait.until(ExpectedConditions.elementToBeClickable(movie));
        movie.click();
        return movie.getText();
    }
}
