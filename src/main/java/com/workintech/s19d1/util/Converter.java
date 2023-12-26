package com.workintech.s19d1.util;

import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.dto.MovieResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static List<ActorResponse> actorResponseConvert(List<Actor> actorList){
        List<ActorResponse> actorResponseList = new ArrayList<>();
        for(Actor actor1 : actorList){
            actorResponseList.add(new ActorResponse(actor1.getId(),actor1.getFirstName()
                    ,actor1.getLastName(),actor1.getBirthDate(),null));
        }
        return actorResponseList;
    }

    public static ActorResponse actorResponseConvert(Actor actor){
            return new ActorResponse(actor.getId(),actor.getFirstName()
                    ,actor.getLastName(),actor.getBirthDate(),null);
    }

    public static ActorResponse actorCreateResponseConvert(Actor actor){
        return new ActorResponse(actor.getId(),actor.getFirstName()
                ,actor.getLastName(),actor.getBirthDate(),actor.getMovieList());
    }

    public static List<MovieResponse> movieResponseConvert(List<Movie> movieList){
        List<MovieResponse> movieResponseList = new ArrayList<>();
        for(Movie movie1 : movieList){
            movieResponseList.add(new MovieResponse(movie1.getId(),movie1.getName()
                    ,movie1.getDirectorName(),movie1.getRating(),movie1.getReleaseDate(),null));
        }
        return movieResponseList;
    }

    public static MovieResponse movieResponseConvert(Movie movie){
        return new MovieResponse(movie.getId(),movie.getName()
                ,movie.getDirectorName(),movie.getRating(),movie.getReleaseDate(),null);
    }

    public static MovieResponse movieCreateResponseConvert(Movie movie){
        return new MovieResponse(movie.getId(),movie.getName()
                ,movie.getDirectorName(),movie.getRating(),movie.getReleaseDate(),movie.getActorList());
    }
}
