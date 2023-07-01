package com.openpay.apirestopenpay.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class modelResponseGeneric {
	private Date timestamp;
	  
	  private String mensaje;
	  
	  @JsonRawValue
	  private String detalles;
	  
	  private String httpCodeMessage;

	  public modelResponseGeneric(String httpCodeMessage, Date timestamp, String message, String details) {
	    super();

	    this.httpCodeMessage=httpCodeMessage;
	    this.timestamp = timestamp;
	    this.mensaje = message;
	    this.detalles = details;
	  }

	  public String getHttpCodeMessage() {
	    return httpCodeMessage;
	  }

	  public Date getTimestamp() {
	    return timestamp;
	  }

	  public String getMensaje() {
	    return mensaje;
	  }

	  public String getDetalles() {
	    return detalles;
	  }

}
