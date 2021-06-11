package edu.bd.myProject.core.service;

import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.invitation.entity.Invitation;

@Local
public interface CoreService {

	/**
	 * Cette méthode est commune aux deux services "core" et renvoie soit le chemin
	 * vers le tableau de bord utilisateur, soit celui vers le tableau de bord
	 * admin. A partir de là, la navigation se fait via le "navigation bean".
	 * 
	 * @return
	 */
	public String getDashboard();

	public Compte getUser() throws Exception;

	public void setUser(Compte user);

	public void supprimerSonCompte();

	public void refuserInvitation(String id) throws Exception;

	public List<Invitation> obtenirSesInvitations() throws Exception;

	public void seDeconnecter();

}
