package com.myretail.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @Dinesh
 */
@XmlRootElement(name="products")
@XmlAccessorType(XmlAccessType.NONE)
public class Products implements Serializable{

  @XmlElement(name = "skuObject")
  private List<SkuDetails> skuDetailsList;

  public List<SkuDetails> getSkuDetailsList() {
    return skuDetailsList;
  }

  public void setSkuDetailsList(List<SkuDetails> skuDetailsList) {
    this.skuDetailsList = skuDetailsList;
  }
}
