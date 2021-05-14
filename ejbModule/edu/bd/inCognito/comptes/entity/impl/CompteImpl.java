package edu.bd.inCognito.comptes.entity.impl;

import java.util.Date;
import edu.bd.inCognito.comptes.queries.CompteQueries;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.bd.inCognito.comptes.entity.Compte;

/**
 * Implémentation pour l'entité {@link Compte}
 * 
 * @author Brique DECKARD
 *
 */
@Entity
@Table(name = "compte")
@NamedQueries({ @NamedQuery(name = "all_accounts", query = CompteQueries.ALL_ACCOUNTS_QUERY),
		@NamedQuery(name = "all_inactive_accounts", query = CompteQueries.ALL_INACTIVE_ACCOUNTS_QUERY),
		@NamedQuery(name = "compte_by_login", query = CompteQueries.COMPTE_BY_LOGIN_QUERY),
		@NamedQuery(name = "compte_by_login_and_mail", query = CompteQueries.ACCOUNT_BY_LOGIN_AND_EMAIL) })
public class CompteImpl implements Compte {

	@Override
	public String toString() {
		return "CompteImpl [id=" + id + ", login=" + login + ", email=" + email + ", motDePasse=" + motDePasse
				+ ", isActif=" + isActif + ", dateCreation=" + dateCreation + "]";
	}


	private static final long serialVersionUID = 1122129325547418638L;

	@Id
	@GeneratedValue(generator = "uuid")
	private String id;

	private String login;

	private String email;

	private String motDePasse;

	private Boolean isActif;

	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@Override
	public Date getDateCreation() {
		return dateCreation;
	}

	@Override
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;

	}

	@Override
	public String getMotDePasse() {
		return this.motDePasse;
	}

	@Override
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@Override
	public Boolean getIsActif() {
		return this.isActif;
	}

	@Override
	public void setIsActif(Boolean actif) {
		this.isActif = actif;

	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getLogin() {
		return this.login;
	}

	@Override
	public void setLogin(String login) {
		this.login = login;

	}

}
