package test.java;

import junit.framework.TestCase;

import org.game.GamingException;
import org.game.bowling.BowlingGameService;
import org.game.bowling.BowlingGameServiceImpl;
import org.game.constants.GamingErrorConstants;

/**
 * @author Daniel
 *Junit class covering few success and error scenarios including invalid input ,Throw not completed
 */
public class BowlingGameTest extends TestCase {


	private BowlingGameService bowlingGame;

	public void setUp() {
		bowlingGame = new BowlingGameServiceImpl();
	}


	public void testTwoFramesScoresOnSinglePush() {
		bowlingGame.addScore("6 2 7 1");
		assertEquals(16, bowlingGame.calculateTotalScore().getTotalScore());
	}

	public void testTwoFramesScoresOnebyOne() {
		try {
			bowlingGame.addScoreForEachThrow("6");
			bowlingGame.addScoreForEachThrow("2");
			bowlingGame.addScoreForEachThrow("7");
			bowlingGame.addScoreForEachThrow("1");
			assertEquals(16, bowlingGame.calculateTotalScore().getTotalScore());
		} catch (GamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void testSpareOnSinglePush() {
		bowlingGame.addScore("10 10 5 5 7");
		assertEquals(69, bowlingGame.calculateTotalScore().getTotalScore());
	}

	public void testSpareOnebyOne() {
		try {
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("5");
			bowlingGame.addScoreForEachThrow("5");
			bowlingGame.addScoreForEachThrow("7");
			assertEquals(69, bowlingGame.calculateTotalScore().getTotalScore());
		} catch (GamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void testAllFramesAsStrikeOnSinglePush() {
		bowlingGame.addScore("10 10 10 10 10 10 10 10 10 10 10 10");
		assertEquals(300, bowlingGame.calculateTotalScore().getTotalScore());
	}

	public void testAllFramesAsStrikeOnebyOne() {
		try {
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			assertEquals(300, bowlingGame.calculateTotalScore().getTotalScore());
		} catch (GamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void testErrorInvalidInput() {
		try {
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("100");
			bowlingGame.addScoreForEachThrow("1");
			bowlingGame.addScoreForEachThrow("4");
			assertEquals(GamingErrorConstants.INVALID_INPUT_SCORE, bowlingGame.calculateTotalScore().getErrorCode());
		} catch (GamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testThrowNotCompleted() {
		try {
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("10");
			bowlingGame.addScoreForEachThrow("1");
			assertEquals(GamingErrorConstants.THROW_NOT_COMPLETED, bowlingGame.calculateTotalScore().getErrorCode());
		} catch (GamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}