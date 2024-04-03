package model;

public class WeatherResponse {
    private String city;
    private String condition;
    private String icon;
    private int conditionId;
    private String description;
    private WeatherData weather;

    // Constructor
    public WeatherResponse() {
    }

    // Getters and setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getConditionId() {
        return conditionId;
    }

    public void setConditionId(int conditionId) {
        this.conditionId = conditionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WeatherData getWeather() {
        return weather;
    }

    public void setWeather(WeatherData weather) {
        this.weather = weather;
    }

    // Inner class representing the nested 'weather' object
    public static class WeatherData {
        private int tempInFahrenheit;
        private int tempInCelsius;

        // Constructor
        public WeatherData() {
        }

        // Getters and setters
        public int getTempInFahrenheit() {
            return tempInFahrenheit;
        }

        public void setTempInFahrenheit(int tempInFahrenheit) {
            this.tempInFahrenheit = tempInFahrenheit;
        }

        public int getTempInCelsius() {
            return tempInCelsius;
        }

        public void setTempInCelsius(int tempInCelsius) {
            this.tempInCelsius = tempInCelsius;
        }
    }
}
