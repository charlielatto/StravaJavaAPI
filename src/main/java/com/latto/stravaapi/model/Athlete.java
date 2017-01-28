/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latto.stravaapi.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author chrls
 */
@Entity
public class Athlete {

    @Id
    private int id;
    private String username;
    private int resource_state;
    private String firstname;
    private String lastname;
    private String city;
    private String state;
    private String country;
    private String sex;
    private boolean premium;
    private String created_at;
    private String updated_at;
    private int badge_type_id;
    private String profile_medium;
    private String profile;
    private String friend;
    private String follower;
    private int follower_count;
    private int friend_count;
    private int mutual_friend_count;
    private int athlete_type;
    private String date_preference;
    private String measurement_preference;
    @OneToMany
    @JoinColumn(name="id")
    private List<Club> clubs;
    private String email;
    private String ftp;
    private double weight;
    @OneToMany
    @JoinColumn(name="id")
    private List<Bike> bikes;
    private String[] shoes;


    public Athlete() {
        
    }

    @Override
    public String toString() {
        return String.format("Found Athlete %s %s", firstname, lastname);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getResource_state() {
        return resource_state;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getSex() {
        return sex;
    }

    public boolean isPremium() {
        return premium;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getBadge_type_id() {
        return badge_type_id;
    }

    public String getProfile_medium() {
        return profile_medium;
    }

    public String getProfile() {
        return profile;
    }

    public String getFriend() {
        return friend;
    }

    public String getFollower() {
        return follower;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public int getFriend_count() {
        return friend_count;
    }

    public int getMutual_friend_count() {
        return mutual_friend_count;
    }

    public int getAthlete_type() {
        return athlete_type;
    }

    public String getDate_preference() {
        return date_preference;
    }

    public String getMeasurement_preference() {
        return measurement_preference;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public String getEmail() {
        return email;
    }

    public String getFtp() {
        return ftp;
    }

    public double getWeight() {
        return weight;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public String[] getShoes() {
        return shoes;
    }

    @Entity
    private class Bike  {

        @Id
        private String id;
        private boolean primary;
        private String name;
        private int resource_state;
        private int distance;

        public Bike() {

        }

        public String getId() {
            return id;
        }

        public boolean isPrimary() {
            return primary;
        }

        public String getName() {
            return name;
        }

        public int getDistance() {
            return distance;
        }

    }

    @Entity
    private class Club  {

        @Id
        private int id;
        private int resource_state;
        private String name;
        private String profile_medium;
        private String profile;
        private String cover_photo;
        private String cover_photo_small;
        private String sport_type;
        private String city;
        private String state;
        private String country;
        private boolean Private;
        private int member_count;
        private boolean featured;
        private boolean verified;
        private String url;

        public Club() {

        }

        public int getId() {
            return id;
        }

        public int getResource_state() {
            return resource_state;
        }

        public String getName() {
            return name;
        }

        public String getProfile_medium() {
            return profile_medium;
        }

        public String getProfile() {
            return profile;
        }

        public String getCover_photo() {
            return cover_photo;
        }

        public String getCover_photo_small() {
            return cover_photo_small;
        }

        public String getSport_type() {
            return sport_type;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getCountry() {
            return country;
        }

        public boolean isPrivate() {
            return Private;
        }

        public int getMember_count() {
            return member_count;
        }

        public boolean isFeatured() {
            return featured;
        }

        public boolean isVerified() {
            return verified;
        }

        public String getUrl() {
            return url;
        }

    }

}
