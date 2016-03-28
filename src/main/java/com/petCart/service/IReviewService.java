package com.petCart.service;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.model.Review;
import com.petCart.model.Supplier;

public interface IReviewService {

	List<Review> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType);

	Integer getRatingByProductId(Integer id);
	String addReview(Integer id, Review review);

}
