package edu.bd.myProject.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.invitation.dao.InvitationDao;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.post.service.PostService;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.service.ProfileService;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.service.SalonService;

@Startup
@Singleton
public class Bootstrap {

	@Inject
	CompteDao comptesDao;

	@Inject
	CompteService compteService;

	@Inject
	SalonService salonService;

	@Inject
	ProfileService profileService;

	@Inject
	InvitationDao invitationDao;

	@Inject
	PostService postService;

	@PostConstruct
	private void init() {

		try {
			initializeAdmin();
			initializeUser();
			initializeUserBrique();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initializeUserBrique() throws Exception {
		Compte compte = comptesDao.obtenirNouvelleEntite();
		compte.setEmail("erreipantoine@gmail.com");
		compte.setIsActif(true);
		compte.setLogin("Pierre");
		compte.setMotDePasse("SECRET");
		compte.setIsAdmin(false);
		try {
			comptesDao.inserer(compte);
		} catch (InCognitoDaoException e) {
			throw new Exception("Erreur insertion user", e);
		}

	}

	@SuppressWarnings("unused")
	private void demoPost() throws Exception {
		try {
			Compte compte = compteService.creerCompte("USR", "ee@ee.ee", "aaA", true, new Date(), true);

			Salon salon = salonService.creerSalon("SALON", compte, Arrays.asList("aa@aa.aa"), true);

			Profile profile = profileService.createProfile("BOLOGNAISE", compte, salon);

			postService.creerNouveauPost(salon, profile, "CHEVAL ?", "Bonjour ! ", null);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@SuppressWarnings("unused")
	private void demoInvitation() throws Exception {
		try {
			// Creer comptes destinataire
			Compte destinataire = compteService.creerCompte("LOGIN1", "a@a.a", "SECRET", true, new Date(), true);
			Compte expediteur = compteService.creerCompte("LOGIN2", "b@b.b", "SECRET", true, new Date(), true);

			ArrayList<Compte> comptes = new ArrayList<Compte>();
			for (int i = 0; i <= 4; i++) {
				comptes.add(comptesDao.obtenir("" + i));
			}

			destinataire = comptes.get(0);
			expediteur = comptes.get(1);

			Salon salon = salonService.creerSalon("JUDO", expediteur, Arrays.asList("poulet@poulet.poulet"), true);

			Invitation invitation = invitationDao.obtenirNouvelleEntite();
			invitation.setDate(new Date());
			invitation.setDestinataire(destinataire);
			invitation.setExpediteur(expediteur);
			invitation.setSalon(salon);
			invitationDao.inserer(invitation);

			List<Invitation> invitations = invitationDao.obtenirTousPourUnCompte(destinataire);
			for (Invitation invitation2 : invitations) {
				System.out.println("INVIT : " + invitation2.toString());
			}
		} catch (InCognitoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private void demoProfile() throws Exception {
		Compte compte;
		try {
			compte = compteService.creerCompte("COMPTE", "EMAIL@EMAIL.FR", "aaAA@@44", true, new Date(), true);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		Salon salon;
		try {
			salon = salonService.creerSalon("SALON", compte, new ArrayList<String>(Arrays.asList("ee@aa.aa")), true);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		try {
			Profile profile = profileService.createProfile("PSEUDO", compte, salon);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		List<Profile> gotProfile = profileService.getProfilesForSalon(salon);
		for (Profile profile : gotProfile) {
			System.out.println("profile " + profile.toString());
		}

		gotProfile = null;

	}

	private void initializeUser() throws Exception {
		Compte compte = comptesDao.obtenirNouvelleEntite();
		compte.setEmail("user1@mail.com");
		compte.setIsActif(true);
		compte.setLogin("USER1");
		compte.setMotDePasse("SECRET");
		compte.setIsAdmin(false);
		try {
			comptesDao.inserer(compte);
		} catch (InCognitoDaoException e) {
			throw new Exception("Erreur insertion user", e);
		}

		Compte compte2 = comptesDao.obtenirNouvelleEntite();
		compte2.setEmail("user2@mail.com");
		compte2.setIsActif(true);
		compte2.setLogin("USER2");
		compte2.setMotDePasse("SECRET");
		compte2.setIsAdmin(false);
		try {
			comptesDao.inserer(compte2);
		} catch (InCognitoDaoException e) {
			throw new Exception("Erreur insertion user", e);
		}

	}

	private void initializeAdmin() throws Exception {
		Compte compte = comptesDao.obtenirNouvelleEntite();
		compte.setEmail("admin@email.com");
		compte.setIsActif(true);
		compte.setLogin("ADMIN");
		compte.setMotDePasse("SECRET");
		compte.setIsAdmin(true);
		try {
			comptesDao.inserer(compte);
		} catch (InCognitoDaoException e) {
			throw new Exception("Erreur insertion admin", e);
		}
	}

}
