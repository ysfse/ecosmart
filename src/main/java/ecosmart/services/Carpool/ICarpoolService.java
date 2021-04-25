package ecosmart.services.Carpool;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import ecosmart.entities.Carpool;
import ecosmart.helpers.CarpoolOffer;

public interface ICarpoolService extends Serializable {
	
	public void offerCarpool(CarpoolOffer carpoolOffer,Long userId ); 
	public List<Carpool> getAllCarpools();
    public void participateToCarpool(Long userId, Long carpoolId);
    public List<Carpool> getPossibleCarpools(Long userId) throws Exception;
}