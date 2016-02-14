package com.petCart.dao.impl;

import java.util.List;
import java.util.Map;

import javassist.NotFoundException;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.jaxrs.ext.search.jpa.JPATypedQueryVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IUserDao;
import com.petCart.dao.generic.impl.GenericDaoImpl;

import com.petCart.model.Product;
import com.petCart.model.Users;

@Repository
@Transactional
public class UserDaoImpl extends GenericDaoImpl<Users> implements IUserDao{

	private final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

	@Override
	public long countAll(Map<String, Object> params) {
		
		return super.countAll(params);
	}

	@Override
	public Users create(Users user) {
		logger.info("inside #class UserDaoImpl #method create entry...");
		try{
			return super.create(user);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("#class UserDaoImpl #method create cause: "+e.toString());
		}
		return null;
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(id);
	}

	@Override
	public Users find(Object id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}

	@Override
	public Users update(Users t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}

	@Override
	public Users findUserByName(String username) {
		logger.info("inside @class UserDaoimpl @method: findUserByName entry...");
		try{
			Query query=getEntityManager().createNamedQuery("findUserByName").setParameter("username",username);
			return  (Users) query.getSingleResult();
		  }catch(Exception ex){
			logger.error("Exception occured @class: UserDaoimpl @method: findUserByName @cause: "+ex.getMessage());
		}
		return null;
		
	}
	
	@Override
	public List<Users> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		logger.info("inside @class UserDaoimpl @method: search entry...");
		try{
			SearchCondition<Users> sc = searchContext.getCondition(Users.class);
			JPATypedQueryVisitor<Users> visitor =  new JPATypedQueryVisitor<Users>(getEntityManager(), Users.class);
			if(sc!=null){
				sc.accept(visitor);
				visitor.visit(sc);
				TypedQuery<Users> typedQuery = visitor.getQuery();
				if(lowerLimit>=0){
		    		typedQuery.setFirstResult(lowerLimit);
		    	}
		    	if(upperLimit>=0){
		    		typedQuery.setMaxResults(upperLimit-lowerLimit+1);
		    	}
				return typedQuery.getResultList();
			 

			}else{
				try {
					throw new NotFoundException("Invalid search query.");
				} catch (NotFoundException e) {
					logger.error("inside @class UserDaoimpl @method: search cause:"+e.toString());
					e.printStackTrace();
				}
			}

		}catch(Exception ex){
			logger.error("inside @class UserDaoimpl @method: search cause:"+ex.toString());

		}

		return null;
	}

	@Override
	public List<Users> findAllUsers() {
		try{
			Query query=getEntityManager().createNamedQuery("findAllUsers");
			return  query.getResultList();
		}catch(Exception ex){
			logger.error("inside @class UserDaoimpl @method: findAllUsers cause:"+ex.toString());
		}
		return null;
	}

	

	
}
