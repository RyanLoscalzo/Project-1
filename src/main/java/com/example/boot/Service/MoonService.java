package com.example.boot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot.Models.Moon;
import com.example.boot.exceptions.ModelNotFound;
import com.example.boot.repository.MoonDao;

@Service
public class MoonService {
    
    @Autowired
    private MoonDao moonDao;

    public Moon getMoonById(int moonId){
        Optional<Moon> possiblePlanet = this.moonDao.findById(moonId);
        if(possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else{
            throw new ModelNotFound("Moon not found");
        }
    }

    public Moon getMoonByName(String name){
        Optional<Moon> possiblePlanet = this.moonDao.findByName(name);
        if(possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else{
            throw new ModelNotFound("Moon not found");
        }
    }

    public List<Moon> getAllMoons(){
        List<Moon> moons = this.moonDao.findAll();
        if(moons.size() != 0){
            return moons;
        } else{
            throw new ModelNotFound("No moons found in the database");
        }
    }

    public List<Moon> getMoonsFromPlanet(int planetId){
        List<Moon> moons = this.moonDao.getMoonsFromPlanet(planetId);
        if(moons.size() != 0){
            return moons;
        } else{
            throw new ModelNotFound("No moons found for this planet");
        }
    }

    public String createMoon(Moon m) {
		this.moonDao.createMoon(m.getName(), m.getMyPlanetId());
		return "Moon created";
	}

    public String deleteMoonById(int moonId){
        this.moonDao.deleteById(moonId);
        return "Moon was deleted with id of " + moonId;
    }
}
