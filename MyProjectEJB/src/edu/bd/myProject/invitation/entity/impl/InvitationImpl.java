// InvitationImpl.java - Copyright pierr
package edu.bd.myProject.invitation.entity.impl;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.entity.impl.CompteImpl;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.entity.impl.SalonImpl;

/**
 * @author pierr
 *
 */
@Entity
@Table(name = "invitation")
public class InvitationImpl implements Invitation {
	@Id()
	@GeneratedValue(generator = "uuid")
	private String id;

	@ManyToOne(targetEntity = SalonImpl.class, cascade = CascadeType.REMOVE)
	private Salon salon;

	private Date dateCreation;

	@ManyToOne(targetEntity = CompteImpl.class, cascade = CascadeType.REMOVE)
	private Compte expediteur;

	@ManyToOne(targetEntity = CompteImpl.class, cascade = CascadeType.REMOVE)
	private Compte destinataire;

	@Override
	public Salon getSalon() {
		return salon;
	}

	@Override
	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	/**
	 * @see edu.bd.myProject.invitation.entity.Invitation#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * @see edu.bd.myProject.invitation.entity.Invitation#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * @see edu.bd.myProject.invitation.entity.Invitation#getDate()
	 */
	@Override
	public Date getDate() {
		return this.dateCreation;
	}

	/**
	 * @see edu.bd.myProject.invitation.entity.Invitation#setDate(java.util.Date)
	 */
	@Override
	public void setDate(Date date) {
		this.dateCreation = date;

	}

	@Override
	public Compte getExpediteur() {
		return this.expediteur;
	}

	@Override
	public void setExpediteur(Compte compte) {
		this.expediteur = compte;
	}

	@Override
	public Compte getDestinataire() {
		return this.destinataire;
	}

	@Override
	public void setDestinataire(Compte compte) {
		this.destinataire = compte;
	}

}
