package edu.bd.myProject.salons.entity;

import java.util.Date;
import java.util.List;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.profiles.entity.Profile;

/**
 * Entit� Salon
 * 
 * @author pierr
 *
 */
public interface Salon {

	/**
	 * Retourne les invitations li�es � ce salo
	 * 
	 * @return
	 */
	public List<Invitation> getInvitations();

	/**
	 * D�finiti les invitations li�es � ce salon.
	 * 
	 * @param invitations
	 */
	public void setInvitations(List<Invitation> invitations);

	/**
	 * Retourne le nom du salon.
	 * 
	 * @return
	 */
	public String getNom();

	/**
	 * D�finit le nom du salon.
	 * 
	 * @param nom
	 */
	public void setNom(String nom);

	/**
	 * retourne l'identififant du salon.
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * D�finit l'identifiant du salon.
	 * 
	 * @param id
	 */
	public void setId(String id);

	/**
	 * Retourne la date de creation du salon.
	 * 
	 * @return
	 */
	public Date getDateCreation();

	/**
	 * D�finit la date de creation du salon.
	 * 
	 * @param date
	 */
	public void setDateCreation(Date date);

	/**
	 * Retourne le createur du salon.
	 * 
	 * @return
	 */
	public Compte getCreateur();

	/**
	 * D�finit le createur du salon.
	 * 
	 * @param compte
	 */
	public void setCreateur(Compte compte);

	/**
	 * D�finit la valeur indiquant si le salon est persistant ou non.
	 * 
	 * @param persistant
	 */
	public void setPersitant(Boolean persistant);

	/**
	 * Retourne la valeur indiquant si le salon est persistant ou non.
	 * 
	 * @return
	 */
	public Boolean getPersistant();

	/**
	 * Retourne les profiles li�s au salon
	 * 
	 * @return
	 */
	public List<Profile> getProfiles();

	/**
	 * D�finit les profiles li�s au salon.
	 * 
	 * @param profiles
	 */
	public void setProfiles(List<Profile> profiles);

}
