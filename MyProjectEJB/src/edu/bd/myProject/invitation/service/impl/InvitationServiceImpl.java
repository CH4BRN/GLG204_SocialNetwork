// InvitationServiceImpl.java - Copyright pierr
package edu.bd.myProject.invitation.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.invitation.dao.InvitationDao;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.invitation.service.InvitationService;
import edu.bd.myProject.salons.entity.Salon;

/**
 * @author pierr
 *
 */
@Stateless
public class InvitationServiceImpl implements InvitationService {

	@Inject
	InvitationDao invitationDao;

	/**
	 * @throws Exception
	 * @see edu.bd.myProject.invitation.service.InvitationService#obtenirInvitationsPourCompte(java.lang.String)
	 */
	@Override
	public List<Invitation> obtenirInvitationsPourCompte(Compte compte) throws Exception {
		try {
			List<Invitation> invitations = invitationDao.obtenirTousPourUnCompte(compte);
			return invitations;
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			throw new Exception("Erreur obtention", e);
		}
	}

	@Override
	public Invitation insererInvitation(Compte expediteur, Compte destinataire, Salon salon) throws Exception {

		try {
			Invitation invitation = invitationDao.obtenirNouvelleEntite();
			invitation.setDate(new Date());
			invitation.setDestinataire(destinataire);
			invitation.setExpediteur(expediteur);
			invitation.setSalon(salon);
			invitation = invitationDao.inserer(invitation);
			return invitation;
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Invitation refuserInvitation(String id) throws Exception {
		try {
			Invitation invitation = invitationDao.obtenir(id);
			invitationDao.supprimer(invitation);
			return invitation;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void supprimer(Invitation invitation) throws Exception {
		try {
			invitationDao.supprimer(invitation);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public List<Invitation> obtenirInvitationsPourUnSalon(Salon salon) throws Exception {
		try {
			return invitationDao.obtenirTousPourUnSalon(salon);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
