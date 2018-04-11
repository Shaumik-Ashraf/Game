//Game.java
/* DESIGN
 * implementing any of the abstract classes will extend the game
 * object output markups by toString:
 *	playername@<id>
 *	itemname#<id>
 *	(skillname)
 *      !eventname
 */

import java.util.*;

public final class Game {

	public static String prompt(Scanner in) {
		System.out.print(">>");
		try {
			return in.nextLine();
		} catch(Exception e) {
			System.out.println("Input Error");
			System.err.println(e);
			System.exit(1);
			return("java sucks!");
		}
	}

	public static String find(String s, String[] arr) {
		try {
			int x = 0;
			x = Integer.parseInt(s);
			if( x >= arr.length ) {return(null);}
			return( arr[x] );
		}
		catch(Exception e) {
			; //not int
		}

		//advanced string compare here
		s = s.substring(0, s.length()-1); //remove trailing '\n'
		s = s.toUpperCase();
		for(int i=0; i<arr.length; i++) {
			if( arr[i].toUpperCase().contains( s ) ) {
				return( arr[i] );
			}
			else continue;
		}

		return(null);
	}

	public static void listOptions(String[] arr) {
		//TODO
	}

	public static PlayerCharacter newGame(int options, Scanner in) {
		//different options can have different setups
		//0: create new character
		//1: load character
		//2: hotseat style

		PlayerCharacter ret;

		//for now...
		ret = new PlayerCharacter("Beta");

		return(ret);
	}

	public static void main(String[] args) {

	  Scanner sc = new Scanner(System.in);
	  GameEvent event;
	  String[] nextEventStrs;
	  GameCharacter player;
	  String s;

	  System.out.println("Game start");
	  player = Game.newGame(0, sc);
	  event = new StartEvent(player);

	  try { while(event!=null) {
	    event.unfold(sc);
	    nextEventStrs = event.nextEvents();
	    if( nextEventStrs==null ) {
		event=null;
	    }
	    else if( nextEventStrs.length == 1 ) {
		event = GameEvent.pool.get(nextEventStrs[0]);
	    }
	    else {
		do {
		    System.out.println("What do you do now?");
		    for(int i=0; i<nextEventStrs.length; i++) {
			GameEvent t = GameEvent.pool.get(nextEventStrs[i]);
			System.out.println(i+") "+t+": "+t.describe()); 
		    }
		    s = Game.prompt(sc);
		    s = Game.find(s, nextEventStrs);
		    if( s==null ) {
			System.out.println("Error try again.");
		    }
	    	} while( s==null );
		event = GameEvent.pool.get(s);
	    } //close else
	} } /*close while and try blocks*/ catch(Exception e) {
		e.printStackTrace();
		System.err.println(e);
		System.exit(1);
	}

	System.exit(0);  //why not
	} //close main()

} //close class




