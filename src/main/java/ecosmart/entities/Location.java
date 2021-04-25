package ecosmart.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="product_id")

                 // this class contains location information
public class Location {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)

@JsonIgnore
private Long location_id;

private String name;

@JsonIgnore
private Double latitude;

@JsonIgnore
private Double longitude;

private String imageUrl;

public String getImageUrl() {
	return imageUrl;
}

public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Double getLatitude() {
	return latitude;
}

public void setLatitude(Double latitude) {
	this.latitude = latitude;
}

public Double getLongitude() {
	return longitude;
}

public void setLongitude(Double longitude) {
	this.longitude = longitude;
}


}
