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

	private static final String FACES_REDIRECT = "?faces-redirect=true";

	private static final String ACCOUNT_CREATION = "accountCreation";
	private static final String ADMIN_DASHBOARD = "adminDashboard";
	private static final String AUTHENTICATION = "authentication";
	private static final String PROFILE_CREATION = "createProfile";
	private static final String SALON_CREATION = "createSalon";
	private static final String CURRENT_SALON = "currentSalon";
	private final String echecInscription = "echecInscription";
	private static final String INDEX = "index";
	private final String joinedSalon = "joinedSalon";
	private static final String MANAGE_INVITATIONS = "manageInvitations";
	private static final String MANAGE_SALONS = "manageSalon";
	private final String seeAccount = "seeAccount";
	private final String succesInscription = "succesInscription";
	private static final String USER_DASHBOARD = "userDashboard";

	@Inject
	@Named("inscriptionBean")
	InscriptionBean inscriptionBean;

	public String getAccountCreation() {
		String value = ACCOUNT_CREATION + FACES_REDIRECT;
		System.out.println("redirect : " + value);
		return value;
	}

	public String getAdminDashboard() {
		return ADMIN_DASHBOARD + FACES_REDIRECT;
	}

	public String getCreateProfile() {
		return PROFILE_CREATION + FACES_REDIRECT;
	}

	public String getCreateSalon() {
		return SALON_CREATION + FACES_REDIRECT;
	}

	public String getCurrentSalon() {
		return CURRENT_SALON + FACES_REDIRECT;
	}

	public String getEchecInscription() {
		return echecInscription + FACES_REDIRECT;
	}

	public String getJoinedSalon() {
		return joinedSalon + FACES_REDIRECT;
	}

	public String getManageInvitations() {
		return MANAGE_INVITATIONS + FACES_REDIRECT;
	}

	public String getManageSalon() {
		return MANAGE_SALONS + FACES_REDIRECT;
	}

	public String getSeeAccount() {
		return seeAccount + FACES_REDIRECT;
	}

	public String getSuccesInscription() {
		return succesInscription + FACES_REDIRECT;
	}

	public String getUserDahboard() {
		return USER_DASHBOARD + FACES_REDIRECT;
	}

	public String createAccount() {
		return this.ACCOUNT_CREATION;
	}

	public String getCreateAccount() {
		return ACCOUNT_CREATION;
	}

	public String getAuthentication() {
		return AUTHENTICATION;
	}

	public String getIndex() {
		String value = INDEX + FACES_REDIRECT;
		System.out.println("REDIRECT : " + value);
		return value;
	}
}
