package edu.bd.myProject.profiles.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.profiles.dao.ProfileDao;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.service.ProfileService;
import edu.bd.myProject.salons.dao.SalonDao;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.service.SalonService;

@Stateless
public class ProfileServiceImpl implements ProfileService {

	@Inject
	CompteService compteService;

	@Inject
	CompteDao compteDao;

	@Inject
	SalonService salonService;

	@Inject
	SalonDao salonDao;

	@Inject
	ProfileDao profileDao;

	@Override
	public Profile createProfile(String pseudo, Compte compte, Salon salon) throws Exception {

		try {
			Profile profile = profileDao.obtenirNouvelleEntite();
			profile.setPseudo(pseudo);
			profile.setUser(compte);
			profile.setSalon(salon);
			profileDao.inserer(profile);
			return profile;

		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			throw new Exception("Erreur creation profile", e);

		}
	}

	@Override
	public List<Profile> getProfilesForSalon(Salon salon) throws Exception {
		try {
			List<Profile> profiles = this.profileDao.obtenirPourUnSalon(salon);
			return profiles;
		} catch (Exception e) {
			throw new Exception("erreur obtention pour salon", e);
		}

	}

	@Override
	public Profile mettreAJour(Profile yourProfile, String newProfileName) {
		yourProfile.setPseudo(newProfileName);
		try {
			this.profileDao.modifier(yourProfile);
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
		}
		return yourProfile;
	}

}
