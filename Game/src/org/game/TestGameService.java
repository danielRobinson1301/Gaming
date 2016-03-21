package org.game;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.game.bean.GameResponse;
import org.game.bowling.BowlingGameService;

/**
 * @author Daniel
 *         Test class used to trigger addScore and calculateTotalScore. If score
 *         need to be added for each throw , we can use addScoreForEachThrow.
 */


public class TestGameService {

	final static Logger logger = Logger.getLogger(TestGameService.class);
	private static final String ERROR_CODE = "Error Code  :";
	private static final String ERROR_MESG = "Error Message :";
	private static final String ENTER_YOUR_INPUTS = "PLEASE_ENTER_INPUTS_MESSAGE";
	private static final String YOUR_TOTAL_SCORE = "Your total score is : ";
	private static final String GAME_TYPE = "BOWLING";
	final static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		GameResponse gameResponse = null;
		BowlingGameService bowlingGameService = (BowlingGameService) GamingFactory.getGameService(GAME_TYPE);
		logger.info(ENTER_YOUR_INPUTS);
		String scoreList = scanner.nextLine();
		gameResponse = bowlingGameService.addScore(scoreList);
		if(null == gameResponse.getErrorCode()){
			gameResponse = bowlingGameService.calculateTotalScore();
		}
		logger.info(YOUR_TOTAL_SCORE + gameResponse.getTotalScore());
		logger.info(ERROR_CODE + gameResponse.getErrorCode());
		logger.info(ERROR_MESG + gameResponse.getErrorMessage());
		scanner.close();
	}

}