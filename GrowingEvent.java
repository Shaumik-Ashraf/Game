//GrowingEvent.java
//Only one instance allowed, which keeps items throughout
//a new constructor will inherit the previous instance's parties and items if any

import java.util.*;

public abstract class GrowingEvent extends GameEvent {

	/*inherits:
	public String name;
	public List<GameCharacter> parties;
	public List<GameItem> items;
	*/

	//creating new growing event with same name will inherit previous event
	public GrowingEvent(String selfname){
		super(selfname, false);
		String toS;

		toS = "!" + name;

		if( GameEvent.pool.containsKey( toS ) ) {
			//inherit all of its parties and items
			GameEvent former = GameEvent.pool.remove( toS );
			this.parties.addAll( former.parties );
			this.items.addAll( former.items );
		}
		GameEvent.pool.put( toS, this );
		//former (GameEvent) now free
	}

	public GrowingEvent(String selfname, List<GameCharacter> p, List<GameItem> i){
		super( selfname, false );
		String toS;

		toS = "!" + name;

		if( GameEvent.pool.containsKey( toS ) ) {
			//inherit all of its parties and items
			GameEvent former = GameEvent.pool.remove( toS );
			this.parties.addAll( former.parties );
			this.items.addAll( former.items );
		}

		if( p!=null ) this.parties.addAll( p );
		if( i!=null ) this.items.addAll( i );

		GameEvent.pool.put(toS, this);
	}

    //return description of event
	public abstract String describe();

	//play out the event
	public abstract void unfold(GameCharacter player, Scanner in);

	//returns array of all next possible events, from which the user will choose
	public abstract String[] nextEvents();

}
