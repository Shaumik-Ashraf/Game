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
		return("Sellers and scammers and buyers and beggers.");
	}

	public void unfold(Scanner in) {
		PlayerCharacter player;

		System.out.println("You are at a market.");
		System.out.println("It is currently closed. Press enter to continue");
		in.nextLine();
	}

	public String[] nextEvents() {

		new MarketEvent(parties, null);
		new ForestEvent(parties, null);

		return( new String[] {"!TownEvent", "!MarketEvent"} );
	}

}
