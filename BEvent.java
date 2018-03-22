//AEvent.java
//dummy event B

import java.util.*;

public class BEvent extends GameEvent {

	public BEvent(GameCharacter player) {
		super("BEvent", player);
	}

	public BEvent(List<GameCharacter> parties, List<GameItem> items) {
		super("BEvent", parties, items);
	}

	public String describe() {
		return("Another generic event B!");
	}

	public void unfold(Scanner in) {
		System.out.println("B happens.");
	}

	public String[] nextEvents() {
		new GreetEvent(players, items);
		return(new String[] {"!GreetEvent"});
	}

}
