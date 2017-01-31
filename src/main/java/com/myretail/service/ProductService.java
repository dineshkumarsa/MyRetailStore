package com.myretail.service;

import com.myretail.model.ErrorRepresentation;
import com.myretail.model.Products;
import com.myretail.model.SkuDetails;
import com.myretail.repository.ProductRepository;
import com.myretail.util.MyRetailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Dinesh
 */

@RestController
@EnableAutoConfiguration
public class ProductService {

  @Autowired
  private MyRetailUtil myRetailUtil;

  @Autowired
 private ProductRepository productRepository;

  /**
   * Used to retrieve the list of product ids
   *
   * @param inputProdIds
   * @return
   */
  @RequestMapping(value="myretail/product/{inputProdIds}",produces = MediaType.APPLICATION_XML_VALUE,method = RequestMethod.GET)
  public ResponseEntity<Products> retrieveByProductId(@PathVariable String inputProdIds){

    List<String> productIdList= null;
    List<SkuDetails> skuList=null;
    Iterator<String> iterator=null;
    String productId=null;

    Products products=null;

    if(inputProdIds!=null && !inputProdIds.isEmpty()){
      productIdList = Arrays.asList(inputProdIds.split(","));

    iterator = productIdList.iterator();
    products=new Products();
    skuList=new ArrayList();
    while(iterator.hasNext()) {
      productId = iterator.next();
      if (myRetailUtil.isInteger(productId)) {
        SkuDetails skuDetail = productRepository.findByProductId(Integer.valueOf(productId));
        if (null != skuDetail) {
          skuList.add(skuDetail);
        } else {
          SkuDetails skuDetails = new SkuDetails();
          skuDetails.setProductId(Integer.valueOf(productId));
          ErrorRepresentation errorRepresentation = new ErrorRepresentation();
          errorRepresentation.setErrorDescription("Product not found ");
          skuDetails.setErrorRepresentation(errorRepresentation);
          skuList.add(skuDetails);
        }
      }else{
        SkuDetails skuDetails = new SkuDetails();
        skuDetails.setProductId(null);
        ErrorRepresentation errorRepresentation = new ErrorRepresentation();
        errorRepresentation.setErrorDescription("Invalid Data : "+ productId);
        skuDetails.setErrorRepresentation(errorRepresentation);
        skuList.add(skuDetails);
      }
    }
     products.setSkuDetailsList(skuList);
      return new ResponseEntity<Products>(products, HttpStatus.OK);
    }
    else{
      return new ResponseEntity<Products>(products, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Used to retrieve the product details based on category.
   *
   * @param category
   * @return
   */
  @RequestMapping(value="myretail/category/{category}",produces = MediaType.APPLICATION_XML_VALUE,method = RequestMethod.GET)
  public ResponseEntity<Products> retrieveByCategory(@PathVariable String category){
    Products products=new Products();
    List<SkuDetails> skuDetailsList=productRepository.findByCategory(category);
    if(null!=skuDetailsList && skuDetailsList.size()>0) {
      products.setSkuDetailsList(skuDetailsList);
      return new ResponseEntity<Products>(products, HttpStatus.OK);
    }else{
      SkuDetails skuDetails = new SkuDetails();
      ErrorRepresentation errorRepresentation = new ErrorRepresentation();
      errorRepresentation.setErrorDescription("Invalid Category: "+category);
      skuDetails.setErrorRepresentation(errorRepresentation);
      products.setSkuDetailsList(Arrays.asList(skuDetails));
      return new ResponseEntity<Products>(products, HttpStatus.NOT_FOUND);
    }
  }
}
