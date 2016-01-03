package com.petCart.springsecurity.security;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class DatabasePasswordSecurerBean extends JdbcDaoSupport {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	private Logger logger = LoggerFactory.getLogger(DatabasePasswordSecurerBean.class);
	
	public void secureDatabase(){
		logger.info("inside @class DatabasePasswordSecurerBean  @method secureDatabase entry...");
		getJdbcTemplate().query("select username,password from users",new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
			   String username = rs.getString(1);
			   String password = rs.getString(2);
			   
			   //UserDetails user = userDetailsService.loadUserByUsername(username);
			   String encodedPassword = passwordEncode.encode(password);
			   getJdbcTemplate().update("update users set password = ? where username = ?",
					   encodedPassword,username);
			  
			 logger.info("@class DatabasePasswordSecurerBean  @method secureDatabase updating password for user: "+username + "to: "+password);
			}
			  
			
		});
		
	}
	
}
