package edu.bd.myProject.profiles.entity.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.salons.entity.Salon;

@Entity
@Table(name = "profile")
public class ProfileImpl implements Profile {

	public Compte getUser() {
		return user;
	}

	public void setUser(Compte user) {
		this.user = user;
	}

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	@ManyToOne
	private Compte user;

	@ManyToOne
	private Salon salon;

	private String pseudo;

	@Id()
	@GeneratedValue(generator = "uuid")
	private String id;

	@Override
	public String getPseudo() {
		return this.pseudo;
	}

	@Override
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;

	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

}
