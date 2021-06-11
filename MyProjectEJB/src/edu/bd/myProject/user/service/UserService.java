package edu.bd.myProject.user.service;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;

@Local
public interface UserService {

	public void supprimerSonCompte(Compte compte) throws Exception;

}
