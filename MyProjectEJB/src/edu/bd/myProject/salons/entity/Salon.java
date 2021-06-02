package edu.bd.myProject.salons.entity;

import java.util.Date;

import edu.bd.myProject.compte.entity.Compte;

public interface Salon {
	public String getNom();

	public void setNom(String nom);

	public String getId();

	public void setId(String id);

	public Date getDateCreation();

	public void setDateCreation(Date date);

	public Compte getCreateur();

	public void setCreateur(Compte compte);

	public String toString();

}
