package edu.bd.myProject.admin.service.impl;

import javax.ejb.Singleton;
import javax.inject.Inject;

import edu.bd.myProject.admin.service.AdminService;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;

@Singleton
public class AdminServiceImpl implements AdminService {

	Compte compte;

	// @Inject
	// CompteDao compteDao;

	@Inject
	CompteService compteService;

	@Override
	public void setAdmin(Compte compte) {
		this.compte = compte;

	}

	@Override
	public void seDeconnecter() {
		this.compte = null;
	}

	@Override
	public Compte getAdmin() {
		return this.compte;
	}

	@Override
	public Compte makeUserAdmin(String id) {
		Compte compte;
		compte = compteService.obtenirUnCompte(id);
		// compte = compteDao.obtenir(id);
		Boolean isAdmin = compte.getIsAdmin();
		compte.setIsAdmin(!isAdmin);
		compteService.mettreAJourCompte(compte);
		// compteDao.modifier(compte);
		return compte;

	}

	@Override
	public Compte makeUserActif(String id) {
		Compte compte = compteService.obtenirUnCompte(id);
		// compte = compteDao.obtenir(id);
		Boolean isActif = compte.getIsActif();
		compte.setIsActif(!isActif);
		// compteDao.modifier(compte);
		compteService.mettreAJourCompte(compte);
		return compte;

	}

	@Override
	public void supprimerUnCompteUtilisateur(String id) throws Exception {

		Compte compte = compteService.obtenirUnCompte(id);
		compteService.supprimerCompte(compte);

	}

}
