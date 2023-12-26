package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.MovieRequest;
import com.workintech.s19d1.dto.MovieResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import com.workintech.s19d1.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponse> findAll(){
        List<Movie> movieList = movieService.findAll();
        return Converter.movieResponseConvert(movieList);
    }

    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable long id){
        return Converter.movieResponseConvert(movieService.findById(id));
    }

    @PostMapping
    public MovieResponse save(MovieRequest movieRequest){
        List<Actor> actorList = movieRequest.getActorList();
        Movie movie1 = movieRequest.getMovie();
        for(Actor actor : actorList){
            movie1.addActor(actor);
        }
        movieService.save(movie1);
        return Converter.movieCreateResponseConvert(movie1);
    }

    @PutMapping("/{id}")
    public MovieResponse update(@RequestBody Movie movie, @PathVariable long id){
        Movie movie1 = movieService.findById(id);
        movie.setActorList(movie1.getActorList());
        movie.setId(id);
        movieService.save(movie);
        return Converter.movieResponseConvert(movie);
    }

    @DeleteMapping("/{id}")
    public MovieResponse delete(@PathVariable long id){
        Movie movie = movieService.findById(id);
        movieService.delete(movie);
        return Converter.movieResponseConvert(movie);
    }
}
