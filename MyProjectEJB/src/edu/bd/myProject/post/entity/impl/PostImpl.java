package edu.bd.myProject.post.entity.impl;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.entity.impl.ProfileImpl;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.entity.impl.SalonImpl;

@Entity
@Table(name = "post")
public class PostImpl implements Post {
	@Column(name = "titre")
	private String titre;

	@ManyToOne(targetEntity = SalonImpl.class, cascade = CascadeType.REMOVE)
	private Salon salon;

	@ManyToOne(targetEntity = ProfileImpl.class, cascade = CascadeType.REMOVE)
	private Profile profile;

	private String body;

	@Id()
	@GeneratedValue(generator = "uuid")
	private String id;

	private Date date = new Date();

	@Override
	public String getTitre() {
		return this.titre;
	}

	@Override
	public void setTitre(String titre) {
		this.titre = titre;

	}

	@Override
	public Salon getSalon() {
		return this.salon;
	}

	@Override
	public void setSalon(Salon salon) {
		this.salon = salon;

	}

	@Override
	public Profile getProfile() {
		return this.profile;
	}

	@Override
	public void setProfile(Profile profile) {
		this.profile = profile;

	}

	@Override
	public String getBody() {
		return this.body;
	}

	@Override
	public void setBody(String body) {
		this.body = body;

	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}

	@Override
	public Date getDate() {
		return this.date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;

	}

}
