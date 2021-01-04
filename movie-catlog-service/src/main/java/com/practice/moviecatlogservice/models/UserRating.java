package com.practice.moviecatlogservice.models;

import java.util.List;

public class UserRating {
    List<Rating> ratingList;

    public UserRating() {
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
