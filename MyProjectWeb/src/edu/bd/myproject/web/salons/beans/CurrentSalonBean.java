package edu.bd.myproject.web.salons.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.core.service.CoreService;
import edu.bd.myProject.core.service.ServiceU;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.post.service.PostService;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.service.ProfileService;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.service.SalonService;
import edu.bd.myproject.web.navigation.beans.NavigationBean;

@Named("currentSalonBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class CurrentSalonBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	@ServiceU
	CoreService coreService;

	@Inject
	private ProfileService profileService;

	@Inject
	private PostService postService;

	@Named
	@Inject
	private NavigationBean navigationBean;

	@Inject
	private SalonService salonService;

	private Salon thisSalon;

	private ArrayList<Profile> currentProfiles;

	private ArrayList<Profile> registeredProfiles;

	private Profile yourProfile;

	private ArrayList<Post> postedContent;

	public String rejoindreSalon() {
		System.out.println("REJOINDRE : " + thisSalon.getNom());

		if (currentProfiles == null) {
			System.out.println("# NO CURRENT PROFILES");
		} else {
			System.out.println("# PROFILES COURANTS : ");
			for (Profile profile : currentProfiles) {
				System.out.println("Current : " + profile);
			}
		}

		try {
			if (this.getRegisteredProfiles() == null) {
				System.out.println("# NO REGISTERED PROFILES");
			} else {
				System.out.println("# PROFILES ENREGISTRES : ");
				for (Profile profile : this.getRegisteredProfiles()) {
					System.out.println("Registered : " + profile);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			if (this.getPostedContent() == null) {
				System.out.println("# NO POSTED CONTENT");
			} else {
				System.out.println("# POSTS : ");
				for (Post post : this.getPostedContent()) {
					System.out.println("Posted : " + post);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return navigationBean.getProfileCreation();

	}

	public void rafraichirPosts() throws Exception {
		this.postedContent = new ArrayList<Post>();
		if (thisSalon == null) {
			throw new Exception("ERREUR : this salon est nul");
		}
		try {
			List<Post> posts = postService.obtenirPourUnSalon(thisSalon);
			this.postedContent.addAll(posts);

			System.out.println("There are " + postedContent.size() + " posts for this Salon");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void rafraichirProfilCourants() throws Exception {
		this.currentProfiles = new ArrayList<Profile>();
		if (thisSalon == null) {
			throw new Exception("ERREUR : this salon est nul");
		}
		try {
			List<Profile> profiles = profileService.obtenirActifsPourUnSalon(thisSalon);
			if (profiles == null) {
				System.out.println("Pas de profiles actifs.");
				return;
			}
			this.currentProfiles.addAll(profiles);
			System.out.println("Il y a " + currentProfiles.size() + " profiles courants pour ce salon.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void rafraichirProfilEnregistres() throws Exception {
		System.out.println("Rafraichir profils enregistrés");
		this.registeredProfiles = new ArrayList<Profile>();
		if (thisSalon == null) {
			throw new Exception("ERREUR : this salon est nul");
		}
		try {
			List<Profile> profiles = profileService.obtenirActifsPourUnSalon(thisSalon);
			if (profiles == null) {
				System.out.println("Pas de profiles enregistrés");
				return;
			} else {
				this.registeredProfiles.addAll(profiles);
				System.out.println("Il y a " + registeredProfiles.size() + " profiles enregistrés pour ce salon.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Post> getPostedContent() throws Exception {
		rafraichirPosts();
		return this.postedContent;

	}

	public void setPostedContent(ArrayList<Post> postedContent) {
		this.postedContent = postedContent;
	}

	public ArrayList<Profile> getRegisteredProfiles() throws Exception {
		rafraichirProfilEnregistres();
		return registeredProfiles;
	}

	public void setRegisteredProfiles(ArrayList<Profile> registeredProfiles) {
		this.registeredProfiles = registeredProfiles;
	}

	public ArrayList<Profile> getCurrentProfiles() throws Exception {
		rafraichirProfilCourants();
		return currentProfiles;
	}

	public void setCurrentProfiles(ArrayList<Profile> currentProfiles) {
		this.currentProfiles = currentProfiles;
	}

	public Salon getThisSalon() {
		return thisSalon;
	}

	public void setThisSalon(Salon thisSalon) {
		List<Profile> profiles = this.profileService.obtenirPourUnSalon(thisSalon);
		this.registeredProfiles = new ArrayList<Profile>(profiles);
		this.thisSalon = thisSalon;
	}

	public Profile getYourProfile() {
		return yourProfile;
	}

	public void setYourProfile(Profile yourProfile) {
		this.yourProfile = yourProfile;
	}

	public void quit() {

		try {
			this.profileService.passerHorsLigne(yourProfile);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!this.thisSalon.getPersistant()) {
			List<Profile> profiles = this.profileService.obtenirActifsPourUnSalon(thisSalon);
			if (profiles == null) {
				try {
					salonService.supprimerSalon(thisSalon);
				} catch (InCognitoDaoException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public String modifierSonProfil() {
		System.out.println("CURRENT : " + this.yourProfile.getPseudo());
		return navigationBean.getModifyProfile();
	}

	public void reset() {
		this.setYourProfile(null);
		this.setThisSalon(null);
	}

}
