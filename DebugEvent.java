//GreetEvent.java
//Each character introduces itself, each item is noticed

import java.util.*;

public class DebugEvent extends GameEvent {

	public DebugEvent() {
		super("DebugEvent");
	}

	public DebugEvent(GameCharacter p) {
		super("DebugEvent", p);
	}

	public DebugEvent(List<GameCharacter> players, List<GameItem> items) {
		super("DebugEvent", players, items);
	}

	//return description of event
	public String describe() {
		return("Remember the agony when the world's first bug was a literal bug?");
	}

	//play out the event
	public void unfold(Scanner in) {
		String s;
		System.out.println("DebugEvent#unfold():");
		System.out.println("====Printing Party================");
		System.out.println(players);

		System.out.println("====Printing Items================");
		System.out.println(items);

		System.out.println("=====Testing Input================");
		System.out.println( Game.prompt(in) );
		System.out.println("Echo success?");

		System.out.println("=====More Debug===================");
		
		System.out.println("Print each player? [y/n/c]");
		s = Game.prompt(in);
		s = Game.find(s, new String[] {"yes", "no", "cancel"});
		if( s.equals("yes") ) {
			for( GameCharacter gch : players ) {
				gch.printAll();
			}
		}
		if( s.equals("cancel") ) {
			return ;
		}

		System.out.println("Print GameCharacter pool? [y/n/c]");
		s = Game.prompt(in);
		s = Game.find(s, new String[] {"yes", "no", "cancel"});
		if( s.equals("yes") ) {
			System.out.println( GameCharacter.pool );
		}
		if( s.equals("cancel") ) {
			return ;
		}

		System.out.println("Print GameEvent pool? [y/n/c]");
		s = Game.prompt(in);
		s = Game.find(s, new String[] {"yes", "no", "cancel"});
		if( s.equals("yes") ) {
			System.out.println( GameEvent.pool );
		}
		if( s.equals("cancel") ) {
			return ;
		}

		System.out.println("Print GameSkill pool? [y/n/c]");
		s = Game.prompt(in);
		s = Game.find(s, new String[] {"yes", "no", "cancel"});
		if( s.equals("yes") ) {
			System.out.println( GameSkill.pool );
		}
		if( s.equals("cancel") ) {
			return ;
		}

		System.out.println("Print GameItem pool? [y/n/c]");
		s = Game.prompt(in);
		s = Game.find(s, new String[] {"yes", "no", "cancel"});
		if( s.equals("yes") ) {
			System.out.println( GameItem.pool );
		}
		if( s.equals("cancel") ) {
			return ;
		}
	}

	//returns array of all next possible events, from which the user will choose
	public String[] nextEvents() {
		return(null);
	}


}
