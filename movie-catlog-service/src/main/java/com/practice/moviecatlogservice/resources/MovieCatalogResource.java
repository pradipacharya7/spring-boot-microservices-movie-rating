package com.practice.moviecatlogservice.resources;

import com.practice.moviecatlogservice.models.CatalogsItem;
import com.practice.moviecatlogservice.models.Movie;
import com.practice.moviecatlogservice.models.Rating;
import com.practice.moviecatlogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    private DiscoveryClient discoveryClient;
    
    @GetMapping("/{username}")
    public List<CatalogsItem> getCatalog(@PathVariable("username") String username){

//    Using Resttemplete Sync
//       UserRating userRating=restTemplate.getForObject("http://localhost:8072/ratingdata/user/"+username, UserRating.class);
        UserRating userRating=restTemplate.getForObject("http://rating-data-service/ratingdata/user/"+username, UserRating.class);

       return userRating.getRatingList().stream().map(rating -> {
            Movie movie=restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

            return new CatalogsItem(movie.getName(),"desc",rating.getRating());
        }).collect(Collectors.toList());

    }
}
/*Using Web Client Async
           Movie movie=webClientBuilder.build()
                   .get()
                   .uri("http://localhost:8071/movies/" + rating.getMovieId())
                   .retrieve()
                   .bodyToMono(Movie.class)
                   .block();
 */
