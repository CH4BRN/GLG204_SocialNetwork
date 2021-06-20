package edu.bd.myProject.profiles.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;
import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.invitation.dao.InvitationDao;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.post.dao.PostsDao;
import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.post.service.PostService;
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

	@Inject
	InvitationDao invitationDao;

	@Inject
	PostService postService;

	@Inject
	PostsDao postDao;

	@Override
	public Profile createProfile(String pseudo, Compte compte, Salon salon) throws Exception {
		try {
			Profile profile = profileDao.obtenirNouvelleEntite();
			profile.setPseudo(pseudo);
			profile.setUser(compte);
			profile.setSalon(salon);
			profile.setConnected(true);
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

	@Override
	public Profile mettreAJour(Profile newProfile) {
		try {
			this.profileDao.modifier(newProfile);
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
		}
		return newProfile;

	}

	@Override
	public Profile supprimer(Profile profile) throws Exception {
		List<Invitation> invitations = this.invitationDao.obtenirTousPourUnCompte(profile.getUser());
		for (Invitation invitation : invitations) {
			invitationDao.supprimer(invitation);
		}
		profile = this.profileDao.supprimer(profile);

		return profile;

	}

	@Override
	public Profile passerHorsLigne(Profile yourProfile) {
		try {
			yourProfile.setConnected(false);
			this.profileDao.modifier(yourProfile);
			return yourProfile;
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Profile> obtenirActifsPourUnSalon(Salon thisSalon) {
		try {
			return this.profileDao.obtenirActifsPourUnSalon(thisSalon);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Profile> obtenirPourUnSalon(Salon thisSalon) {
		try {
			return this.profileDao.obtenirPourUnSalon(thisSalon);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Profile obtenirPourUnCompteEtUnSalon(Compte currentAccount, Salon salon) {
		try {
			return this.profileDao.obtenirPourUnCompteEtUnSalon(currentAccount, salon);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Profile activerConnexion(Profile profile) throws Exception {
		try {
			profile.setConnected(true);
			profile = profileDao.modifier(profile);
			return profile;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<String> exclureProfile(Salon salon, String profileId) throws Exception {

		List<Post> posts = postDao.obtenirPourUnProfil(profileId);
		List<String> postId = new ArrayList<String>();
		for (Post post : posts) {
			postId.add(post.getId());
		}
		for (String id : postId) {
			System.out.println("ID : " + id);
			Post post = postDao.obtenir(id);
			System.out.println("POST : " + post.getBody());
			postDao.supprimer(post);
			System.out.println("DELETED");
		}
		profileDao.supprimer(profileDao.obtenir(profileId));
		return postId;

	}

}
