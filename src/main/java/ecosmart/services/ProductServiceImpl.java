package ecosmart.services;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ecosmart.entities.*;
import javax.transaction.Transactional;
import ecosmart.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import ecosmart.entities.Product;
import ecosmart.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@Service
@Transactional
public class ProductServiceImpl implements IProductService  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CommandLineRepository commandLineRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommandRepository commandRepo;

	 @Override

	 //get products based on a searched text, and show them in an ascedent order o their co2 emission
    public List<Product> getAllProducts(String keyword){
	

	        if (productRepo.findAll().size() > 0) {
	        	
	        	if(keyword == null) {
	         
	        	return productRepo.findAll();
	        	}
	        	
	        	else {
	        		
	        		
	        		List<Product> products = productRepo.search(keyword);
	        		
	        		return products ;
	        		
	        	}
	         	
	        } else {
	            
	        	return new ArrayList<>();
	        }
		
	
	}


/*	@Override
	public void buyProduct(Long product_id,Float qte,Long user_id) throws Exception {
		
		
	  Optional<Product> product = productRepo.findById(product_id); //optional type beacuse it might return or not a value
	  User user = userRepo.findById(user_id).get();
	  
		if(product.isPresent() && product.get().Is_in_stock()) {    // verify if the product with the given id exists and is in stock
		
			Float qte_stock = product.get().getQte_stock();
			
			if(qte <= qte_stock) {
					Float price = product.get().getPrice_product();   // the price of the product the user chosed
			
					CommandLine line = new CommandLine();
					Command command = new Command();
			
					List<Command> commands = user.getCommands();
			
					for(Command c : commands) {   
							if(!c.getState()) {       //Looking for the command that is not closed yet (not paid) it means the one containing products
									command = c;
									break;
							}
					}
			
			   /* Set the fields of the line
					line.setProduct(product.get());
					line.setPrix_produit(price);
					line.setQte_commande(qte);
					line.setTotal_ligne((double) (qte*price));
		     
		      /*The current user (making the order) 
					command.setUser(user);
		    
		     /*Set the date of the last update of the command
					command.setCommandDate(new Date());
		    
		      /*Attribute the line to the command
					line.setCommand(command);
					
			/*  Modify the quantity in stock of the product
					product.get().setQte_stock(qte_stock - qte);
		      /*Save objects
					
					commandLineRepo.save(line);
					commandRepo.save(command);
		    
		 }	 
			else {
				throw new Exception("Quantity of the product with ID =" + product_id + " in stock is not enough");
			}
		}
		
		else {
				throw new Exception("Product with ID =" + product_id + " not found or is not in stock");
		}
		
		
		

	}
	 */
	 
	
	
	
}
