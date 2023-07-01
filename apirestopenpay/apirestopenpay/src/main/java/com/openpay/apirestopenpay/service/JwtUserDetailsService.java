package com.openpay.apirestopenpay.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openpay.apirestopenpay.config.ConfigurationClass;
import com.openpay.apirestopenpay.model.UserDetail;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private ConfigurationClass c;
	
	 @Override
	    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		 if ("erick".equals(s)) {
				return new User("erick", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
						new ArrayList<>());
			} else {
				throw new UsernameNotFoundException("User not found with username: " + s);
			}
	    }
}