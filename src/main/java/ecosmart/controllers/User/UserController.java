package ecosmart.controllers.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ecosmart.entities.User;
import ecosmart.security.CurrentUser;
import ecosmart.security.UserPrinciple;
import ecosmart.services.User.IUserService;

@RestController
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@GetMapping("/currentUser")
	@PreAuthorize("hasRole('USER')")
	
	public User getUserInformation(@CurrentUser UserPrinciple currentUser) {
		Long userId = currentUser.getId();
		return userService.getUserInformation(userId).get();
	}

}
