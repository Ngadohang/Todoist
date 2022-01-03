package Todoist.Test;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class App {
	public static void main(String[] args) {

		ProductInfo productInfoAmazon, productInfoEbay;
		String nameWebsiteAmazon, nameProductAmazon, linkProductAmazon;
		float priceProductAmazon;
		String nameWebsiteEbay, nameProductEbay, linkProductEbay;
		float priceProductEbay;
		boolean status = false;
		int elements = 0;
		WebDriver driver;
		ArrayList<ProductInfo> prodList = new ArrayList<ProductInfo>();
		AbtractPage page = new AbtractPage();
		// amazon
		driver = page.getBrowserDriver("chrome", "https://www.amazon.com/");
		page.openPageUrl(driver, "https://www.amazon.com/");
		page.senkeyToElement(driver, "//input[@id='twotabsearchtextbox']", "iPhone 11");
		page.clickToElement(driver, "//span[@id='nav-search-submit-text']");
		elements = page.findElementsByXpath(driver, "//div[@data-asin]//h2/a/span").size();
		System.out.println(elements);
		for (int i = 1; i <= elements; i++) {
			page.refreshCurrentPage(driver);
			System.out.println(i);
			nameWebsiteAmazon = page.getNameWebsite(driver);
			System.out.println(nameWebsiteAmazon);
			nameProductAmazon = page.getNameProduct(driver, "(//div[@data-asin]//h2/a/span)[%s]", i);
			status = page.isProductIphone11(nameProductAmazon);
			System.out.println(nameProductAmazon);			
			priceProductAmazon = page.getPriceProduct(driver,
					"//*[text()='%s']//ancestor::*/following-sibling::*[@class='sg-row']//*[@class='a-price']//*[@class='a-price-whole']",
					nameProductAmazon);
			System.out.println(priceProductAmazon);
			linkProductAmazon = page.getLinkProduct(driver, "//*[text()='%s']/parent::a", nameProductAmazon);
			System.out.println(linkProductAmazon);
			if (status = true) {
				productInfoAmazon = new ProductInfo(nameWebsiteAmazon, nameProductAmazon, priceProductAmazon, linkProductAmazon);
				prodList.add(productInfoAmazon);
			}
		}
		driver.quit();
		Collections.sort(prodList);
		page.writeJsontotheFile(prodList,"products.json");

	}

}
