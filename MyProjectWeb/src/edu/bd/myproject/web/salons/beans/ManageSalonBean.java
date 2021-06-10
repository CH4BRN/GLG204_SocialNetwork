package edu.bd.myproject.web.salons.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.profiles.dao.ProfileDao;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.salons.dao.SalonDao;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.service.SalonService;
import edu.bd.myproject.web.navigation.beans.NavigationBean;

@Named("manageSalonBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class ManageSalonBean {

	private Salon salonToManage;

	@Named
	@Inject
	NavigationBean navigationBean;

	@Inject
	ProfileDao profileDao;

	@Inject
	SalonService salonService;

	@Inject
	SalonDao salonDao;

	private ArrayList<Profile> registeredProfiles;

	public Salon getSalonToManage() {
		return salonToManage;
	}

	public void setSalonToManage(Salon salonToManage) {
		this.salonToManage = salonToManage;
	}

	public ArrayList<Profile> getRegisteredProfiles() throws Exception {
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
