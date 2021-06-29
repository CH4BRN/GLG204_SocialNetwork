package edu.bd.myproject.web.navigation.beans;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("tabMenuBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class TabMenuBean implements Serializable {

	@Named
	@Inject
	NavigationBean navigationBean;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@SuppressWarnings("unchecked")
	public String doAdminWork() {
		FacesContext fC = FacesContext.getCurrentInstance();
		@SuppressWarnings("unchecked")
		Map<String, String> params = (Map<String, String>) fC.getExternalContext().getRequestParameterMap();
		String param = ((java.util.Map<String, String>) params).get("i");
		System.out.println("Onglet : " + param);
		index = Integer.valueOf(param);
		switch (index) {
		case 0:
			System.out.println("DASHBOARD");
			return navigationBean.getAdminDashboard();

		case 1:
			System.out.println("ACCOUNT");
			return navigationBean.getSeeAccount();

		case 2:
			System.out.println("SALONS");
			return navigationBean.getSeeSalons();

		default:
			break;
		}
		return "";

	}

	@SuppressWarnings("unchecked")
	public String doSomeWork() {
		FacesContext fC = FacesContext.getCurrentInstance();
		@SuppressWarnings("unchecked")
		Map<String, String> params = (Map<String, String>) fC.getExternalContext().getRequestParameterMap();
		String param = ((java.util.Map<String, String>) params).get("i");
		System.out.println("Onglet : " + param);

		// Change the index that TabMenu refers as activated tab
		index = Integer.valueOf(param);

		switch (index) {
		case 0:
			return navigationBean.getUserDashboard();

		case 1:
			return navigationBean.getManageSalons();
		case 2:
			return navigationBean.getManageInvitations();
		case 3:
			return navigationBean.getManageAccount();

		default:
			break;
		}
		return "";
	}

}
