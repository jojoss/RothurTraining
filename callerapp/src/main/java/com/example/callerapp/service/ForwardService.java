package com.example.callerapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForwardService {

    @Value("${ordersystem.base-url}")
    private String ordersystemBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchAllUsers() {
        String url = ordersystemBaseUrl + "/users";
        return restTemplate.getForObject(url, String.class);
    }

    public String fetchUserById(int id) {
        String url = ordersystemBaseUrl + "/users/" + id;
        return restTemplate.getForObject(url, String.class);
    }

    public String createUser(String userJson) {
        String url = ordersystemBaseUrl + "/users";
        return restTemplate.postForObject(url, userJson, String.class);
    }

    public void deleteUserById(int id) {
        String url = ordersystemBaseUrl + "/users/" + id;
        restTemplate.delete(url);
    }

}