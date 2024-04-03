package service;

import model.WeatherResponse;

public interface IWeatherApi {

    WeatherResponse getWeather();

    void updateWeather(String weatherParameter, int weatherParameterValue);

}
