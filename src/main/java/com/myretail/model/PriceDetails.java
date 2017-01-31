package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Dinesh
 */

@XmlRootElement(name="priceDetails")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="PRICE_DETAILS")
public class PriceDetails implements Serializable {

  @XmlElement
  private BigDecimal price;

  @XmlTransient
  private SkuDetails skuDetails;

  @Column(name="PRICE")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Id
  @OneToOne
  @JoinColumn(name="PRODUCT_ID")
  @JsonBackReference
  public SkuDetails getSkuDetails() {
    return skuDetails;
  }

  public void setSkuDetails(SkuDetails skuDetails) {
    this.skuDetails = skuDetails;
  }


}
