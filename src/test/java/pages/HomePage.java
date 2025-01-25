package pages;

import locators.HomePageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class HomePage {
    private ChromeDriver chromeDriver;
    private WebElement citySearchBar;
    private WebDriverWait wait;
    //TODO: if wait not used anywhere else move inside citySuggestion


    public HomePage(ChromeDriver chromeDriver, Properties properties) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    public void goToHomePage(String baseURI) {
        chromeDriver.get(baseURI);
    }

    public void searchLocation(String location) {
        citySearchBar = chromeDriver.findElement(By.tagName(HomePageLocators.CITY_SEARCHBAR));
        citySearchBar.sendKeys(location);
    }

    public List<WebElement> getCitiesSuggestion() {
        By citySuggestion = By.xpath(HomePageLocators.SUGGESTED_CITIES);
        wait.until(ExpectedConditions.visibilityOfElementLocated(citySuggestion));
        return chromeDriver.findElements(citySuggestion);
    }

    public void selectLocation(WebElement city) {
        city.click();
    }

    public WebElement getActualLocation() {
        By location = By.xpath(HomePageLocators.LOCATION);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(location));
        return chromeDriver.findElement(location);
    }

    public void searchMovie() {
        WebElement movieSearchBar = chromeDriver.findElement(By.xpath(HomePageLocators.MOVIE_SEARCHBAR));
        movieSearchBar.click();
        WebElement moviesSearchBar = chromeDriver.findElement(By.xpath(HomePageLocators.MOVIES_SEARCHBAR));
        moviesSearchBar.sendKeys("Kudumbasthan");
    }
}
