package com.example.boot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot.Models.Planet;
import com.example.boot.exceptions.ModelNotFound;
import com.example.boot.repository.PlanetDao;

@Service
public class PlanetService {
    
    @Autowired
    private PlanetDao planetDao;

    public Planet getPlanetById(int planetId){
        Optional<Planet> possiblePlanet = this.planetDao.findById(planetId);
        if(possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else{
            throw new ModelNotFound("Planet not found");
        }
    }

    public Planet getPlanetByName(String name){
        Optional<Planet> possiblePlanet = this.planetDao.findByName(name);
        if(possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else{
            throw new ModelNotFound("Planet not found");
        }
    }

    public List<Planet> getAllPlanets(){
        List<Planet> planets = this.planetDao.findAll();
        if(planets.size() != 0){
            return planets;
        } else{
            throw new ModelNotFound("No planets found in the database");
        }    }

    public String createPlanet(Planet p) {
		this.planetDao.createPlanet(p.getName(), p.getOwnerId());
		return "Planet created";
	}

    public String deletePlanetById(int planetId){
        this.planetDao.deleteById(planetId);
        return "Planet was deleted with id of " + planetId;
    }
}
