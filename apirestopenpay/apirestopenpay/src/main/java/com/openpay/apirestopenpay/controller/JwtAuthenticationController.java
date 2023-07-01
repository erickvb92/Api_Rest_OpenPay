package com.openpay.apirestopenpay.controller;

import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openpay.apirestopenpay.config.ConfigurationClass;
import com.openpay.apirestopenpay.config.JwtTokenUtil;
import com.openpay.apirestopenpay.model.JwtRequest;
import com.openpay.apirestopenpay.model.JwtResponse;
import com.openpay.apirestopenpay.model.modelResponseGeneric;
import com.openpay.apirestopenpay.serviceImpl.ServiceRepositoryImpl;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	ServiceRepositoryImpl serviceRepositoryImpl;

	@Autowired
	private ConfigurationClass c;

	private static final Logger log = LogManager.getLogger(JwtAuthenticationController.class);

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest,
			HttpServletRequest request) throws Exception {
		modelResponseGeneric respuesta = null;
		String token = null;
		try {
			log.info(
					"_________________________________________________INICIO___________________________________________________________");
			String user = authenticationRequest.getUsername();
			String password = authenticationRequest.getPassword();

			respuesta = serviceRepositoryImpl.getUser(user, password, request);

			if (respuesta.getHttpCodeMessage().equals("200")) {
				c.setPass(password);
				authenticate(user, password);

				final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(user);

				token = jwtTokenUtil.generateToken(userDetails);
			}

		} catch (Exception e) {
			token = null;
			//e.printStackTrace();
			log.error("Log level: ERROR " + e.getMessage());
			return new JwtResponse("404", new Date(), "Error: Usuario o password incorrecto", null, token);
		}
		log.info(
				"_________________________________________________FIN___________________________________________________________");

		return new JwtResponse(respuesta.getHttpCodeMessage(), new Date(), respuesta.getMensaje(),
				"" + respuesta.getDetalles(), token);
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			 throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			 throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
