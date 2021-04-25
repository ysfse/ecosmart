package ecosmart.services.Command;

import java.util.*;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecosmart.entities.Command;
import ecosmart.entities.CommandLine;
import ecosmart.entities.Product;
import ecosmart.entities.Shipping;
import ecosmart.entities.User;
import ecosmart.helpers.Order;
import ecosmart.repositories.CommandLineRepository;
import ecosmart.repositories.CommandRepository;
import ecosmart.repositories.ProductRepository;
import ecosmart.repositories.UserRepository;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommandServiceImpl implements ICommandService{

	@Autowired 
	CommandRepository commandRepo;
	
	@Autowired 
	ProductRepository productRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CommandLineRepository commandLineRepo;
	
	@Override
	public List<Float> getCommandsStatisticsPerDay(Long userId, Date date) throws Exception{
		
		try {
		         //current user
		  User user = userRepo.findById(userId).get();
		  System.out.println(userId);
		  
		        //commands done by the user 
		  List<Command> commands = commandRepo.findByUser(user);
		  
		 // List<Command> userCommands = commandRepo.findByCommandDateEqualsAndUser(date,user);    //IT's not working
		  
		  System.out.println("commands are" + commands);
		  
		       //result array of statistics
		  List<Float> statisticsList = new ArrayList<>();

	  	  
		             //statistics
		  Float carbonGainPerDay =  0f;   //After using ecosmart
		  Float carbonRatioPerDay = 0f ;   //Before Using Ecosmart
		  
		  Float carbonGainPerProduct = 0f ;
		  Float carbonRatioPerProduct = 0f;
		  
		  Float carbonGainPerCommand = 0f;
		  Float carbonRatioPerCommand = 0f;
		  
		 
		  int totalCommands = 0;    // number of exising commands
		  int totalLines = 0;       //number of lines in the command
		  
		         //date formatter
		  SimpleDateFormat sdformat = new
	                SimpleDateFormat("yyyy-MM-dd");
		  
		  
		  if(commands.size()>0) {
			 
		  for(int i=0;i<7;i++) { 
			  
			  System.out.println(sdformat.format(date));
			  totalCommands = 0;
			  carbonGainPerDay =  0f;   //After using ecosmart
				carbonRatioPerDay = 0f ;   //Before Using Ecosmart
			  for(Command c : commands) {
				    				 
					  carbonGainPerCommand = 0f;
					  carbonRatioPerCommand = 0f ;
				      totalLines = 0;	 
				
			        Date commandDate = sdformat.parse(c.getCommandDate().toString());
			       
				  	if(c.getState() && commandDate.equals(date)) {      // only include treated commands in the statistics
				  			totalCommands += 1;                 // to calculate the number of commands done by the user
				  			List<CommandLine> lines = c.getLines();
				  	
				  			for(CommandLine line : lines) {
				  					carbonGainPerProduct = 0f ;
				  					carbonRatioPerProduct = 0f;
				  					totalLines+=1;
				  			carbonGainPerProduct= line.getProduct().getCarbonBalance().getCarbonGain();
				  			carbonGainPerCommand+= carbonGainPerProduct;
				  			
				  			
				  			carbonRatioPerProduct = line.getProduct().getCarbonBalance().getCarbonRatio();
				  			carbonRatioPerCommand+= carbonRatioPerProduct;
				  			
				  			}
				  			
				  	 		if(totalLines != 0) {
				 	 					c.setCarbonGain(carbonGainPerCommand/totalLines);
				 	 					c.setCarbonRatio(carbonRatioPerCommand/totalLines);
				  	 		}	
				  	 		
				  	 		carbonGainPerDay+= c.getCarbonGain();
				  	 		carbonRatioPerDay+= c.getCarbonRatio();
				  	}
				  					  	
			 }	
	
		  
		 	if(totalCommands != 0) {
		 			 statisticsList.add(carbonRatioPerDay/totalCommands);   // the average value to keep it under 100% in the chart(front end)
		 			 statisticsList.add(carbonGainPerDay/totalCommands);    // same
		 			  
		 		 }
		 		 
		 	else {
		 			 statisticsList.add(0f);   
		 			 statisticsList.add(0f); 
		 		 }
		  

		     //increment the date for the next iteration
		  Calendar c = Calendar.getInstance(); 
		  c.setTime(date); 
		  c.add(Calendar.DATE, 1);
		  date = c.getTime();
		  }
		 
		
     }
		  
return statisticsList;
		  
		
	  } catch(Exception e) {
		  System.out.println(e);
		  return new ArrayList<>();
	      }
		
}

	@Override
	public void addCommand(Long userId, Order order) {
		
		     // the ids of the products to insert in the comand
		  List<Long> productsIds = order.getProducts().stream().map(product -> product.get("key")).collect(Collectors.toList());
			  
		  Command command = new Command();
		  
		  command.setUser(userRepo.findById(userId).get());
		  System.out.println("user " + userRepo.findById(userId).get());
		  
		  
		  List<CommandLine> lines = new ArrayList<>();
		  		  
		  for (Long productId : productsIds) {
			  
			
			       // extract the quantity of the product
	          int quantity = order.getProducts().stream().filter(product -> product.get("key") == productId).findFirst().get().get("values").intValue();
	          
	          Product product = productRepo.findById(productId).get();

			  System.out.println("product " +product);
                 
			     //insert information of the command line
			  CommandLine line = new CommandLine();
			  line.setProduct(product);
			  line.setQte_commande(quantity);
			  line.setTotal_line(quantity*product.getPrice_product());
			  line.setCommand(command);
			  commandLineRepo.save(line);
			     //add the line to the command
			  lines.add(line);

	       }

		  command.setLines(lines);	  
		  System.out.println("lines " + lines);
		  
		       //insert information about the command
		  Float totalCommand = 0f;
		  
		  Float carbonGainPerProduct = 0f ;
		  Float carbonRatioPerProduct = 0f;
		  
		  Float carbonGainPerCommand = 0f;
		  Float carbonRatioPerCommand = 0f;
		  
		  int totalLines = 0;
		  
		  for(CommandLine line : lines) {
			  
			    Product product = line.getProduct();
			    totalLines+=1;
			    
				if(product.getCarbonBalance().getCarbonGain() == null || product.getCarbonBalance().getCarbonRatio() == null  ) {
					 Float co2Emission = product.getCarbonBalance().getCo2Emission();
					 Float maxCo2Emission = product.getCarbonBalance().getMaxCo2Emission();
					 product.getCarbonBalance().setCarbonGain(100 * (maxCo2Emission - co2Emission)/maxCo2Emission);
					 product.getCarbonBalance().setCarbonRatio(co2Emission/maxCo2Emission);
					 
				  }
			
			    
			     totalCommand +=line.getTotal_line();
	  			 carbonGainPerProduct= product.getCarbonBalance().getCarbonGain();
	  			 carbonRatioPerProduct = product.getCarbonBalance().getCarbonRatio();
	  			 carbonGainPerCommand+= carbonGainPerProduct;
	  			 carbonRatioPerCommand+= carbonRatioPerProduct;
		  }
		  
			command.setTotal_command(totalCommand);				  
		 	command.setCarbonGain(carbonGainPerCommand/totalLines);
		  	command.setCarbonRatio(carbonRatioPerCommand/totalLines);
		 
		 
		
		          //insert the shipping information
		  Shipping shipping = new Shipping(); 
		  command.setCommandDate(new Date());
		  shipping.setCity(order.getCity());
		  shipping.setCountry(order.getCountry());
		  shipping.setPaymentMode(order.getPaymentMode());
		  shipping.setShippingAddress1(order.getShippingAddress1());
		  shipping.setShippingAddress2(order.getShippingAddress2());
		  shipping.setZipCode(order.getZipCode());
		  
		          
		  command.setShipping(shipping);
		  
		  
		  command.getShipping().setCity(order.getCity());
		  command.getShipping().setCountry(order.getCountry());
		  command.getShipping().setPaymentMode(order.getPaymentMode());
		  command.getShipping().setShippingAddress1(order.getShippingAddress1());
		  command.getShipping().setShippingAddress2(order.getShippingAddress2());
		  command.getShipping().setZipCode(order.getZipCode());
		  
		  command.setState(true);
		  
		  commandRepo.save(command);
		  	
	}	

}
