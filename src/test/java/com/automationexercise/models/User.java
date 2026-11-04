package com.automationexercise.models;

import java.util.UUID;

public record User(
        String name,
        String email,
        String password,
        String day,
        String month,
        String year,
        String firstName,
        String lastName,
        String company,
        String address,
        String country,
        String state,
        String city,
        String zipcode,
        String mobile) {

    public static User random() {
        String id = UUID.randomUUID().toString().substring(0, 8);
        return new User(
                "qa_" + id,
                "qa_" + id + "@example.com",
                "Passw0rd!",
                "15", "May", "1990",
                "John", "Doe", "QA Corp",
                "1 Main Street", "Canada", "Ontario", "Toronto", "M5V 2T6",
                "+14165550123");
    }
}