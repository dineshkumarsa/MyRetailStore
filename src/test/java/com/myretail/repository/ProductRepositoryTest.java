package com.myretail.repository;

import com.myretail.MyRetailTest;
import com.myretail.model.SkuDetails;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Dinesh
 */

@Transactional
public class ProductRepositoryTest extends MyRetailTest {

  @Autowired
  ProductRepository productRepository;

  @Test
  public void findByCategoryTest(){

    List<SkuDetails> skuDetailsList=productRepository.findByCategory("toys");
    Assert.assertEquals("expected size for toys 2",2,skuDetailsList.size());
    Assert.assertNotSame("expected size for toys 2",1,skuDetailsList.size());

    List<SkuDetails> skuBabyDetailsList=productRepository.findByCategory("baby");
    Assert.assertEquals("expected size for baby 1",1,skuBabyDetailsList.size());
    Assert.assertNotSame("expected size for baby 1",2,skuBabyDetailsList.size());
  }
  @Test
  public void findByIdTest(){

    SkuDetails skuDetails=productRepository.findByProductId(5555);
    Assert.assertNotNull("Sku available for 5555", skuDetails);

    SkuDetails skuDetail=productRepository.findByProductId(55555);
    Assert.assertNull("Sku not available for 55555", skuDetail);
  }
}
