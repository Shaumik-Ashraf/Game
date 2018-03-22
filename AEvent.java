//AEvent.java
//dummy event A

import java.util.*;

public class AEvent extends GameEvent {

	public AEvent(GameCharacter player) {
		super("AEvent", player);
	}
	
	public AEvent(List<GameCharacter> parties, List<GameItem> items) {
		super("AEvent", parties, items);
	}

	public String describe() {
		return("Generic event A!");
	}

	public void unfold(Scanner in) {
		System.out.println("A unfolds.");
	}

	public String[] nextEvents() {
		new GreetEvent(players, items);
		return(new String[] {"!GreetEvent"});
	}

}
