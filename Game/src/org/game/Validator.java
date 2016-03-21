package org.game;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.game.bean.FrameScores;
import org.game.bowling.BowlingGameService;
import org.game.constants.GamingConstants;
import org.game.constants.GamingErrorConstants;

/**
 * * @author Daniel
 * This class contains validation methods for all games.
 *
 */

public class Validator {

	final static Logger logger = Logger.getLogger(BowlingGameService.class);

	/**
	 * Method used to check the input string contains only numbers and
	 * whitespaces.
	 **/
	public static boolean isOnlyNumbers(String score) {
		Pattern pattern = Pattern
				.compile(GamingConstants.REGX_NUM_WHITE_SPACES);
		if (pattern.matcher(score).matches()) {
			return true;
		}
		return false;
	}


	/**
	 * Method used to check the bowling score list contains valid data.
	 * i.e.1. each throw score should between 0-10 
	 *     2. cumulative score of throw one and two in a frame should not exceed 10 except tenth frame.
	 **/
	public static String validateBowlingScoreCard(
			List<FrameScores> frameScoreList) {
		String errorCode = null;
		Iterator<FrameScores> itr = frameScoreList.iterator();
		int i = 0;
		while (itr.hasNext()) {
			FrameScores scores = itr.next();
			int throwOne = scores.getThrowOne();
			int throwTwo = scores.getThrowTwo();
			int bonusThrow = null !=  scores.getBonusThrow() ?  scores.getBonusThrow().intValue() : 0;

			if (i == GamingConstants.MAX_FRAME_IN_LIST ){
				if(throwOne < 0 || throwOne > GamingConstants.MAX_STRIKE_SCORE
						|| (throwTwo < 0 || throwTwo > GamingConstants.MAX_STRIKE_SCORE) 
						|| (bonusThrow < 0 || bonusThrow > GamingConstants.MAX_STRIKE_SCORE)) {
					errorCode = GamingErrorConstants.INVALID_INPUT_SCORE;
					break;
				}
			} else if (throwOne < 0 || throwOne > GamingConstants.MAX_STRIKE_SCORE
					|| throwTwo < 0 || throwTwo > GamingConstants.MAX_STRIKE_SCORE || (throwOne + throwTwo > GamingConstants.MAX_STRIKE_SCORE)) {
				errorCode = GamingErrorConstants.INVALID_INPUT_SCORE;
				break;
			}
			i++;
		}
		if(logger.isDebugEnabled()){
		 logger.debug("Status after validation "+errorCode);
		}
		return errorCode;
	}
}
