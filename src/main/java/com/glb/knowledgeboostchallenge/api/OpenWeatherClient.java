package com.glb.knowledgeboostchallenge.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.glb.knowledgeboostchallenge.exception.APIClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class OpenWeatherClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherClient.class);

    public static String makeRequest(String endpoint) throws APIClientException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(endpoint))
            .build();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            LOGGER.error(e.getMessage());
            throw new APIClientException(e.getMessage());
        }
        return httpResponse.body();
    }

}
