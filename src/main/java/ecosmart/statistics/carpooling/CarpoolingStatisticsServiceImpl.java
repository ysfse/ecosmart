package ecosmart.statistics.carpooling;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.format.DateTimeFormatter;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import ecosmart.entities.Carpool;
import ecosmart.entities.User;
import ecosmart.repositories.CarpoolRepository;
import ecosmart.repositories.UserRepository;
import ecosmart.entities.Fuel;
@Service
@Transactional
public class CarpoolingStatisticsServiceImpl implements ICarpoolingStatisticsService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Autowired 
	 CarpoolRepository carpoolRepo;
		
	@Autowired
	UserRepository userRepo;
	 
	             //this method calculates (for the user who calls it from the front end) the quanity of co2 that could be emitted if each 
	             // of the people who participated in the carpool went separately (each one is his/her own car), and the co2 emission with carpool
	@Override
	public List<Float> getCarpoolsStatisticsPerDay(Long driverId,Date date) throws ParseException {
		
		        // get the user who has called the method(the one authenticated to the app, and get the carpools he has proposed
		User driver = userRepo.findById(driverId).get();
		List<Carpool> list = carpoolRepo.findByDriver(driver);	
		
		     //instanciate the array of results
		List<Float> carpoolStatistics = new ArrayList<>();
		
				
		int Co2EmissionforDiesel =2640;          // Grammes of co2 per litre for diesel
		int Co2EmissionforEssence =2392;          // Grammes of co2 per litre for gasoline
		
		         //values that will be calculated for the date specified
        Float co2EmissionBeforeCarpoolPerDay = 0f;
	    Float  co2EmissionAfterCarpoolPerDay = 0f;

	     DateTimeFormatter dtf =
	    		 				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	     SimpleDateFormat sdformat = 
	    		                new  SimpleDateFormat("yyyy-MM-dd");
	     
	     String stringDateSpecified = sdformat.format(date);      // Convert from Date to String
	     
	     
	     Float fuelConsumptionPer100km = 0f;     // specific for each carpool/car
	     
	   
	      int totalCarpools = 0;
	      
		       // iterate through the carpools found to extract information
	      for(Carpool c : list) {

		        // String -> LocalDateTime
		        LocalDateTime localDateTime = LocalDateTime.parse(c.getDepartureTime().format(dtf), dtf);

		        // LocalDateTime -> String
		        String carpoolDateS =localDateTime.format(dtf).substring(0,10);  //get only the date and not the time

		   		    
		         // for debugging
		        System.out.println(stringDateSpecified);
		        System.out.println(carpoolDateS);
		        System.out.println(stringDateSpecified.equals(carpoolDateS));
		         
		        
		                 //check if the carpool is already done and if it verifies the date specified
		        if(c.getState() && stringDateSpecified.equals(carpoolDateS)) {    
		        	
		        	
		         	 totalCarpools += 1;
				     fuelConsumptionPer100km = c.getFuelConsumptionPer100Km();
				
				     Fuel fuel = c.getFuel();
		             System.out.println(fuel);
		             
		        
				    if(fuel.equals(Fuel.Diesel)) {
				    		Float consumption = (fuelConsumptionPer100km * Co2EmissionforDiesel / 100)*(c.getRoute_distance()/1000);
					 
				    		c.setCo2EmissionBeforeCarpool(consumption*c.getActualPlaces());
				    		co2EmissionBeforeCarpoolPerDay +=
				    								consumption*c.getActualPlaces();
					 
				    		c.setCo2EmissionAfterCarpool(consumption);
				    			co2EmissionAfterCarpoolPerDay +=
							 							consumption;
				    	}
				
				
				    else if(fuel.equals(Fuel.Gasoline) ){
				    			Float consumption = (fuelConsumptionPer100km * Co2EmissionforEssence / 100)*(c.getRoute_distance()/1000);
					
					
					 c.setCo2EmissionBeforeCarpool(consumption*c.getActualPlaces());
					 co2EmissionBeforeCarpoolPerDay +=
	                                                  consumption*c.getActualPlaces();
					 
					 
					 c.setCo2EmissionAfterCarpool(consumption);
					 co2EmissionAfterCarpoolPerDay +=
							 						  consumption;
				    }
				
				    else {
					 System.out.println("None of them");

				    }
                           		        	
		        }
		        
		}
	         //once we've went through all carpools, we calculate the average of co2 emission before and after carpool by dividing by the total number of carpools

		   if(totalCarpools!=0) {
			   carpoolStatistics.add(co2EmissionBeforeCarpoolPerDay/totalCarpools);
			   carpoolStatistics.add(co2EmissionAfterCarpoolPerDay/totalCarpools);
		   }
		      // if no carpool was done on the day specified then we add 0 for both before and after carpool statistics
		   else {
			   carpoolStatistics.add(0f);
			   carpoolStatistics.add(0f);
		   }
		
		return carpoolStatistics;
}
	
	             // this method calls the previous one to get statosctics for the day specified + 6 days after 
	@Override
	public List<Float> getCarpoolStatisticsFor6DaysAfter(Long driverId, Date date) throws ParseException{
		
	  	List<Float> statisticsOf7Days = new ArrayList<>();
		      for(int i=0; i<7; i++) {
		    	  
		    	  List<Float> stats = getCarpoolsStatisticsPerDay(driverId,date);
		    	  Calendar c = Calendar.getInstance(); 
					 c.setTime(date); 
					 c.add(Calendar.DATE, 1);   // add one day to the day , to calculate statistics of the next day in the next iteration of the loop
					 date = c.getTime();
					 
					 statisticsOf7Days.addAll(stats);
		      }
		      
		      return statisticsOf7Days;
		
	}

}
