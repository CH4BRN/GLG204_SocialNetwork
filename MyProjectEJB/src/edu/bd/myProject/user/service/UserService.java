package edu.bd.myProject.user.service;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;

@Local
public interface UserService {

	public Compte getUser();

	public void setUser(Compte compte);

	public void seDeconnecter();

}
