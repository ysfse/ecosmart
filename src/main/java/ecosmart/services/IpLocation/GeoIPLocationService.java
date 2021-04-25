package ecosmart.services.IpLocation;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import ecosmart.entities.GeoIP;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface GeoIPLocationService {
    GeoIP getIpLocation( HttpServletRequest request) throws IOException, GeoIp2Exception;
}

