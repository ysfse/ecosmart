package ecosmart.services.Carpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecosmart.entities.Carpool;
import ecosmart.entities.Fuel;
import ecosmart.entities.Location;
import ecosmart.entities.Product;
import ecosmart.entities.User;
import ecosmart.helpers.CarpoolOffer;
import ecosmart.repositories.CarpoolRepository;
import ecosmart.repositories.UserRepository;

@Service
@Transactional
public class CarpoolServiceImpl implements ICarpoolService {

	@Autowired 
	private CarpoolRepository carpoolRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void offerCarpool(CarpoolOffer carpoolOffer,Long userId) {
		//instanciate a new carpool
		Carpool carpool = new Carpool();
		User user = userRepo.findById(userId).get();
		
		//Set the driver ( the one who offered the carpool)
		carpool.setDriver(user);
		
		
		//set the departure time of the carpool
		LocalDateTime departureTime = (carpoolOffer.getDepart_time() != null) ? carpoolOffer.getDepart_time() : LocalDateTime.now();
		carpool.setDepartureTime(departureTime);
		
		LocalDateTime departureTimeFlexibility = (carpoolOffer.getDepartTimeFlexibility() != null) ? carpoolOffer.getDepartTimeFlexibility() : LocalDateTime.now();
		carpool.setDepartureTimeFlexibility(departureTimeFlexibility);
		
		// set the origin and destination of the carpool
		Location depart = new Location();
		String departName = (carpoolOffer.getDepart_address() != null) ? carpoolOffer.getDepart_address() : "";
		depart.setName(departName);
		carpool.setDepartureLocation(depart);
		
		Location arrival = new Location();
		String arrivalName = (carpoolOffer.getArrival_address() != null) ? carpoolOffer.getArrival_address() : "";	
		arrival.setName(arrivalName);	
		carpool.setArrivalLocation(arrival);
		
      //set the number of available places 
		carpool.setTotalPlaces(carpoolOffer.getTotalPlaces());
		
	  // accept or not smooking and having pets
		carpool.setPets(carpoolOffer.getPets());
		carpool.setSmoking(carpoolOffer.getSmoking());
		
		Float routeDistance = (carpoolOffer.getRouteDistance() != null) ? carpoolOffer.getRouteDistance() : 0;	
		carpool.setRoute_distance(carpoolOffer.getRouteDistance());
		
		ecosmart.entities.Fuel fuel = (carpoolOffer.getFuel() != null) ? carpoolOffer.getFuel() : Fuel.Gasoline;	
		carpool.setFuel(fuel);
		
		Float fuelConsumptionPer100Km = (carpoolOffer.getFuelConsumptionPer100Km() != null) ? carpoolOffer.getFuelConsumptionPer100Km() : 0;
		carpool.setFuelConsumptionPer100Km(fuelConsumptionPer100Km);
		
		carpool.setState(true);
		
		carpoolRepo.save(carpool);
	}

	       //get the carpools in which the user can participate (that still has available places)
	@Override
	public List<Carpool> getAllCarpools() {
	     if (carpoolRepo.findAll().size() > 0) {
	        	
	            return carpoolRepo.findByTotalPlacesGreaterThan(0);
	        	
	        } else {
	            return new ArrayList<Carpool>();
	        }
		
	}

	@Override
	public void participateToCarpool(Long userId,Long carpool_id) {
			User user = userRepo.findById(userId).get();
		
			Optional<Carpool> carpool = carpoolRepo.findById(carpool_id);
			
			int totalPlaces = carpool.get().getTotalPlaces();
			int actualPlaces = carpool.get().getActualPlaces();
			int count = 0; 
			
		 if(carpool.isPresent()) {
			 List<User> passengers = carpool.get().getPassengers();
			 try {
			   for(User passenger : passengers) {
				 	if(userId != passenger.getUser_id()) {   // if the user has alreasy participated to the carpool then he doesnt see it in the carpools list
				 			count +=1;
				 			
				 	}
			   } 	
			   
				 	if(count == passengers.size()) {
				 		
				 		if(totalPlaces > 0 ) {
							carpool.get().setTotalPlaces(totalPlaces-1);
							carpool.get().setActualPlaces(actualPlaces+1);
							carpool.get().addPassenger(user);
						
						}
				 		else {
							throw new Exception();
							
						}
				 	}
				 	else {
				 		throw new Exception();
				 	}
					
			  
		 	      	 	
	 }catch(Exception e) {
			 System.out.println("You cant participate");
		 }			 
	}
}
            // to  get the carpools with vailable places and that will have place on the day of search and before midnight and in which the user does not participate
	@Override
	public List<Carpool> getPossibleCarpools(Long userId) throws Exception {
          
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		   LocalDateTime availableAfter = LocalDateTime.now()/*.plusMinutes(15)*/;  // to get the carpools that will happen at least in 15min or more 
		   LocalDateTime availableBefore = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);   //before midnight of the current day
		   System.out.println(dtf.format(availableAfter)+" "+dtf.format(availableBefore));  
		   
		  List<Carpool> carpools = carpoolRepo.findByDepartureTimeBetween(availableAfter,availableBefore);
		  List<Carpool> resultCarpools = new ArrayList<>();
		  int count = 0;
		
		  for(Carpool c : carpools) {
			  if(c.getTotalPlaces() != 0) {
				  
				 List<User> passengers = c.getPassengers();
				 for(User passenger : passengers) {
					 	if(userId != passenger.getUser_id()) {   // if the user has alreasy participated to the carpool then he doesnt see it in the carpools list
					 		
					 		 count+=1;
					 	}
									   
				  }
				 
				 if(count == passengers.size()) {
					   resultCarpools.add(c);
				 }
				 
			  }			 
		  }
		  return resultCarpools;
		 	
	}
    
}
