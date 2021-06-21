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
	String urlRegex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	String youtubeRegex = "^(https?)?(://)?(www.)?((youtube.com)|(youtu.be))/watch\\?v=[a-zA-Z0-9_-]*";

	String extractYoutubeRegex = "^(https?)?(://)?(www.)?((youtube.com)|(youtu.be))/watch\\?v=[a-zA-Z0-9_-]*";

	public String getYoutubeUrl(String chaine) {
		Pattern pattern = Pattern.compile(extractYoutubeRegex);
		Matcher matcher = pattern.matcher(chaine);
		while (matcher.find()) {
			String youtubeUrl = matcher.group();
			youtubeUrl = youtubeUrl.replace("watch?v=", "v/");
			
			return youtubeUrl;
		}
		return null;
	}

	private boolean utiliserRegex(String chaine, String regex) {
		// Compile the ReGex
		Pattern p = Pattern.compile(regex);
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

	public boolean verifierUrlYoutube(String chaine) {
		return utiliserRegex(chaine, youtubeRegex);

	}

	public boolean verifierUrl(String chaine) {
		return utiliserRegex(chaine, urlRegex);

	}

}
