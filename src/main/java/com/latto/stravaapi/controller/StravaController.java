/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latto.stravaapi.controller;

import com.latto.stravaapi.service.StravaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author chrls
 */
@Controller
public class StravaController {
    
    @Autowired
    private StravaService stravaService;
    
    @RequestMapping("/test")
    public String index() {
        return "This is my Strava App";
    }
    
    @RequestMapping(value="/authenticateURL", method=RequestMethod.GET)
    public @ResponseBody String getAuthUrl() {
        return stravaService.getAuthUrl();
    }
    
    @RequestMapping(value="/finishAuth", method=RequestMethod.GET)
    public String home(@RequestParam(value="code", required=true) String code) {
        stravaService.finishAuth(code);
        return "home";
    }
    
    @RequestMapping(value="/getAthlete", method=RequestMethod.GET)
    public @ResponseBody String getAthlete() {
        return stravaService.getAthlete();
    }
    
}
