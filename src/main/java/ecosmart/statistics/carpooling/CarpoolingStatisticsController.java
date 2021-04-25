package ecosmart.statistics.carpooling;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ecosmart.entities.Carpool;
import ecosmart.entities.Product;
import ecosmart.security.CurrentUser;
import ecosmart.security.UserPrinciple;
import java.time.*;
@RequestMapping("/carpools")
@RestController
public class CarpoolingStatisticsController {
	
	@Autowired
	ICarpoolingStatisticsService carpoolingStat;
	
	
	    @GetMapping("/statistics")
		// @PreAuthorize("hasRole('USER')")
		// @ResponseBody
		 public List<Float> getAllDoneCarpools(@CurrentUser UserPrinciple currentUser,@RequestParam("date") 
		 @DateTimeFormat(pattern="yyyy-MM-dd")  Date date) throws Exception{
			 
	    	    Long userId = currentUser.getId();  	    	    
		        List<Float> list = carpoolingStat.getCarpoolStatisticsFor6DaysAfter(userId,date);
		     
		    
		        return list;
		    }
	
}
