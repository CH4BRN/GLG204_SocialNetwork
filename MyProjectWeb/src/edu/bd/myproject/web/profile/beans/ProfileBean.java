package edu.bd.myproject.web.profile.beans;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import edu.bd.myProject.salons.entity.Salon;

@Named("profileBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class ProfileBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String newProfileName;

	private Salon salon;

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

	public void createProfileForSalon() {
		System.out.println("PSEUDO : " + newProfileName + " SALON : " + salon.toString());

	}
}
