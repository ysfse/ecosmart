package ecosmart.services.User;

import java.io.Serializable;
import java.util.Optional;

import ecosmart.entities.User;

public interface IUserService extends Serializable {

	
	public Optional<User>  getUserInformation(Long user_id);
}
