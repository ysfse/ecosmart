package ecosmart.controllers.product;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import net.minidev.json.JSONObject;

import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Map;

import ecosmart.entities.*;
import javax.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ecosmart.entities.Product;
import ecosmart.security.CurrentUser;
import ecosmart.security.UserPrinciple;
import ecosmart.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@RestController

public class ProductController {

	@Autowired
	IProductService productService;
	
	@GetMapping("/products")
	//@PreAuthorize("hasRole('USER')")
	// @ResponseBody
	 public List<Product> getAllProducts(Model model, @Param("keyword") String keyword) throws Exception{
		 
	        List<Product> list = productService.getAllProducts(keyword);
	        model.addAttribute("list", list);
	        model.addAttribute("keyword", keyword);
	       // return new List<Product>(list, new HttpHeaders(), HttpStatus.OK);
	        return list;
	    }
	
}
	 /*
	  @GetMapping("/products")
	  public ResponseEntity<Map<String, Object>> getAllProducts(
	      //  @RequestParam(required = false) String title,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "30") int size,
	        @Param("keyword") String keyword
	      ) {

	    try {
	      List<Product> tutorials = new ArrayList<Product>();
	      Pageable paging = PageRequest.of(page, size);
	      
	      Page<Product> pageTuts;
	    
	        pageTuts =productService.getAllProducts(keyword, paging);

	      tutorials = pageTuts.getContent();

	      Map<String, Object> response = new HashMap<>();
	      response.put("products", tutorials);
	      response.put("currentPage", pageTuts.getNumber());
	      response.put("totalItems", pageTuts.getTotalElements());
	      response.put("totalPages", pageTuts.getTotalPages());

	      return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	 */
	/*  @PostMapping("/buy/{productId}")
	  @PreAuthorize("hasRole('USER')")
	    public void buyProduct(@CurrentUser UserPrinciple currentUser, @RequestBody String qte,@PathVariable("productId") Long productId /*,@PathVariable("qte") Double qte*/
	  /*  {
	         Long userId = currentUser.getId();    // to get the current user info
		     Float quantity = Float.parseFloat(JSON.parseObject(qte).get("quantity").toString());
	         productService.buyProduct(productId,quantity,userId);

	    }*/
	
//}
