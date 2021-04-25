package ecosmart.entities;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity 
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CommandLine {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_line;

	private int qte_commande;      // the quantity of the product in the command


	private Float total_line;
	
    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)  @JoinColumn(name="id_command", nullable=false)
    private Command command;         //the command containing this line
  
    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL) @JoinColumn(name="product_id", nullable=false)
    private Product product;        // the product correspanding to this line of the command
 
	public Long getId_line() {
		return id_line;
	}

	public void setId_line(Long id_line) {
		this.id_line = id_line;
	}

	public int getQte_commande() {
		return qte_commande;
	}

	public void setQte_commande(int qte_commande) {
		this.qte_commande = qte_commande;
	}


	public Float getTotal_line() {
		return total_line;
	}

	public void setTotal_line(Float total_ligne) {
		this.total_line = total_ligne;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CommandLine [id_line=" + id_line + ", qte_commande=" + qte_commande + ", total_line=" + total_line
				 + ", product=" + product + "]";
	}
  
  
}
