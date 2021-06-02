package edu.bd.myProject.bootstrap;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;

@Startup
@Singleton
public class Bootstrap {

	@Inject
	CompteDao comptesDao;

	@PostConstruct
	private void init() {

		try {
			initializeAdmin();
			initializeUser();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initializeUser() throws Exception {
		Compte compte = comptesDao.obtenirNouvelleEntité();
		compte.setEmail("user@email.com");
		compte.setIsActif(true);
		compte.setLogin("USER");
		compte.setMotDePasse("SECRET");
		compte.setIsAdmin(false);
		try {
			comptesDao.inserer(compte);
		} catch (InCognitoDaoException e) {
			throw new Exception("Erreur insertion user", e);
		}

	}

	private void initializeAdmin() throws Exception {
		Compte compte = comptesDao.obtenirNouvelleEntité();
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
