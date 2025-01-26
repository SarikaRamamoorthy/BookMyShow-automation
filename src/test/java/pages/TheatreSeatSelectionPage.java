package pages;

import locators.TheatreSeatSelectionPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import stepDefinitions.MovieBookingStepDef;

import java.util.List;

public class TheatreSeatSelectionPage {
    private RemoteWebDriver webDriver = MovieBookingStepDef.webDriver;

    @FindBy(id = TheatreSeatSelectionPageLocators.MOVIE_NAME)
    WebElement movieName;
    @FindBy(id = TheatreSeatSelectionPageLocators.THEATRE_NAME)
    WebElement theatreName;
    @FindBy(id = TheatreSeatSelectionPageLocators.SEAT_COUNT)
    WebElement seatCount;
    @FindBy(xpath = TheatreSeatSelectionPageLocators.ROW)
    List<WebElement> rowsWithSeats;

    By seats = By.xpath("/div/a");

    public TheatreSeatSelectionPage() {
        PageFactory.initElements(webDriver, this);
    }

    public String getMovieName() {
        return movieName.getText();
    }

    public String getTheatreName() {
        return theatreName.getText();
    }

    public int getSeatCount() {
        return Integer.parseInt(seatCount.getText());
    }

    public List<WebElement> getAllRows() {
        return rowsWithSeats;
    }

    public void displaySeatsWithAdjacentAvailability(WebElement rowOfSeats, int row) {
        List<WebElement> seatElements = rowOfSeats.findElements(seats);

        for (int seatIndex = 0; seatIndex < seatElements.size(); seatIndex++) {
            WebElement currentSeat = seatElements.get(seatIndex);

            if (currentSeat.getAttribute("class").contains("_available")) {

                boolean isPrevSeatAvailable = seatIndex > 0 && seatElements.get(seatIndex - 1).getAttribute("class").contains("_available");
                boolean isNextSeatAvailable = seatIndex < seatElements.size() - 1 && seatElements.get(seatIndex + 1).getAttribute("class").contains("_available");

                if (isPrevSeatAvailable && isNextSeatAvailable) {
                    String seatNumber = currentSeat.getText();
                    System.out.println("Row "+ row + ": " + "Seat " + seatNumber + " has adjacent available seats.");
                }
            }
        }
    }
}
