package com.petCart.dao.impl;

import java.util.List;
import java.util.Map;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IDepartmentDAO;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Department;
import com.petCart.model.Product;

@Repository
@Transactional
public class DepartmentDaoImpl extends GenericDaoImpl<Department> implements IDepartmentDAO{

	private final Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class);

	
	@Override
	public Long countAll() {
		   return ((Long)this.getEntityManager().createQuery("select count(d) from Department d").getSingleResult());		
		}

	@Override
	public Department create(Department dept) {
		return super.create(dept);
	}

	@Override
	public void delete(Object dept) {
		super.delete(dept);
		
	}

	@Override
	public Department find(Object dept) {
		return super.find(dept);
	}

	@Override
	public Department update(Department dept) {
		return super.update(dept);
	}

	

	@Override
	public List<Department> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		logger.info("inside @class DepartmentDaoimpl @method: search entry...");
		
			try{
				SearchCondition<Department> sc = context.getCondition(Department.class);
				JPATypedQueryVisitor<Department> visitor =  new JPATypedQueryVisitor<Department>(getEntityManager(), Department.class);
				if(sc!=null){
					sc.accept(visitor);
					visitor.visit(sc);
					TypedQuery<Department> typedQuery = visitor.getQuery();
					if(lowerLimit>=0){
			    		typedQuery.setFirstResult(lowerLimit);
			    	}
			    	if(upperLimit>=0){
			    		typedQuery.setMaxResults(upperLimit-lowerLimit+1);
			    	}
					return typedQuery.getResultList();
				 

				}else if(orderBy !=null && orderType!=null){
					/*here impl orderby and order type loginc*/
				}else if(lowerLimit !=null && upperLimit!=null){
					CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
					CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
					Root<Department> from = criteriaQuery.from(Department.class);
					CriteriaQuery<Department> select = criteriaQuery.select(from);
					TypedQuery<Department> typedQuery = getEntityManager().createQuery(select);
					
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
						logger.error("inside @class DepartmentDaoimpl @method: search cause:"+e.toString());
						e.printStackTrace();
					}
				}

			}catch(Exception ex){
				ex.printStackTrace();
				logger.error("inside @class DepartmentDaoimpl @method: search cause:"+ex.toString());

			}
			
			return null;
       }

	@Override
	public List<Department> findAllDepartment() {
		logger.info("inside @class DepartmentDaoimpl @method: findAllDepartment entry...");
		try{
			Query query=getEntityManager().createNamedQuery("findAllDepartment");
			return  query.getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Exception occured @class: ProductDaoimpl @method: findAllDepartment @cause: "+ex.getMessage());
		}
		return null;
	}
	
	

}
