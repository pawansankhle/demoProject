package com.petCart.dao;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Product;
import com.petCart.model.Review;
import com.petCart.model.Users;

public interface IReviewDao extends IGenericDao<Review> {
	
	Integer getRatingByProductId(Product product);
	Review addReview(Review review);
	Review getReviewByUserandProduct(Integer userid, Integer productid);
	

}
