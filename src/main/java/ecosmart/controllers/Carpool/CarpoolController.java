package ecosmart.controllers.Carpool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import ecosmart.entities.Carpool;
import ecosmart.entities.Product;
import ecosmart.helpers.CarpoolOffer;
import ecosmart.helpers.HttpReqRepsUtils;
import ecosmart.security.CurrentUser;
import ecosmart.security.UserPrinciple;
import ecosmart.services.Carpool.ICarpoolService;
@RequestMapping("/carpools")
@RestController
public class CarpoolController {
	
	@Autowired 
	ICarpoolService carpoolService;
	
	 @PostMapping("/offer")
	    public void offerCarpool(@CurrentUser UserPrinciple currentUser, @RequestBody CarpoolOffer carpoolOffer ) throws Exception
	    {
		     Long userId = currentUser.getId();    // to get the current user info
	         carpoolService.offerCarpool(carpoolOffer,userId);

	    }
	 
	 
	 @GetMapping("/all")
	 public List<Carpool> getAllCarpools(/*Model model, @Param("keyword") String keyword*/) throws Exception{

	        List<Carpool> list = carpoolService.getAllCarpools();
	
	        return list;
	 } 
	 
	  @PostMapping("/participate/{carpoolId}")

	    public void participateToCarpool(@CurrentUser UserPrinciple currentUser, @PathVariable("carpoolId") Long carpoolId /*,@PathVariable("qte") Double qte*/) throws Exception
	    {
		      Long userId = currentUser.getId();  // to get the current user info
	          carpoolService.participateToCarpool(userId, carpoolId);

	    }
	  
	  @GetMapping("/availableCarpools")
		  public List<Carpool> getAvailableCarpools(@CurrentUser UserPrinciple currentUser) throws Exception{
		  		  Long userId = currentUser.getId();  // to get the current user info
			      return carpoolService.getPossibleCarpools(userId);
		  }
	  

}

