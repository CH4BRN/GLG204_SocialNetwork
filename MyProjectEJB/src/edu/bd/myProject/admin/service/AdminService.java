package edu.bd.myProject.admin.service;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;

@Local
public interface AdminService {

	public void setAdmin(Compte compte);

	public void seDeconnecter();

	public Compte getAdmin();

	public Compte makeUserAdmin(String id);
	
	public Compte makeUserActif(String id);

	public void supprimer(String id);

}
