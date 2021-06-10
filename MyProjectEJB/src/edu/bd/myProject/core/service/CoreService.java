package edu.bd.myProject.core.service;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;

@Local
public interface CoreService {

	public String getDashboard();

	public Compte getUser();

	public void setUser(Compte user);

}
