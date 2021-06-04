package edu.bd.myproject.web.utilisateur.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.invitation.service.InvitationService;
import edu.bd.myProject.user.service.UserService;
import edu.bd.myproject.web.navigation.beans.NavigationBean;

@Named("currentUserBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class CurrentUserBean {

	@Override
	public String toString() {

		return "CurrentUserBean [currentAccount=" + currentAccount.toString() + "]";
	}

	private void rafraichirInvitations() throws Exception {
		try {
			currentUserInvitations = new ArrayList<Invitation>();
			List<Invitation> newInvitations = invitationService.obtenirInvitationsPourCompte(currentAccount);

			currentUserInvitations.addAll(newInvitations);
		} catch (Exception e) {
			throw e;
		}
	}

	@Named
	@Inject
	NavigationBean navigationBean;
	
	

	@Inject
	UserService userService;

	@Inject
	InvitationService invitationService;

	private ArrayList<Invitation> currentUserInvitations;

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

	private Compte currentAccount;

	public Compte getCurrentAccount() {
		if (currentAccount == null) {
			currentAccount = userService.getUser();
			System.out.println("\n\ncurrent : " + currentAccount.getLogin());
		}

		return currentAccount;
	}

	public void setCurrentAccount(Compte currentAccount) {
		System.out.println(currentAccount.toString());

		this.currentAccount = currentAccount;
	}

	public void refuserInvitation(String id) throws Exception {
		System.out.println("REFUSER");
		try {
			this.invitationService.refuserInvitation(id);
			rafraichirInvitations();
		} catch (Exception e) {
			throw e;
		}
	}

	public String accepterInvitation(String id) throws Exception {
		System.out.println("ACCEPTER");
		

		return "createProfile";
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String supprimerSonCompte() {
		userService.setUser(currentAccount);

		try {
			userService.supprimerSonCompte();
		} catch (Exception e) {
			e.printStackTrace();
			addMessage("Erreur", "Compte non supprimé");
			return navigationBean.getUserDahboard();
		}
		addMessage("Confirmé", "Compte supprimé");
		return navigationBean.getIndex();
	}

	public String seDeconnecter() {
		userService.setUser(null);
		this.currentAccount = null;
		return navigationBean.getIndex();
	}

}
