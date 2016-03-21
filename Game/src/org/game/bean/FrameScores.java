package org.game.bean;

import org.game.constants.GamingConstants;

/**
 * @author Daniel
 * 
 * Bean class holds properties of a frame in bowling score card.
 */

public class FrameScores {
	
	private int throwOne;
	private int throwTwo;
	private Integer bonusThrow;
	private boolean isStrike;
	private boolean isSpare;
	private boolean isFrameCompleted;
	
	/**
	 * @return the throwOne
	 */
	public int getThrowOne() {
		return throwOne;
	}
	/**
	 * @param throwOne the throwOne to set
	 */
	public void setThrowOne(int throwOne) {
		this.throwOne = throwOne;
		if (throwOne == GamingConstants.MAX_STRIKE_SCORE) {
			isStrike = true;
		}
	}
	/**
	 * @return the throwTwo
	 */
	public int getThrowTwo() {
		return throwTwo;
	}
	/**
	 * @param throwTwo the throwTwo to set
	 */
	public void setThrowTwo(int throwTwo) {
		this.throwTwo = throwTwo;
		setFrameCompleted(true);
		if (throwOne + throwTwo == GamingConstants.MAX_STRIKE_SCORE) {
			isSpare = true;
		}
	}
	/**
	 * @return the isStrike
	 */
	public boolean isStrike() {
		return isStrike;
	}
	/**
	 * @param isStrike the isStrike to set
	 */
	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}
	/**
	 * @return the isSpare
	 */
	public boolean isSpare() {
		return isSpare;
	}
	/**
	 * @param isSpare the isSpare to set
	 */
	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}
	/**
	 * @return the isFrameCompleted
	 */
	public boolean isFrameCompleted() {
		return isFrameCompleted;
	}
	/**
	 * @param isFrameCompleted the isFrameCompleted to set
	 */
	public void setFrameCompleted(boolean isFrameCompleted) {
		this.isFrameCompleted = isFrameCompleted;
	}
	/**
	 * @return the bonusThrow
	 */
	public Integer getBonusThrow() {
		return bonusThrow;
	}
	/**
	 * @param bonusThrow the bonusThrow to set
	 */
	public void setBonusThrow(Integer bonusThrow) {
		this.bonusThrow = bonusThrow;
	}

	
}