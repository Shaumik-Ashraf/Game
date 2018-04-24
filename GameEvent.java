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
	public List<GameCharacter> parties;
	public List<GameItem> items;

	//creating new event with same name will overwrite previous event
	public GameEvent(String selfname){
		id = idCounter;
		idCounter++;

		name = selfname;
		parties = new LinkedList();
		items = new LinkedList();

		if( GameEvent.pool.containsKey( toString() ) ) {
			//if prev instance existed, inherit all of its items, but NOT parties
			//parties = GameEvent.pool.get( toString() ).parties;
			items = GameEvent.pool.get( toString() ).items;
			GameEvent.pool.remove( toString() );
		}
		GameEvent.pool.put( toString(), this );

	}

	public GameEvent(String selfname, GameCharacter player) {
		id = idCounter;
		idCounter++;

		parties = new LinkedList();
		parties.add(player);
		items = new LinkedList();

		pool.put( toString(), this );
	}

	public GameEvent(String selfname, List<GameCharacter> p, List<GameItem> i){
		id = idCounter;
		idCounter++;

		name = selfname;
		parties = p;
		items = i;

		if( parties==null ) {
			parties = new LinkedList();
		}
		if( items==null ) {
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
		parties = p;
		items = i;
	}

	public void destroy(String ids) {
		if( ids.indexOf("@")!=-1 ) {
			destroyParty( GameCharacter.pool.get( ids ) );
		}
		else if( ids.indexOf("#")!=-1 ) {
			destroyItem( GameItem.pool.get( ids ) );
		}
		else ;
	}

	public void destroyItem(GameItem item) {
		int i = items.indexOf(item);
		GameItem toDestroy = items.remove(i);
		toDestroy.destroy();
	}

	public void destroyParty(GameCharacter party) {
		int i = parties.indexOf(party);
		GameCharacter gch = parties.remove(i);
		gch.destroy();
	}

	//return description of event
	public abstract String describe();

	//play out the event
	public abstract void unfold(Scanner in);

	//returns array of all next possible events, from which the user will choose
	public abstract String[] nextEvents();

}
