package edu.bd.myproject.web.salons.beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myproject.web.navigation.beans.NavigationBean;

@Named("emailInvitBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class EmailInvitBean implements Serializable {

	@Named
	@Inject
	private NavigationBean navigationBean;

	private ArrayList<String> emailsToAdd = new ArrayList<String>();
	private static final long serialVersionUID = 1L;
	private String emailToAdd;

	public ArrayList<String> getEmailsToAdd() {
		return emailsToAdd;
	}

	public void setEmailsToAdd(ArrayList<String> emailsToAdd) {
		this.emailsToAdd = emailsToAdd;
	}

	public String getEmailToAdd() {
		return emailToAdd;
	}

	public void setEmailToAdd(String emailToAdd) {
		this.emailToAdd = emailToAdd;
	}

	public String addEmail() {
		if (this.emailsToAdd == null) {
			emailsToAdd = new ArrayList<String>();
		}
		this.emailsToAdd.add(emailToAdd);
		System.out.println("Added " + emailToAdd);
		this.emailToAdd = "";
		return navigationBean.getManageCurrentSalon();
	}

	public void deleteEmail(String email) throws Exception {
		try {
			this.emailsToAdd.remove(email);
		} catch (Exception e) {
			throw new Exception("Erreur suppression", e);
		}

	}

}
