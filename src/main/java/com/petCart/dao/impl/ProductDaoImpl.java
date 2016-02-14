package com.petCart.dao.impl;

import java.util.List;

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

import com.petCart.dao.IProductDAO;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Product;


@Repository
@Transactional
public class ProductDaoImpl extends GenericDaoImpl<Product> implements IProductDAO{

	private final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);



	public Product addProduct(Product p) {
		return super.create(p);
	}


	public void deleteProduct(Product p) {
		super.delete(p);
	}


	public Product find(Product p) {
		return super.find(p);
	}


	public Product findById(Long id) {
		logger.info("inside @class ProductDaoimpl @method: findById entry...");
		try{
			Query query=getEntityManager().createNamedQuery("findProductById").setParameter("id", id);
			return  (Product) query.getSingleResult();
		  }catch(Exception ex){
			logger.error("Exception occured @class: ProductDaoimpl @method: findById @cause: "+ex.getMessage());
		}
		return null;


	}




	@Override
	public List<Product> findAllProduct() {
		logger.info("inside @class ProductDaoimpl @method: findAll entry...");
		try{
			Query query=getEntityManager().createNamedQuery("findAllProduct");
			return  query.getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Exception occured @class: ProductDaoimpl @method: findAll @cause: "+ex.getMessage());
		}
		return null;
	}


	@Override
	public List<Product> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		logger.info("inside @class ProductDaoimpl @method: search entry...");
		try{
			SearchCondition<Product> sc = searchContext.getCondition(Product.class);
			JPATypedQueryVisitor<Product> visitor =  new JPATypedQueryVisitor<Product>(getEntityManager(), Product.class);
			if(sc!=null){
				sc.accept(visitor);
				visitor.visit(sc);
				TypedQuery<Product> typedQuery = visitor.getQuery();
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
					logger.error("inside @class ProductDaoimpl @method: search cause:"+e.toString());
					e.printStackTrace();
				}
			}

		}catch(Exception ex){
			logger.error("inside @class ProductDaoimpl @method: search cause:"+ex.toString());

		}

		return null;
	}


	@Override
	public Product viewProduct(Long id) {
		logger.info("inside @class ProductDaoimpl @method: viewProduct entry...");
		try{
			Query query=getEntityManager().createNamedQuery("viewProduct").setParameter("id",id);
			return  (Product)query.getSingleResult();

		}catch(Exception e){
			e.printStackTrace();
			logger.error("inside @class ProductDaoimpl @method: viewProduct cause:"+e.toString());
		}
		return null;
	}


}
