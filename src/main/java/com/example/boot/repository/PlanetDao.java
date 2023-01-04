package com.example.boot.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.boot.Models.Planet;

public interface PlanetDao extends JpaRepository<Planet,Integer> {

    Optional<Planet> findByName(String planetName);

    @Transactional
	@Modifying
	@Query(value = "insert into planets values (default, :planetName, :ownerId)", nativeQuery = true)
	void createPlanet(@Param("planetName") String planetName, @Param("ownerId") int ownerId);

}
