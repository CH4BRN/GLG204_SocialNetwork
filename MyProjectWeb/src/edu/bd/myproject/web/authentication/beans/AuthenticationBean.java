package edu.bd.myproject.web.authentication.beans;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.authentication.service.AuthenticationService;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.core.service.CoreService;
import edu.bd.myproject.web.admin.beans.AdminBean;
import edu.bd.myproject.web.navigation.beans.NavigationBean;
import edu.bd.myproject.web.salons.beans.CurrentSalonBean;
import edu.bd.myproject.web.utilisateur.beans.CurrentUserBean;

@Named("authenticationBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class AuthenticationBean implements Serializable {

	@Inject
	AuthenticationService authenticationService;

	@Named
	@Inject
	AdminBean adminBean;

	@Named
	@Inject
	CurrentUserBean currentUserBean;

	@Named(value = "currentSalonBean")
	@Inject
	CurrentSalonBean CurrentSalonBean;

	@Named
	@Inject
	NavigationBean navigationBean;
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

	public String authentifier() throws Exception {

		CoreService service;
		try {
			service = this.authenticationService.authentifier(this.login, this.password);	
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Echec authentification " + e.getMessage(), null));
			return navigationBean.getIndex();
		}
		
		Compte user = service.getUser();

		if (user == null) {
			throw new Exception(this.getClass().getSimpleName() + "service User null");
		}

		System.out.println("COMPTE : " + user.toString());

		if (user.getIsAdmin()) {
			System.out.println("ADMIN SERVICE");

			if (adminBean == null) {
				throw new Exception(this.getClass().getSimpleName() + "Admin bean null");
			}
			adminBean.setCoreService(service);
			adminBean.setUser(user);
			if (adminBean.getUser() == null) {
				throw new Exception(this.getClass().getSimpleName() + "Admin bean User null");
			}
		} else {
			System.out.println("USER SERVICE");

			if (currentUserBean == null) {
				throw new Exception(this.getClass().getSimpleName() + "User bean null");
			}
			currentUserBean.setCoreService(service);
			currentUserBean.setCurrentAccount(user);

			if (currentUserBean.getCurrentAccount() == null) {
				throw new Exception(this.getClass().getSimpleName() + "User bean User null");
			}
		}
		System.out.println("Dashboard : " + service.getDashboard());
		return service.getDashboard();

		/*
		 * } 
		 * catch (Exception e) { 
		 * throw new Exception(this.getClass().getSimpleName() + e.getMessage()); /* 
		 * FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Echec authentification " + e.getMessage(), null)); 
		 * return navigationBean.getIndex();
		 */
		// }

	}

}
