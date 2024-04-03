/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package selenium;

import common.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class WeatherCityPage {

    private static final String PAGE_URL = "https://openweathermap.org/city";

    private WebDriver driver;

    //Locators
    private final By inputSearchElements = By.cssSelector("input[type='text'][name='q'][placeholder='Weather in your city']");

    private final By dateAndTimeElement = By.xpath("//span[@class = 'orange-text']");
    private final String h2TextElement = "//h2[text()='XXX']";

    // Constructor
    public WeatherCityPage(WebDriver driver) {

        this.driver = driver;
    }

    public void checkCityTitle(String city) {

        driver.findElement(By.xpath(h2TextElement.replace("XXX", city))).getText().equals(city);
    }

    public void checkDateAndTime(String city) {

        Assert.assertEquals(driver.findElement(dateAndTimeElement).getText(), Utils.getCurrentDateAndTimeForTheCity(city));
    }

}