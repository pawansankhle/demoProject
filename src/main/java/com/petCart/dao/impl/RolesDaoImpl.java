package com.petCart.dao.impl;



import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IRolesDAO;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Roles;
import com.petCart.model.userRoles;

@Repository
@Transactional
public class RolesDaoImpl extends GenericDaoImpl<Roles> implements IRolesDAO {

	private final Logger logger = LoggerFactory.getLogger(RolesDaoImpl.class);

	@Override
	public Roles findRoleByName(userRoles roleName) throws Exception {
		logger.debug("inside class "+this.getClass().getName()+"@method findRoleByName entry ");
		try{
			Query query=getEntityManager().createNamedQuery("findRoleByName").setParameter("roleName",roleName);
			return  (Roles) query.getSingleResult(); 
			
		}catch(Exception ex){
			logger.error("inside class "+this.getClass().getName()+"@method findRoleByName cause:"+ex.toString());
		}
		return null;
	}

}
