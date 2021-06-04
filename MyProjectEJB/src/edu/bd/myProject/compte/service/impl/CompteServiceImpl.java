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

/**
 * @author pierr
 *
 */
@Stateless
public class CompteServiceImpl implements CompteService {

	@Inject
	CompteDao comptesDao;

	/**
	 * @throws Exception
	 * @see edu.bd.myProject.compte.service.CompteService#creerCompte(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.Boolean, java.util.Date,
	 *      java.lang.Boolean)
	 */
	@Override
	public Compte creerCompte(String login, String email, String motDePasse, Boolean isActif, Date dateCreation,
			Boolean isAdmin) throws Exception {

		Compte compte = comptesDao.obtenirNouvelleEntité();
		compte.setDateCreation(dateCreation);
		compte.setEmail(email);
		compte.setIsActif(isActif);
		compte.setIsAdmin(isAdmin);
		compte.setLogin(login);
		compte.setMotDePasse(motDePasse);

		try {
			comptesDao.inserer(compte);
		} catch (InCognitoDaoException e) {
			throw new Exception("erreur creerCompte", e);
		}
		

		return compte;
	}

	@Override
	public List<Compte> obtenirTousLesComptes() {
		try {
			return this.comptesDao.obtenirTousLesComptes();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
