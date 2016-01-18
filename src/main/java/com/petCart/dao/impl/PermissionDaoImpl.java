package com.petCart.dao.impl;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IPermissionDao;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Permissions;
import com.petCart.model.userPermission;

@Repository
@Transactional
public class PermissionDaoImpl extends GenericDaoImpl<Permissions> implements IPermissionDao {
	private final Logger logger = LoggerFactory.getLogger(PermissionDaoImpl.class);
	

	@Override
	public Permissions findPermissionByName(userPermission permissionname) {
		logger.debug("inside class "+this.getClass().getName()+"@method findPermissionByName entry ");
		try{
			Query query=getEntityManager().createNamedQuery("findPermissionsByName").setParameter("permissionname",permissionname);
			return  (Permissions) query.getSingleResult(); 
			
		}catch(Exception ex){
			logger.error("inside class "+this.getClass().getName()+"@method findPermissionByName cause:"+ex.toString());
		}
		return null;
		
	}

}
