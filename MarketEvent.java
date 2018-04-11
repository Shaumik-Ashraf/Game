//MarketEvent

import java.util.*;

public class MarketEvent extends GameEvent {

	public MarketEvent() {
		super("MarketEvent");
	}

	public MarketEvent(GameCharacter player) {
		super("MarketEvent", player);
	}

	public MarketEvent(List<GameCharacter> parties, List<GameItem> items) {
		super("MarketEvent", parties, items);
	}

	public String describe() {
		return("The only town in the game.");
	}

	public void unfold(Scanner in) {
		PlayerCharacter player;

		System.out.println("You are at a market.");
		System.out.println("It is currently closed. Press enter to continue");
		in.nextLine();		
	}

	public String[] nextEvents() {

		new MarketEvent(players, items);
		new ForestEvent(players, items);

		return( new String[] {"!TownEvent"} );
	}

}
