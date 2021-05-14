/**
 * 
 */
package edu.bd.inCognito.comptes.entity;

import java.util.Date;

import edu.bd.inCognito.framework.persistence.entity.InCognitoEntity;

/**
 * Interface pour l'entité Compte.
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
	 * Définir le login.
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
	 * Définit l'meail
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
	 * Définit le mot de passe.
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
	 * Définit une valeur indiquant si le compte est actif ou inactif.
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
	 * Définit la date de crétaion du compte
	 * 
	 * @param dateCreation
	 */
	void setDateCreation(Date dateCreation);

}
