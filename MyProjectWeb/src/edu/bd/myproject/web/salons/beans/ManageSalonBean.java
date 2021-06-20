package edu.bd.myproject.web.salons.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.invitation.service.InvitationService;
import edu.bd.myProject.post.dao.PostsDao;
import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.profiles.dao.ProfileDao;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.service.ProfileService;
import edu.bd.myProject.salons.dao.SalonDao;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.service.SalonService;
import edu.bd.myproject.web.navigation.beans.NavigationBean;
import edu.bd.myproject.web.utilisateur.beans.CurrentUserBean;

@Named("manageSalonBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class ManageSalonBean {

	private Salon salonToManage;

	@Named
	@Inject
	NavigationBean navigationBean;

	@Named
	@Inject
	EmailInvitBean emailInvitBean;

	@Named
	@Inject
	CurrentUserBean currentUserBean;

	@Inject
	ProfileDao profileDao;

	@Inject
	SalonService salonService;

	@Inject
	ProfileService profileService;

	@Inject
	InvitationService invitationService;

	@Inject
	SalonDao salonDao;

	@Inject
	PostsDao postDao;

	public String excludeAccount(String profileId) throws Exception {
		System.out.println("Exclure : " + profileId);

		try {
			this.profileService.exclureProfile(salonToManage, profileId);
			refreshRegisteredProfiles();

		} catch (Exception e) {
			throw e;
		}
		System.out.println("Exclue");
		return navigationBean.getManageCurrentSalon();

	}

	public String invitNewEmails() {
		System.out.println("INVIT ! ");
		try {
			this.salonService.addEmailsToSalon(this.salonToManage, emailInvitBean.getEmailsToAdd(),
					currentUserBean.getCurrentAccount());
			this.emailInvitBean.setEmailsToAdd(new ArrayList<String>());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	private ArrayList<Profile> registeredProfiles;

	public Salon getSalonToManage() {
		return salonToManage;
	}

	public void setSalonToManage(Salon salonToManage) {
		this.salonToManage = salonToManage;
	}

	private void refreshRegisteredProfiles() throws Exception {
		this.registeredProfiles = new ArrayList<Profile>();
		if (salonToManage == null) {
			throw new Exception("ERREUR : this salon est nul");
		}
		try {

			List<Profile> profiles = profileDao.obtenirPourUnSalon(salonToManage);

			this.registeredProfiles.addAll(profiles);
			System.out.println("There are " + registeredProfiles.size() + " profiles for this salon.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Profile> getRegisteredProfiles() throws Exception {
		refreshRegisteredProfiles();

		return registeredProfiles;
	}

	public void setRegisteredProfiles(ArrayList<Profile> registeredProfiles) {
		this.registeredProfiles = registeredProfiles;
	}

	public String modifierNom() {
		try {
			salonDao.modifier(salonToManage);
			return navigationBean.getManageCurrentSalon();
		} catch (Exception e) {
			return navigationBean.getManageSalon();
		}
	}

}
