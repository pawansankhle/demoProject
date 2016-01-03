package com.petCart.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IUserDao;
import com.petCart.model.Files;
import com.petCart.model.LoginForm;
import com.petCart.model.Permissions;
import com.petCart.model.Roles;
import com.petCart.model.Users;
import com.petCart.model.userPermission;
import com.petCart.model.userRoles;
import com.petCart.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService{
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	IUserDao userDao;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;


	@Override
	public Users createUser(Users user) {
		logger.info("inside @class UserServiceImpl @method createUser entry...");
		try{

			Users user1 = userDao.findUserByName(user);

			if(user1 == null){

				Files file = new Files();
				file.setFile(null);
				String encodedPassword = passwordEncoder.encode(user.getPassword());
				Permissions permission = new Permissions();
				permission.setPermissionname(userPermission.user_permission);
				permission.setDescription("permssion for user who is new");
				Set<Permissions> permissions = new HashSet<Permissions>();
				permissions.add(permission);

				Roles role = new Roles();
				role.setDescription("normal user");
				role.setRollName(userRoles.ROLE_USER);
				role.setPermissions(permissions);

				Set<Roles> roles = new HashSet<Roles>();
				roles.add(role);

				user.setRoles(roles);
				user.setEnabled(true);
				user.setPassword(encodedPassword);
				user.setImage(file);
				return userDao.create(user);
			}else{

				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("@class UserServiceImpl @method createUser cause: "+e.toString());
		}
		return user;
	}

	@Override
	public String Customlogin(String j_username,String j_password,HttpSession session) {
		logger.info("inside @class UserServiceImpl @method Customlogin entry...");
		
		Authentication token = new UsernamePasswordAuthenticationToken(j_username, j_password);
		try{

			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String username = authentication.getName();
			String roles = authentication.getAuthorities().toString().replace("[","").replace("]","");
			String sessionid = session.getId();
			return "{\"status\": \"success\", \"username\": \""+username+"\",\"roles\": \""+roles+"\",\"id\": \""+sessionid+"\"}";

		}catch(org.springframework.security.core.AuthenticationException error){
			error.printStackTrace();
			logger.error("@class UserServiceImpl @method Customlogin  cause: "+error.toString());
			return "{\"status\": \"error\", \"message\": \""+error.getMessage()+"\"}";
		}
	}

	
}


