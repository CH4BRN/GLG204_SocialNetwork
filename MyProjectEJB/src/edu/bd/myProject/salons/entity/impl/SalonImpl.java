// SalonImpl.java - Copyright pierr
package edu.bd.myProject.salons.entity.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.entity.impl.CompteImpl;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.invitation.entity.impl.InvitationImpl;
import edu.bd.myProject.post.entity.Post;
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

	private Boolean isPersistant = false;

	private Boolean signal = false;

	public Boolean getSignal() {
		return signal;
	}

	public void setSignal(Boolean signal) {
		this.signal = signal;
	}

	@Column(name = "nom", unique = true, nullable = false)
	private String nom;

	@Id()
	@GeneratedValue(generator = "uuid")
	private String id;

	private Date dateCreation;

	@ManyToOne(targetEntity = CompteImpl.class)
	private Compte createur;

	@OneToMany(targetEntity = InvitationImpl.class, cascade = CascadeType.REMOVE)
	private List<Invitation> invitations;

	@OneToMany(targetEntity = ProfileImpl.class, cascade = CascadeType.REMOVE)
	private List<Profile> profiles;

	@OneToMany(targetEntity = ProfileImpl.class, cascade = CascadeType.REMOVE)
	private List<Post> posts;

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

	@Override
	public void setPersitant(Boolean persistant) {
		this.isPersistant = persistant;

	}

	@Override
	public Boolean getPersistant() {
		return this.isPersistant;
	}

	@Override
	public List<Invitation> getInvitations() {
		return this.invitations;
	}

	@Override
	public void setInvitations(List<Invitation> invitations) {
		this.invitations = invitations;

	}

	@Override
	public List<Profile> getProfiles() {
		return this.profiles;
	}

	@Override
	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

}
