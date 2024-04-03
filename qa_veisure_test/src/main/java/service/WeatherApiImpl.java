package service;

import com.google.gson.Gson;
import model.WeatherResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

public class WeatherApiImpl implements IWeatherApi {

    private static final Logger logger = LogManager.getLogger(WeatherApiImpl.class);

    private static final String BASE_URL = "https://backend-interview.tools.gcp.viesure.io/";

    private final Gson gson = new Gson();

    @Override
    public WeatherResponse getWeather() {
        try {
            // Create HttpClient instance
            HttpClient httpClient = HttpClients.createDefault();

            // Create HttpGet request
            HttpGet httpGet = new HttpGet(BASE_URL + "weather");

            // Log request details
            System.setProperty("log4j.configurationFile", "C:\\projects\\qa_veisure_test\\src\\main\\resources\\log4j2.xml");
            logger.info("Executing request: {}", httpGet.getRequestLine());

            // Execute request and get response
            HttpResponse httpResponse = httpClient.execute(httpGet);

            // Log response details
            logResponse(httpResponse);

            // Get response entity
            HttpEntity entity = httpResponse.getEntity();

            // Read response content as string
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                // Parse JSON string to WeatherResponse object
                return gson.fromJson(json, WeatherResponse.class);
            }
        } catch (Exception e) {

            logger.error("Error executing request: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateWeather(String weatherParameter, int weatherParameterValue) {
        StringBuilder response = new StringBuilder();
        try {
            // Create HttpClient instance
            HttpClient httpClient = HttpClients.createDefault();

            // Create HttpPut request
            HttpPut httpPut = new HttpPut(BASE_URL + "weather/" + weatherParameter + "");

            // Set content type
            httpPut.setHeader("Content-Type", "application/json");

            if (weatherParameter.equalsIgnoreCase("temp")) {
                weatherParameter = "tempInFahrenheit";
            }
            Map<String, Integer> weatherUpdateParameter = new HashMap<>();
            weatherUpdateParameter.put(weatherParameter, weatherParameterValue);

            // Convert request object to JSON string
            String jsonRequest = gson.toJson(weatherUpdateParameter);

            // Set request body
            httpPut.setEntity(new StringEntity(jsonRequest));

            //System.setProperty("log4j.configurationFile", "C:\\projects\\qa_veisure_test\\src\\main\\resources\\log4j2.xml");
            logger.info("Executing request: {}", httpPut.getRequestLine());

            // Execute request and get response
            HttpResponse httpResponse = httpClient.execute(httpPut);

            // Log response details
            logResponse(httpResponse);

            // Get response entity
            HttpEntity entity = httpResponse.getEntity();

            // Read response content as string
            if (entity != null) {
                response.append(EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.append("Error: ").append(e.getMessage());
        }
    }

    private void logResponse(HttpResponse response) {
        logger.info("HTTP Status: {}", response.getStatusLine().getStatusCode());
    }
}

