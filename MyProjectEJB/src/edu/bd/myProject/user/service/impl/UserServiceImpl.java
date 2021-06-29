package edu.bd.myProject.user.service.impl;

import javax.ejb.Stateful;
import javax.inject.Inject;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;
import edu.bd.myProject.user.service.UserService;

@Stateful
public class UserServiceImpl implements UserService {

	@Inject
	CompteService compteService;

	@Override
	public void supprimerSonCompte(Compte compte) throws Exception {
		if (compte == null) {
			throw new Exception("Compte nul");
		}
		compteService.supprimerCompte(compte);
	}

}
