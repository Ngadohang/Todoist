package Todoist.Test;

import java.util.ArrayList;
import java.util.Collections;

public class test {
	public static void main(String[] args) {
		boolean status = false;
		String nameProduct="Apple iPhone 11 [128GB, White] + Carrier Subscription [Cricket Wireless]";
		if(nameProduct.toLowerCase().contains("iphone 11")) {
			status= true;
		}
		ArrayList<ProductInfo> prodList = new ArrayList<ProductInfo>();
		ProductInfo product1 = new ProductInfo("a", "a", (float) 125, "a");
		ProductInfo product2 = new ProductInfo("b", "b", (float) 150, "c");
		ProductInfo product3 = new ProductInfo("d", "d", (float) 50, "d");
		ProductInfo product4 = new ProductInfo("e", "e", (float) 89, "e");
		prodList.add(product1);
		prodList.add(product2);
		prodList.add(product3);
		prodList.add(product4);
		Collections.sort(prodList);
		for (ProductInfo productInfo : prodList) {
			productInfo.printfPro(productInfo);			
		}
		
		String text="1,064";
		String [] value = null;
		String values = null;
		if(text.indexOf(",")>0) {
			value= text.split(",");
		}
		values= String.join("",value[0], value[1]);
		
		
		
	}

}
