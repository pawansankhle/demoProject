package com.petCart.service.impl;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IReviewDao;
import com.petCart.model.Review;
import com.petCart.model.Supplier;
import com.petCart.service.IReviewService;

@Service
@Transactional
public class ReviewServiceImpl implements IReviewService {

	
	private Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);
	
	@Autowired 
	IReviewDao reviewDao;
	
	
	@Override
	public List<Review> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		try{
			return reviewDao.search(context, lowerLimit, upperLimit, orderBy, orderType);
		}catch(Exception ex){
			logger.error("@class @method cause: "+ex.toString());
			return null;
		}
	}

}
