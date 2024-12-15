package com.projects.health.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.health.models.Medecin;

public interface MedecinRepository extends JpaRepository<Medecin,Integer>{
	
	

}
