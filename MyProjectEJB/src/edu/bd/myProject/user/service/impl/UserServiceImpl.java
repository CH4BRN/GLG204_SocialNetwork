package edu.bd.myProject.user.service.impl;

import javax.ejb.Stateful;
import javax.inject.Inject;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.user.service.UserService;

@Stateful
public class UserServiceImpl implements UserService {

	@Inject
	CompteDao compteDao;

	@Override
	public void supprimerSonCompte(Compte compte) throws Exception {
		if (compte == null) {
			throw new Exception("Compte nul");
		}
		compteDao.supprimerCompte(compte);
	}

}
