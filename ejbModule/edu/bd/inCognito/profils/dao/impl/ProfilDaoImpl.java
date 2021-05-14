/**
 * 
 */
package edu.bd.inCognito.profils.dao.impl;

import java.util.List;

import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.framework.persistence.dao.impl.GenericDaoImpl;
import edu.bd.inCognito.profils.dao.ProfilDao;
import edu.bd.inCognito.profils.entity.Profil;
import edu.bd.inCognito.profils.entity.impl.ProfilImpl;

/**
 * @author Brique DECKARD
 *
 */
public class ProfilDaoImpl extends GenericDaoImpl<Profil, String> implements ProfilDao {

	@Override
	public List<Profil> obtenirTous() throws InCognitoException {

		return null;
	}

	@Override
	public Profil obtenirNouvelleEntite() {
		// TODO Auto-generated method stub
		return new ProfilImpl();
	}

	@Override
	public Profil obtenir(String identifier) throws InCognitoException {
		// TODO Auto-generated method stub
		return null;
	}

}
