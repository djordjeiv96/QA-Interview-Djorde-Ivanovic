package stepDefinitions;

import common.Utils;
import context.WeatherContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import service.WeatherApiImpl;


public class WeatherStepDefinitions {


    private final WeatherApiImpl weatherApi;
    private final WeatherContext weatherContext;


    public WeatherStepDefinitions(WeatherApiImpl weatherApi, WeatherContext weatherContext) {
        this.weatherApi = weatherApi;
        this.weatherContext = weatherContext;
    }

    @Given("Weather {string} changed by value {int}")
    public void weatherConditionChangedByValueConditionId(String weatherParam, int valueParam) {
        weatherApi.updateWeather(weatherParam, valueParam);
    }

    @When("User checks weather")
    public void userChecksWeather() {
        weatherContext.setWeatherContext(weatherApi.getWeather());
    }

    @Then("The weather should be checked for Vienna city")
    public void theWeatherShouldBeCheckedForViennaCity() {
        Assert.assertEquals(weatherContext.getWeatherContext().getCity(), "vienna", "The actual city name '" + weatherContext.getWeatherContext().getCity() + "' should be equal to 'Vienna'");
    }

    @Then("The weather condition should be in {string} conditionState")
    public void theWeatherConditionShouldBeInConditionStateConditionState(String weatherCondition) {
        Assert.assertEquals(weatherContext.getWeatherContext().getCondition(), weatherCondition, "The actual condition '" + weatherContext.getWeatherContext().getCondition() + "' should be equal to '" + weatherCondition + "' expected");
    }

    @And("The icon field name correspond to {string} conditionState")
    public void theIconFieldNameCorrespondToConditionStateConditionState(String weatherCondition) {
        Assert.assertEquals(weatherContext.getWeatherContext().getIcon(), weatherCondition.concat(".png"), "The actual icon field name '" + weatherContext.getWeatherContext().getIcon() + "' should be equal to '" + weatherCondition.concat(".png") + "' expected");
    }

    @Then("The temperature in celsius should match {int} temperature in fahrenheit based on formula")
    public void theTemperatureInCelsiusShouldMatchTempValueInFahrenheitTemperatureInFahrenheitBasedOnFormula(int tempInFahrenheit) {

        //Check if temp in Fahrenheit match from the response
        Assert.assertEquals(weatherContext.getWeatherContext().getWeather().getTempInFahrenheit(), tempInFahrenheit, "The actual fahrenheit temp '" + weatherContext.getWeatherContext().getWeather().getTempInFahrenheit() + "' should be equal to '" + tempInFahrenheit + "' expected");
        //Check celsius temperature
        Assert.assertEquals(weatherContext.getWeatherContext().getWeather().getTempInCelsius(), Utils.convertFahrenheitToCelsius(tempInFahrenheit), "The actual celsius temp '" + weatherContext.getWeatherContext().getWeather().getTempInCelsius() + "' should be equal to '" + Utils.convertFahrenheitToCelsius(tempInFahrenheit) + "' expected");
    }

    @Then("The weather description should correspond to {int} temperature value")
    public void theWeatherDescriptionShouldCorrespondToCelsiusTemperatureValue(int tempInFahrenheit) {
        Assert.assertEquals(weatherContext.getWeatherContext().getDescription(), "The weather is ".concat(Utils.getDescriptionBasedOnCelsiusTemp(Utils.convertFahrenheitToCelsius(tempInFahrenheit))));
    }

}
