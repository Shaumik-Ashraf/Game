//GreetEvent.java
//Each character introduces itself, each item is noticed

import java.util.*;

public class GreetEvent extends GameEvent {

	public GreetEvent() {
		super("GreetEvent");
	}

	public GreetEvent(GameCharacter p) {
		super("GreetEvent", p);
	}

	public GreetEvent(List<GameCharacter> players, List<GameItem> items) {
		super("GreetEvent", players, items);
	}

	//return description of event
	public String describe() {
		return("Meet the peeps.");
	}

	//play out the event
	public void unfold(Scanner in) {
		System.out.println("Party:");
		System.out.println(players);
		System.out.println("Items:");
		System.out.println(items);
	}

	//returns array of all next possible events, from which the user will choose
	public String[] nextEvents() {
		String[] ret = new String[4];

		ret[0] = new GreetEvent(players,items).toString();
		ret[1] = new DebugEvent(players,items).toString();
		ret[2] = new AEvent(players,items).toString();
		ret[3] = new BEvent(players,items).toString();


		return(ret);
	}


}
