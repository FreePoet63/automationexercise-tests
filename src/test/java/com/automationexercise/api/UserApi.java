package com.automationexercise.api;

import com.automationexercise.config.Config;
import com.automationexercise.models.User;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/** Fast test data setup/cleanup through the site's public API (no browser needed). */
public final class UserApi {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private UserApi() {
    }

    public static void create(User user) {
        Map<String, String> form = new LinkedHashMap<>();
        form.put("name", user.name());
        form.put("email", user.email());
        form.put("password", user.password());
        form.put("title", "Mr");
        form.put("birth_date", user.day());
        form.put("birth_month", user.month());
        form.put("birth_year", user.year());
        form.put("firstname", user.firstName());
        form.put("lastname", user.lastName());
        form.put("company", user.company());
        form.put("address1", user.address());
        form.put("address2", "");
        form.put("country", user.country());
        form.put("zipcode", user.zipcode());
        form.put("state", user.state());
        form.put("city", user.city());
        form.put("mobile_number", user.mobile());

        String body = send("POST", "/api/createAccount", form);
        if (!body.contains("User created!")) {
            throw new IllegalStateException("Cannot create user via API: " + body);
        }
    }

    public static void delete(User user) {
        Map<String, String> form = new LinkedHashMap<>();
        form.put("email", user.email());
        form.put("password", user.password());
        send("DELETE", "/api/deleteAccount", form);
    }

    private static String send(String method, String path, Map<String, String> form) {
        String encoded = form.entrySet().stream()
                .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8)
                        + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        HttpRequest request = HttpRequest.newBuilder(URI.create(Config.get("base.url") + path))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .method(method, BodyPublishers.ofString(encoded))
                .build();
        try {
            HttpResponse<String> response = CLIENT.send(request, BodyHandlers.ofString());
            return response.body();
        } catch (IOException e) {
            throw new IllegalStateException("API call failed: " + method + " " + path, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("API call interrupted", e);
        }
    }
}