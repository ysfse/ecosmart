package ecosmart.helpers;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import ecosmart.entities.Fuel;

//this is a helper class where I made all fileds of the carpool offer with their getters and setters, to make the body request in the carpool controlller one object
public class CarpoolOffer {

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	LocalDateTime departTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	LocalDateTime departTimeFlexibility;
	int totalPlaces = 0;
	Boolean pets;
	Boolean smoking;
	String depart_address;
    String arrival_address;
  //  String car_type;
    Fuel fuel;
    Float fuelConsumptionPer100Km;
    Float routeDistance;
    
	public Float getRouteDistance() {
		return routeDistance;
	}
	public void setRouteDistance(Float routeDistance) {
		this.routeDistance = routeDistance;
	}
	public LocalDateTime getDepart_time() {
		return departTime;
	}
	public void setDepart_time(LocalDateTime depart) {
		this.departTime = depart;
	}
	
	
	public LocalDateTime getDepartTimeFlexibility() {
		return departTimeFlexibility;
	}
	public void setDepartTimeFlexibility(LocalDateTime departTimeFlexibility) {
		this.departTimeFlexibility = departTimeFlexibility;
	}
	
	public int getTotalPlaces() {
		return totalPlaces;
	}
	public void setTotalPlaces(int totalPlaces) {
		this.totalPlaces = totalPlaces;
	}
	public Boolean getPets() {
		return pets;
	}
	public void setPets(Boolean pets) {
		this.pets = pets;
	}
	public Boolean getSmoking() {
		return smoking;
	}
	public void setSmoking(Boolean smoking) {
		this.smoking = smoking;
	}
	public String getDepart_address() {
		return depart_address;
	}
	public void setDepart_address(String depart_address) {
		this.depart_address = depart_address;
	}
	public String getArrival_address() {
		return arrival_address;
	}
	public void setArrival_address(String arrival_address) {
		this.arrival_address = arrival_address;
	}
	public LocalDateTime getDepartTime() {
		return departTime;
	}
	public void setDepartTime(LocalDateTime departTime) {
		this.departTime = departTime;
	}
	public Fuel getFuel() {
		return fuel;
	}
	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}
	public Float getFuelConsumptionPer100Km() {
		return fuelConsumptionPer100Km;
	}
	public void setFuelConsumptionPerKm(Float fuelConsumptionPerKm) {
		this.fuelConsumptionPer100Km = fuelConsumptionPerKm;
	}
	
    
}
