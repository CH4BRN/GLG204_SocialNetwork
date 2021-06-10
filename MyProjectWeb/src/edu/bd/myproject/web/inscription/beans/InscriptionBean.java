package edu.bd.myproject.web.inscription.beans;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.service.CompteService;
import edu.bd.myproject.web.navigation.beans.NavigationBean;

@Named
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class InscriptionBean {

	@Inject
	CompteService compteService;

	@Named
	@Inject
	NavigationBean navigationBean;

	public String inscrire() {
		try {
			compteService.creerCompte(this.login, this.email, this.motDePasse, false, new Date(), false);
			return navigationBean.getSuccesInscription();
		} catch (Exception e) {
			switch (e.getMessage()) {
			case "LOGIN_EXISTS":
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Le login existe déjà.", null));
				break;
			case "EMAIL_EXISTS":
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Le mail existe déjà.", null));
				break;
			default:
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), null));
				break;
			}

			return "";
		}

	}

	public String goToIndex() {
		this.email = "";
		this.login = "";
		this.motDePasse = "";
		return navigationBean.getIndex();
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
