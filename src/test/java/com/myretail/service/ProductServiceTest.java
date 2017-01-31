package com.myretail.service;

import com.myretail.MyRetailTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Dinesh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = ProductService.class)
public class ProductServiceTest extends MyRetailTest {

  protected MockMvc mvc;

  @Autowired
  protected WebApplicationContext webApplicationContext;

  @Autowired
  ProductService productService;


  private static final String productURI="/myretail/product/{productId}";

  private static final String catalogURI="/myretail/category/{category}";

  @Before
  public void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }


  @Test
  public void testGetProductId() throws Exception {

    String productId="5555,55433";

    MvcResult result = mvc.perform(
        MockMvcRequestBuilders.get(productURI,productId).accept(
            MediaType.APPLICATION_XML)).andReturn();

    String content = result.getResponse().getContentAsString();

    int status = result.getResponse().getStatus();

    Assert.assertEquals("Success - expected HTTP status 200", 200, status);

  }
  @Test
  public void testGetProductIdForNull() throws Exception {

    String productId=null;

    MvcResult result = mvc.perform(
        MockMvcRequestBuilders.get(productURI,productId).accept(
            MediaType.APPLICATION_XML)).andReturn();

    String content = result.getResponse().getContentAsString();
    int status = result.getResponse().getStatus();

    Assert.assertEquals("failure - expected HTTP status 404", 404, status);

  }

  @Test
  public void testGetProductIdForPostRequest() throws Exception {

    String productId="5555";

    MvcResult result = mvc.perform(
        MockMvcRequestBuilders.post(productURI, productId).accept(
            MediaType.APPLICATION_XML)).andReturn();

    String content = result.getResponse().getContentAsString();
    int status = result.getResponse().getStatus();

    Assert.assertEquals("failure - expected HTTP method not found 405", 405, status);

  }
  @Test
  public void testGetCategoryId() throws Exception {

    String category="baby";

    MvcResult result = mvc.perform(
        MockMvcRequestBuilders.get(catalogURI,category).accept(
            MediaType.APPLICATION_XML)).andReturn();

    String content = result.getResponse().getContentAsString();

    int status = result.getResponse().getStatus();

    Assert.assertEquals("Success - expected HTTP status 200", 200, status);
  }

  @Test
  public void testGetCategoryIdSizeCheck() throws Exception {

    String category="toys";

    MvcResult result = mvc.perform(
        MockMvcRequestBuilders.get(catalogURI,category).accept(
            MediaType.APPLICATION_XML)).andReturn();

    String content = result.getResponse().getContentAsString();

    int status = result.getResponse().getStatus();

    Assert.assertEquals("Success - expected HTTP status 200", 200, status);

  }
  @Test
  public void testGetCategoryIdForNull() throws Exception {

    String category=null;

    MvcResult result = mvc.perform(
        MockMvcRequestBuilders.get(catalogURI,category).accept(
            MediaType.APPLICATION_XML)).andReturn();

    String content = result.getResponse().getContentAsString();
    int status = result.getResponse().getStatus();

    Assert.assertEquals("failure - expected HTTP status 404", 404, status);
    Assert.assertTrue(
        "failure - expected HTTP response body to have a value",
        content.trim().length() == 0);

  }

  @Test
  public void testGetCategoryIdForInvalidData() throws Exception {

    String category=null;

    MvcResult result = mvc.perform(
        MockMvcRequestBuilders.get(catalogURI,category).accept(
            MediaType.APPLICATION_XML)).andReturn();

    String content = result.getResponse().getContentAsString();
    int status = result.getResponse().getStatus();

    Assert.assertEquals("failure - expected HTTP status 404", 404, status);

    Assert.assertTrue(
        "failure - expected HTTP response body to have a value",
        content.trim().length() == 0);

  }

  @Test
  public void testGetCategoryIdForPostRequest() throws Exception {

    String category="invalidReq";

    MvcResult result = mvc.perform(
        MockMvcRequestBuilders.post(catalogURI, category).accept(
            MediaType.APPLICATION_XML)).andReturn();

    String content = result.getResponse().getContentAsString();
    int status = result.getResponse().getStatus();

    Assert.assertEquals("failure - expected HTTP method not found 405", 405, status);

  }
  @Test
   public void testProductIdForInvalidData(){

    productService.retrieveByProductId(null);
    productService.retrieveByProductId("@!@#!");
    productService.retrieveByProductId(",,,,,");
    productService.retrieveByProductId("1,2");
  }

  @Test
  public void testCategoryIdForInvalidData(){

    productService.retrieveByCategory(null);
    productService.retrieveByCategory("@!@#!");
    productService.retrieveByCategory(",,,,,");
    productService.retrieveByCategory("1,2");
    productService.retrieveByCategory("toysss");
  }

}
