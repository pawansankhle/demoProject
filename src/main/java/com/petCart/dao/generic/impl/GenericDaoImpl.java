package com.petCart.dao.generic.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javassist.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import com.petCart.dao.generic.IGenericDao;

public abstract class GenericDaoImpl<T> implements IGenericDao<T> {


	Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> type;

	public GenericDaoImpl(){
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}


	@Override
	public long countAll(final Map<String, Object> params) {
		final StringBuffer queryString = new StringBuffer("SELECT count(o) from");
		queryString.append(type.getSimpleName()).append("o");
		queryString.append(this.getQueryClauses(params, null));
		final Query query = this.entityManager.createQuery(queryString.toString());

		return (Long) query.getSingleResult();

	}


	private Object getQueryClauses(Map<String, Object> params, Object object) {

		return null;
	}

	@Override
	public T create(final T t) {
		return this.entityManager.merge(t);
	}

	@Override
	public void delete(Object id) {
		this.entityManager.remove(this.entityManager.getReference(type, id));

	}

	@Override
	public T find(Object id) {
		return this.entityManager.find(type, id);
	}

	@Override
	public T update(final T t) {
		return this.entityManager.merge(t);
	}





	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}


	@Override
	public List<T> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		logger.info("GenericDaoImpl-search method start With param maxLimit : "+upperLimit+" , minLimit:"+lowerLimit+", orderby : "+orderBy+" ,orderType : "+orderType);

		try{
			SearchCondition<T> sc = searchContext.getCondition(getType());
			JPATypedQueryVisitor<T> visitor =  new JPATypedQueryVisitor<T>(getEntityManager(), getType());
			if(sc!=null){
				
				logger.info("search condition is: "+sc.toString());
				logger.info("search condition is: "+visitor.toString());
				sc.accept(visitor);
				visitor.visit(sc);
			
				TypedQuery<T> typedQuery = visitor.getQuery();
				if(lowerLimit>=0){
		    		typedQuery.setFirstResult(lowerLimit);
		    	}
		    	if(upperLimit>=0){
		    		typedQuery.setMaxResults(upperLimit-lowerLimit+1);
		    	}
		    	
				return typedQuery.getResultList();
			 

			}else{
				final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
			    final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getType());
			    final Root<T> root = criteriaQuery.from(getType());
			    if(orderBy.equalsIgnoreCase("desc")){
			    	criteriaQuery.select(root).orderBy(criteriaBuilder.desc(root.get(orderBy)));
			    }else if(orderBy.equalsIgnoreCase("asc")){
			    	criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get(orderBy)));
			    }
			    
			   
			    TypedQuery<T> typedQuery = getEntityManager().createQuery(criteriaQuery);
				if(lowerLimit>=0){
					typedQuery.setFirstResult(lowerLimit);
				}
				if(upperLimit>=0){
					typedQuery.setMaxResults(upperLimit-lowerLimit+1);
				}
				logger.info("Inside  @class :"+this.getClass().getName()+" @Method :searchSiteForIssue @Return");
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

		
	}
	
	
	
}
