// CompteServiceImpl.java - Copyright pierr
package edu.bd.myProject.compte.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.post.service.PostService;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.service.ProfileService;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.service.SalonService;

/**
 * @author pierr
 *
 */
@Stateless
public class CompteServiceImpl implements CompteService {

	@Inject
	CompteDao comptesDao;

	@Inject
	SalonService salonService;

	@Inject
	PostService postService;

	@Inject
	ProfileService profileService;

	/**
	 * @throws Exception
	 * @see edu.bd.myProject.compte.service.CompteService#creerCompte(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.Boolean, java.util.Date,
	 *      java.lang.Boolean)
	 */
	@Override
	public Compte creerCompte(String login, String email, String motDePasse, Boolean isActif, Date dateCreation,
			Boolean isAdmin) throws Exception {

		checkIfLoginExists(login);
		checkIfEmailExists(email);
		try {
			return comptesDao.inserer(compteFactory(login, email, motDePasse, isActif, dateCreation, isAdmin));
		} catch (InCognitoDaoException e) {
			return null;
		}

	}

	private void checkIfEmailExists(String email) throws InCognitoDaoException, Exception {
		Compte tempCompte = comptesDao.obtenirParEmail(email);
		if (tempCompte != null) {
			throw new Exception("EMAIL_EXISTS");
		}
	}

	private void checkIfLoginExists(String login) throws InCognitoDaoException, Exception {
		Compte tempCompte = comptesDao.obtenirParLogin(login);

		if (tempCompte != null) {
			throw new Exception("LOGIN_EXISTS");
		}
	}

	private Compte compteFactory(String login, String email, String motDePasse, Boolean isActif, Date dateCreation,
			Boolean isAdmin) {
		Compte compte;
		try {
			compte = comptesDao.obtenirNouvelleEntite();
			compte.setDateCreation(dateCreation);
			compte.setEmail(email);
			compte.setIsActif(isActif);
			compte.setIsAdmin(isAdmin);
			compte.setLogin(login);
			compte.setMotDePasse(motDePasse);
			return compte;
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @see edu.bd.myProject.compte.service.CompteService#obtenirTousLesComptes()
	 */
	@Override
	public List<Compte> obtenirTousLesComptes() {
		try {
			return this.comptesDao.obtenirTous();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @throws Exception
	 * @see edu.bd.myProject.compte.service.CompteService#supprimerCompte(edu.bd.myProject.compte.entity.Compte)
	 */
	@Override
	public Compte supprimerCompte(Compte compte) throws Exception {
		List<Post> posts;

		List<Profile> profiles = profileService.obtenirPourUnCompte(compte);
		for (Profile profile : profiles) {
			posts = postService.obtenirPourUnProfil(profile.getId());
			for (Post post : posts) {
				postService.supprimer(post);
			}
			profileService.supprimer(profile);
		}

		List<Salon> salons;
		try {
			salons = salonService.obtenirSalonsCreesParUtilisateur(compte);
			for (Salon salon : salons) {
				salonService.supprimerSalon(salon);
			}

		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			return null;
		}
		compte = comptesDao.supprimer(compte);

		return compte;
	}

	@Override
	public Compte obtenirUnCompte(String id) {
		try {
			return comptesDao.obtenir(id);
		} catch (InCognitoDaoException e) {
			return null;
		}
	}

	/**
	 * @see edu.bd.myProject.compte.service.CompteService#obtenirCompteParEmail(java.lang.String)
	 */
	@Override
	public Compte obtenirCompteParEmail(String email) {
		try {
			return comptesDao.obtenirParEmail(email);
		} catch (InCognitoDaoException e) {
			return null;
		}
	}

	@Override
	public Compte obtenirCompteParLogin(String login) {
		try {
			return comptesDao.obtenirParLogin(login);
		} catch (InCognitoDaoException e) {
			return null;
		}
	}

	@Override
	public Compte mettreAJourCompte(Compte compte) {
		try {
			return comptesDao.modifier(compte);
		} catch (Exception e) {
			return null;
		}
	}

}
