//GameEvent.java
/* string: !eventname
 *
 */

import java.util.*;

public abstract class GameEvent {

	public static HashMap<String,GameEvent> pool = new HashMap<String,GameEvent>(); 
	//pool: className => instance

	public String name;  //same as descendants name, UNIQUE
	public List<GameCharacter> parties;
	public List<GameItem> items;

	//creating new event with same name will overwrite previous event
	public GameEvent(String selfname){
		String toS;
		idCounter++;

		parties = new LinkedList();
		items = new LinkedList();
		
		name = selfname;
		toS = "(" + name + ")";

		if( GameEvent.pool.containsKey( toS ) ) {
			//replace previous instance
			GameEvent.pool.remove( toS );
		}
		GameEvent.pool.put( toString(), this );

	}

	public GameEvent(String selfname, List<GameCharacter> p, List<GameItem> i){
		String toS;

		name = selfname;
		parties = p;
		items = i;
		toS = "(" + name + ")";

		if( parties==null ) {
			parties = new LinkedList();
		}
		if( items==null ) {
			items = new LinkedList();
		}

		if( GameEvent.pool.containsKey( toS ) ) {
			//replace previous instance
			GameEvent.pool.remove( toS );
		}
		GameEvent.pool.put( toString(), this );
	}

	public String toString() {
		return("!" + name);
	}

	public void add(String[] ids) {
		for(String s : ids) {
			if( s.indexOf("@")!=-1 ) {
				parties.add( GameCharacter.pool.get(s) );
			}
			else if( s.indexOf("#")!=-1 ) {
				items.add( GameItem.pool.get(s) );
			}
			else ;
		}
	}
	
	public void reset(List<GameCharacter> p, List<GameItem> i) {
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
	public abstract void unfold(GameCharacter player, Scanner in);

	//returns array of all next possible events, from which the user will choose
	public abstract String[] nextEvents();

}
