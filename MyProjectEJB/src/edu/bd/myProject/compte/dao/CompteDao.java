package edu.bd.myProject.compte.dao;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.IGenericDao;
import edu.bd.myProject.framework.dao.InCognitoDaoException;

/**
 * 
 * @author pierr
 *
 */
@Local
public interface CompteDao extends IGenericDao<Compte, String> {

	public Compte obtenirParLogin(String login) throws InCognitoDaoException;

	public Compte obtenirParEmail(String value) throws InCognitoDaoException;

}
