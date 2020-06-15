package pages;

import org.openqa.selenium.WebDriver;

import Utils.AnalyticsFile;

public class WebSiteAnalytics extends Page{
	private final static String  URL =  "http://192.168.99.100/web-site-analytics/";
	private final static String PAGE_TITLE = "FAQ";
	private final static String JSONKEYNAME = "FAQ";

	


	  
	  public WebSiteAnalytics(AnalyticsFile jsonFile) {
			super( URL, PAGE_TITLE,JSONKEYNAME, jsonFile);
		
	}

}
