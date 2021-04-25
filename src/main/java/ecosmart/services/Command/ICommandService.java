package ecosmart.services.Command;
import ecosmart.entities.Command;
import ecosmart.helpers.Order;

import java.util.List;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDateTime;

public interface ICommandService extends Serializable{

	    List<Float> getCommandsStatisticsPerDay(Long userId,Date date) throws Exception;
	    
	    public void addCommand(Long userId, Order order);
	    
	    
	    
}
