package com.petCart.dao.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IReviewDao;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Product;
import com.petCart.model.Review;
import com.petCart.model.Users;

@Repository
@Transactional
public class ReviewDaoImpl  extends GenericDaoImpl<Review> implements IReviewDao{
	
	Logger logger = LoggerFactory.getLogger(ReviewDaoImpl.class);
	@Override
	public List<Review> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		
		return super.search(searchContext, lowerLimit, upperLimit, orderBy, orderType);
	}

	@Override
	public Integer getRatingByProductId(Product product) {
		try{
			Query query = getEntityManager().createNamedQuery("Review.ratingbyProductId").setParameter("product", product);
			return (Integer) query.getSingleResult();
		}catch(EntityNotFoundException ex){
			logger.info("@class: ReviewDaoImpl  @method: getRatingByProductId cause: "+ex.toString());
			return null;
		}
		
	}
	
	@Override
	public Review getReviewByUserandProduct(Integer userid,Integer productid) {
		logger.info("userid: "+userid+"productid: "+productid);
		try{
			Query query = getEntityManager().createNamedQuery("Review.byUserAndProduct").setParameter("customer", userid).setParameter("product",productid);
			return (Review)query.getSingleResult();
		}catch(EntityNotFoundException ex){
			logger.info("@class: ReviewDaoImpl  @method: getReviewByUserandProduct cause: "+ex.toString());
			return null;
		}catch (NoResultException e) {
			logger.info("@class: ReviewDaoImpl  @method: getReviewByUserandProduct cause: "+e.toString());
			return null;
		}
		
	}

	@Override
	public Review addReview(Review review) {
		logger.info("@class: ReviewDaoImpl @method: addReview  obj is: "+review.toString());
		try{
			return super.create(review);
		}catch(Exception ex){
			logger.error("@class: ReviewDaoImpl @method: addReview cause: "+ex.toString());
			return null;
		}
		
	}
	
	

}
