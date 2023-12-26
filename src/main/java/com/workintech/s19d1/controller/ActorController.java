package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.Converter;
import jakarta.persistence.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {
    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<ActorResponse> findAll() {
        List<Actor> actorList = actorService.findAll();
        return Converter.actorResponseConvert(actorList);
    }

    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable long id){
       return Converter.actorResponseConvert(actorService.findById(id));
    }

    @PostMapping
    public ActorResponse save(@RequestBody ActorRequest actorRequest){
        List<Movie> movieList = actorRequest.getMovieList();
        Actor actor = actorRequest.getActor();
        for(Movie movie : movieList){
            actor.addMovie(movie);
        }
        actorService.save(actor);
        return Converter.actorCreateResponseConvert(actor);
    }

    @PutMapping("/{id}")
    public ActorResponse update(@RequestBody Actor actor, @PathVariable long id){
        Actor actor1 = actorService.findById(id);
        actor.setMovieList(actor1.getMovieList());
        actor.setId(id);
        actorService.save(actor);
        return Converter.actorResponseConvert(actor);
    }

    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable long id){
        Actor actor = actorService.findById(id);
        actorService.delete(actor);
        return Converter.actorResponseConvert(actor);
    }
}
