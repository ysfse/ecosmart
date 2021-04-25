package ecosmart.controllers.Command;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ecosmart.entities.Product;
import ecosmart.helpers.CarpoolOffer;
import ecosmart.helpers.Order;
import ecosmart.security.CurrentUser;
import ecosmart.security.UserPrinciple;
import ecosmart.services.Command.ICommandService;
@RequestMapping("/commands")
@RestController
public class CommandController {
	
	@Autowired
	ICommandService commandService;
	
	@GetMapping("/statistics")
	 public List<Float> getCarbonStatistics(@CurrentUser UserPrinciple currentUser, @RequestParam("date") 
	 @DateTimeFormat(pattern="yyyy-MM-dd") Date date) throws Exception{
				Long userId = currentUser.getId();   //info of the user authenticated
	            return commandService.getCommandsStatisticsPerDay(userId, date);
	    }
	
	@PostMapping("/add")
	    public void addCommand(@CurrentUser UserPrinciple currentUser, @RequestBody Order order ) throws Exception
	    {
		     Long userId = currentUser.getId();    // to get the current user info
	         commandService.addCommand(userId, order);

	    }
	

}
