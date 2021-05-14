package edu.bd.inCognito.bootstrap;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import edu.bd.inCognito.comptes.dao.ComptesDao;
import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.comptes.service.ComptesService;
import edu.bd.inCognito.core.service.VisiteurService;
import edu.bd.inCognito.exceptions.InCognitoCompteException;
import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.exceptions.InCognitoMailException;
import edu.bd.inCognito.exceptions.InCognitoPasswordException;
import edu.bd.inCognito.mail.dao.MailDao;
import edu.bd.inCognito.mail.service.MailService;
import edu.bd.inCognito.utilisateur.service.UtilisateurService;

@Startup
@Singleton
public class InCognitoStartup {

	@PostConstruct
	private void init() {
		demo();
	}

	@Inject
	MailDao mailDao;

	@Inject
	MailService mailService;

	@Inject
	ComptesDao comptesDao;

	@Inject
	ComptesService comptesService;
	
	@Inject
	VisiteurService visiteurService;

	private void demo() {
		System.out.println("DEMO ! ");
		try {
			visiteurService.creerUnCompte("Pierre", "erreipantoine@gmail.com", "S3cr&-123");
			visiteurService.récupérerCompte("Pierre", "erreipantoine@gmail.com");
			UtilisateurService userService =  visiteurService.connexionUtilisateur("Pierre", "S3cr&-123");
			userService.gererLesInvitations();
			userService.consulterProfil("Pierre");
			userService.seDeconnecter();
			
			/*
			Compte compte = comptesService.creerCompte("Pierre", "erreipantoine@gmail.com", "S3cr&-123");
			Compte compte2 = comptesService.creerCompte("Gerard", "paul.bidou@gmail.com", "P1df@-321");
			mailService.envoyerMail(compte, "42");
			List<Compte> comptesInactifs = comptesService.obtenirComptesInactifs();
			for (Compte compte3 : comptesInactifs) {

				System.out.println("\nCompte : " + comptesService.obtenirCompteParLogin(compte3.getLogin()));
			}
			*/
		} catch (InCognitoException e) {
			if (e.getCause().getClass().getSimpleName().equals(InCognitoMailException.class.getSimpleName())) {
				System.out.println("MAIL INVALIDE");
			}
			if (e.getCause().getClass().getSimpleName().equals(InCognitoPasswordException.class.getSimpleName())) {
				System.out.println("MOT DE PASSE INVALIDE");
			}
			if ( e.getCause().getClass().getSimpleName().equals(InCognitoCompteException.class.getSimpleName())) {
				System.out.println("LE COMPTE N'EXISTE PAS.");
			}
		}

	}

}
