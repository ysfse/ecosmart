package ecosmart.entities;


import lombok.Data;
                   // class not used : the purpose was to get the location of the user connected, I didn't use it for time issues
@Data
public class GeoIP {

    private String ipAddress;
    private String device;
    private String city;
    private String fullLocation;
    private Double latitude;
    private Double longitude;
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFullLocation() {
		return fullLocation;
	}
	public void setFullLocation(String fullLocation) {
		this.fullLocation = fullLocation;
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