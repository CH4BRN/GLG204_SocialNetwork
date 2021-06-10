package edu.bd.myProject.compte.entity;

import java.util.Date;
import java.util.List;

import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.profiles.entity.Profile;

public interface Compte {

	public List<Profile> getCreatedProfiles();

	public void setCreatedProfiles(List<Profile> profiles);

	public List<Invitation> getInvitationsExpedies();

	public void setInvitationsExpedies(List<Invitation> invitations);

	public List<Invitation> getInvitationsRecues();

	public void setInvitationsRecues(List<Invitation> invitations);

	public Boolean getIsConnecte();

	public void setIsCOnnecte(Boolean isConnecte);

	@Override
	String toString();

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
	 * 
	 * @return
	 */
	Boolean getIsAdmin();

	/**
	 * Deginir le compte en tant qu'admin
	 * 
	 * @param isAdmin
	 */
	void setIsAdmin(Boolean isAdmin);

}
