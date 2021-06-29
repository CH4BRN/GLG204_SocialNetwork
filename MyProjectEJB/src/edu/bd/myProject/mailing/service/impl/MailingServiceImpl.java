package edu.bd.myProject.mailing.service.impl;

import javax.ejb.Stateless;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.mailing.service.MailingService;

@Stateless
public class MailingServiceImpl implements MailingService {

	@Override
	public Boolean envoyerMail(String adresseDestinataire, Compte emetteur, String titre, String corps)
			throws Exception {
		//boolean result = false;
		return true;
		/*
		try {
			String SMTP = "smtp.gmail.com";
			String PORT = "587";

			final String userName = "erreipantoine@gmail.com";
			final String password = "J3_m3_conn3ct3";

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			});

			// Creer un objet "MimeMessage" par défaut
			MimeMessage message = new MimeMessage(session);

			// Email emetteur
			String from = emetteur.getEmail();
			// Definir le champ "from" de l'entête
			message.setFrom(new InternetAddress(from));

			// email destinataire
			String to = adresseDestinataire;

			InternetAddress[] internetAddresses = new InternetAddress[1];
			internetAddresses[0] = new InternetAddress(to);

			message.setRecipients(Message.RecipientType.TO, internetAddresses);

			// Définir le titre
			message.setSubject(titre);
			// Définir le message
			message.setText(corps);

			message.setSentDate(new Date());
			session.setDebug(true);
			// Envoyer le mail
			Transport.send(message);
			result = true;

		} catch (AddressException erreurAddresse) {
			erreurAddresse.printStackTrace();
			throw new Exception("Erreur mail", erreurAddresse);
		} catch (

		MessagingException erreurMessage) {
			erreurMessage.printStackTrace();
			throw new Exception("Erreur mail", erreurMessage);
		}
		return result;
		*/
	}

}
