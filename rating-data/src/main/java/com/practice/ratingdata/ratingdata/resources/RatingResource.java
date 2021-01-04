package com.practice.ratingdata.ratingdata.resources;

import com.practice.ratingdata.ratingdata.models.Rating;
import com.practice.ratingdata.ratingdata.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {
    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,5);
    }
    @GetMapping("user/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> list= Arrays.asList(
                new Rating("abc",4),
                new Rating("pqr",3),
                new Rating("xyz",5)
        );
        UserRating userRating=new UserRating();
        userRating.setRatingList(list);
        return userRating;
    }
}
