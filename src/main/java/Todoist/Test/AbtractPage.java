package Todoist.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbtractPage {
	private WebDriver driver;
	
	//Initialization driver
	public WebDriver getBrowserDriver(String browserName,String url) {
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-infobars");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			driver= new ChromeDriver(options);
		}else {
			System.out.println("Only run test on Chrome");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MINUTES);
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	//action with selenium
	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	public void clickToElement(WebDriver driver, String locator) {
		sleepInMilisecond(500);
		driver.findElement(By.xpath(locator)).click();
	}
	
	public void senkeyToElement(WebDriver driver, String locator, String value) {
		waitForElementVisible(driver, locator);
		driver.findElement(By.xpath(locator)).clear();
		if (driver.toString().toUpperCase().contains("chrome") || driver.toString().toUpperCase().contains("edge")) {
			sleepInMilisecond(1000);
		}
		sleepInMilisecond(500);
		driver.findElement(By.xpath(locator)).sendKeys(value);
	}
	
	public void sleepInMilisecond(long milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// the result need to get by selenium
	public String getNameWebsite(WebDriver driver) {
		return driver.getTitle();
	}
	public String getNameProduct(WebDriver driver, String locator, int values) {
		String result="";
		try {
			result= driver.findElement(By.xpath(getDynamicLocator(locator, String.valueOf(values)))).getText();
		} catch (Exception e) {
			e.printStackTrace();
			result= "";
		}
		return result;
	}
	
	public float getPriceProduct(WebDriver driver, String locator, String values) {
		float price = 0;
		String priceText = "";
		String text = "";
		try {
			text = driver.findElement(By.xpath(getDynamicLocator(locator, values))).getText();			
			if (text.indexOf(",") > 0) {
				String [] value = text.split(",");
				priceText = priceText.join("", value[0], value[1]);
				price = Float.parseFloat(priceText);
			}
			price= Float.parseFloat(text);
		} catch (Exception ignore) {
			price=0;
		}
		return price;
	}
	 
//	public static void ignoringExc(RunnableExc r) {
//		  try { r.run(); } catch (Exception e) { }
//		}
//	@FunctionalInterface public interface RunnableExc { void run() throws Exception; }
	
	public String getLinkProduct(WebDriver driver, String locator,String values) {
		return  driver.findElement(By.xpath(getDynamicLocator(locator, values))).getAttribute("href");
		}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator));
	}
	
	public String getDynamicLocator(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}
	
	public String getCurrentPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	public void waitForElementPresence(WebDriver driver, String locator, String...values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getDynamicLocator(locator, values))));
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public boolean isProductIphone11(String nameProduct) {
		boolean status = false;
		if(nameProduct.toLowerCase().contains("iphone 11")) {
			status= true;
		}
		return status;
	}
	public void writeJsontotheFile(List<ProductInfo> products,String namefile) {
		JSONArray proList = new JSONArray();
		for (ProductInfo product : products) {
			JSONObject Pro = new JSONObject();
			Pro.put("nameWebsite",product.getNameWebsite());
			Pro.put("nameProduct",product.getNameProduct());
			Pro.put("priceProduct",product.getPriceProduct());
			Pro.put("linkProduct",product.getLinkProduct());
			String productString = Pro.toString();
			proList.add(productString);
		}
		 //Write JSON file
        try (FileWriter file = new FileWriter(namefile)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(proList.toJSONString()); 
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
