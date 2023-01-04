package com.example.boot.controller;

import java.util.List;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.Models.Planet;
import com.example.boot.Service.PlanetService;
import com.example.boot.exceptions.AuthenticationFailed;
import com.example.boot.exceptions.ModelNotFound;

@RestController
public class PlanetController {

    private static Logger planetLogger = LoggerFactory.getLogger(PlanetController.class);
    
    @Autowired
    private PlanetService planetService;

    @ExceptionHandler(AuthenticationFailed.class)
    public ResponseEntity<String> authenticationFailed(AuthenticationFailed e){
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ModelNotFound.class)
    public ResponseEntity<String> modelNotFound(ModelNotFound e){
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> sqlIssue(PSQLException e){
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> deleteFailed(EmptyResultDataAccessException e){
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>("could not delete moon", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("api/planets/id/{id}")
    public ResponseEntity<Planet> getPlanetbyId(@PathVariable int id){
        return new ResponseEntity<>(this.planetService.getPlanetById(id), HttpStatus.OK);
    }

    @GetMapping("api/planets/{name}")
    public ResponseEntity<Planet> getPlanetbyName(@PathVariable String name){
        return new ResponseEntity<>(this.planetService.getPlanetByName(name), HttpStatus.OK);
    }

    @GetMapping("api/planets")
    public ResponseEntity<List<Planet>> getAllPlanets(){
        return new ResponseEntity<>(this.planetService.getAllPlanets(), HttpStatus.OK);
    }

    @PostMapping("api/planet")
    public ResponseEntity<String> createPlanet(@RequestBody Planet planet){
        String message = this.planetService.createPlanet(planet);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("api/planet/{id}")
    public ResponseEntity<String> deletePlanetById(@PathVariable int id){
        return new ResponseEntity<>(this.planetService.deletePlanetById(id), HttpStatus.OK);
    }
}
