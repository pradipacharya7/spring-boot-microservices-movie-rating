package com.practice.moviecatlogservice.resources;

import com.practice.moviecatlogservice.models.CatalogsItem;
import com.practice.moviecatlogservice.models.Movie;
import com.practice.moviecatlogservice.models.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @GetMapping("/{username}")
    public List<CatalogsItem> getCatalog(@PathVariable("username") String username){
        RestTemplate restTemplate=new RestTemplate();
        List<Rating> list= Arrays.asList(
                new Rating("abc",4),
                new Rating("pqr",3),
                new Rating("xyz",5)
        );

       return list.stream().map(rating -> {
            Movie movie=restTemplate.getForObject("http://localhost:8071/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogsItem(movie.getName(),"desc",rating.getRating());
        }).collect(Collectors.toList());

    }
}
