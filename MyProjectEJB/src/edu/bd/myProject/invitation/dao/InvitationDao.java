package edu.bd.myProject.invitation.dao;

import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.IGenericDao;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.salons.entity.Salon;

@Local
public interface InvitationDao extends IGenericDao<Invitation, String> {

	public List<Invitation> obtenirTousPourUnCompte(Compte compte) throws InCognitoDaoException;

	public List<Invitation> obtenirTousPourUnSalon(Salon salon) throws InCognitoDaoException;

	public Invitation obtenirPourUnSalonEtUnCompte(Salon salon, Compte compte) throws InCognitoDaoException;
}
