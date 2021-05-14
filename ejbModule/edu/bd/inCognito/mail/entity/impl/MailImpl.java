/**
 * 
 */
package edu.bd.inCognito.mail.entity.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.comptes.entity.impl.CompteImpl;
import edu.bd.inCognito.mail.entity.Mail;
import edu.bd.inCognito.mail.queries.MailQueries;

/**
 * Implémentation pour l'entity bean {@link Mail}
 * 
 * @author Brique DECKARD
 *
 */
@Entity
@Table(name = "mail")
@NamedQueries({ @NamedQuery(name = "all_mails", query = MailQueries.ALL_MAILS_QUERY) })
public class MailImpl implements Mail {

	private static final long serialVersionUID = -2705662492785259162L;

	@Id
	@GeneratedValue(generator = "uuid")
	private String identifier;

	@ManyToOne(targetEntity = CompteImpl.class)
	private Compte destinataire;

	private String message;

	@Override
	public String getId() {
		return this.identifier;
	}

	@Override
	public Compte getDestinataire() {
		return this.destinataire;
	}

	@Override
	public void setDestinataire(Compte destinataire) {
		this.destinataire = destinataire;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void setId(String id) {
		this.identifier = id;
	}

}
