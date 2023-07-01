package com.openpay.apirestopenpay.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class JwtResponse {
	private Date timestamp;
	  
	  private String mensaje;
	  
	  @JsonRawValue
	  private String detalles;
	  
	  private String httpCodeMessage;
	  
	  private static final long serialVersionUID = -8091879091924046844L;
	  
		private final String jwttoken;

		public String getToken() {
			return this.jwttoken;
		}

	  public JwtResponse(String httpCodeMessage, Date timestamp, String message, String details, String token) {
	    super();

	    this.httpCodeMessage=httpCodeMessage;
	    this.timestamp = timestamp;
	    this.mensaje = message;
	    this.detalles = details;
	    this.jwttoken = token;
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
