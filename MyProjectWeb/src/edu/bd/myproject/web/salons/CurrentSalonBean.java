package edu.bd.myproject.web.salons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;

import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.post.dao.PostsDao;
import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.profiles.dao.ProfileDao;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.service.ProfileService;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myproject.web.navigation.beans.NavigationBean;

@Named("currentSalonBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class CurrentSalonBean implements Serializable {

	@Inject
	ProfileService profileService;

	@Inject
	ProfileDao profileDao;

	@Named
	@Inject
	NavigationBean navigationBean;

	@Inject
	PostsDao postDao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Profile> currentProfiles;

	private ArrayList<Profile> registeredProfiles;

	private ArrayList<Post> postedContent;

	public void rafraichirPosts() throws Exception {
		this.postedContent = new ArrayList<Post>();
		if (thisSalon == null) {
			throw new Exception("Erreur : this salon est nul");
		}
		try {
			List<Post> posts = postDao.obtenirPourUnSalon(thisSalon);
			this.postedContent.addAll(posts);

			System.out.println("There are " + postedContent.size() + " posts for this Salon");

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
		this.registeredProfiles = new ArrayList<Profile>();
		if (thisSalon == null) {
			throw new Exception("ERREUR : this salon est nul");
		}
		try {

			List<Profile> profiles = profileDao.obtenirPourUnSalon(thisSalon);

			this.registeredProfiles.addAll(profiles);
			System.out.println("There are " + registeredProfiles.size() + " profiles for this salon.");
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
			List<Profile> profiles = this.profileDao.obtenirPourUnSalon(thisSalon);
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

	public String modifierSonProfil() {
		System.out.println("CURRENT : " + this.yourProfile.getPseudo());
		return navigationBean.getModifyProfile();
	}

}
