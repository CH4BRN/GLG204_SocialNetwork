// SalonImpl.java - Copyright pierr
package edu.bd.myProject.salons.entity.impl;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.entity.impl.CompteImpl;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.entity.impl.ProfileImpl;
import edu.bd.myProject.salons.entity.Salon;

/**
 * @author pierr
 *
 */
@Entity
@Table(name = "salon")
@NamedQueries({ @NamedQuery(name = "getSalon_byName", query = "SELECT s FROM SalonImpl s WHERE s.nom = :name"),
		@NamedQuery(name = "getSalon_byCreator", query = "SELECT s FROM SalonImpl s WHERE s.createur.id = :id") })
public class SalonImpl implements Salon {

	@Override
	public String toString() {
		return "SalonImpl [nom=" + nom + ", id=" + id + ", dateCreation=" + dateCreation + ", createur=" + createur
				+ "]";
	}

	@Column(name = "nom", unique = true, nullable = false)
	private String nom;

	@Id()
	@GeneratedValue(generator = "uuid")
	private String id;

	private Date dateCreation;

	@ManyToOne(targetEntity = CompteImpl.class)
	private Compte createur;

	/**
	 * @see edu.bd.myProject.salons.entity.Salon#getNom()
	 */
	@Override
	public String getNom() {
		return this.nom;
	}

	/**
	 * @see edu.bd.myProject.salons.entity.Salon#setNom(java.lang.String)
	 */
	@Override
	public void setNom(String nom) {
		this.nom = nom;

	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}

	@Override
	public Date getDateCreation() {
		return this.dateCreation;
	}

	@Override
	public void setDateCreation(Date date) {
		this.dateCreation = date;

	}

	@Override
	public Compte getCreateur() {
		return this.createur;
	}

	@Override
	public void setCreateur(Compte compte) {
		this.createur = compte;

	}

}
