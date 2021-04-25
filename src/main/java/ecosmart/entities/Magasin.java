package ecosmart.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
//@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="magasin_id")

               // the shop/store where the products are

public class Magasin  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long magasin_id;
	
	private String nom_magasin;
	
	private String adress_magasin;
	
	@JsonIgnore
	@JsonManagedReference
	@OneToMany(targetEntity= Product.class, mappedBy="magasin")
	private List<Product> produits = new ArrayList<>();


	public Long getMagasin_id() {
		return magasin_id;
	}


	public void setMagasin_id(Long magasin_id) {
		this.magasin_id = magasin_id;
	}


	public String getNom_magasin() {
		return nom_magasin;
	}


	public void setNom_magasin(String nom_magasin) {
		this.nom_magasin = nom_magasin;
	}


	public String getAdress_magasin() {
		return adress_magasin;
	}


	public void setAdress_magasin(String adress_magasin) {
		this.adress_magasin = adress_magasin;
	}


	public List<Product> getProduits() {
		return produits;
	}


	public void setProduits(List<Product> produits) {
		this.produits = produits;
	}



	@Override
	public String toString() {
		return  magasin_id + " " + nom_magasin ;
	}
	
	
	
}
