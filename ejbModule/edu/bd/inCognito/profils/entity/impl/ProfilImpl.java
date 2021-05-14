/**
 * 
 */
package edu.bd.inCognito.profils.entity.impl;

import edu.bd.inCognito.profils.entity.Profil;

/**
 * @author Brique DECKARD
 *
 */
public class ProfilImpl implements Profil {

	private String identifier;
	private String pseudo;

	@Override
	public String getId() {
		return this.identifier;
	}

	@Override
	public void setId(String id) {
		this.identifier = id;

	}

	@Override
	public String getPseudo() {
		return this.pseudo;
	}

	@Override
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

}
