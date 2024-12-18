package com.projects.health.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.health.models.Medecin;
import com.projects.health.models.Patient;



@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
	
	public List<Patient> findBySexe(String sexe);
	public List<Patient> findByAgeBetween(int min,int max);
	public boolean existsByCin(int cin);
	public List<Patient>findByAgeGraterThan(int age);
	
	
	//public Medecin findByMedecinTraitant(int idMedecin);
	

}
