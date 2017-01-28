/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latto.stravaapi.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author chrls
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StravaControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    public StravaControllerTest() {
    }
    
    @Before
    public void setUp(){

    }

    /**
     * Test of index method, of class StravaController.
     */
    @Test
    public void testIndex() throws Exception{       
        mvc.perform(MockMvcRequestBuilders.get("/test").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("This is my Strava App")));       
    }

    /**
     * Test of getAuthUrl method, of class StravaController.
     */
    @Test
    public void testGetAuthUrl() throws Exception {
        String expResult = "https://www.strava.com/oauth/authorize?client_id=14160&response_type=code&redirect_uri=http://charlielatto.zapto.org:8090/finishAuth";
        mvc.perform(MockMvcRequestBuilders.get("/authenticateURL").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expResult)));  
        
    }

    /**
     * Test of getAthlete method, of class StravaController.
     */
    @Test
    public void testGetAthlete() throws Exception {
          MvcResult result= mvc.perform(MockMvcRequestBuilders.get("/getAthlete").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();
           
           String content = result.getResponse().getContentAsString();
           assertThat(content,is("null"));
    }
    
}
