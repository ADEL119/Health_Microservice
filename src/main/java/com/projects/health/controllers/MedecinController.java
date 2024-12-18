package com.projects.health.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.health.models.Medecin;
import com.projects.health.models.Patient;
import com.projects.health.repositories.MedecinRepository;
import com.projects.health.repositories.PatientRepository;

import jakarta.transaction.Transactional;

@RestController
public class MedecinController {
	
	@Autowired
	MedecinRepository mRep;
	@Autowired
	PatientRepository pRep;
	
	@PostMapping("medecin/add")
	public void ajouterMedecin(@RequestBody Medecin m) {
		mRep.save(m);
	}
	@GetMapping("/medecin")
	public List<Medecin> afficherLesMedecins(){
		return mRep.findAll();
		
		
		
	}
	@GetMapping("/medecin/searchByCin/{id}")
	public Optional<Medecin> rechercheMedecinParId(@PathVariable int id){
		
		return mRep.findById(id);	//issue here: it returns the medecinTraitant attribute
									
	}
	@Transactional
	@PutMapping("/medecin/addPatient/{medecinId}/{patientCin}")
	public void ajouterPatientSuivie(@PathVariable int medecinId,@PathVariable int patientCin) {
		Optional<Medecin> m=mRep.findById(medecinId);
		Optional<Patient> p=pRep.findById(patientCin);
		if (p.isPresent()&& m.isPresent()) {
		Medecin	medecin=m.get();
		Patient patient=p.get();
		medecin.addPatient(patient);
			}
		
		
		
	}
	
	
	
	@DeleteMapping("/medecin/delete/{id}")
	public void supprimerMedecin(@PathVariable int id) {
		
		mRep.deleteById(id);
	
}
	
	@DeleteMapping("/medecin/delete")
	public void supprimerTousMedecins() {
		mRep.deleteAll();
	}
	
	
	
	
	
	

}
