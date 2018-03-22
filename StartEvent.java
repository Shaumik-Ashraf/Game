//StartEvent.java
//this is the first event, right after character creation
//you must implement all the abstract methods to weave your own adventure
//remember that List<GameCharacter> players exists with player within

import java.util.*;

public class StartEvent extends GameEvent {

	public StartEvent() {
		super("StartEvent");
	}

	public StartEvent(GameCharacter player) {
		super("StartEvent", player);
	}

	public StartEvent(List<GameCharacter> parties, List<GameItem> items) {
		super("StartEvent", parties, items);
	}

	public String describe() {
		return("The start of all nostalgia, expectations, let downs, and regrets.");
	}

	public void unfold(Scanner in) {
		System.out.println("StartEvent ...");
	}

	public String[] nextEvents() {
		new GreetEvent(players, items);
		new DebugEvent(players, items);
		return(new String[] {"!GreetEvent", "!DebugEvent"});
	}

}

