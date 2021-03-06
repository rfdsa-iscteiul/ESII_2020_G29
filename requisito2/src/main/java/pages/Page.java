package pages;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.AnalyticsFile;
import Utils.Email;


/**
 * @author anamartacontente
 *
 */
public class Page {
	
	  protected  WebDriver driver;
	  protected String url;
	  protected String pageTitle;
	  private String JsonKeyName;
	  protected AnalyticsFile jsonFile;

	
	  /**
	 * @param url of the page being tested
	 * @param pageTitle the title of the page being tested
	 * @param JsonKeyName the the key that represents the page on the json file that has the analytics
	 * @param jsonFile object that deals with analytics
	 */
	public Page( String url, String pageTitle, String JsonKeyName, AnalyticsFile jsonFile){

		
	    this.url = url;
	    this.pageTitle=pageTitle;
	    this.JsonKeyName= JsonKeyName;
	    this.jsonFile = jsonFile;
	    
	    
	  }
	/**
	 * Sets Up driver for testing 
	 */  
	public void setUp() throws Exception {
		  System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		  driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  
	  }
	
	/**
	 * Tests If page is available  it will send an email otherwise
	 */
	  
	  public void testPage() {
		  
	
		  driver.get(url);
		  
		  if( driver.getTitle().contentEquals( pageTitle)    ) {
			  System.out.println("Success");
			  jsonFile.alterJson(true, JsonKeyName);
		  }else {
			  System.out.println( driver.getTitle() );
			  System.out.println("Failed");
			  jsonFile.alterJson(false, JsonKeyName);
			  Email.sendEmail("Page down", pageTitle + " is unavailable");

		  } 
			  
	
			  
		  
	  }
	  
	  
	  /**
		 * Sets Up the test and tests it
		 */
	  public void test() {
		  try {
			setUp();
			testPage();
		} catch (Exception e) {
			jsonFile.alterJson(false, JsonKeyName);
			Email.sendEmail("Page down", pageTitle + " is unavailable");
		}finally{
			driver.close();
			
		}
		  
		  
	  }
	  
	  
	  
	  
	 /**
	 * Waits for the page to load
	 */
	public void waitForLoad() {
	        ExpectedCondition<Boolean> pageLoadCondition = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                    }
	                };
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(pageLoadCondition);
	    }

			
}



