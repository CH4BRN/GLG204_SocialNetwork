package edu.bd.myproject.web.invitation.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

@Named("invitSomeoneBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class InvitSomeoneBean {

	private String adresseEmail;

	public String getAdresseEmail() {

		return adresseEmail;
	}

	public void setAdresseEmail(String adresseEmail) {
		System.out.println(adresseEmail);
		this.adresseEmail = adresseEmail;
	}

	private ArrayList<String> emailList = new ArrayList<String>();

	public ArrayList<String> getEmailList() {
		emailList = new ArrayList<String>(new HashSet<String>(this.emailList));
		return emailList;
	}

	public void setEmailList(ArrayList<String> emailList) {
		this.emailList = emailList;
	}

	public void addEmail() {
		this.emailList.add(this.adresseEmail);
		System.out.println("EMAIL " + this.adresseEmail);
		this.adresseEmail = "";
	}

	public void deleteEmail(String email) {

		this.emailList.remove(email);
		System.out.println(email + " supprim�.");
	}
}
