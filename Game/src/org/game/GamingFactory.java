package org.game;

import org.game.bowling.BowlingGameServiceImpl;
import org.game.constants.GamingConstants;

/**
 * @author Daniel
 * Factory class used to create objects for each game.
 */

public class GamingFactory {
		
	   public static GamingService getGameService(String gameType){
	      if(gameType == null){
	         return null;
	      }		
	      if(gameType.equalsIgnoreCase(GamingConstants.BOWLING)){
	         return new BowlingGameServiceImpl();
	      }      
	      return null;
	   }
}