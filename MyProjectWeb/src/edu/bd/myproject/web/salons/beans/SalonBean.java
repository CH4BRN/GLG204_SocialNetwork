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

	private ArrayList<Salon> participatedSalons = new ArrayList<Salon>();

	private ArrayList<Salon> currentUserSalons = new ArrayList<Salon>();

	private ArrayList<Salon> allTheSalon = new ArrayList<Salon>();

	public ArrayList<Salon> getAllTheSalon() {
		allTheSalon = new ArrayList<Salon>();
		List<Salon> newSalons = this.salonService.obtenirTousLesSalons();
		if (newSalons != null) {
			allTheSalon.addAll(newSalons);
		}
		return allTheSalon;
	}

	public String supprimerCeSalon(Salon salon) {
		System.out.println("SUPPRIMER CE SALON " + salon.getNom());
		try {
			this.salonService.supprimerSalon(salon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rafraichirListe();
		return navigationBean.getSeeSalons();
	}

	public void setAllTheSalon(ArrayList<Salon> allTheSalon) {
		this.allTheSalon = allTheSalon;
	}

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

	private Boolean isPersistant;

	public void changePersistant() {
		this.isPersistant = !this.isPersistant;
		System.out.println("PERSISTANT");
	}

	public Boolean getIsPersistant() {
		return isPersistant;
	}

	public void setIsPersistant(Boolean isPersistant) {
		System.out.println("PERSISTANT");
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
			System.out.println("SALON NULL");

			creerSalon();

			return navigationBean.getSalonCreation();
		} else {
			System.out.println("SALON EXISTE");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Le salon existe déjà", null));
			return "";
		}
	}

	public String inviter() throws Exception {
		Salon salon = currentSalonBean.getThisSalon();
		System.out.println("Inviter pour le salon = " + salon.getNom());
		Compte createur = currentUserBean.getCurrentAccount();
		ArrayList<String> emails = invitSomeoneBean.getEmailList();

		Salon result = salonService.addEmailsToSalon(salon, emails, createur);
		if (result == null) {

		}
		return "";
	}

	public String creerSalon() throws Exception {

		Salon salon = null;
		Compte createur = currentUserBean.getCurrentAccount();
		String name = this.newSalonName;
		boolean persistant = isPersistant;

		salon = this.salonService.creerSalon(name, createur, new ArrayList<String>(), persistant);
		System.out.println("Salon créé : ".toUpperCase() + salon.toString());
		profileBean.setSalon(salon);
		currentSalonBean.setThisSalon(salon);
		profileBean.setCompte(createur);
		this.newSalonName = "";
		this.isPersistant = false;

		return navigationBean.getSuccesSalonCreation();
	}

	public void supprimerSalon(String id) {
		System.out.println("SUPPRIMER : " + id);
		try {
			Salon salon = salonService.obtenirSalonParId(id);
			if (salon == null) {
				return;
			}
			System.out.println("SUPPRIMER : " + salon.getNom());
			this.salonService.supprimerSalon(salon);

		} catch (Exception e) {
			e.printStackTrace();
			return;
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
			if (salons != null) {
				this.currentUserSalons.addAll(salons);
			}

			List<Salon> tousLesSalons = this.salonService.obtenirTousLesSalons();
			this.allTheSalon = new ArrayList<Salon>();
			if (tousLesSalons != null) {
				this.allTheSalon.addAll(tousLesSalons);
			}

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
			return navigationBean.getManageSalons();
		}

		profileBean.setSalon(salon);
		profileBean.setCompte(currentUserBean.getCurrentAccount());
		this.currentSalonBean.setThisSalon(salon);
		return navigationBean.getProfileCreation();
	}

	public String gererSalon(String id) {
		Salon salon = null;
		try {
			salon = salonService.obtenirSalonParId(id);
			manageSalonBean.setSalonToManage(salon);
			return navigationBean.getManageCurrentSalon();
		} catch (Exception e) {
			e.printStackTrace();
			return navigationBean.getManageSalons();
		}

	}

}
