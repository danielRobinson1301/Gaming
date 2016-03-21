package org.game.bean;

/**
 * @author Daniel
 * 
 * Bean class holds properties for displaying result.
 */

public class GameResponse {
	
	private int totalScore;
	private String errorCode;
	private String errorMessage;
	
	/**
	 * @return the totalScore
	 */
	public int getTotalScore() {
		return totalScore;
	}
	/**
	 * @param totalScore the totalScore to set
	 */
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameResult [totalScore=" + totalScore + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
}
