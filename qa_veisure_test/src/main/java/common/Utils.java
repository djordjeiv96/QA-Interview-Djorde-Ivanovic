package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {


    //Convert from fahrenheit to celsius temperature value
    public static int convertFahrenheitToCelsius(int fahrenheit) {
        double celsiusDouble = (fahrenheit - 32) * 5.0 / 9.0;
        return (int) Math.floor(celsiusDouble);
    }

    public static String getDescriptionBasedOnCelsiusTemp(int celsiusTemp) {

        if (celsiusTemp <= 0) {
            return "freezing";
        } else if (celsiusTemp > 0 && celsiusTemp < 10) {
            return "cold";
        } else if (celsiusTemp >= 10 && celsiusTemp < 20) {
            return "mild";
        } else if (celsiusTemp >= 20 && celsiusTemp < 25) {
            return "warm";
        } else {
            return "hot";
        }
    }

    public static Date parseToDate(String dateString) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, hh:mmaa");

        // Parse the input date string to obtain a Date object
        return dateFormat.parse(dateString);
    }

    //Helpers method use to get Date and Time for the city location
    public static String getCurrentDateAndTimeForTheCity(String city) {
        // Extract city and country code from the input parameter
        String[] parts = city.split(", ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("City input format should be 'City, CountryCode'");
        }
        String cityName = parts[0];
        String countryCode = parts[1];

        // Construct the time zone ID using the city and country code
        ZoneId zoneId = ZoneId.of("" + getCountry(countryCode.toUpperCase()) + "/" + cityName);

        // Get the current date and time for the specified time zone
        LocalDateTime currentDateTime = LocalDateTime.now(zoneId);

        // Define a date-time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, hh:mma", Locale.ENGLISH);

        // Format the current date and time
        String formattedDateTime = currentDateTime.format(formatter);

        // Convert AM/PM to lowercase
        return formattedDateTime.replace("AM", "am").replace("PM", "pm");

    }

    //In case there is necessity for more countries the better solution would be to store it in config file for instance
    private static String getCountry(String countryCode) {
        String countryName;
        switch (countryCode) {
            case "AU":
                countryName = "Australia";
                break;
            case "US":
                countryName = "America";
                break;
            case "CA":
                countryName = "Canada";
                break;
            case "IT":
                countryName = "Italy";
                break;
            case "DE":
                countryName = "Germany";
                break;
            // Add more cases as needed
            default:
                countryName = "Unknown";
                break;
        }
        return countryName;
    }

}
