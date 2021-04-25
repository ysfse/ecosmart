package ecosmart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@SpringBootApplication
@EnableAutoConfiguration(exclude= {SecurityAutoConfiguration.class})
@ComponentScan({"ecosmart.controllers","ecosmart.statistics","ecosmart.services","ecosmart.helpers","ecosmart.useful","ecosmart.configurations","ecosmart.security"})
//@ComponentScan(basePackages={"ecosmart.controllers"})
//@ComponentScan(basePackages={"ecosmart.services"})
@EnableJpaRepositories({"ecosmart.repositories","ecosmart.entities"})
@EntityScan(basePackages = {"ecosmart.entities","ecosmart.helpers"})
public class EcosmartApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(EcosmartApplication.class, args);
		System.out.println("finished");
		
	}

}
