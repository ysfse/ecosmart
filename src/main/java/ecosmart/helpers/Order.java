package ecosmart.helpers;

import java.util.List;

import ecosmart.entities.CommandLine;
import java.util.*;
public class Order {
	
String shippingAddress1;
	
	String shippingAddress2;
	
	String city;
	
	String ZipCode;
	
	//int qte_commande;
		
	String paymentMode;
	
	String country;
	
	//List<Long> procuctsIds;
	
	List<Map<String,Long>> products;


	/*public Map<String, Double> getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Map<String, Double> productQuantity) {
		this.productQuantity = productQuantity;
	}*/

	public List<Map<String, Long>> getProducts() {
		return products;
	}

	public void setProducts(List<Map<String, Long>> products) {
		this.products = products;
	}

	public String getShippingAddress1() {
		return shippingAddress1;
	}

	public void setShippingAddress1(String shippingAddress1) {
		this.shippingAddress1 = shippingAddress1;
	}

	public String getShippingAddress2() {
		return shippingAddress2;
	}

	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}



	/*public List<Long> getProcuctsIds() {
		return procuctsIds;
	}

	public void setProcuctsIds(List<Long> procucts) {
		this.procuctsIds = procucts;
	}*/

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
