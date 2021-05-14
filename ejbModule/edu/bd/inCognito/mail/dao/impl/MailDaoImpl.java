/**
 * 
 */
package edu.bd.inCognito.mail.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.framework.persistence.dao.impl.GenericDaoImpl;
import edu.bd.inCognito.mail.dao.MailDao;
import edu.bd.inCognito.mail.entity.Mail;
import edu.bd.inCognito.mail.entity.impl.MailImpl;

/**
 * Implémentation du {@link MailDao}
 * 
 * @author Brique DECKARD
 *
 */
@Stateless
public class MailDaoImpl extends GenericDaoImpl<Mail, String> implements MailDao {

	@Override
	public Mail obtenir(String identifier) throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " OBTENIR " + Mail.class.getSimpleName());
		if (identifier.isEmpty()) {
			throw new InCognitoException("L'identifiant du mail est nul.");
		}
		try {
			return this.getEm().find(MailImpl.class, identifier);
		} catch (Exception e) {
			throw new InCognitoException(e);
		}
	}

	@Override
	public List<Mail> obtenirTous() throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " OBTENIR_TOUS " + Mail.class.getSimpleName());
		Query query = this.getEm().createNamedQuery("all_mails", Mail.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new InCognitoException(e);
		}
	}

	@Override
	public Mail obtenirNouvelleEntite() {
		return new MailImpl();
	}

}
