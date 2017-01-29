/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latto.stravaapi.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.latto.stravaapi.model.Activity;
import com.latto.stravaapi.model.Athlete;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final String ACTIVITES_URL = "https://www.strava.com/api/v3/athlete/activities";
    private Gson gson;
    @Autowired
    private RestTemplate restTemplate;
    private HttpEntity entity;
    private Athlete athlete = null;
    private List<Activity> yearActivites;
    private List<Activity> rangeActivites;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init() {
        gson = new Gson();
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", "Bearer " + PUBLIC_ACCESS_TOKEN);

        entity = new HttpEntity(headers);
        restTemplate = restTemplate();
        yearActivites = new ArrayList<>();
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
        return String.format(AUTH_URL + "?client_id=%d&response_type=code&redirect_uri=%s", CLIENT_ID, callback);
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
            access_token = jsonObject.get("access_token").getAsString();
            athlete = gson.fromJson(jsonObject.get("athlete"), Athlete.class);
        } catch (HttpClientErrorException e) {
            System.err.println(e.getMessage() + " " + e.getResponseBodyAsString());
        }

    }

    public String getYearActivites(int year) {
        yearActivites = new ArrayList<>();
        Date start, end;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, year);

        logger.info(String.format("Adding Activites for year %s", year));

        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        setTimeToBeginningOfDay(calendar);
        start = calendar.getTime();

        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setTimeToEndofDay(calendar);
        end = calendar.getTime();

        long starttime = (long) start.getTime() / 1000;
        long endtime = (long) end.getTime() / 1000;

        yearActivites = iterateActivityPages(starttime, endtime, yearActivites);
        logger.info(String.format("Found %d activites for %d", yearActivites.size(), year));
        return gson.toJson(yearActivites);
    }
    
    public String getActivitesInDateRange(String startDateString, String endDateString) {
        rangeActivites = new ArrayList<>();
        Date startDate = null, endDate = null;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();
        
        logger.info(String.format("Adding activites for range %s to %s", startDateString, endDateString));
        try {
        cal.setTime(df.parse(startDateString));
        setTimeToBeginningOfDay(cal);
        startDate = cal.getTime();

        cal.setTime(df.parse(endDateString));
        setTimeToEndofDay(cal);
        endDate = cal.getTime();
     
        } catch (ParseException e){
            logger.error(e.getMessage());
        }
        
        long starttime = (long) startDate.getTime() / 1000;
        long endtime = (long) endDate.getTime() / 1000;
                
        rangeActivites = iterateActivityPages(starttime, endtime, rangeActivites);
        logger.info(String.format("Found %d activites for range %s to %s", rangeActivites.size(), startDateString, endDateString));
        return gson.toJson(rangeActivites);
       
    }

    public List<Activity> iterateActivityPages(long starttime, long endtime, List<Activity> activites) {
        boolean empty = false;
        int page = 1;
        List<Activity> tempList = null;

        while (!empty) {
            tempList = getActivites(starttime, endtime, page);
            if (tempList.isEmpty()) {
                empty = true;
                logger.info(String.format("No more activites on page %d", page));
            } else {
                activites.addAll(tempList);
                logger.info(String.format("Added Activites on page %d", page));
            }
            page++;
        }

        return activites;
    }

    public List<Activity> getActivites(long starttime, long endtime, int page) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ACTIVITES_URL)
                .queryParam("after", starttime)
                .queryParam("before", endtime)
                .queryParam("per_page", 200)
                .queryParam("page", page)
                .queryParam("access_token", access_token);

        HttpEntity<String> response = null;
        List<Activity> activites = null;
        try {
            response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);

            activites = gson.fromJson(response.getBody(), new TypeToken<List<Activity>>() {
            }.getType());

        } catch (HttpClientErrorException e) {
            System.err.println(e.getMessage() + " " + e.getResponseBodyAsString());
        }

        return activites;
    }

    private static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }
}
