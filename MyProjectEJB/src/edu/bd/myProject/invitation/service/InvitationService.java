package edu.bd.myProject.invitation.service;

import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.salons.entity.Salon;

@Local
public interface InvitationService {

	public List<Invitation> obtenirInvitationsPourCompte(Compte compte) throws Exception;

	public Invitation insererInvitation(Compte expediteur, Compte destinataire, Salon salon) throws Exception;

	public Invitation refuserInvitation(String id) throws Exception;

	public void supprimer(Invitation invitation) throws Exception;

	public List<Invitation> obtenirInvitationsPourUnSalon(Salon salon) throws Exception;

}
