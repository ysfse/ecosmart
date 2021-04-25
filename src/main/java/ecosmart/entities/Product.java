package ecosmart.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Data;

 @Entity 
// @Data
 @Builder
 @NoArgsConstructor @AllArgsConstructor
 @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="product_id")

public class Product  implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long product_id;

private String libelle_product;

private String image_url;

private Float price_product;

private boolean is_in_stock;

private Float qte_stock;        // quantity of the product in stock

//private int quantity = 1;

//@JsonBackReference
@ManyToOne @JoinColumn(name="magasin_id", nullable=false)
@JsonIdentityReference(alwaysAsId = true)
@JsonIgnore
private Magasin magasin;



//@JsonBackReference
@ManyToOne @JoinColumn(name="category_id", nullable=false)
@JsonIdentityReference(alwaysAsId = true)
//@JsonIgnore
private Category category;


@ManyToMany(mappedBy ="products")
private List<Basket> baskets = new ArrayList<>();         //the product could be in many carts

@OneToMany(targetEntity= CommandLine.class, mappedBy="product")
@JsonIgnore
private List<CommandLine> lines = new ArrayList<>();       // the product could be in many lines of commands

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "carbonBalance_id", referencedColumnName = "carbonBalance_id")
@JsonIdentityReference(alwaysAsId = true)
private CarbonBalance carbonBalance;                       // Information about the carbon emission of this product


public Long getProduct_id() {
	return product_id;
}


public void setProduct_id(Long product_id) {
	this.product_id = product_id;
}


public String getLibelle_product() {
	return libelle_product;
}


public void setLibelle_product(String libelle_product) {
	this.libelle_product = libelle_product;
}



public Float getPrice_product() {
	return price_product;
}


public String getImage_url() {
	return image_url;
}


public void setImage_url(String image_url) {
	this.image_url = image_url;
}


public void setPrice_product(Float price_product) {
	this.price_product = price_product;
}


public boolean Is_in_stock() {
	return is_in_stock;
}


public void setIs_in_stock(boolean is_in_stock) {
	this.is_in_stock = is_in_stock;
}


public Float getQte_stock() {
	return qte_stock;
}


public void setQte_stock(Float qte_stock) {
	this.qte_stock = qte_stock;
}


public Magasin getMagasin() {
	return magasin;
}


public void setMagasin(Magasin magasin) {
	this.magasin = magasin;
}


public Category getCategory() {
	return category;
}


public void setCategory(Category category) {
	this.category = category;
}


public List<Basket> getBaskets() {
	return baskets;
}


public void setBaskets(List<Basket> baskets) {
	this.baskets = baskets;
}


public List<CommandLine> getLines() {
	return lines;
}


public void setLines(List<CommandLine> lines) {
	this.lines = lines;
}


public CarbonBalance getCarbonBalance() {
	return carbonBalance;
}


public void setCarbonBalance(CarbonBalance carbonBalance) {
	this.carbonBalance = carbonBalance;
}


public boolean isIs_in_stock() {
	return is_in_stock;
}


@Override
public String toString() {
	return product_id + " " + libelle_product + " "
			+ price_product ;
}









}
