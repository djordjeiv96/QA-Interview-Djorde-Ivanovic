
package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class WeatherPage {

    private static final String PAGE_URL = "https://openweathermap.org/";

    private WebDriver driver;

    //Locators
    private final By inputSearchElements = By.cssSelector("input[type='text'][name='q'][placeholder='Weather in your city']");

    private final String link = "//a[text()='XXX']";

    // Constructor
    public WeatherPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to navigate to the Weather page
    public void navigateToWeatherPage() {
        driver.get(PAGE_URL);
    }

    public void verifyPlaceHolderForSearchMenu() {

        Assert.assertEquals(driver.findElements(inputSearchElements).get(0).getAttribute("placeholder"), "Weather in your city");
    }

    public void userEnterCity(String city) {
        driver.findElements(inputSearchElements).get(0).sendKeys(city);
        driver.findElements(inputSearchElements).get(0).sendKeys(Keys.ENTER);
    }

    public void userClickOnTheCity(String city) {
        driver.findElement(By.xpath(link.replaceAll("XXX", city))).click();
    }

}
