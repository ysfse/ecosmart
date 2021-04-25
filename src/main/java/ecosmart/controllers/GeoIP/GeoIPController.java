package ecosmart.controllers.GeoIP;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import ecosmart.entities.GeoIP;
import ecosmart.helpers.HttpReqRepsUtils;
import ecosmart.services.IpLocation.GeoIPLocationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class GeoIPController {

    private final GeoIPLocationService geoIPLocationService;

    public GeoIPController(GeoIPLocationService geoIPLocationService) {
        this.geoIPLocationService = geoIPLocationService;
    }

    @GetMapping("/geoIP")
    public GeoIP getLocation(/*@PathVariable String ipAddress,*/ HttpServletRequest request) throws IOException, GeoIp2Exception {
    	return geoIPLocationService.getIpLocation(/*ipAddress,*/ request);
    }
}