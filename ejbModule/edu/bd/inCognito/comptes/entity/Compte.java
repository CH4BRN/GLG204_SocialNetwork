/**
 * 
 */
package edu.bd.inCognito.comptes.entity;

import java.util.Date;

import edu.bd.inCognito.framework.persistence.entity.InCognitoEntity;

/**
 * Interface pour l'entit� Compte.
 * 
 * @author Brique DECKARD
 *
 */
public interface Compte extends InCognitoEntity<String> {

	/**
	 * Obtenir le login
	 * 
	 * @return
	 */
	String getLogin();

	/**
	 * D�finir le login.
	 * 
	 * @param login
	 */
	void setLogin(String login);

	/**
	 * Retourne l'email.
	 * 
	 * @return
	 */
	String getEmail();

	/**
	 * D�finit l'meail
	 * 
	 * @param email
	 */
	void setEmail(String email);

	/**
	 * Retourne le mot de passe.
	 * 
	 * @return
	 */
	String getMotDePasse();

	/**
	 * D�finit le mot de passe.
	 * 
	 * @param motDePasse
	 */
	void setMotDePasse(String motDePasse);

	/**
	 * Retourne une valeur indiquant si le compte est actif ou inactif.
	 * 
	 * @return
	 */
	Boolean getIsActif();

	/**
	 * D�finit une valeur indiquant si le compte est actif ou inactif.
	 * 
	 * @param actif
	 */
	void setIsActif(Boolean actif);

	/**
	 * Retourne la date de la creation du compte
	 * 
	 * @return
	 */
	Date getDateCreation();

	/**
	 * D�finit la date de cr�taion du compte
	 * 
	 * @param dateCreation
	 */
	void setDateCreation(Date dateCreation);

}
