package com.petCart.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IPermissionDao;
import com.petCart.dao.IRolesDAO;
import com.petCart.dao.ISupplierDao;
import com.petCart.dao.IUserDao;
import com.petCart.model.Files;
import com.petCart.model.LoginForm;
import com.petCart.model.Permissions;
import com.petCart.model.Product;
import com.petCart.model.Roles;
import com.petCart.model.Supplier;
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
	IRolesDAO rolesDao;

	@Autowired
	ISupplierDao supplierDao;


	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;


	@Override
	public Users createUser(Users user) {
		logger.info("inside @class UserServiceImpl @method createUser entry...");
		try{

			Users user1 = userDao.findUserByName(user.getUsername());
			Roles role = rolesDao.findRoleByName(userRoles.ROLE_USER);
			System.out.println("role is:"+role.toString());
			if(user1 == null){

				Files file = new Files();
				file.setFile(null);
				String encodedPassword = passwordEncoder.encode(user.getPassword());
				/*Permissions permission = new Permissions();
				permission.setPermissionname(userPermission.user_permission);
				permission.setDescription("permssion for user who is new");
				Set<Permissions> permissions = new HashSet<Permissions>();
				permissions.add(permission);
				 */
				/*Roles role = new Roles();
				role.setDescription("normal user");
				role.setRollName(userRoles.ROLE_USER);
				role.setPermissions(permissions);
				 */
				Set<Roles> roles = new HashSet<Roles>();
				roles.add(role);
				System.out.println("roles is: "+roles.toString());

				user.setRoles(roles);
				user.setEnabled(true);
				user.setPassword(encodedPassword);
				user.setImage(file);
				return userDao.create(user);
			}else{

				return null;
			}
		}catch(Exception e){
			logger.error("@class UserServiceImpl @method createUser cause: "+e.toString());
		}
		return user;
	}

	@Override
	public Users Customlogin(String j_username,String j_password,HttpSession session) {
		logger.info("inside @class UserServiceImpl @method Customlogin entry...");

		Authentication token = new UsernamePasswordAuthenticationToken(j_username, j_password);
		try{
			logger.info("inside @class userServiceImpl @method customlogin token is: "+token.toString());
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			session.setAttribute("authentication",authentication);
			String username = authentication.getName();
			Users user = userDao.findUserByName(username);
			if(user!=null && authentication!=null)
				return user;
		}catch(org.springframework.security.core.AuthenticationException ex){
			ex.printStackTrace();
			logger.error("@class UserServiceImpl @method Customlogin  cause: "+ex.toString());
			return null;
		}
		return null;
	}

	@Override
	public void customLogout(HttpServletRequest request,HttpSession session) {
		logger.info("inside @class UserServiceImpl @method customLogout entry...");

		try{
			Authentication authentication = (Authentication) session.getAttribute("authentication");
			if (authentication != null){ 
				new SecurityContextLogoutHandler().logout(request,null,authentication);
			}
			SecurityContextHolder.getContext().setAuthentication(null);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("@class UserServiceImpl @method customLogout  cause: "+ex.toString());

		}
	}

	@Override
	public Users findByName(String username) {
		logger.info("inside @class UserServiceImpl @method findByName entry...");
		try{
			return userDao.findUserByName(username);
		}catch(Exception ex){
			logger.error("@class UserServiceImpl @method findByName  cause: "+ex.toString());
			return null;
		}
	}

	@Override
	public String updateProfile(HttpSession session, Users user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> search(SearchContext context,Integer lowerLimit, Integer upperLimit,
			String orderBy, String orderType) {
		logger.info("inside @class UserServiceImpl  @mehod search");
		try{
			return userDao.search(context,lowerLimit,upperLimit,orderBy,orderType);
		}catch(Exception ex){
			logger.error("@class UserServiceImpl @method search  cause: "+ex.toString());
			return null;

		}
	}

	@Override
	public List<Users> getAllUsers() {
		logger.info("inside @class UserServiceImpl  @mehod getAllUsers");
		try{
			return userDao.findAllUsers();
		}catch(Exception ex){
			logger.error("@class UserServiceImpl @method getAllUsers  cause: "+ex.toString());
			return null;

		}
	}




	@Override
	public Users changeUserState(Authentication auth,String action,Integer id) {
		try{
			Users user = userDao.find(id);
			if(user != null){
				if(action.equalsIgnoreCase("disable")){
					user.setEnabled(false);
				}else if(action.equalsIgnoreCase("enable")){
					user.setEnabled(true);
				}
				return userDao.update(user);
			}

		}catch(EntityNotFoundException ex){
			logger.error("@class UserServiceImpl @method disableUser  cause: "+ex.toString());
			return null;

		}catch (Exception e) {
			logger.error("@class UserServiceImpl @method disableUser  cause: "+e.toString());
			return null;
		}


		return null;
	}

	@Override
	public String deleteUser(Authentication authentication, Integer id) {
		logger.info("inside @class UserService @method deleteUser id is: "+id);
		try{
			Users user = userDao.find(id);
			for(Roles role : user.getRoles()){
				logger.info("user role is: "+role);

				if(user !=null && role.getRoleName().equals(userRoles.ROLE_USER)){
					user.setImage(null);
					user.setRoles(null);
					userDao.update(user);
					userDao.delete(id);
				}else if(user !=null && role.getRoleName().equals(userRoles.ROLE_SUPPLIER)){
					Supplier supplier = supplierDao.findSupplierByDetailId(user);
					if(supplier!=null){
						supplier.setDeleted(true);
						user.setEnabled(false);
						userDao.update(user);
						supplierDao.update(supplier);
					}
				}
			}

			return "{\"status\":\"ok\",\"msg\":\"User Deleted Successfully...\"}";

		}catch(EntityNotFoundException ex){
			logger.error("@class UserServiceImpl @method deleteUser  cause: "+ex.toString());
			return null;

		}catch (Exception e) {
			logger.error("@class UserServiceImpl @method deleteUser  cause: "+e.toString());
			return null;
		}
	}

	@Override
	public Users updateUser(Users user) {
		try{
			logger.info("@class @method entry userid id"+user.getId());
			Users user1 = userDao.findUserById(user.getId());
			if(user1!=null){
				user.setRoles(user1.getRoles());
				user.setPassword(user1.getPassword());
				return userDao.update(user);
			}
			
			
		}catch(Exception ex){
			logger.error("@class @method cause: "+ex.toString());
			return null;
		}
		return null;
	}

	@Override
	public String changePassword(String username,String oldPassword, String newPassword) {
		try{
			Users user = userDao.findUserByName(username);
			//String oldpwd = passwordEncoder.encode(oldPassword);
			if(user !=null){
				user.setPassword(passwordEncoder.encode(newPassword));
			    userDao.update(user);
				
			}
			return "{\"status\": \"success\"}";
			
		}catch(Exception ex){
			logger.error("@class userService @method changePassword cause: "+ex.toString());
			return null;
		}
		
}


    

}


