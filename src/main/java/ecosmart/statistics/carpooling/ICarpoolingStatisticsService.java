package ecosmart.statistics.carpooling;

import java.io.Serializable;
import java.text.ParseException;

import ecosmart.entities.Carpool;
import ecosmart.entities.User;

import java.util.List;
import java.util.Date;

public interface ICarpoolingStatisticsService extends Serializable {
   
	public List<Float> getCarpoolsStatisticsPerDay(Long driverId,Date date) throws ParseException;
	public List<Float> getCarpoolStatisticsFor6DaysAfter(Long driverId, Date date) throws ParseException;
	
}
