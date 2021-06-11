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
import edu.bd.myProject.core.service.CoreService;
import edu.bd.myProject.core.service.ServiceU;
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
		return navigationBean.getCreateProfile();
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String supprimerSonCompte() {
		try {
			coreService.supprimerSonCompte();
		} catch (Exception e) {
			e.printStackTrace();
			addMessage("Erreur", "Compte non supprimé");
			return navigationBean.getUserDahboard();
		}
		addMessage("Confirmé", "Compte supprimé");
		return navigationBean.getIndex();
	}

	public String seDeconnecter() {
		coreService.seDeconnecter();
		this.currentSalonBean.reset();
		this.currentAccount = null;
		return navigationBean.getIndex();
	}

}
