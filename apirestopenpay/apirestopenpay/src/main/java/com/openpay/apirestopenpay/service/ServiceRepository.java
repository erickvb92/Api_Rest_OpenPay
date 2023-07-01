package com.openpay.apirestopenpay.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.openpay.apirestopenpay.model.Root;
import com.openpay.apirestopenpay.model.modelResponseGeneric;

@Repository
public interface ServiceRepository {

	/*
	 * =============================================================================
	 * ========================= SERVICIOS API
	 * =============================================================================
	 */
	public Root getCharacterData() throws Exception;

	public Root getCharacterDataId(String id) throws Exception;

	/*
	 * =============================================================================
	 * ========================= LOGIN
	 * =============================================================================
	 */
	public modelResponseGeneric getUser(String user, String password, HttpServletRequest request) throws Exception;

}
