package com.globant.knowledgeboostchallenge.utils;


import java.util.Objects;

public class URLUtil {
    private String baseURL;
    private StringBuilder params;

    public URLUtil(String baseURL) {
        this.baseURL = baseURL;
        params = new StringBuilder();
    }

    public URLUtil set(String paramName, String paramValue) {
        if (!Objects.isNull(paramName) && !Objects.isNull(paramValue)) {
            if (params.length() != 0)
                params.append("&").append(paramName).append("=").append(paramValue);
            else
                params.append(paramName).append("=").append(paramValue);
        }
        return this;
    }

    public URLUtil replace(String paramName, String value) {
        baseURL = baseURL.replace(paramName, value);
        return this;
    }

    public String build() {
        return baseURL + params;
    }

}
