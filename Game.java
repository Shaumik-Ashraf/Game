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
		System.out.print(">> ");
		try {
			return in.nextLine();
		} catch(Exception e) {
			System.out.println("Input Error");
			System.err.println(e);
			System.exit(1);
			return("java sucks!");
		}
	}

	/*return first index (i) of where s matches arr; note s matches arr[i] if:
	 *s is an int within the range of arr
	 *s is contained within arr[i] (case insensitive)
	 *returns -1 if not found
	 */
	public static int find(String s, String[] arr) {
		try {
			int x = 0;
			x = Integer.parseInt(s);
			if( x >= arr.length ) { return(-1); }
			return( x );
		}
		catch(Exception e) {
			; //not int
		}

		//advanced string compare here
		s = s.toUpperCase();
		for(int i=0; i<arr.length; i++) {
			if( arr[i].toUpperCase().contains( s ) ) {
				return( i );
			}
			else continue;
		}

		return(-1);
	}

	//prompts to continue or not, assumes yes if only '\n' is scanned
	public static boolean promptContinue(Scanner in) {
		String s;
		int x;
		System.out.println("Would you like to continue? [Y/n]");
		s = Game.prompt(in);
		x = Game.find(s, new String[] {"yes","no"});
		return( x==0 || x==-1 );
	}

	public static int prompt(Scanner in, String[] arr) {
		String s;
		int ret;
		int fails = 0;
		do {
			for(int i=0; i<arr.length; i++) {
				System.out.println(i + ") " + arr[i]);
			}
			s = prompt(in);
			ret = find(s, arr);
			if( ret==-1 ) {
				fails++;
				if( fails%3 ==0 ) {
					System.out.println("You gotta learn to type!");
				}
				System.out.println("Please try again:");
			}
		} while( ret==-1 );
		return( ret );
	}

	public static int prompt(Scanner in, ArrayList<String> arr, int ignore) {
		String[] ArrayListDotToArrayMethodIsStupid;
		ArrayListDotToArrayMethodIsStupid = new String[ arr.size() ];

		for(int i=0; i<arr.size(); i++) {
			ArrayListDotToArrayMethodIsStupid[i] = arr.get(i);
		}

		return Game.prompt(in, ArrayListDotToArrayMethodIsStupid);
	}

	public static PlayerCharacter getPlayer() {
		String[] arr = new String[GameCharacter.pool.size()];
		GameCharacter.pool.keySet().toArray(arr);
		for(int i=0; i<arr.length; i++) {
			GameCharacter gch = GameCharacter.pool.get( arr[i] );
			if( gch.cookies.containsKey("IsMainCharacter") ) {
				return( (PlayerCharacter)gch );
			}
		}
		return(null);
	}

	public static int rng() {  //return a random number from 0 to 10
		return( (int)(Math.random()*10) );
	}

	public static int rng(int max) {
		return( (int)(Math.random()*max) );
	}

	public String[] listToArray(AbstractList<String> list) { //converts List<String> to String[]
		String[] ret = new String[ list.size() ];
		list.toArray(ret);
		
		return(ret);
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
	  ArrayList<String> options;
	  GameCharacter player;
	  int x;

	  System.out.println("Game start");
	  player = Game.newGame(0, sc);
	  event = new StartEvent();

	  while( event!=null ) {
	    event.unfold(player, sc);
	    nextEventStrs = event.nextEvents();
	    options = new ArrayList<String>();

	    if( nextEventStrs==null ) {
			event=null;
	    }
	    else if( nextEventStrs.length == 1 ) {
		event = GameEvent.pool.get(nextEventStrs[0]);
		System.out.println("You head to the " + event + ".");
	    }
	    else {
		for(int i=0; i<nextEventStrs.length; i++) {
			String s;
			if( !GameEvent.pool.containsKey(nextEventStrs[i]) ) {
				System.err.println("Found nonexisting event in nextEventStrs: " + nextEventStrs[i]);
				System.err.println("Last event: " + event);
				System.err.println("Next event !Debug!");
				event = new DebugEvent();
			}
			s = nextEventStrs[i]
			    + ": "
			    + GameEvent.pool.get(nextEventStrs[i]).describe();
			options.add(s);
		}

		System.out.println("Where do you go now?");
		x = Game.prompt(sc, options, 0);
		event = GameEvent.pool.get( nextEventStrs[x] );
	    }
	    for(GameItem gi : GameItem.pool.values()) {
		gi.standby();
	    }
	    System.out.print("\n\n");
	  }

	  System.exit(0);  //why not

	} //close main()

}




