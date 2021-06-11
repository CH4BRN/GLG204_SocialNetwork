// SalonServiceImpl.java - Copyright pierr
package edu.bd.myProject.salons.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.invitation.dao.InvitationDao;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.invitation.service.InvitationService;
import edu.bd.myProject.post.dao.PostsDao;
import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.post.service.PostService;
import edu.bd.myProject.profiles.dao.ProfileDao;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.salons.dao.SalonDao;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.service.SalonService;

/**
 * @author pierr
 *
 */
@Stateless
public class SalonServiceImpl implements SalonService {

	@Inject
	SalonDao salonDao;

	@Inject
	ProfileDao profileDao;

	@Inject
	InvitationDao invitationDao;

	@Inject
	PostsDao postDao;

	@Inject
	PostService postService;

	@Inject
	CompteDao compteDao;

	@Inject
	InvitationService invitationService;

	/**
	 * @throws InCognitoDaoException
	 * @see edu.bd.myProject.salons.service.SalonService#creerSalon(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public Salon creerSalon(String name, Compte createur, List<String> emails, Boolean isPersistant) throws Exception {
		try {
			Salon salon = this.salonDao.obtenirNouvelleEntite();
			salon.setNom(name);
			salon.setCreateur(createur);
			salon.setPersitant(isPersistant);
			this.salonDao.inserer(salon);

			for (String string : emails) {
				try {
					Compte destinataire = compteDao.obtenirParEmail(string);
					if (destinataire != null) {
						this.invitationService.insererInvitation(createur, destinataire, salon);
					}

				} catch (InCognitoDaoException e) {
					e.printStackTrace();
				}
			}

			return salon;
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			throw new Exception("erreur creation", e);
		}
	}

	@Override
	public List<Salon> obtenirSalonsCreesParUtilisateur(Compte utilisateur) throws InCognitoDaoException {
		try {
			List<Salon> salons = this.salonDao.obtenirSalonsParCreateur(utilisateur);
			return salons;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Salon> obtenirSalonsAuxquelsUtilisateurParticipe(Compte utilisateur) throws InCognitoDaoException {
		try {
			List<Salon> salons = this.salonDao.obtenirSalonsParticipe(utilisateur);
			return salons;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Salon supprimerSalon(Salon salon) throws InCognitoDaoException {
		try {
			List<Post> posts = this.postDao.obtenirPourUnSalon(salon);
			if (posts != null) {
				for (Post post : posts) {
					this.postDao.supprimer(post);
				}
			}

			List<Profile> profiles = this.profileDao.obtenirPourUnSalon(salon);
			if (profiles != null) {
				for (Profile profile : profiles) {
					this.profileDao.supprimer(profile);
				}
			}

			List<Invitation> invitations = this.invitationDao.obtenirTousPourUnSalon(salon);
			if (invitations != null) {
				for (Invitation invitation : invitations) {
					this.invitationDao.supprimer(invitation);
				}
			}

			salonDao.supprimer(salon);
			return salon;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Salon obtenirSalonParId(String id) throws InCognitoDaoException {
		try {
			Salon salon = salonDao.obtenir(id);
			return salon;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public Salon obtenirSalonParNom(String nom) throws Exception {
		try {
			Salon salon = salonDao.obtenirParNom(nom);
			return salon;
		} catch (Exception e) {
			return null;
		}
	}

}
