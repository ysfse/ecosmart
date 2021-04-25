package ecosmart.services.User;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecosmart.entities.User;
import ecosmart.repositories.UserRepository;
@Service
@Transactional
public class UserServiceImpl implements IUserService  {

	
	 @Autowired
	 UserRepository userRepo;
	 
	public Optional<User> getUserInformation(Long user_id) {
		
		return userRepo.findById(user_id);
		
	}
}
