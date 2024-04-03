Feature: Weather
  Check weather in Vienna

  Scenario: Verify City Name
    When User checks weather
    Then The weather should be checked for Vienna city

  Scenario Outline: Verify weather condition based on different conditions
    Given Weather 'condition' changed by value <conditionId>
    When User checks weather
    Then The weather condition should be in '<conditionState>' conditionState
    And The icon field name correspond to '<conditionState>' conditionState

    Examples:
      | conditionId | conditionState |
      | 1           | clear          |
      | 2           | windy          |
      | 3           | mist           |
      | 4           | drizzle        |
      | 5           | dust           |

  Scenario Outline: Verify weather temperature calculation - Fahrenheit to Celsius
    Given Weather 'temp' changed by value <tempValueInFahrenheit>
    When User checks weather
    Then The temperature in celsius should match <tempValueInFahrenheit> temperature in fahrenheit based on formula

    Examples:
      | tempValueInFahrenheit |
      | 0                     |
      | -5                    |
      | 18                    |
      | 55                    |
      | 77                    |
      | 78                    |
      | 50                    |
      | 68                    |
      | 74                    |


  Scenario Outline: Verify weather description output for different weather temperature
    Given Weather 'temp' changed by value <tempValueInFahrenheit>
    When User checks weather
    Then The weather description should correspond to <tempValueInFahrenheit> temperature value

    Examples:
      | tempValueInFahrenheit |
      | 0                     |
      | 32                    |
      | 40                    |
      | 50                    |
      | 52                    |
      | 68                    |
      | 74                    |
      | 77                    |
      | 80                    |



