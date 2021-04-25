package ecosmart.services;

import java.io.Serializable;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;

import ecosmart.entities.PagingResponse;
import ecosmart.entities.Product;


public interface IProductService extends Serializable  {
	
	//String FIND ="select libelle_product, price, nom_magasin, libelle_category from product, magasin,category ";
	//@Query(FIND)
	//public Page<Product> getAllProducts(String keyword,Pageable pageable);
	
	public List<Product> getAllProducts(String keyword);

//	public void buyProduct(Long product_id, Float qte, Long user_id) throws Exception;
	
	
}
