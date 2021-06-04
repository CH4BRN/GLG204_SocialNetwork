package edu.bd.myproject.web.salons;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.invitation.service.InvitationService;
import edu.bd.myProject.salons.dao.SalonDao;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.service.SalonService;
import edu.bd.myproject.web.navigation.beans.NavigationBean;
import edu.bd.myproject.web.profile.beans.ProfileBean;
import edu.bd.myproject.web.utilisateur.beans.CurrentUserBean;

@Named("salonBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class SalonBean {

	private ArrayList<Salon> participatedSalons;

	public ArrayList<Salon> getParticipatedSalons() {
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

	private ArrayList<Salon> currentUserSalons;

	public ArrayList<Salon> getCurrentUserSalons() {
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

	@Named("currentUserBean")
	@Inject
	CurrentUserBean currentUserBean;

	@Named("profileBean")
	@Inject
	ProfileBean profileBean;
	
	@Named
	@Inject
	CurrentSalonBean currentSalonBean;

	@Named
	@Inject
	NavigationBean navigationBean;

	private String newSalonName;

	@Inject
	private SalonService salonService;

	@Inject
	private CompteService compteService;

	@Inject
	private CompteDao compteDao;

	@Inject
	private InvitationService invitationService;

	public String getNewSalonName() {
		return newSalonName;
	}

	public void setNewSalonName(String newSalonName) {
		this.newSalonName = newSalonName;
	}

	public String creerSalon(List<String> emails, Compte createur) throws Exception {

		Salon salon = null;

		try {
			System.out.println("SALON NAME : " + this.newSalonName);
			System.out.println("CREATEUR : " + createur.getLogin());

			salon = this.salonService.creerSalon(this.newSalonName, createur, emails);
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
		}

		profileBean.setSalon(salon);
		profileBean.setCompte(createur);

		for (String string : emails) {
			try {
				Compte compte = compteDao.obtenirParEmail(string);
				if (compte != null) {
					System.out.println("LOGIN " + compte.getLogin());
					this.invitationService.insererInvitation(currentUserBean.getCurrentAccount(), compte, salon);
				}

			} catch (InCognitoDaoException e) {
				e.printStackTrace();
			}
		}

		return navigationBean.getCreateProfile();
	}

	public void supprimerSalon(String id) throws InCognitoDaoException {
		System.out.println("SUPPRIMER");
		try {
			Salon salon = salonService.obtenirSalonParId(id);
			System.out.println("SUPPRIMER : " + salon.getNom());
			this.salonService.supprimerSalon(salon);
			rafraichirListe();
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			throw e;

		}

	}

	private void rafraichirListe() {
		try {
			List<Salon> salons = this.salonService
					.obtenirSalonsCreesParUtilisateur(currentUserBean.getCurrentAccount());
			this.currentUserSalons.addAll(salons);
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
		}
	}

	public String rejoindreSalon(String id) {
		try {
			Salon salon = salonService.obtenirSalonParId(id);
			profileBean.setSalon(salon);
			profileBean.setCompte(currentUserBean.getCurrentAccount());
			this.currentSalonBean.setThisSalon(salon);
			

		} catch (InCognitoDaoException e) {
			e.printStackTrace();
		}
		return "createProfile";
	}

}
