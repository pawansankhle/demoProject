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

import com.petCart.dao.ISupplierDao;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Supplier;
import com.petCart.model.Users;

@Repository
@Transactional
public class SupplierDaoimpl extends GenericDaoImpl<Supplier>  implements ISupplierDao {

	private final Logger logger = LoggerFactory.getLogger(SupplierDaoimpl.class);
	
	@Override
	public long countAll(Map<String, Object> params) {
		return super.countAll(params);
	}

	@Override
	public Supplier create(Supplier t) {
		logger.info("inside #class SupplierDaoimpl #method create entry...");
		try{
			return super.create(t);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("#class SupplierDaoimpl #method create cause: "+e.toString());
		}
		return null;
	}

	@Override
	public void delete(Object id) {
		super.delete(id);
	}

	@Override
	public Supplier find(Object id) {
		return super.find(id);
	}

	@Override
	public Supplier update(Supplier t) {
		return super.update(t);
	}
	
	@Override
	public List<Supplier> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		logger.info("inside @class SupplierDaoimpl @method: search entry...");
		try{
			SearchCondition<Supplier> sc = searchContext.getCondition(Supplier.class);
			JPATypedQueryVisitor<Supplier> visitor =  new JPATypedQueryVisitor<Supplier>(getEntityManager(), Supplier.class);
			if(sc!=null){
				sc.accept(visitor);
				visitor.visit(sc);
				TypedQuery<Supplier> typedQuery = visitor.getQuery();
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
					logger.error("inside @class SupplierDaoimpl @method: search cause:"+e.toString());
					e.printStackTrace();
				}
			}

		}catch(Exception ex){
			logger.error("inside @class SupplierDaoimpl @method: search cause:"+ex.toString());

		}
         return null;
	   }
	
	@Override
	public List<Supplier> findAllSuppliers() {
		try{
			Query query=getEntityManager().createNamedQuery("findAllSuppliers");
			return  query.getResultList();
		}catch(Exception ex){
			logger.error("inside @class SupplierDaoimpl @method: findAllSuppliers cause:"+ex.toString());
		}
		return null;
	}

}
