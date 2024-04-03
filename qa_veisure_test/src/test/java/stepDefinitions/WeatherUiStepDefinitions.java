/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.WeatherCityPage;
import selenium.WeatherPage;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class WeatherUiStepDefinitions {

    private WebDriver driver;
    private WeatherPage weatherPage;
    private WeatherCityPage weatherCityPage;

    @Before("@UI")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\projects\\qa_veisure_test\\src\\main\\java\\selenium\\driver\\chromedriver.exe");

        // Initialize ChromeDriver instance
        driver = new ChromeDriver();
        //Add implicit waiter
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After("@UI")
    public void tearDown(Scenario scenario) {
        // Close the WebDriver instance
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User opens to main web page")
    public void userOpensToMainWebPage() {
        weatherPage = new WeatherPage(driver);
        weatherPage.navigateToWeatherPage();
    }

    @When("User compared placeholder value")
    public void userComparedPlaceholderValue() {
        weatherPage.verifyPlaceHolderForSearchMenu();
    }

    @Given("User check weather for {string} city")
    public void userCheckWeatherForCityCity(String city) {
        weatherPage.userEnterCity(city);
    }

    @Then("City {string} is appeared in correct format")
    public void citySydneyAUIsAppearedInCorrectFormat(String city) {
        weatherCityPage = new WeatherCityPage(driver);
        weatherCityPage.checkCityTitle(city);
    }

    @And("Date and Time shown on the screen is correct based on {string} city location")
    public void dateAndTimeShownOnTheScreenIsCorrectBasedOnCityLocation(String city) throws ParseException {
        weatherCityPage.checkDateAndTime(city);
    }

    @When("User clicks on {string} city")
    public void userClicksOnSydneyAUCity(String city) {
        weatherPage.userClickOnTheCity(city);

    }
}