package com.petCart.dao.impl;

import java.util.List;

import javassist.NotFoundException;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

	@Override
	public Long countAll() {
		   return ((Long)this.getEntityManager().createQuery("select count(d) from Department d").getSingleResult());		
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


	/*@Override
	public List<Product> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		logger.info("GenericDaoImpl-search method start With param maxLimit : "+upperLimit+" , minLimit:"+lowerLimit+", orderby : "+orderBy+" ,orderType : "+orderType);

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
				final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
			    final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(getType());
			    final Root<Product> root = criteriaQuery.from(getType());
			    criteriaQuery.select(root).orderBy(criteriaBuilder.desc(root.get("id")));
			    
			    TypedQuery<Product> typedQuery = getEntityManager().createQuery(criteriaQuery);
				if(lowerLimit>=0){
					typedQuery.setFirstResult(lowerLimit);
				}
				if(upperLimit>=0){
					typedQuery.setMaxResults(upperLimit-lowerLimit+1);
				}
				logger.info("Inside  @class :"+this.getClass().getName()+" @Method :searchSiteForIssue @Return: ");
				return typedQuery.getResultList();
			
			}

		}catch(Exception ex){
			try {
				throw new NotFoundException("Invalid search query.");
			} catch (NotFoundException e) {
				logger.error("inside @class GenericDaoImpl @method: search cause:"+e.toString());
				e.printStackTrace();
				return null;
			}
		}

		
	}*/
	
	@Override
	public List<Product> search(SearchContext searchContext,
			Integer lowerLimit, Integer upperLimit, String orderBy,
			String orderType) {
		// TODO Auto-generated method stub
		return super.search(searchContext, lowerLimit, upperLimit, orderBy, orderType);
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
