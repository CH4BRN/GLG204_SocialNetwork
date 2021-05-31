package edu.bd.myProject.admin.service.impl;

import javax.ejb.Singleton;
import javax.inject.Inject;

import edu.bd.myProject.admin.service.AdminService;
import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;

@Singleton
public class AdminServiceImpl implements AdminService {

	Compte compte;

	@Inject
	CompteDao compteDao;

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
		try {
			compte = compteDao.obtenir(id);
			Boolean isAdmin = compte.getIsAdmin();
			compte.setIsAdmin(!isAdmin);
			compteDao.modifier(compte);
			return compte;
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Compte makeUserActif(String id) {
		Compte compte;
		try {
			compte = compteDao.obtenir(id);
			Boolean isActif = compte.getIsActif();
			compte.setIsActif(!isActif);
			compteDao.modifier(compte);
			return compte;
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void supprimer(String id) {
		try {
			Compte compte = compteDao.obtenir(id);
			compteDao.supprimerCompte(compte);
		} catch (InCognitoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
