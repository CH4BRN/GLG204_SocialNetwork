package edu.bd.myproject.web.dialog.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myproject.web.navigation.beans.NavigationBean;

@Named("dialogBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class DialogBean {

	@Named
	@Inject
	NavigationBean navigationBean;

	public String closeDialog() {
		return navigationBean.getSalonCreation();

	}

}
