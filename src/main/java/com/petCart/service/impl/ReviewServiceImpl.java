package com.petCart.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IReviewDao;
import com.petCart.model.Product;
import com.petCart.model.Review;
import com.petCart.model.Supplier;
import com.petCart.model.Users;
import com.petCart.service.IProductService;
import com.petCart.service.IReviewService;
import com.petCart.service.IUserService;
import com.petCart.springsecurity.security.UserInfo;

@Service
@Transactional
public class ReviewServiceImpl implements IReviewService {

	
	private Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);
	
	@Autowired 
	IReviewDao reviewDao;
	
	@Autowired 
	IProductService productService;
	
	@Autowired
	IUserService userService;
	
	
	@Override
	public List<Review> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		try{
			return reviewDao.search(context, lowerLimit, upperLimit, orderBy, orderType);
		}catch(Exception ex){
			logger.error("@class: ReviewServiceImpl @method search cause: "+ex.toString());
			return null;
		}
	}


	@Override
	public Integer getRatingByProductId(Integer id) {
		try{
			Product product = productService.findById(id);
			if(product !=null)
			{
				return reviewDao.getRatingByProductId(product);
			}
		}catch(Exception ex){
			
		}
		
		return null;
	}

    @Override
	public String addReview(Integer id, Review review) {
		try{
			String msg = "ok";
			String status = "success";
            Product product = productService.findById(id);
            Users user = UserInfo.getCurrentUser();
            Review oldReview = null;
            if(user !=null && product != null){
            	
            		//oldReview = reviewDao.getReviewByUserandProduct(user,product);
            	
            	
            	
            	if(oldReview == null){
            		if(review.getRating() != null  && review.getReview() != null){
                		review.setCustomer(user);
                    	review.setCreatedOn(new Date());
                    	review.setProduct(product);
                    	review.setReview(review.getReview().trim());
                    	reviewDao.create(review);
                	}else{
                		status="failed";
                    	msg = "Review and Rating Cannot be blank";
                	}
            	}else{
            		status="failed";
                	msg = "You can Add One Review at a time";
            	}
            }else{
            	status="failed";
            	msg = "You Need to Login First!!";
            }
			return "{\"status\":\""+status+"\",\"msg\":\""+msg+"\"}";
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("@class ReviewServiceImpl @method addReview cause: "+ex.toString());
			return null;
		}
		
	}


	
}
