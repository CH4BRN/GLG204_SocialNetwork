package edu.bd.myproject.web.salons;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.salons.dao.SalonDao;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.service.SalonService;
import edu.bd.myproject.web.profile.beans.ProfileBean;
import edu.bd.myproject.web.utilisateur.beans.CurrentUserBean;

@Named("salonBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class SalonBean {

	private ArrayList<Salon> salons;

	private ArrayList<Salon> currentUserSalons;

	public ArrayList<Salon> getCurrentUserSalons() {
		this.currentUserSalons = new ArrayList<Salon>();
		try {
			List<Salon> salons = this.salonService.obtenirSalonsPourUtilisateur(currentUserBean.getCurrentAccount());
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

	private String newSalonName;

	@Inject
	private SalonService salonService;

	public String getNewSalonName() {
		return newSalonName;
	}

	public void setNewSalonName(String newSalonName) {
		this.newSalonName = newSalonName;
	}

	public String creerSalon(List<String> emails, Compte createur) {

		for (String string : emails) {
			System.out.println("M : " + string);
		}

		try {
			System.out.println("SALON NAME : " + this.newSalonName);
			System.out.println("CREATEUR : " + createur.getLogin());

			this.salonService.creerSalon(this.newSalonName, createur, emails);
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
		}
		return "currentSalon";
	}

	public void supprimerSalon(String id) {
		System.out.println("SUPPRIMER");
		try {
			this.salonService.supprimerSalon(id);
			rafraichirListe();
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
		}

	}

	private void rafraichirListe() {
		try {
			List<Salon> salons = this.salonService.obtenirSalonsPourUtilisateur(currentUserBean.getCurrentAccount());
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

		} catch (InCognitoDaoException e) {
			e.printStackTrace();
		}
		return "createProfile";
	}

}
