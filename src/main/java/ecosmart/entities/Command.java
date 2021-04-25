package ecosmart.entities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Command {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_command;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date commandDate;                        //the date of the last update of the command
	
	private Float total_command;                    // total price of the comands

	
	@OneToMany(targetEntity= CommandLine.class, mappedBy="command")
	@JsonIgnore
	private List<CommandLine> lines = new ArrayList<>();          // a command has many lines and each line contains one product with its quantity
	
	
	@OneToOne(mappedBy = "command")
    private Basket basket;                                 // the cart : before the command was treated


	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)  @JoinColumn(name="user_id", nullable=false)
    private User user;                                    // the user who has done the command
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)  @JoinColumn(name="shippingId", nullable=true)
	private Shipping shipping;                          // shipping information if the user chosed the shipping option
	
	private Boolean state;  									 // the state of a command is if it's closed(paid) or the user is still adding products to it .
	
	private Float carbonGain ;     										  // percentage of co2 saved by the whole command (sum of carbon gain of every product in the command)

	private Float carbonRatio ;                                           //  Idem but for carbonRatio of every product
	
	public Long getId_command() {
		return id_command;
	}

	public void setId_command(Long id_command) {
		this.id_command = id_command;
	}




	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public Date getCommandDate() {
		return commandDate;
	}

	public void setCommandDate(Date commandDate) {
		this.commandDate = commandDate;
	}

	public Float getTotal_command() {
		return total_command;
	}

	public void setTotal_command(Float total_command) {
		this.total_command = total_command;
	}

	

	public Float getCarbonGain() {
		return carbonGain;
	}

	public void setCarbonGain(Float carbonGain) {
		this.carbonGain = carbonGain;
	}

	public Float getCarbonRatio() {
		return carbonRatio;
	}

	public void setCarbonRatio(Float carbonRatio) {
		this.carbonRatio = carbonRatio;
	}



	public List<CommandLine> getLines() {
		return lines;
	}

	public void setLines(List<CommandLine> lines) {
		this.lines = lines;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	
	
	
	
}
