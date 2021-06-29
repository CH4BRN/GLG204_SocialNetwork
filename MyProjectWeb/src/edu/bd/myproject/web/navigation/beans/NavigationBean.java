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

	public String getAccountCreation() {
		return ACCOUNT_CREATION;
	}

	public String getSuccesSalonCreation() {
		return SUCCES_SALON_CREATION;
	}

	public String getSuccesProfileCreation() {
		return SUCCES_PROFILE_CREATION;
	}

	public String getAdminDashboard() {
		return ADMIN_DASHBOARD;
	}

	public String getAuthentication() {
		return AUTHENTICATION;
	}

	public String getProfileCreation() {
		return PROFILE_CREATION;
	}

	public String getSalonCreation() {
		return SALON_CREATION;
	}

	public String getCurrentSalon() {
		return CURRENT_SALON;
	}

	public String getEchecInscription() {
		return ECHEC_INSCRIPTION;
	}

	public String getIndex() {
		return INDEX;
	}

	public String getJoinedSalon() {
		return JOINED_SALON;
	}

	public String getManageInvitations() {
		return MANAGE_INVITATIONS;
	}

	public String getManageSalons() {
		return MANAGE_SALONS;
	}

	public String getSeeAccount() {
		return SEE_ACCOUNT;
	}

	public String getSuccesInscription() {
		return SUCCES_INSCRIPTION;
	}

	public String getUserDashboard() {
		return USER_DASHBOARD;
	}

	public String getModifyProfile() {
		return MODIFY_PROFILE;
	}

	public String getManageCurrentSalon() {
		return MANAGE_CURRENT_SALON;
	}

	public String getManageAccount() {
		return MANAGE_CURRENT_ACCOUNT;
	}

	private static final String ACCOUNT_CREATION = "accountCreation" + FACES_REDIRECT;
	private static final String SUCCES_SALON_CREATION = "succesCreationSalon" + FACES_REDIRECT;
	private static final String SUCCES_PROFILE_CREATION = "succesCreationProfile" + FACES_REDIRECT;
	private static final String ADMIN_DASHBOARD = "adminDashboard" + FACES_REDIRECT;
	private static final String AUTHENTICATION = "authentication" + FACES_REDIRECT;
	private static final String PROFILE_CREATION = "createProfile" + FACES_REDIRECT;
	private static final String SALON_CREATION = "createSalon" + FACES_REDIRECT;
	private static final String CURRENT_SALON = "currentSalon" + FACES_REDIRECT;
	private static final String ECHEC_INSCRIPTION = "echecInscription" + FACES_REDIRECT;
	private static final String INDEX = "index" + FACES_REDIRECT;
	private static final String JOINED_SALON = "joinedSalon" + FACES_REDIRECT;
	private static final String MANAGE_INVITATIONS = "manageInvitations" + FACES_REDIRECT;
	private static final String MANAGE_SALONS = "manageSalon" + FACES_REDIRECT;
	private static final String SEE_ACCOUNT = "seeAccount" + FACES_REDIRECT;
	private static final String SUCCES_INSCRIPTION = "succesInscription" + FACES_REDIRECT;
	private static final String USER_DASHBOARD = "userDashboard" + FACES_REDIRECT;
	private static final String MODIFY_PROFILE = "modifyProfile" + FACES_REDIRECT;
	private static final String MANAGE_CURRENT_SALON = "manageCurrentSalon" + FACES_REDIRECT;
	private static final String MANAGE_CURRENT_ACCOUNT = "manageCurrentAccount" + FACES_REDIRECT;
	private static final String SEE_SALONS = "seeSalons" + FACES_REDIRECT;

	@Inject
	@Named("inscriptionBean")
	InscriptionBean inscriptionBean;

	public String getSeeSalons() {
		return this.SEE_SALONS;
	}

}
