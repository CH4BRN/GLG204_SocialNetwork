/**
 * 
 */
package edu.bd.inCognito.mail.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.mail.dao.MailDao;
import edu.bd.inCognito.mail.entity.Mail;
import edu.bd.inCognito.mail.service.MailService;

/**
 * Implémentation de {@link MailService}
 * 
 * @author Brique DECKARD
 *
 */
@Stateless
public class MailServiceImpl implements MailService {

	@Inject
	MailDao mailDao;

	@Override
	public Mail envoyerMail(Compte destinataire, String message) throws InCognitoException {
		Mail mail = mailDao.obtenirNouvelleEntite();
		mail.setDestinataire(destinataire);
		mail.setMessage(message);
		try {
			mailDao.creer(mail);
		} catch (InCognitoException e) {
			e.printStackTrace();
			throw e;

		}
		System.out.println("Mail sent to : " + mail.getDestinataire().getEmail());
		return mail;
	}

}
