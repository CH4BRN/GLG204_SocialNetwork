package edu.bd.myproject.web.inscription.beans;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;

@Named
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class InscriptionBean {

	@Inject
	CompteService compteService;

	public String inscrire() {
		try {
			compteService.creerCompte(this.login, this.email, this.motDePasse, false, new Date(), false);
			return "succesInscription";
		} catch (Exception e) {
			e.printStackTrace();
			return "echecInscription";

		}
	}

	public String goToIndex() {
		this.email = "";
		this.login = "";
		this.motDePasse = "";
		return "index";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Boolean getIsActif() {
		return isActif;
	}

	public void setIsActif(Boolean isActif) {
		this.isActif = isActif;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	private String login;
	private String email;
	private String motDePasse;
	private Boolean isActif;
	private Date dateCreation;
	private Boolean isAdmin;
}
