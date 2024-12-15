package com.projects.health.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.health.models.Medecin;
import com.projects.health.repositories.MedecinRepository;

@RestController
public class MedecinController {
	
	@Autowired
	MedecinRepository mRep;
	
	@PostMapping("medecin/add")
	public void ajouterMedecin(@RequestBody Medecin m) {
		mRep.save(m);
	}
	
	
	

}
