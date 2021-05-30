package edu.bd.myProject.authentication.service;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;

@Local
public interface AuthenticationService {

	public Compte authentifier(String login, String motDePasse) throws Exception;
}
