package com.openpay.apirestopenpay.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.openpay.apirestopenpay.controller.RestApiControllerMarvel;
import com.openpay.apirestopenpay.entitys.Bitacora;
import com.openpay.apirestopenpay.model.Root;
import com.openpay.apirestopenpay.model.modelResponseGeneric;
import com.openpay.apirestopenpay.model.responseModelGetUser;
import com.openpay.apirestopenpay.service.BitacoraService;
import com.openpay.apirestopenpay.service.ServiceRepository;
import com.openpay.apirestopenpay.utils.Utils;

@Service
public class ServiceRepositoryImpl implements ServiceRepository {

	private static final Logger log = LogManager.getLogger(ServiceRepositoryImpl.class);

	 @Autowired
	 BitacoraService bitacoraService;
	 
	@Override
	public Root getCharacterData() throws Exception {
		Root respuesta = null;
		try {
			log.info("Log level: INFO, Peticion realizada desde apirestopenpay");

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			RestTemplate plantilla = new RestTemplate();
			String url = "http://localhost:8181/api/openpay/character";
			log.info("Log level: INFO: " + "la URL es: " + url);

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<Root> response = plantilla.exchange(url, HttpMethod.GET, entity, Root.class);
			respuesta = response.getBody();

			log.info("Log level: INFO: Peticion Exitosa desde apirestopenpay");

			Bitacora bitacora = new Bitacora(Utils.getFecha(), "erick", "Llamada al servicio : "+url);
			bitacoraService.saveOrUpdate(bitacora);
			log.info("Log level: INFO: Datos guardados en bitacora");
			
		} catch (Exception e) {
			log.error("Log level: ERROR: " + e.getMessage());
		}
		return respuesta;
	}

	@Override
	public Root getCharacterDataId(String id) throws Exception {
		Root respuesta = null;
		try {
			log.info("Log level: INFO, Peticion realizada desde apirestopenpay");

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			RestTemplate plantilla = new RestTemplate();
			String url = "http://localhost:8181/api/openpay/characterId?id="+id;
			log.info("Log level: INFO: " + "la URL es: " + url);

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<Root> response = plantilla.exchange(url, HttpMethod.GET, entity, Root.class);
			respuesta = response.getBody();

			log.info("Log level: INFO: Peticion Exitosa desde apirestopenpay");

			Bitacora bitacora = new Bitacora(Utils.getFecha(), "erick", "Llamada al servicio : "+url);
			bitacoraService.saveOrUpdate(bitacora);
			log.info("Log level: INFO: Datos guardados en bitacora");
			
		} catch (Exception e) {
			log.error("Log level: ERROR: " + e.getMessage());
		}
		return respuesta;
	}

	@Override
	public modelResponseGeneric getUser(String user, String password, HttpServletRequest request) throws Exception {
		responseModelGetUser respuesta = null;
		String mensaje = "";
		String codigo = "";
		Object json = "";
		try {
			codigo = "200";
			Gson gson = new Gson();
			json = gson.toJson(user);
			mensaje = "ok Exito: Acceso correcto";
			
			Bitacora bitacora = new Bitacora(Utils.getFecha(), "erick", "ok Exito: Acceso correcto ");
			bitacoraService.saveOrUpdate(bitacora);
			log.info("Log level: INFO: Datos guardados en bitacora");
		} catch (Exception e) {
			mensaje = "Error: Excepcion Ocurrida";
			log.error("Log level: ERROR " + e.getMessage());
			codigo = "404";
		}
		if (json.equals("")) {
			Gson gson = new Gson();
			json = gson.toJson("");
		}
		return new modelResponseGeneric(codigo, new Date(), mensaje, "" + json);
	}
}
