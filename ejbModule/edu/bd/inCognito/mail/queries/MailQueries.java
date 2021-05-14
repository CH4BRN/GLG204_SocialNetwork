package edu.bd.inCognito.mail.queries;

import edu.bd.inCognito.mail.entity.Mail;

/**
 * Requêtes JPA pour l'entité {@link Mail}
 * 
 * @author Brique DECKARD
 *
 */
public abstract class MailQueries {

	/**
	 * Obtenir tous les mails
	 */
	public static final String ALL_MAILS_QUERY = "SELECT m FROM MailImpl m";

}
