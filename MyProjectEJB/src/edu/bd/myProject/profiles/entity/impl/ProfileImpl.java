package edu.bd.myProject.profiles.entity.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.entity.impl.CompteImpl;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.entity.impl.SalonImpl;

@Entity
@Table(name = "profile")
public class ProfileImpl implements Profile {
	@Id()
	@GeneratedValue(generator = "uuid")
	private String id;

	@ManyToOne(targetEntity = CompteImpl.class)
	private Compte user;

	@ManyToOne(targetEntity = SalonImpl.class)
	private Salon salon;

	private String pseudo;

	private Boolean connected;

	@Override
	public Compte getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "ProfileImpl [user=" + user + ", salon=" + salon + ", pseudo=" + pseudo + ", id=" + id + "]";
	}

	@Override
	public void setUser(Compte user) {
		this.user = user;
	}

	@Override
	public Salon getSalon() {
		return salon;
	}

	@Override
	public void setSalon(Salon salon) {
		this.salon = salon;
	}

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

	@Override
	public Boolean getConnected() {
		return this.connected;
	}

	@Override
	public void setConnected(Boolean connected) {
		this.connected = connected;

	}

}
