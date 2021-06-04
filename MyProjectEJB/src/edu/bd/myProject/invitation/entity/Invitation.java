package edu.bd.myProject.invitation.entity;

import java.util.Date;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.salons.entity.Salon;

public interface Invitation {

	public String getId();

	public void setId(String id);

	public Date getDate();

	public void setDate(Date date);

	Salon getSalon();

	void setSalon(Salon salon);

	Compte getExpediteur();

	void setExpediteur(Compte compte);

	Compte getDestinataire();

	void setDestinataire(Compte compte);

}
