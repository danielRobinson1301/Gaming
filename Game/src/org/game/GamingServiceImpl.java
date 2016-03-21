package org.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.game.bean.GameResponse;
import org.game.constants.GamingConstants;
import org.game.constants.GamingErrorConstants;

/**
 * @author Daniel
 * This class holds common functionalities utilized for all gaming application. 
 */

public class GamingServiceImpl{
	
	final static Logger logger = Logger.getLogger(GamingServiceImpl.class);
	Properties  properties =null;
	ResourceBundle resourceBundle = null;	
	Locale locale = null;
	protected String errorCode;
	protected String errorMessage;
	
	
	/**
	 * This method validates the input text should contain only numbers and whitespace. 
	 * @param score
	 * @throws GameException
	 */
	public void validateInputs(String score) throws GamingException{
		if(!Validator.isOnlyNumbers(score)){
			throw new GamingException(GamingErrorConstants.INVALID_INPUT_SCORE,null,getResourceBundle().getString(GamingErrorConstants.INVALID_INPUT_SCORE));
		}
	}	

	/**
	 * This method loads bundle for the specified locale
	 * @return resourceBundle
	 */
	public ResourceBundle getResourceBundle() {
		if(resourceBundle==null)				
			resourceBundle = ResourceBundle.getBundle(GamingConstants.RESOURCE_BUNDLE,getLocale());
		return resourceBundle;
	}	

	/**
	 * This method loads the properties file
	 * @return Properties
	 */	
	protected Properties getProperties() { 
		if(properties!=null)
			return properties;
		InputStream input = null;
		File file = null;		
		try {
			properties = new Properties();
			file = new File(GamingConstants.CONFIG_PATH);
			input = new FileInputStream(file);			
			properties.load(input);			
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
					file = null;					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;
	}	

	/**
	 * Method used to populate final gameResult 
	 * @param totalScore
	 * @param errorCode
	 * @param errorMesg 
	 */
	protected GameResponse populateGameResult(int totalScore) {
		GameResponse gameResult = new GameResponse();
		gameResult.setTotalScore(totalScore);
		gameResult.setErrorCode(errorCode);
		if(errorCode != null && errorMessage == null ){
			errorMessage = getResourceBundle().getString(errorCode);
		}
		gameResult.setErrorMessage(errorMessage);
		if(logger.isDebugEnabled()){
			logger.debug("Game Result---->"+gameResult.toString());
		}
		return gameResult;
	}
	
	/**
	 * This method gets Locale
	 * @return Locale
	 */
	protected Locale getLocale() {			
		locale =  new Locale((String)getProperties().get(GamingConstants.LOCALE));
		return locale;
	}




}