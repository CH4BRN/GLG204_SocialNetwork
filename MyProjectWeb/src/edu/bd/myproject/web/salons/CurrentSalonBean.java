package edu.bd.myproject.web.salons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.profiles.dao.ProfileDao;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.service.ProfileService;
import edu.bd.myProject.salons.entity.Salon;

@Named("currentSalonBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class CurrentSalonBean implements Serializable {

	@Inject
	ProfileService profileService;

	@Inject
	ProfileDao profileDao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Profile> currentProfiles;

	private ArrayList<Profile> registeredProfiles;

	public ArrayList<Profile> getRegisteredProfiles() {
		this.registeredProfiles = new ArrayList<Profile>();
		try {
			this.registeredProfiles.addAll(profileService.getProfilesForSalon(thisSalon));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registeredProfiles;
	}

	public void setRegisteredProfiles(ArrayList<Profile> registeredProfiles) {
		this.registeredProfiles = registeredProfiles;
	}

	public ArrayList<Profile> getCurrentProfiles() {
		return currentProfiles;
	}

	public void setCurrentProfiles(ArrayList<Profile> currentProfiles) {
		this.currentProfiles = currentProfiles;
	}

	private Salon thisSalon;

	public Salon getThisSalon() {
		return thisSalon;
	}

	public void setThisSalon(Salon thisSalon) {
		System.out.println("This salon : " + thisSalon.toString());
		try {
			List<Profile> profiles = this.profileDao.obtenirPourUnSalon(thisSalon.getId());
			this.registeredProfiles = new ArrayList<Profile>(profiles);

		} catch (InCognitoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.thisSalon = thisSalon;
	}

	private Profile yourProfile;

	public Profile getYourProfile() {
		return yourProfile;
	}

	public void setYourProfile(Profile yourProfile) {
		this.yourProfile = yourProfile;
	}

	public String quit() {
		return "manageSalon";
	}

}
