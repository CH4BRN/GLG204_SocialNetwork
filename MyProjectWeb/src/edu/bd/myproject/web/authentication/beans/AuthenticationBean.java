package edu.bd.myproject.web.authentication.beans;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.admin.service.AdminService;
import edu.bd.myProject.authentication.service.AuthenticationService;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.user.service.UserService;
import edu.bd.myproject.web.utilisateur.beans.CurrentUserBean;

@Named("authenticationBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class AuthenticationBean implements Serializable {

	@Inject
	AuthenticationService authenticationService;

	@Inject
	AdminService adminService;

	@Inject
	UserService userService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String login;
	private String password;

	/**
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String authentifier() {
		try {

			Compte compte = this.authenticationService.authentifier(this.login, this.password);
			if (!compte.getIsActif()) {
				return "index";
			}
			if (!compte.getIsAdmin()) {
				this.userService.setUser(compte);
				return "userDashboard";
			} else {
				this.adminService.setAdmin(compte);
				return "adminDashboard";
			}

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Echec authentification", null));
		}
		return "index";

	}

}
