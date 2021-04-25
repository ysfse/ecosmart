package ecosmart.entities;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="category_id")
 

  
public class Category  implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long category_id;
	 
	private String Libelle_category ;                                //Name of the category
	
	@JsonManagedReference
	@OneToMany(targetEntity= Product.class, mappedBy="category")
	@JsonIgnore
	private List<Product> produits = new ArrayList<>();                //contains the products of this category

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getLibelle_category() {
		return Libelle_category;
	}

	public void setLibelle_category(String libelle_category) {
		Libelle_category = libelle_category;
	}

	/*public List<Product> getProduits() {
		return produits;
	}*/

	public void setProduits(List<Product> produits) {
		this.produits = produits;
	}



	@Override
	public String toString() {
		return  category_id + " " + Libelle_category ;
	}

	
	
	
	

}
