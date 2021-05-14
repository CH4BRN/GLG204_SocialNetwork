/**
 * 
 */
package edu.bd.inCognito.mail.service;

import javax.ejb.Local;

import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.mail.entity.Mail;

/**
 * Service pour la manipulation des entités {@link Mail}
 * 
 * @author Brique DECKARD
 *
 */
@Local
public interface MailService {
	/**
	 * Creation d'un mail
	 * 
	 * @param destinataire
	 * @param message
	 * @return
	 * @throws InCognitoException
	 */
	Mail envoyerMail(Compte destinataire, String message) throws InCognitoException;

}
