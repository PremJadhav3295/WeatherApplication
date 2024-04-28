package com.weatherApi.Controller;

import java.util.concurrent.ExecutionException;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class WeatherController {

    private final AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();

    @GetMapping("/forecast-summary/{city}")
    public String getForecastSummaryByCity(@PathVariable String city) throws ExecutionException, InterruptedException {
        return asyncHttpClient.prepareGet("https://forecast9.p.rapidapi.com/rapidapi/forecast/" + city + "/summary/")
                .addHeader("X-RapidAPI-Key", "6104ac8581mshb6ccf6138cfb2f7p1754abjsn3968b352b789")
                .addHeader("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                .execute()
                .toCompletableFuture()
                .thenApply(response -> {
                    try {
                        return response.getResponseBody();
                    } catch (Exception e) {
                        return "Error occurred: " + e.getMessage();
                    }
                })
                .join();
    }

    @GetMapping("/hourly-forecast/{city}")
    public String getHourlyForecastByCity(@PathVariable String city) throws ExecutionException, InterruptedException {
        return asyncHttpClient.prepareGet("https://forecast9.p.rapidapi.com/rapidapi/forecast/" + city + "/hourly/")
                .addHeader("X-RapidAPI-Key", "6104ac8581mshb6ccf6138cfb2f7p1754abjsn3968b352b789")
                .addHeader("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                .execute()
                .toCompletableFuture()
                .thenApply(response -> {
                    try {
                        return response.getResponseBody();
                    } catch (Exception e) {
                        return "Error occurred: " + e.getMessage();
                    }
                })
                .join();
    }
}
