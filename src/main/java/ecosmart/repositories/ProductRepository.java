package ecosmart.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ecosmart.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,JpaSpecificationExecutor<Product> {
	
	
	List<Product> findAll();

	
	@Query("SELECT p FROM Product p  WHERE CONCAT(p.category, ' ', p.libelle_product,' ',p.magasin.nom_magasin) LIKE %?1% ORDER BY p.carbonBalance.co2Emission ASC")
	 public List<Product> search(String keyword);
	
  
	public Optional<Product> findById(Long productId);
       
}
