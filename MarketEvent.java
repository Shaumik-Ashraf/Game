//MarketEvent

import java.util.*;

public class MarketEvent extends GrowingEvent {

	public MarketEvent() {
		super("MarketEvent");
	}

	public MarketEvent(List<GameCharacter> parties, List<GameItem> items) {
		super("MarketEvent", parties, items);
	}

	public String describe() {
		return("Sellers and scammers and buyers and beggers.");
	}

	public void unfold(GameCharacter player, Scanner in) {

		System.out.println("You are at a market.");
		System.out.println("It is currently closed. Press enter to continue");
		in.nextLine();
	}

	public String[] nextEvents() {

		new MarketEvent();
		new ForestEvent();

		return( new String[] {"!TownEvent", "!MarketEvent"} );
	}

}
