//GreetEvent.java
//Each character introduces itself, each item is noticed

import java.util.*;

public class DebugEvent extends GameEvent {

	private boolean goToStart = false;

	public DebugEvent() {
		super("DebugEvent");
	}

	public DebugEvent(GameCharacter p) {
		super("DebugEvent", p);
	}

	public DebugEvent(List<GameCharacter> parties, List<GameItem> items) {
		super("DebugEvent", parties, items);
	}

	//return description of event
	public String describe() {
		return("Remember the agony when the world's first bug was a literal bug?");
	}

	//play out the event
	public void unfold(GameCharacter player, Scanner in) {
		String[] options = new String[] {"yes", "no", "quit"};
		int x;
		System.out.println("DebugEvent#unfold():");
		System.out.println("====Printing Player================");
		System.out.println(player);
		player.printAll();

		System.out.println("====Printing Parties================");
		System.out.println(parties);

		System.out.println("====Printing Items================");
		System.out.println(items);

		System.out.println("=====Testing Input================");
		System.out.println( Game.prompt(in) );
		System.out.println("Did it echo your input?");
		System.out.println("Pick one:");
		x = Game.prompt(in, new String[] {"A", "B", "C"});
		System.out.println("You picked choice number " + x + ".");

		System.out.println("=====More Debug===================");

		System.out.println("Print each party? [y/n/q]");
		x = Game.prompt(in, options);
		if( x==0 ) {
			for( GameCharacter gch : parties ) {
				gch.printAll();
			}
		}
		if( x==2 ) {
			System.exit(0);
		}

		System.out.println("Print GameCharacter pool? [y/n/c]");
		x = Game.prompt(in, options);
		if( x==0 ) {
			System.out.println( GameCharacter.pool );
		}
		if( x==1 ) {
			System.exit(0);
		}

		System.out.println("Print GameEvent pool? [y/n/c]");
		x = Game.prompt(in, options);
		if( x==0 ) {
			System.out.println( GameEvent.pool );
		}
		if( x==2 ) {
			System.exit(0);
		}

		System.out.println("Print GameSkill pool? [y/n/q]");
		x = Game.prompt(in, options);
		if( x==0 ) {
			System.out.println( GameSkill.pool );
		}
		if( x==2 ) {
			return ;
		}

		System.out.println("Print GameItem pool? [y/n/q]");
		x = Game.prompt(in, options);
		if( x==0 ) {
			System.out.println( GameItem.pool );
		}
		if( x==2 ) {
			System.exit(0);
		}

		System.out.println("Would like to go back to !StartEvent?");
		x = Game.prompt(in, options);
		if( x==0 ) {
			goToStart = true;
		}
		else {
			goToStart = false;
		}

	}

	public String[] nextEvents() {
		if( goToStart ) {
			return( new String[] {new StartEvent().toString()} );
		}
		return(null);
	}

}
