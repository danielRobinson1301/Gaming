package org.game;


/**
 * @author Daniel
 * Exception class used if any failure in Gaming application.
 */

public class GamingException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;
	
	public GamingException(String message){  
		  super(message);  
	}  
	

	public GamingException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public GamingException(String message, Throwable cause, String pErrorCode) {
        super(message, cause);
        setErrorCode(pErrorCode);
        setErrorMessage(message);
    }
	
	
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


}