package com.projects.health.models;



import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "patient")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"dateDebutTraitement"}, allowGetters =true)
public class Patient {
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cin;  
	@NotBlank(message = "La valeur nom ne peut pas être vide")
	private String nom;
	@NotBlank(message = "La valeur sexe ne peut pas être vide")
	private String sexe;
	
	@Min(value = 0, message = "L'âge ne peut pas être négatif")

	private int age;
	
	@ManyToOne
	@JoinColumn(name="id_medecin_traitant",nullable=true)
	@JsonIgnore  //to ignore the medecinTraitant attribute in json,then below the jsonProperty add the needed value(idMedecin) inside the json
	//will also prevent the infinite recursion.
	private Medecin medecinTraitant;
	
	 @JsonProperty("medecinTraitantId")
	  public Integer getMedecinTraitantId() {
		 if(medecinTraitant !=null)
	        return  medecinTraitant.getIdMedecin() ;
	    
	 else 
		 return null;
}
	@Column(nullable=false, updatable=false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date dateDebutTraitement;
	public Patient(int cin, @NotBlank(message = "La valeur nom ne peut pas être vide") String nom,
			@NotBlank(message = "La valeur sexe ne peut pas être vide") String sexe, int age,Medecin medecinTraitant) {
		this.cin = cin;
		this.nom = nom;
		this.sexe = sexe;                               
		this.age = age;                              
		this.medecinTraitant=medecinTraitant;
}
	
	public Medecin getMedecinTraitant() {
		return medecinTraitant;
	}
	public void setMedecinTraitant(Medecin medecinTraitant) {
		this.medecinTraitant = medecinTraitant;
	}
	@PrePersist
    public void prePersist() {
        if (this.dateDebutTraitement == null) {
            this.dateDebutTraitement = new Date();  // Utilise la date actuelle si aucune date n'est fournie
        }
    }
	public Patient() {
		
	}
	
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Date getDateDebutTraitement() {
		return dateDebutTraitement;
	}
	public void setDateDebutTraitement(Date dateDebutTraitement) {
		this.dateDebutTraitement = dateDebutTraitement;
	} 
	
	
	
	

	
	

}
