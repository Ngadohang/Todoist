package Todoist.Test;

import java.util.Comparator;

public class ProductInfo implements  Comparable<ProductInfo> {
	private String nameWebsite;
	private String nameProduct;
	private Float priceProduct;
	private String linkProduct;
	
	public ProductInfo(String nameWebsite,String nameProduct,Float priceProduct,String linkProduct) {
		this.nameWebsite=nameWebsite;
		this.nameProduct=nameProduct;
		this.priceProduct=priceProduct;
		this.linkProduct=linkProduct;
	}
	
	
	
	public String getNameWebsite() {
		return nameWebsite;
	}



	public void setNameWebsite(String nameWebsite) {
		this.nameWebsite = nameWebsite;
	}



	public String getNameProduct() {
		return nameProduct;
	}



	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}


	public Float getPriceProduct() {
		return priceProduct;
	}

	public void setPriceProduct(Float priceProduct) {
		this.priceProduct = priceProduct;
	}


	public String getLinkProduct() {
		return linkProduct;
	}
	
	public void setLinkProduct(String linkProduct) {
		this.linkProduct = linkProduct;
	}



	public void printfPro(ProductInfo proInfo) {
		System.out.print(proInfo.nameWebsite + proInfo.nameProduct + proInfo.priceProduct + proInfo.linkProduct);
	}

	@Override
	public int compareTo(ProductInfo pro) {
		return this.getPriceProduct().compareTo(pro.getPriceProduct());
	}
	
//	Comparator<ProductInfo> compareByPrice = new Comparator<ProductInfo>() {
//		@Override
//		public int compare(ProductInfo o1, ProductInfo o2) {
//			// TODO Auto-generated method stub
//			return 0;
//		}
//	};
	
}
