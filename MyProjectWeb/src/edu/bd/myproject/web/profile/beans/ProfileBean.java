package edu.bd.myproject.web.profile.beans;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.service.ProfileService;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myproject.web.navigation.beans.NavigationBean;
import edu.bd.myproject.web.salons.CurrentSalonBean;
import edu.bd.myproject.web.utilisateur.beans.CurrentUserBean;

@Named("profileBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class ProfileBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String newProfileName;

	@Inject
	ProfileService profileService;

	@Named
	@Inject
	CurrentSalonBean currentSalonBean;

	@Named
	@Inject
	CurrentUserBean currentUserBean;

	@Named
	@Inject
	NavigationBean navigationBean;

	private Salon salon;

	private Compte compte;

	public Compte getCompte() {
		if (compte == null) {
			this.compte = currentUserBean.getCurrentAccount();
		}
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	public String getNewProfileName() {
		return newProfileName;
	}

	public void setNewProfileName(String newProfileName) {
		this.newProfileName = newProfileName;
	}

	public String createProfileForSalon() {
		System.out.println("\n\nCREATE PROFILE FOR SALON\n\n");
		try {
			Profile profile = profileService.createProfile(this.newProfileName, this.getCompte(), this.salon);
			System.out.println("PROFILE : " + profile.toString());
			currentSalonBean.setYourProfile(profile);
			currentSalonBean.setThisSalon(this.salon);

		} catch (Exception e) {
			e.printStackTrace();
			return "createProfile";
		}
		return "currentSalon";

	}

	public String modifier() {
		System.out.println("MODIFIER : " + this.newProfileName);
		Profile profil = this.profileService.mettreAJour(currentSalonBean.getYourProfile(), this.newProfileName);
		currentSalonBean.setYourProfile(profil);
		return navigationBean.getCurrentSalon();
	}
}
