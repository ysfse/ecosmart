package ecosmart.entities;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="carpool_id")
public class Carpool {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
 private Long carpool_id;

@JsonIgnore
@JsonFormat(pattern="yyyy-MM-dd HH:mm") 
private LocalDateTime departureTime;                   // the time the driver will suggest for starting the journey

@JsonIgnore  
@JsonFormat(pattern="yyyy-MM-dd HH:mm")
private LocalDateTime departureTimeFlexibility;        // departureTime + how much time the driver could wait (10min / 15min..)

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "depart_location_id", referencedColumnName = "location_id")
private Location departureLocation;                     //This is going to be as a field of the autocomplete places api of google maps (which the driver is going to indicate))

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "arrival_location_id", referencedColumnName = "location_id")
private Location arrivalLocation;                        //This is going to be as a field of the autocomplete places api of google maps (which the driver is going to indicate))


private int totalPlaces;                                //Number of places the car has

private int actualPlaces = 1;                           //Number of those who actually participated in the carpool 1 is default (the driver him/herself)

@JsonIgnore
private Boolean smoking;                                // the driver allows/does not allow smoking

@JsonIgnore
private Boolean pets;									// the driver allows/does not allow pets
   
@Enumerated(EnumType.STRING)
private Fuel fuel;                                      // Diesel or Gasoline

private Float fuelConsumptionPer100Km;                  // fuel consumption per 100km

private Float route_distance;                           //Calculated using the google maps directions API 

@JsonIgnore
private Boolean state;                                  //it indicates if the carpool has been done or not, only the driver has the possibility to mark the carpool as "done"

private Float co2EmissionBeforeCarpool;                 // check ecosmart/statistics/carpooling/CarpoolingServiceImpl for calculation method


private Float co2EmissionAfterCarpool;                 //Idem


@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)  
@JoinColumn(name="user_id", nullable=false)
@JsonIdentityReference(alwaysAsId = true)
@JsonIgnore
private User driver;

@ManyToMany
@JoinTable(
  name = "users_carpools", 
  joinColumns = @JoinColumn(name = "carpool_id"), 
  inverseJoinColumns = @JoinColumn(name = "user_id"))
@JsonIdentityReference(alwaysAsId = true)
private List<User> passengers =  new ArrayList<>();      //List of users who participated in the carpool




                               //getters and setters
public LocalDateTime getDepartureTime() {
	return departureTime;
}

public void setDepartureTime(LocalDateTime departureTime) {
	this.departureTime = departureTime;
}

public LocalDateTime getDepartureTimeFlexibility() {
	return departureTimeFlexibility;
}

public void setDepartureTimeFlexibility(LocalDateTime departureTimeFlexibility) {
	this.departureTimeFlexibility = departureTimeFlexibility;
}

public Location getDepartureLocation() {
	return departureLocation;
}

public void setDepartureLocation(Location departureLocation) {
	this.departureLocation = departureLocation;
}

public Location getArrivalLocation() {
	return arrivalLocation;
}

public void setArrivalLocation(Location arrivalLocation) {
	this.arrivalLocation = arrivalLocation;
}

public Float getCo2EmissionBeforeCarpool() {
	return co2EmissionBeforeCarpool;
}

public void setCo2EmissionBeforeCarpool(Float co2EmissionBeforeCarpool) {
	this.co2EmissionBeforeCarpool = co2EmissionBeforeCarpool;
}

public Float getCo2EmissionAfterCarpool() {
	return co2EmissionAfterCarpool;
}

public void setCo2EmissionAfterCarpool(Float co2EmissionAfterCarpool) {
	this.co2EmissionAfterCarpool = co2EmissionAfterCarpool;
}



public int getTotalPlaces() {
	return totalPlaces;
}

public void setTotalPlaces(int totalPlaces) {
	this.totalPlaces = totalPlaces;
}

public int getActualPlaces() {
	return actualPlaces;
}

public void setActualPlaces(int actualPlaces) {
	this.actualPlaces = actualPlaces;
}

public Boolean getSmoking() {
	return smoking;
}

public void setSmoking(Boolean smoking) {
	this.smoking = smoking;
}

public Boolean getPets() {
	return pets;
}

public void setPets(Boolean pets) {
	this.pets = pets;
}

public List<User> getPassengers() {
	return passengers;
}

public void setPassengers(List<User> passengers) {
	this.passengers = passengers;
}

public void addPassenger(User user) {
	this.passengers.add(user);
}
public User getDriver() {
	return driver;
}

public void setDriver(User driver) {
	this.driver = driver;
}

public Long getCarpool_id() {
	return carpool_id;
}

public void setCarpool_id(Long carpool_id) {
	this.carpool_id = carpool_id;
}



public Fuel getFuel() {
	return (Fuel) fuel;
}

public void setFuel(Fuel fuel) {
	this.fuel = (Fuel) fuel;
}

public Float getFuelConsumptionPer100Km() {
	return fuelConsumptionPer100Km;
}

public void setFuelConsumptionPer100Km(Float fuelConsumptionPerKm) {
	this.fuelConsumptionPer100Km = fuelConsumptionPerKm;
}

public Float getRoute_distance() {
	return route_distance;
}

public void setRoute_distance(Float route_distance) {
	this.route_distance = route_distance;
}

public Boolean getState() {
	return state;
}

public void setState(Boolean state) {
	this.state = state;
}


}
