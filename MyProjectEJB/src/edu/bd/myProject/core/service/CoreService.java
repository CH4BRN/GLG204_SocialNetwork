package edu.bd.myProject.core.service;

import javax.ejb.Local;

import edu.bd.myProject.admin.service.AdminService;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.user.service.UserService;

@Local
public interface CoreService {

	public String getDashboard();

	public Compte getUser();

	public void setUser(Compte user);

}
