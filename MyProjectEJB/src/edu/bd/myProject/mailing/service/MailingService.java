package edu.bd.myProject.mailing.service;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;

@Local
public interface MailingService {

	public Boolean envoyerMail(String adresseDestinataire, Compte emetteur, String titre, String corps) throws Exception;

}
