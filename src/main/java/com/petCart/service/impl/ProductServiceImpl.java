package com.petCart.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.ICategoryDAO;
import com.petCart.dao.IDepartmentDAO;
import com.petCart.dao.IFilesDao;
import com.petCart.dao.IProductDAO;
import com.petCart.dao.ISupplierDao;
import com.petCart.model.Category;
import com.petCart.model.Department;
import com.petCart.model.Files;
import com.petCart.model.Product;
import com.petCart.model.Roles;
import com.petCart.model.Supplier;
import com.petCart.model.Users;
import com.petCart.model.userRoles;
import com.petCart.service.IProductService;
import com.petCart.springsecurity.security.UserInfo;
import com.petCart.util.ConfigUtil;


@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	IProductDAO productDAO;

	@Autowired 
	ISupplierDao supplierDao;
	
	@Autowired
	IDepartmentDAO departmentDao;
	
	@Autowired
	ICategoryDAO categoryDao;
	
	@Autowired 
	IFilesDao filesDao;

	@Override
	public Product  addProduct(Product product){
		logger.info("inside @class ProductServiceImpl  @mehod addProduct product is: "+product.toString());
		try{

			Users user = UserInfo.getCurrentUser();
			Department dept = departmentDao.find(product.getDepartment().getId());
			Category cat = categoryDao.find(product.getCategory().getId());
			product.setCreatedtime(new Date());
			product.setModifiedtime(new Date());
			product.setShowitem(true);
			product.setDepartment(dept);
			product.setCategory(cat);
			Supplier supplier = null;
			if(user !=null){
				logger.info("inside @class ProductServiceImpl  @mehod addProduct currernt username is: "+user.getUsername());
				for(Roles role : user.getRoles()){
					logger.info("inside @class ProductServiceImpl  @mehod addProduct currernt role is: "+role.getRoleName());

					if(role.getRoleName().equals(userRoles.ROLE_SUPPLIER)){
						supplier = supplierDao.findSupplierByDetailId(user);
						product.setSupplier(supplier);
						return productDAO.create(product);
					}else if(role.getRoleName().equals(userRoles.ROLE_ADMIN)){
						return productDAO.create(product);
					}
				}

			}


		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("@class @method cause: "+ex.toString());
		}
		return null;
	}



	@Override
	public List<Product> getAllProduct() {
		return productDAO.findAllProduct();

	}

	@Override
	public void deleteProduct(Product product) {
		productDAO.delete(product);

	}

	@Override
	public Product getProduct() {
		return null;
	}

	@Override
	public Product findById(Integer id) {
		return productDAO.findById(id);
	}



	@Override
	public List<Product> search(SearchContext context,Integer lowerLimit, Integer upperLimit,
			String orderBy, String orderType) {
		logger.info("inside @class ProductServiceImpl  @mehod search");
		return productDAO.search(context,lowerLimit,upperLimit,orderBy,orderType);
	}



	@Override
	public Product viewProduct(Integer id) {
		logger.info("inside @class ProductServiceImpl  @mehod viewProduct id is: "+id);
		return productDAO.viewProduct(id);
	}



	@Override
	public Long totalCount() {
		logger.info("inside @class ProductServiceImpl  @mehod totalCount entry");
		try{
			return productDAO.countAll();
		}catch(Exception ex){
			logger.error("@class @method cause: "+ex.toString());
			return null;
		}

	}



	@Override
	public Product updateProduct(Product product) {
		logger.info("inside @class ProductServiceImpl  @mehod updateProduct entry");
		try{

			Product oldProduct = productDAO.find(product.getId());
			if(oldProduct !=null){
				oldProduct.setName(product.getName());
				oldProduct.setModifiedtime(new Date());
				oldProduct.setDiscount(product.getDiscount());
				oldProduct.setPrice(product.getPrice());
				oldProduct.setShowitem(product.getShowitem());
				oldProduct.setQuantity(product.getQuantity());
				return productDAO.update(oldProduct);
			}
		}catch(EntityNotFoundException ex){
			logger.error("@class ProductServiceImpl @method updateProduct cause: "+ex.toString());
		}catch (Exception e) {
			logger.error("@class ProductServiceImpl @method updateProduct cause: "+e.toString());
		}
		return null;
	}



	@Override
	public Product enableDisable(Integer id, String action) {
		try{
			Product product = productDAO.find(id);
			if(product !=null){
		        if(action.equalsIgnoreCase("enable")){
		        	   product.setShowitem(true);
		        }else{
		        	product.setShowitem(false);
		        }
		        return productDAO.update(product);
			}
			
		}catch(EntityNotFoundException ex){
			logger.error("@class ProductServiceImpl @method enableDisable cause: "+ex.toString());
		}
		catch(Exception ex){
			logger.error("@class ProductServiceImpl @method enableDisable cause: "+ex.toString());
			
		}
		return null;
	}
	
	
	@Override
	public String uploadProductImage(String filename,Integer id, InputStream in) {
		
		OutputStream out = null;
		String filePath = null;
		Files file = new Files();
		try {
			Product product = productDAO.findById(id);
			// file1 = filesDao.find(file.getId());
			 
			 file.setCreatedtime(new Date());
			 file.setFilename(filename);
			 file.setFile("uploads/products/"+id+"/"+filename);
			 List<Files> files = new ArrayList<Files>();
			 files.add(file);
			 
			 Files f = filesDao.create(file);
			
			 product.getImages().add(f);
			 productDAO.update(product);
			
			 
			String folderPath = ConfigUtil.getConfigProp(ConfigUtil.APP_DIRECTORY)+"/"+"uploads/products/"+id+"/";
			File oldfile = new File(folderPath);
			filePath = folderPath+filename; 
			if(!oldfile.exists()){
				(new File(folderPath)).mkdirs();
			}
			
			
			logger.error("exist folder"+oldfile.exists());
			out = new FileOutputStream(filePath);
			IOUtils.copy(in, out);

			}catch (Exception e) {
			logger.error(" @Class: productServiceimpl @Method: uploadProductImage while creating attachment @error: ",e);
			
		} finally {
			try {
				if(in!=null)
					in.close();
				if(out!=null)
					out.close();
			} catch(Exception e) {
				logger.error("productServiceimpl while closing file @exception: "+e);
			}
		}
		return filePath;
	}



	@Override
	public List<Product> findProductRecommendation(Integer id) {
		try{
			Product product = productDAO.findById(id);
			if(product !=null){
				return productDAO.findProductRecommendation(product);
			}
			
		}catch(Exception ex){
			logger.error("@Class: productServiceimpl @Method: findProductRecommendation cause: "+ex.toString());
		    return null;
		}
		return null;
	}
}
