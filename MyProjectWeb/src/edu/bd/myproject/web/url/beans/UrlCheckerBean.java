package edu.bd.myproject.web.url.beans;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

@Named("urlCheckerBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class UrlCheckerBean {

	// Regex to check valid URL
	String regex = "((http|https)://)(www.)?" + "[a-zA-Z0-9@:%._\\+~#?&//=]" + "{2,256}\\.[a-z]"
			+ "{2,6}\\b([-a-zA-Z0-9@:%" + "._\\+~#?&//=]*)";

	public boolean verifierUrl(String chaine) {
		// Compile the ReGex
		Pattern p = Pattern.compile(regex);
		// If the string is empty
		// return false
		if (chaine == null) {
			return false;
		}

		// Find match between given string
		// and regular expression
		// using Pattern.matcher()
		Matcher m = p.matcher(chaine);

		// Return if the string
		// matched the ReGex
		return m.matches();

	}

}
