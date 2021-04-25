package ecosmart.controllers.Authentication;
import ecosmart.entities.Role;
import ecosmart.entities.RoleName;
import ecosmart.entities.User;
import ecosmart.helpers.LoginForm;
import ecosmart.helpers.SignUpForm;
import ecosmart.repositories.*;
import ecosmart.security.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    
    @PostMapping("/signin")
   // @PreAuthorize("permitAll")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
  //  @PreAuthorize("permitAll")
    public ResponseEntity registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getFirstName(),signUpRequest.getLastName(), 
                signUpRequest.getEmail(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        
        List<Role> strRoles = signUpRequest.getRole();
        List<Role> roles = new ArrayList<>();
        Role userRole;
        userRole = (Role) roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				 roles.add(userRole);  

				 user.setRoles(roles);
			     userRepository.save(user);	
			       
			       //THE CODE BELOW IS FOR LATER MODIFICATIONS ON THE APP ON CASE WE WANT TO ADD OTHER ROLES (admin...)
/*				
         for(Role role : strRoles) {
        	   RoleName name = role.getName();
        	 	if(name==RoleName.ROLE_ADMIN) {	    		
	    			Role adminRole;
					try {
						adminRole = (Role) roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
						roles.add(adminRole);	
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			    			
        	}
        	else if(name==RoleName.ROLE_PM) {
	            	Role pmRole;
					try {
						pmRole = (Role) roleRepository.findByName(RoleName.ROLE_PM)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
						roles.add(pmRole);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
        	}
        	else {
	        		Role userRole;
					try {
						userRole = (Role) roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
						roles.add(userRole);  
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		
        	}  
         }*/

       

        return ResponseEntity.ok().body("User registered successfully!");
    }
}
