package com.myretail.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Aravind
 */
@XmlRootElement(name="SKU")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder ={"productId","skuId","name","category","priceDetails","errorRepresentation"} )
@Entity
@Table(name="SKU_DETAILS")
public class SkuDetails implements Serializable{

  @XmlAttribute
  private Integer productId;

  @XmlElement
  private String skuId;

  @XmlElement
  private String name;

  @XmlElement
  private String category;

  @XmlElement
  private PriceDetails priceDetails;

  @XmlElement(name="error")
  private ErrorRepresentation errorRepresentation;

  @XmlTransient
  private Date lastUpdateTime;

  @Id
  @GeneratedValue
  @Column(name = "PRODUCT_ID")
  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  @Column(name = "SKU_ID")
  public String getSkuId() {
    return skuId;
  }

  public void setSkuId(String skuId) {
    this.skuId = skuId;
  }

  @Column(name = "PRODUCT_NAME")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name="PRODUCT_CATEGORY")
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="PRODUCT_ID")
  public PriceDetails getPriceDetails() {
    return priceDetails;
  }

  public void setPriceDetails(PriceDetails priceDetails) {
    this.priceDetails = priceDetails;
  }

  @Column(name="UPDATED_TIMESTAMP")
  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  @Transient
  public ErrorRepresentation getErrorRepresentation() {
    return errorRepresentation;
  }

  public void setErrorRepresentation(ErrorRepresentation errorRepresentation) {
    this.errorRepresentation = errorRepresentation;
  }
}
