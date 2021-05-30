package edu.bd.myProject.compte.entity;

import java.util.Date;

public interface Compte {

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

	/**
	 * getId
	 *
	 * TODO : Fill method utility
	 * 
	 * @return
	 */
	String getId();

	/**
	 * setId
	 *
	 * TODO : Fill method utility
	 * 
	 * @param id
	 */
	void setId(String id);

	/**
	 * Le compte est il admin ?
	 * @return
	 */
	Boolean getIsAdmin();

	/**
	 * Deginir le compte en tant qu'admin
	 * @param isAdmin
	 */
	void setIsAdmin(Boolean isAdmin);

}
