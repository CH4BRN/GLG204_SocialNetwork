/**
 * 
 */
package edu.bd.inCognito.profils.entity;

import edu.bd.inCognito.framework.persistence.entity.InCognitoEntity;

/**
 * Entité profil
 * 
 * @author Brique DECKARD
 *
 */
public interface Profil extends InCognitoEntity<String> {
	/**
	 * Obtenir le pseudo
	 * 
	 * @return
	 */
	public String getPseudo();

	/**
	 * Definir le pseudo
	 * 
	 * @param pseudo
	 */
	public void setPseudo(String pseudo);
}
