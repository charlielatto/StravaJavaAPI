/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latto.stravaapi.model;

/**
 *
 * @author chrls
 */
public class Activity {
    
    private int id;
    private int resource_state;
    private String external_id;
    private int upload_id;
    private AthleteShort athlete;
    private String name;
    private double distance;
    private int moving_time;
    private int elapsed_time;
    private double total_elevation_gain;
    private String type;
    private String start_date;
    private String start_date_local;
    private String timezone;
    private int utc_offset;
    private double[] start_latlng;
    private double[] end_latlng;
    private String location_city;
    private String location_state;
    private String location_country;
    private double start_latitude;
    private double start_longitude;
    private int achievement_count;
    private int kudos_count;
    private int comment_count;
    private int athlete_count;
    private int photo_count;
    private Map map;
    private boolean trainer;
    private boolean commute;
    private boolean manual;
    private boolean Private;
    private boolean flagged;
    private String gear_id;
    private double average_speed;
    private double max_speed;
    private double average_cadence;
    private int average_temp;
    private double average_watts;
    private double kilojoules;
    private boolean device_watts;
    private boolean has_heartrate;
    private double average_heartrate;
    private int max_heartrate;
    private double elev_high;
    private double elev_low;
    private int pr_count;
    private int total_photo_count;
    private boolean has_kudoed;
    private int workout_type;
    private int suffer_score;
    
    public Activity(){
        
    }

    public int getId() {
        return id;
    }

    public int getResource_state() {
        return resource_state;
    }

    public String getExternal_id() {
        return external_id;
    }

    public int getUpload_id() {
        return upload_id;
    }

    public AthleteShort getAthlete() {
        return athlete;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public int getMoving_time() {
        return moving_time;
    }

    public int getElapsed_time() {
        return elapsed_time;
    }

    public double getTotal_elevation_gain() {
        return total_elevation_gain;
    }

    public String getType() {
        return type;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getStart_date_local() {
        return start_date_local;
    }

    public String getTimezone() {
        return timezone;
    }

    public int getUtc_offset() {
        return utc_offset;
    }

    public double[] getStart_latlng() {
        return start_latlng;
    }

    public double[] getEnd_latlng() {
        return end_latlng;
    }

    public String getLocation_city() {
        return location_city;
    }

    public String getLocation_state() {
        return location_state;
    }

    public String getLocation_country() {
        return location_country;
    }

    public double getStart_latitude() {
        return start_latitude;
    }

    public double getStart_longitude() {
        return start_longitude;
    }

    public int getAchievement_count() {
        return achievement_count;
    }

    public int getKudos_count() {
        return kudos_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getAthlete_count() {
        return athlete_count;
    }

    public int getPhoto_count() {
        return photo_count;
    }

    public Map getMap() {
        return map;
    }

    public boolean isTrainer() {
        return trainer;
    }

    public boolean isCommute() {
        return commute;
    }

    public boolean isManual() {
        return manual;
    }

    public boolean isPrivate() {
        return Private;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public String getGear_id() {
        return gear_id;
    }

    public double getAverage_speed() {
        return average_speed;
    }

    public double getMax_speed() {
        return max_speed;
    }

    public double getAverage_cadence() {
        return average_cadence;
    }

    public int getAverage_temp() {
        return average_temp;
    }

    public double getAverage_watts() {
        return average_watts;
    }

    public double getKilojoules() {
        return kilojoules;
    }

    public boolean isDevice_watts() {
        return device_watts;
    }

    public boolean isHas_heartrate() {
        return has_heartrate;
    }

    public double getAverage_heartrate() {
        return average_heartrate;
    }

    public int getMax_heartrate() {
        return max_heartrate;
    }

    public double getElev_high() {
        return elev_high;
    }

    public double getElev_low() {
        return elev_low;
    }

    public int getPr_count() {
        return pr_count;
    }

    public int getTotal_photo_count() {
        return total_photo_count;
    }

    public boolean isHas_kudoed() {
        return has_kudoed;
    }

    public int getWorkout_type() {
        return workout_type;
    }

    public int getSuffer_score() {
        return suffer_score;
    }

    private static class Map {
        
        private String id;
        private String summary_polyline;
        private int resource_state;

        public Map() {
        }

        public String getId() {
            return id;
        }

        public String getSummary_polyline() {
            return summary_polyline;
        }

        public int getResource_state() {
            return resource_state;
        }
        
        
    }

    private static class AthleteShort {

        private int id;
        private int resource_state;

        public AthleteShort() {
        }

        public int getId() {
            return id;
        }

        public int getResource_state() {
            return resource_state;
        }
        
        
    }
    
}
