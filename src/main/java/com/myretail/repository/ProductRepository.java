package com.myretail.repository;

import com.myretail.model.SkuDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Aravind
 */

@Repository
public interface ProductRepository extends CrudRepository<SkuDetails,Long>{

  public List<SkuDetails> findByCategory(String category);

  public SkuDetails findByProductId(Integer productId);


}
