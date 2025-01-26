package pages;

import locators.MovieDetailsPageLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.MovieBookingStepDef;

import java.time.Duration;

public class MovieDetailsPage {

    private RemoteWebDriver webDriver = MovieBookingStepDef.webDriver;
    private WebDriverWait wait;

    @FindBy(xpath = MovieDetailsPageLocators.BOOK_TICKET)
    private WebElement bookTicketButton;
    @FindBy(xpath = MovieDetailsPageLocators.LANGUAGE_AND_FORMAT)
    private WebElement languageAndFormatButton;
    @FindBy(xpath = MovieDetailsPageLocators.MOVIE_NAME)
    private WebElement movieName;


    public MovieDetailsPage() {
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public String getMovieName() {
        return movieName.getText();
    }

    public void selectBookTicket() {
        wait.until(ExpectedConditions.elementToBeClickable(bookTicketButton)); // Wait until the button is clickable
        bookTicketButton.click();
    }

    public void selectLanguageAndFormat() {
        wait.until(ExpectedConditions.elementToBeClickable(languageAndFormatButton)); // Wait until the button is clickable
        languageAndFormatButton.click();
    }
}
