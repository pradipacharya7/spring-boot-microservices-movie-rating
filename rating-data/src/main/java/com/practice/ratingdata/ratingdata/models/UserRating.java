package com.practice.ratingdata.ratingdata.models;

import java.util.List;

public class UserRating {
    List<Rating> ratingList;


    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
