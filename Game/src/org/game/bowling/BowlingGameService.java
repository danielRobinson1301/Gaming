package org.game.bowling;

import org.game.GamingException;
import org.game.GamingService;

/**
 * @author Daniel
 * Interface with abstract method specific for bowling and extends generic GamingService
 */

public interface BowlingGameService extends GamingService {

	//method to add score for each throw
	void addScoreForEachThrow(String score) throws GamingException;	
	
	//TODO- if scoreCard needs to be displayed
	Object getScoreCard();
}