package com.efotul.movie.movieapi.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoSuchActorException extends Exception {
    public NoSuchActorException(String message) {
        super(message);
    }
}
