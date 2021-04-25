package ecosmart.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="")
public class CarbonBalance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long carbonBalance_id;                 
	
	@JsonIgnore
	private String unit;                                                                           // the unit we use to define the carbon Emission : ex: carbon emission per product/per 100g..
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	@JsonIgnore
	private Product product;
	
	
	private Float co2Emission = (float) 0;                                                         //this is the actual quantity of Co2 Emission
	
	private Float maxCo2Emission = (float) 1;                                                     // this is an estimation of the max value of Co2 emission that this kind of product might reach(the product of the same kind in the database that has the max value) )
	
	private Float carbonGain  /* = 100 * (maxCo2Emission - co2Emission)/maxCo2Emission*/ ;       // percentage of co2 saved by chosing this product, carbonGain = 100 * (maxCo2Emission - co2Emission)/maxCo2Emission 

	private Float carbonRatio /* = co2Emission/maxCo2Emission */;                               //percentage of co2 emission of this product compared to the product of same category with max value
	
	public Long getCarbonBalance_id() {
		return carbonBalance_id;
	}

	public void setCarbonBalance_id(Long carbonBalance_id) {
		this.carbonBalance_id = carbonBalance_id;
	}

	public Float getCarbonRatio() {
		return carbonRatio;
	}

	public void setCarbonRatio(Float carbonRatio) {
		this.carbonRatio = carbonRatio;
	}

	public Long getCabonBalanceId() {
		return carbonBalance_id;
	}

	public void setCabonBalanceId(Long cabonBalanceId) {
		this.carbonBalance_id = cabonBalanceId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Float getCo2Emission() {
		return co2Emission;
	}

	public void setCo2Emission(Float co2Emission) {
		co2Emission = co2Emission;
	}

	public Float getMaxCo2Emission() {
		return maxCo2Emission;
	}

	public void setMaxCo2Emission(Float maxCo2Emission) {
		this.maxCo2Emission = maxCo2Emission;
	}

	public Float getCarbonGain() {
		return carbonGain;
	}

	public void setCarbonGain(Float carbonGain) {
		this.carbonGain = carbonGain;
	}
	
	
	
}
