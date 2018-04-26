//TownEvent

import java.util.*;

public class TownEvent extends GrowingEvent {

	public TownEvent() {
		super("TownEvent");
	}

	public TownEvent(List<GameCharacter> parties, List<GameItem> items) {
		super("TownEvent", parties, items);
	}

	public String describe() {
		return("The only town in the game.");
	}

	public void unfold(GameCharacter player, Scanner in) {
		System.out.println("You are in a town.");
		player.printAll();
		System.out.println("Time passes...");
	}

	public String[] nextEvents() {

		new MarketEvent();
		new ForestEvent();
		new DebugEvent();

		return( new String[] {"!MarketEvent", "!ForestEvent", "!DebugEvent"} );
	}

}
