package edu.bd.myProject.compte.dao;

import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;

/**
 * 
 * @author pierr
 *
 */
@Local
public interface CompteDao {

	public Compte inserer(Compte compte) throws InCognitoDaoException;

	public Compte obtenir(String id) throws InCognitoDaoException;

	public Compte obtenirParLogin(String login) throws InCognitoDaoException;

	public Compte obtenirNouvelleEntité();

	public Compte obtenirParEmail(String value) throws InCognitoDaoException;

	public List<Compte> obtenirTousLesComptes() throws InCognitoDaoException;

	public Compte modifier(Compte compte) throws InCognitoDaoException;

	public Compte supprimerCompte(Compte compte) throws InCognitoDaoException;

}
