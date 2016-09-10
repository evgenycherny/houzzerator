package com.e3.houzzerator.datascrapper.download.helpers;

import org.apache.http.client.methods.HttpUriRequest;
import org.mockito.ArgumentMatcher;

import java.util.HashMap;
import java.util.Map;

public class RequestMatcher extends ArgumentMatcher<HttpUriRequest> {
    private String path;
    private Map<String, String> headers;

    public RequestMatcher() {
        this.headers = new HashMap<>();
    }
    public RequestMatcher withPath(String path) {
        this.path = path;
        return this;
    }
    public RequestMatcher withHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }
    public boolean matches(Object argument) {
        HttpUriRequest req = (HttpUriRequest)argument;

        return matchesPath(req) &&
                matchesHeaders(req);
    }
    private boolean matchesPath(HttpUriRequest req) {
        return path == null || req.getURI().toString().equals(path);
    }
    private boolean matchesHeaders(HttpUriRequest req) {
        if (headers.isEmpty()) return true;
        for (String key: headers.keySet()) {

            if (!req.containsHeader(key)) return false;
            if (!req.getFirstHeader(key).getValue().equals(headers.get(key)))
                return false;
        }
        return true;
    }

}
