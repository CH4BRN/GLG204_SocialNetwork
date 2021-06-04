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

	private Compte user;

	@Override
	public Compte getUser() {
		return this.user;
	}

	@Override
	public void setUser(Compte compte) {
		this.user = compte;

	}

	@Override
	public void seDeconnecter() {
		this.user = null;
	}

	@Override
	public void supprimerSonCompte() throws Exception {
		if (user == null) {
			throw new Exception("Compte nul");
		}
		compteDao.supprimerCompte(user);

	}

}
