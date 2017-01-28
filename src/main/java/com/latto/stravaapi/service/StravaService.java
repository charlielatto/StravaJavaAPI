/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latto.stravaapi.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.latto.stravaapi.model.Athlete;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author chrls
 */
@Service
public class StravaService {

    private static final String PUBLIC_ACCESS_TOKEN = "8ec3b44e990e901fc808825e43a9a6286c71ca97";
    private static String access_token;
    private static final int CLIENT_ID = 14160;
    private static final String CLIENT_SECRET = "9418bbb1abcaffb68263aabd51a2c732e4d82564";
    private static final String AUTH_URL = "https://www.strava.com/oauth/authorize";
    private static final String TOKEN_URL = "https://www.strava.com/oauth/token";
    private static final String callback = "http://charlielatto.zapto.org:8090/finishAuth";
    private Gson gson;
    @Autowired
    private RestTemplate restTemplate;
    private HttpEntity entity;
    private Athlete athlete = null;

    @PostConstruct
    public void init() {
        gson = new Gson();
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", "Bearer " + PUBLIC_ACCESS_TOKEN);

        entity = new HttpEntity(headers);
        restTemplate = restTemplate();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    public String addAthlete() {
//        HttpEntity<String> response = restTemplate.exchange("https://www.strava.com/api/v3/athlete", HttpMethod.GET, entity, String.class);
//        athlete = gson.fromJson(response.getBody(), Athlete.class);
//        return String.format("Athlete %s %s added to repo", athlete.getFirstname(), athlete.getLastname());
//    }
    public String getAthlete() {
        return gson.toJson(athlete);
    }

    public String getAuthUrl() {      
        return String.format(AUTH_URL+"?client_id=%d&response_type=code&redirect_uri=%s",CLIENT_ID,callback);
    }

    public void finishAuth(String code) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TOKEN_URL)
                .queryParam("client_id", CLIENT_ID)
                .queryParam("client_secret", CLIENT_SECRET)
                .queryParam("code", code);

        HttpEntity<String> response = null;
        try {
            response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.POST,
                    entity,
                    String.class);

            JsonObject jsonObject = gson.fromJson(response.getBody(), JsonObject.class);
            access_token = jsonObject.get("access_token").toString();
            athlete = gson.fromJson(jsonObject.get("athlete"), Athlete.class);
        } catch (HttpClientErrorException e) {
            System.err.println(e.getMessage() + " " + e.getResponseBodyAsString());
        }

    }
}
