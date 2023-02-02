package com.globant.dnlpalestina.WeatherService.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ServerErrorException;

import java.io.IOException;
import java.rmi.ServerException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger Log = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {
        Log.info("hasError???");


        return (httpResponse.getStatusCode().is4xxClientError()
                        || httpResponse.getStatusCode().is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {
        Log.info("handling Errors!!!");

        if (httpResponse.getStatusCode().is5xxServerError()) {
            Log.info("httpResponse is 5xxServerError!!!");
        } else if (httpResponse.getStatusCode().is4xxClientError()) {
            Log.info("httpResponse is 4xxClientError!!!");
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                Log.info("httpResponse is NOT_FOUND ERROR!!!");
            }
        }
    }
}