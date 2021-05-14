/**
 * 
 */
package edu.bd.inCognito.mail.entity;

import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.framework.persistence.entity.InCognitoEntity;

/**
 * Interface pour l'entity bean "mail"
 * 
 * @author Brique DECKARD
 *
 */
public interface Mail extends InCognitoEntity<String> {

	/**
	 * Obtenir le destinataire.
	 * 
	 * @return
	 */
	Compte getDestinataire();

	/**
	 * Definir le destinataire.
	 * 
	 * @param destinataire
	 */
	void setDestinataire(Compte destinataire);

	/**
	 * Obtenir le message
	 * 
	 * @return
	 */
	String getMessage();

	/**
	 * Definir le message.
	 * 
	 * @param message
	 */
	void setMessage(String message);

}
