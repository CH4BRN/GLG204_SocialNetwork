// CompteImpl.java - Copyright pierr
package edu.bd.myProject.compte.entity.impl;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.salons.entity.Salon;

/**
 * @author pierr
 *
 */
@Entity
@Table(name = "compte")
@NamedQueries({ @NamedQuery(name = "getAccount_byLogin", query = "SELECT c FROM CompteImpl c WHERE c.login = :login"),
		@NamedQuery(name = "getAccount_byEmail", query = "SELECT c FROM CompteImpl c WHERE c.email = :email"),
		@NamedQuery(name = "getAccount_all", query = "SELECT c FROM CompteImpl c") })
public class CompteImpl implements Compte {

	@Column(name = "login", unique = true, nullable = false)
	private String login;
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	@Column(name = "motDePasse", nullable = false)
	private String motDePasse;
	@Column(name = "isActif", nullable = false)
	private Boolean isActif;
	@Column(name = "dateCreation", nullable = false)
	private Date dateCreation;
	@Id()
	@GeneratedValue(generator = "uuid")
	private String id;
	@Column(name = "isAdmin", nullable = false)
	private Boolean isAdmin;

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#getLogin()
	 */
	@Override
	public String getLogin() {
		return this.login;
	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#setLogin(java.lang.String)
	 */
	@Override
	public void setLogin(String login) {
		this.login = login;

	}

	@Override
	public String toString() {
		return "CompteImpl [login=" + login + ", email=" + email + ", motDePasse=" + motDePasse + ", isActif=" + isActif
				+ ", dateCreation=" + dateCreation + ", id=" + id + ", isAdmin=" + isAdmin + "]";
	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#getEmail()
	 */
	@Override
	public String getEmail() {
		return this.email;
	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;

	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#getMotDePasse()
	 */
	@Override
	public String getMotDePasse() {
		return this.motDePasse;
	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#setMotDePasse(java.lang.String)
	 */
	@Override
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;

	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#getIsActif()
	 */
	@Override
	public Boolean getIsActif() {
		return this.isActif;
	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#setIsActif(java.lang.Boolean)
	 */
	@Override
	public void setIsActif(Boolean actif) {
		this.isActif = actif;
	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#getDateCreation()
	 */
	@Override
	public Date getDateCreation() {
		return this.dateCreation;
	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#setDateCreation(java.util.Date)
	 */
	@Override
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#getIsAdmin()
	 */
	@Override
	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	/**
	 * @see edu.bd.myProject.compte.entity.Compte#setIsAdmin(java.lang.Boolean)
	 */
	@Override
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
