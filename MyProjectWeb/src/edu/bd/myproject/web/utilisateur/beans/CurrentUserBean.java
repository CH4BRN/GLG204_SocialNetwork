package edu.bd.myproject.web.utilisateur.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;
import edu.bd.myProject.core.service.CoreService;
import edu.bd.myProject.core.service.ServiceU;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myproject.web.navigation.beans.NavigationBean;
import edu.bd.myproject.web.salons.beans.CurrentSalonBean;

@Named("currentUserBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class CurrentUserBean {

	@Named
	@Inject
	NavigationBean navigationBean;

	@Named
	@Inject
	CurrentSalonBean currentSalonBean;

	@Inject
	@ServiceU
	private CoreService coreService;

	@Inject
	CompteService compteService;
	
	@Inject 
	CompteDao compteDao;

	private Boolean modifyLoginState = false;

	private Boolean modifyPasswordState = false;

	public Boolean getModifyPasswordState() {
		return modifyPasswordState;
	}

	public void setModifyPasswordState(Boolean modifyPasswordState) {
		this.modifyPasswordState = modifyPasswordState;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	private String loginAction = "modifier";

	private String passwordAction = "modifier";

	public String getPasswordAction() {
		return passwordAction;
	}

	public void setPasswordAction(String passwordAction) {
		this.passwordAction = passwordAction;
	}

	public String saveNewInformations() {
		System.out.println("Login :" + currentAccount.getLogin());
		System.out.println("Password :" + currentAccount.getMotDePasse());
		try {
			compteDao.modifier(currentAccount);
		} catch (InCognitoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private String newLogin = "";

	private String newPassword = "";

	public String getLoginAction() {
		return loginAction;
	}

	public void setLoginAction(String loginAction) {
		this.loginAction = loginAction;
	}

	public void loginModification() {
		System.out.println("LOGIN : " + newLogin);

		if (!modifyLoginState) {
			modifyLoginState = true;
			loginAction = "sauver";
			System.out.println("Modifier login ");
		} else {
			modifyLoginState = false;
			loginAction = "modifier";
			Compte compte = compteService.obtenirCompteParLogin(newLogin);
			if (compte != null) {
				System.out.println("Compte non null");
				newLogin = currentAccount.getLogin();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Le login existe déjà.", null));
			} else {
				System.out.println("Compte null");
				currentAccount.setLogin(newLogin);

				System.out.println("Sauver login ");
			}

		}

	}

	public void passwordModification() {
		System.out.println("PW : " + newPassword);
		if (!modifyPasswordState) {
			modifyPasswordState = true;
			passwordAction = "sauver";
			System.out.println("Modifier mot de passe");
		} else {
			modifyPasswordState = false;
			passwordAction = "modifier";
			currentAccount.setMotDePasse(newPassword);
			System.out.println("Sauver mot de passe");
		}
	}

	public String getNewLogin() {
		return newLogin;
	}

	public void setNewLogin(String newLogin) {
		this.newLogin = newLogin;
	}

	public Boolean getModifyLoginState() {
		return modifyLoginState;
	}

	public void setModifyLoginState(Boolean modifyLoginState) {
		this.modifyLoginState = modifyLoginState;
	}

	public CoreService getCoreService() {
		return coreService;
	}

	public void setCoreService(CoreService coreService) {
		this.coreService = coreService;
	}

	private Compte currentAccount;

	private ArrayList<Invitation> currentUserInvitations;

	@Override
	public String toString() {

		return "CurrentUserBean [currentAccount=" + currentAccount.toString() + "]";
	}

	private void rafraichirInvitations() throws Exception {
		try {
			currentUserInvitations = new ArrayList<Invitation>();
			List<Invitation> newInvitations = coreService.obtenirSesInvitations();
			currentUserInvitations.addAll(newInvitations);
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<Invitation> getCurrentUserInvitations() {
		try {
			rafraichirInvitations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentUserInvitations;
	}

	public void setCurrentUserInvitations(ArrayList<Invitation> currentUserInvitations) {
		this.currentUserInvitations = currentUserInvitations;
	}

	public Compte getCurrentAccount() throws Exception {
		currentAccount = coreService.getUser();

		return currentAccount;
	}

	public void setCurrentAccount(Compte currentAccount) {
		if (currentAccount != null) {
			newLogin = currentAccount.getLogin();
			newPassword = currentAccount.getMotDePasse();
		}
		this.currentAccount = currentAccount;
	}

	public void refuserInvitation(String id) throws Exception {
		System.out.println("REFUSER");
		try {
			coreService.refuserInvitation(id);
			rafraichirInvitations();
		} catch (Exception e) {
			throw e;
		}
	}

	public String accepterInvitation(String id) throws Exception {
		System.out.println("ACCEPTER");
		return navigationBean.getProfileCreation();
	}

	public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String supprimerSonCompte() {
		try {
			coreService.supprimerSonCompte();
		} catch (Exception e) {
			e.printStackTrace();
			addMessage("Erreur", "Compte non supprimé", FacesMessage.SEVERITY_ERROR);
			return navigationBean.getUserDashboard();
		}
		addMessage("Confirmé", "Compte supprimé", FacesMessage.SEVERITY_INFO);
		return navigationBean.getIndex();
	}

	public String seDeconnecter() {
		coreService.seDeconnecter();
		this.currentSalonBean.reset();
		this.currentAccount = null;
		return navigationBean.getIndex();
	}

}
