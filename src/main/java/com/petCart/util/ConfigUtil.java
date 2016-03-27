package com.petCart.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class ConfigUtil {

	private static Logger logger=LoggerFactory.getLogger(ConfigUtil.class); 
	
	public static final String MAIL_SERVER_CONFIG="MAIL_SERVER_CONFIG";
	public static final String CONFIG_PROPS="application.properties";

	public static final String EMAIL_ID = "EMAIL_ID";
    public static final String EMAIL_PASSWORD = "EMAIL_PASSWORD";
    public static final String HOST_NAME = "HOST_NAME";
	public static final String SMTP_PORT ="SMTP_PORT";
	public static final String APP_DIRECTORY = "APP_DIRECTORY";
	
	
	private static PropertiesConfiguration config;
	
	public static String getConfigProp(String key)
	{		
			return (String) config.getProperty(key);
	}
	public static List<String> getConfigPropArray(String key)
	{		
			return (List<String>) config.getProperty(key);
	}
	
	public static void  setConfigProp(String key,String value)
	{		
			 config.setProperty(key,value);
			 try {
				config.save();
			} catch (ConfigurationException e) {
				e.printStackTrace();
			  logger.error("Error Inside  @class :"+ConfigUtil.class.getName()+" @Method :setConfigProp()"+e.getMessage());
			}
	}
	
	
	
	static
	{
		try {
			config = new PropertiesConfiguration(CONFIG_PROPS);
			config.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			 logger.error(e.getMessage());
		}
		
	}
	
	
	public static Map<String,Integer> getUpperLowerLimit(Integer page,Integer limit){
		Map<String,Integer> result = new HashMap<String,Integer>();
	    
		if(page>0 && limit >0){
		Integer lowerLimit = (limit*(page-1));
		Integer upperLimit = (lowerLimit -1) + limit;
		
		result.put("lowerLimit", lowerLimit);
		result.put("upperLimit", upperLimit);
		return result;
		}else{
			return null;
		}
	}
}
