//TownEvent

import java.util.*;

public class TownEvent extends GameEvent {

	public TownEvent() {
		super("TownEvent");
	}

	public TownEvent(GameCharacter player) {
		super("TownEvent", player);
	}

	public TownEvent(List<GameCharacter> parties, List<GameItem> items) {
		super("TownEvent", parties, items);
	}

	public String describe() {
		return("The only town in the game.");
	}

	public void unfold(Scanner in) {
		System.out.println("You are in a town.");
		System.out.println("Time Passes");
	}

	public String[] nextEvents() {

		new MarketEvent(parties, null);
		new ForestEvent(parties, null);
		new DebugEvent(parties, items);

		return( new String[] {"!MarketEvent", "!ForestEvent", "!DebugEvent"} );
	}

}
