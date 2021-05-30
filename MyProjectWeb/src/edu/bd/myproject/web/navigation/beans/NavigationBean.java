package edu.bd.myproject.web.navigation.beans;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myproject.web.inscription.beans.InscriptionBean;

@Named("navigationBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@Named("inscriptionBean")
	InscriptionBean inscriptionBean;

	private String index = "index";

	private String authentication = "authentication";

	private String createAccount = "accountCreation";

	public String createAccount() {
		return this.createAccount;
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

}
