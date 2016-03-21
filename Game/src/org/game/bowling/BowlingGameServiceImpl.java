package org.game.bowling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.game.GamingException;
import org.game.GamingServiceImpl;
import org.game.Validator;
import org.game.bean.FrameScores;
import org.game.bean.GameResponse;
import org.game.constants.GamingConstants;
import org.game.constants.GamingErrorConstants;

/**
 * @author Daniel
 * 
 *         This class holds all logical implementation specific for adding score
 *         ,getting total score and score card for bowling.
 */
public class BowlingGameServiceImpl extends GamingServiceImpl implements
BowlingGameService {

	final static Logger logger = Logger.getLogger(BowlingGameService.class);
	private List<FrameScores> frameScoreList = new ArrayList<FrameScores>();


	/**
	 * Method used to add each throw score to frameScoreList. frameScoreList
	 * structured similar to bowling scorecard.
	 * 
	 * @param score
	 * @throws GamingException 
	 */

	public void addScoreForEachThrow(String score) throws GamingException {
		FrameScores scores;
		validateInputs(score);
		int individualScore = Integer.parseInt(score);
		int framesCount = frameScoreList.size();
		if (framesCount > 0) {
			scores = frameScoreList.get(framesCount - 1);
			if (framesCount != GamingConstants.MAX_FRAME
					&& (scores.isStrike() || scores.isFrameCompleted())) {
				createFrame(individualScore);
			} else if (framesCount == GamingConstants.MAX_FRAME
					&& ((scores.isStrike() && scores.isFrameCompleted()) || scores
							.isSpare())) {
				scores.setBonusThrow(individualScore);
			} else {
				scores.setThrowTwo(individualScore);
				scores.setFrameCompleted(true);
			}
		} else {
			createFrame(individualScore);
		}
	}

	/**Method used to create frame and adds the score for first throw.
	 * @param score
	 */
	private void createFrame(int score) {
		FrameScores scores = new FrameScores();
		scores.setThrowOne(score);
		frameScoreList.add(scores);
	}

	/**
	 * Method used to add scores to scoreCard in a single push. Here it
	 * validated the input and adds score to each frame.
	 * 
	 * @param score
	 */

	public GameResponse addScore(String score){
		try {
			String[] scoresList = score.split(GamingConstants.REGX_WHITE_SPACES);
			for (String individualScore : scoresList) {
				addScoreForEachThrow(individualScore);
			}
		}
		catch (GamingException e) {
			errorCode = e.getErrorCode();
			errorMessage = e.getMessage();
			logger.error("Exception while adding score to score card: "+ e.getMessage());
		}
		return populateGameResult(0);
	}

	/**
	 * Method used to calculate total score from the score card.
	 * Throws error in following scenarios
	 * 1.if throw is incomplete and returns current score.
	 * 2.If scores in score card are not valid
	 * @return gameResult
	 */

	public GameResponse calculateTotalScore() {
		int totalScore = 0;
		try {
			errorCode = Validator.validateBowlingScoreCard(frameScoreList);
			int i = 0;
			Iterator<FrameScores> itr = frameScoreList.iterator();
			if(null == errorCode){
				while (itr.hasNext()) {
					FrameScores currentFrame = itr.next();
					if (i == GamingConstants.MAX_FRAME_IN_LIST) {
						totalScore = tenPin(totalScore, frameScoreList);
					} else if (currentFrame.isStrike()) {
						totalScore = strikeThrow(totalScore, i);
					} else if (currentFrame.isSpare()) {
						totalScore = spareThrow(totalScore, i);
					} else {
						totalScore += currentFrame.getThrowOne() + currentFrame.getThrowTwo();
					}
					i++;
				}
			}
		} catch (Exception e) {
			errorCode = GamingErrorConstants.EXCEPTION_IN_BOWLING_SERVICE;
			errorMessage = e.getMessage();
			logger.error("Runtime exception while calculating total score: "
					+ e.getMessage());
		}
		return populateGameResult(totalScore);
	}

	/**
	 * Method used to calculate score if throw is spare.
	 * @param totalScore
	 * @param frameIndex
	 * @return
	 */
	private int spareThrow(int totalScore, int frameIndex) {
		totalScore += GamingConstants.MAX_STRIKE_SCORE;
		if(frameScoreList.size() > frameIndex + 1){
			if(frameScoreList.get(frameIndex + 1) != null){
				totalScore += frameScoreList.get(frameIndex + 1).getThrowOne();
			}
		}else{
			errorCode = GamingErrorConstants.THROW_NOT_COMPLETED;
		}
		return totalScore;
	}

	/**
	 * Method used to calculate score if throw is strike.
	 * @param totalScore
	 * @param frameIndex
	 * @return
	 */
	private int strikeThrow(int totalScore, int frameIndex) {
		totalScore += GamingConstants.MAX_STRIKE_SCORE;
		if(frameScoreList.size() > frameIndex + 1){
			FrameScores firstFrame = frameScoreList.get(frameIndex + 1);
			if (firstFrame.isStrike() && frameIndex + 1 != GamingConstants.MAX_FRAME_IN_LIST) {
				if(frameScoreList.size() > frameIndex + 2 ){
					FrameScores secondFrame = frameScoreList.get(frameIndex + 2);
					totalScore += GamingConstants.MAX_STRIKE_SCORE + secondFrame.getThrowOne();
				}else{
					totalScore += GamingConstants.MAX_STRIKE_SCORE;
					errorCode = GamingErrorConstants.THROW_NOT_COMPLETED;	
				}
			} else {
				totalScore += firstFrame.getThrowOne() + firstFrame.getThrowTwo();
				if (!firstFrame.isFrameCompleted()) {
					errorCode = GamingErrorConstants.THROW_NOT_COMPLETED;
				} 
			}
		}else{
			errorCode = GamingErrorConstants.THROW_NOT_COMPLETED;
		}
		return totalScore;
	}

	/**
	 * Method used to calculate score for the tenth frame as it may contain a
	 * bonus throw.
	 * @return totalScore
	 */
	private int tenPin(int totalScore, List<FrameScores> frameScoreList)
			throws GamingException {
		FrameScores maxFrame = frameScoreList
				.get(GamingConstants.MAX_FRAME_IN_LIST);
		int bonusThrow = null !=  maxFrame.getBonusThrow() ?  maxFrame.getBonusThrow().intValue() : 0;
		totalScore += maxFrame.getThrowOne() + maxFrame.getThrowTwo()
				+ bonusThrow;
		if (!maxFrame.isFrameCompleted() || ((maxFrame.isStrike() || maxFrame.isSpare()) && null == maxFrame.getBonusThrow())) {
			errorCode = GamingErrorConstants.THROW_NOT_COMPLETED;
		}
		return totalScore;
	}

	/**
	 * Method used to get full score card of the player.
	 * TODO
	 * @return totalScore
	 */

	public Object getScoreCard() {
		// TODO Auto-generated method stub
		return null;
	}
}