package pages;

import locators.SeatPopUpLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.MovieBookingStepDef;

import java.time.Duration;

public class SeatPopUp {
    private RemoteWebDriver webDriver = MovieBookingStepDef.webDriver;

    @FindBy(xpath = SeatPopUpLocators.SEAT_COUNT)
    WebElement seatCount;
    @FindBy(id = SeatPopUpLocators.SELECT_SEAT)
    WebElement selectSeat;

    public SeatPopUp() {
        PageFactory.initElements(webDriver, this);
    }

    public void selectSeatCount() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(seatCount));
        seatCount.click();
    }

    public void clickSelectSeats() {
        selectSeat.click();
    }
}
