package edu.bd.myProject.authentication.service;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.core.service.CoreService;

@Local
public interface AuthenticationService {

	public CoreService authentifier(String login, String motDePasse) throws Exception;
}
