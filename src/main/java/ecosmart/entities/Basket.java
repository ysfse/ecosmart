package ecosmart.entities;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor @Builder
@Getter @Setter @NoArgsConstructor    @ToString

@Entity 
public class Basket {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long basket_id;
	
	@ManyToMany
	@JoinTable(
	  name = "product_basket", 
	  joinColumns = @JoinColumn(name = "basket_id"), 
	  inverseJoinColumns = @JoinColumn(name = "product_id"))
	
	 private List<Product> products = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_command", referencedColumnName = "id_command")
	private Command command ;
	
	
}
