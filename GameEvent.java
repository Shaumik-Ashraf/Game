//GameEvent.java
/* string: !eventname
 *
 */

import java.util.*;

public abstract class GameEvent {

	private static int idCounter;
	public static HashMap<String,GameEvent> pool = new HashMap<String,GameEvent>(); 
	//pool: className => instance

	private int id;
	public String name;  //same as descendants name, UNIQUE
	public List<GameCharacter> players;
	public List<GameItem> items;

	//creating new event with same name will overwrite previous event
	public GameEvent(String selfname){
		id = idCounter;
		idCounter++;

		name = selfname;
		players = new LinkedList();
		items = new LinkedList();

		pool.put( toString(), this );
	}

	public GameEvent(String selfname, GameCharacter player) {
		id = idCounter;
		idCounter++;

		players = new LinkedList();
		players.add(player);
		items = new LinkedList();

		pool.put( toString(), this );
	}

	public GameEvent(String selfname, List<GameCharacter> p, List<GameItem> i){
		id = idCounter;
		idCounter++;

		name = selfname;
		players = p;
		items = i;

		if( p==null ) {
			players = new LinkedList();
		}
		if( i==null ) {
			items = new LinkedList();
		}

		GameEvent.pool.put( toString(), this );
	}

	public int getId() {
		return(id);
	}

	public String toString() {
		return("!" + name);
	}

	public void reload(List<GameCharacter> p, List<GameItem> i) {
		players = p;
		items = i;
	}

	//return description of event
	public abstract String describe();

	//play out the event
	public abstract void unfold(Scanner in);

	//returns array of all next possible events, from which the user will choose
	public abstract String[] nextEvents();

}
