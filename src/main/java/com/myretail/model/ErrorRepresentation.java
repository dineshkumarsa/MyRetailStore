package com.myretail.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Dinesh
 */
@XmlRootElement(name="error")
@XmlAccessorType(XmlAccessType.NONE)
public class ErrorRepresentation {

  @XmlElement
  private String errorDescription;

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }
}
