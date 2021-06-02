package edu.bd.myproject.web.utilisateur.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.user.service.UserService;

@Named("currentUserBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class CurrentUserBean {

	@Override
	public String toString() {

		return "CurrentUserBean [currentAccount=" + currentAccount.toString() + "]";
	}

	@Inject
	UserService userService;

	private Compte currentAccount;

	public Compte getCurrentAccount() {
		if (currentAccount == null) {
			currentAccount = userService.getUser();
		}
		return currentAccount;
	}

	public void setCurrentAccount(Compte currentAccount) {
		System.out.println(currentAccount.toString());
		this.currentAccount = currentAccount;
	}

}
