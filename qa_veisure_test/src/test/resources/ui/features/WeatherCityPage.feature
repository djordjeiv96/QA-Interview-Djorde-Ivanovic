Feature: WeatherCityPage
  Test main component on base page https://openweathermap.org/city/

  @UI
  Scenario: Validate for city weather parameter appeared in correct format
    Given User opens to main web page
    And User check weather for 'Sydney' city
    When User clicks on ' Sydney, AU' city
    Then City 'Sydney, AU' is appeared in correct format
    And Date and Time shown on the screen is correct based on 'Sydney, AU' city location
