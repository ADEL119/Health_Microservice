package com.projects.health.models;



import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="medecin")
@EntityListeners(AuditingEntityListener.class)

public class Medecin {
	
	@Id
	private int idMedecin;
	
	@NotBlank(message = "La valeur nom ne peut pas être vide")
	private String nom;
	
	@NotBlank(message = "La valeur specialite ne peut pas être vide")
	private String spécialité;
	
	@NotBlank(message = "La valeur adresse ne peut pas être vide")
	private String adresse;
	
	private int numeroTel;
	
	@OneToMany(mappedBy = "medecinTraitant")
	private List<Patient> patientsSuivis= new ArrayList<>();
	public Medecin() {
		
	}
	
	public Medecin(int idMedecin, @NotBlank(message = "La valeur nom ne peut pas être vide") String nom,
			@NotBlank(message = "La valeur specialite ne peut pas être vide") String spécialité,
			@NotBlank(message = "La valeur adresse ne peut pas être vide") String adresse, int numeroTel,List<Patient> patientsSuivis) {
		this.idMedecin = idMedecin;
		this.nom = nom;
		this.spécialité = spécialité;
		this.adresse = adresse;
		this.numeroTel = numeroTel;
		this.patientsSuivis=patientsSuivis= new ArrayList<>();
		//this.service=service;
	}
	/* public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	} */
	
	  // Getters and setters
    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;  // Manual ID assignment
    }
	 public List<Patient> getPatientsSuivis() {
		return patientsSuivis;
	} 
	public void setPatientsSuivis(List<Patient> patientsSuivis) {
		this.patientsSuivis = patientsSuivis;
	} 
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSpécialité() {
		return spécialité;
	}
	public void setSpécialité(String spécialité) {
		this.spécialité = spécialité;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getNumeroTel() {
		return numeroTel;
	}
	public void setNumeroTel(int numeroTel) {
		this.numeroTel = numeroTel;
	}
	
	public void addPatient(Patient patient) {
        this.patientsSuivis.add(patient);
        patient.setMedecinTraitant(this); // Update the relationship in the Patient entity
    }
	

}