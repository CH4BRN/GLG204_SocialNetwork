package edu.bd.myproject.web.salons.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.profiles.dao.ProfileDao;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.service.ProfileService;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.service.SalonService;
import edu.bd.myproject.web.invitation.beans.InvitSomeoneBean;
import edu.bd.myproject.web.navigation.beans.NavigationBean;
import edu.bd.myproject.web.profile.beans.ProfileBean;
import edu.bd.myproject.web.utilisateur.beans.CurrentUserBean;

@Named("salonBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class SalonBean {

	private ArrayList<Salon> participatedSalons;

	private ArrayList<Salon> currentUserSalons;

	@Named
	@Inject
	CurrentUserBean currentUserBean;

	@Named
	@Inject
	ProfileBean profileBean;

	@Named
	@Inject
	CurrentSalonBean currentSalonBean;

	@Named
	@Inject
	NavigationBean navigationBean;

	@Named
	@Inject
	InvitSomeoneBean invitSomeoneBean;

	@Named
	@Inject
	ManageSalonBean manageSalonBean;

	@Inject
	private SalonService salonService;

	@Inject
	private ProfileDao profileDao;

	@Inject
	private ProfileService profileService;

	private String newSalonName;

	private Boolean isPersistant = false;

	public Boolean getIsPersistant() {
		return isPersistant;
	}

	public void setIsPersistant(Boolean isPersistant) {
		this.isPersistant = isPersistant;
	}

	public ArrayList<Salon> getParticipatedSalons() throws Exception {
		participatedSalons = new ArrayList<Salon>();
		try {
			List<Salon> salons = salonService
					.obtenirSalonsAuxquelsUtilisateurParticipe(currentUserBean.getCurrentAccount());
			participatedSalons.addAll(salons);
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
		}

		return participatedSalons;
	}

	public void setParticipatedSalons(ArrayList<Salon> participatedSalons) {
		this.participatedSalons = participatedSalons;
	}

	public ArrayList<Salon> getCurrentUserSalons() throws Exception {
		this.currentUserSalons = new ArrayList<Salon>();
		try {
			List<Salon> salons = this.salonService
					.obtenirSalonsCreesParUtilisateur(currentUserBean.getCurrentAccount());
			this.currentUserSalons.addAll(salons);
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
		}

		return currentUserSalons;
	}

	public void setCurrentUserSalons(ArrayList<Salon> currentUserSalons) {
		this.currentUserSalons = currentUserSalons;
	}

	public String getNewSalonName() {
		return newSalonName;
	}

	public void setNewSalonName(String newSalonName) {
		this.newSalonName = newSalonName;
	}

	public String checkSalon() throws Exception {

		Salon salon = this.salonService.obtenirSalonParNom(newSalonName);
		if (salon == null) {
			return navigationBean.getCreateSalon();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Le salon existe déjà", null));
			return "";
		}
	}

	public String creerSalon() throws Exception {

		Salon salon = null;
		Compte createur = currentUserBean.getCurrentAccount();
		List<String> emails = invitSomeoneBean.getEmailList();
		String name = this.newSalonName;
		boolean persistant = isPersistant;

		salon = this.salonService.creerSalon(name, createur, emails, persistant);

		profileBean.setSalon(salon);
		currentSalonBean.setThisSalon(salon);
		profileBean.setCompte(createur);
		this.newSalonName = "";
		this.isPersistant = false;

		return navigationBean.getSuccesSalonCreation();
	}

	public void supprimerSalon(String id)  {
		try {
			Salon salon = salonService.obtenirSalonParId(id);
			if (salon == null) {
				return;
			}
			System.out.println("SUPPRIMER : " + salon.getNom());
			this.salonService.supprimerSalon(salon);
			
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			return ;
		}
		try {
			rafraichirListe();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void rafraichirListe() {
		try {
			List<Salon> salons = this.salonService
					.obtenirSalonsCreesParUtilisateur(currentUserBean.getCurrentAccount());
			this.currentUserSalons.addAll(salons);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String rejoindreSalon(String id) throws Exception {
		Salon salon = null;
		try {
			salon = salonService.obtenirSalonParId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return navigationBean.getManageSalon();
		}

		profileBean.setSalon(salon);
		profileBean.setCompte(currentUserBean.getCurrentAccount());
		this.currentSalonBean.setThisSalon(salon);
		return navigationBean.getCreateProfile();
	}

	public String gererSalon(String id) {
		Salon salon = null;
		try {
			salon = salonService.obtenirSalonParId(id);
			manageSalonBean.setSalonToManage(salon);
			return navigationBean.getManageCurrentSalon();
		} catch (Exception e) {
			e.printStackTrace();
			return navigationBean.getManageSalon();
		}

	}

}
