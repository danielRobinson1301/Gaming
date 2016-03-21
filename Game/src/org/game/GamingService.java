package org.game;

import java.util.ResourceBundle;

import org.game.bean.GameResponse;
/**
 * @author Daniel
 * Interface contain abstract method common for any game.
 *
 */

/**
 * @author Daniel
 * Interface with abstract methods common for all games.
 */

public interface GamingService {

	//method to add scores in a single push
	GameResponse addScore(String score);	
	//method to calculate total score	
	GameResponse calculateTotalScore();
	
	void validateInputs(String score) throws GamingException;	
	
	ResourceBundle getResourceBundle();
	
}