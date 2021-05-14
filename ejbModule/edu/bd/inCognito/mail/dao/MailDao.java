package edu.bd.inCognito.mail.dao;

import javax.ejb.Local;

import edu.bd.inCognito.framework.persistence.dao.GenericDao;
import edu.bd.inCognito.mail.entity.Mail;

/**
 * Interface pour la maintenance des entités {@link Mail}
 * 
 * @author Brique DECKARD
 *
 */
@Local
public interface MailDao extends GenericDao<Mail, String> {

}
