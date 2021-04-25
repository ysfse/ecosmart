package ecosmart.helpers;

import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * <h2>GeoLocationConfig</h2>
 */
@Slf4j
@Configuration
public class GeoLocationConfig {

	@Autowired
    private static DatabaseReader reader ;
    private final ResourceLoader resourceLoader;

    public GeoLocationConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


	@Bean
    public DatabaseReader databaseReader() {
        try {
           // System.out.println("GeoLocationConfig: Trying to load GeoLite2-Country database...");

           /* Resource resource = resourceLoader.getResource("GeoLite2-Country.mmdb");
            InputStream dbAsStream = resource.getInputStream();*/
            
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream dbAsStream = classLoader.getResourceAsStream("GeoLite2-City.mmdb");

          //  System.out.println("GeoLocationConfig: Database was loaded successfully.");

            // Initialize the reader
           return reader = new DatabaseReader
                    .Builder(dbAsStream)
                    .fileMode(Reader.FileMode.MEMORY)
                    .build();

        } catch (IOException | NullPointerException e) {
        	 System.out.println("Database reader cound not be initialized. "+e);
            return null;
        }
    }
}