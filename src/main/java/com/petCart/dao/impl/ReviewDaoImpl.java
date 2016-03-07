package com.petCart.dao.impl;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IReviewDao;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Review;

@Repository
@Transactional
public class ReviewDaoImpl  extends GenericDaoImpl<Review> implements IReviewDao{
	
	@Override
	public List<Review> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		
		return super.search(searchContext, lowerLimit, upperLimit, orderBy, orderType);
	}

}
