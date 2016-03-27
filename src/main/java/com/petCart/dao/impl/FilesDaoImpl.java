package com.petCart.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IFilesDao;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Files;
import com.petCart.model.Product;

@Repository
@Transactional
public class FilesDaoImpl extends GenericDaoImpl<Files> implements IFilesDao {

	private final Logger logger = LoggerFactory.getLogger(FilesDaoImpl.class);
	@Override
	public long countAll(Map<String, Object> params) {
		return 0;
	}

	@Override
	public Files create(Files t) {
		return super.create(t);
	}

	@Override
	public void delete(Object id) {
		super.delete(id);

	}

	@Override
	public Files find(Object object) {
		return super.find(object);
	}

	@Override
	public Files update(Files t) {
		return super.update(t);
	}

	@Override
	public List<Files> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		
		return super.search(context, lowerLimit, upperLimit, orderBy, orderType);
	}

}
