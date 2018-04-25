//GrowingEvent.java
//Only one instance allowed, which keeps items throughout
//a new constructor will inherit the previous instance's parties and items if any

public abstract class GrowingEvent extends GameEvent {

	/*inherits:
	public String name;
	public List<GameCharacter> parties;
	public List<GameItem> items;
	*/

	//creating new growing event with same name will inherit previous event
	public GrowingEvent(String selfname){
		String toS;

		toS = "(" + name + ")";

		if( GameEvent.pool.containsKey( toS ) ) {
			//inherit all of its parties and items
			GameEvent former = GameEvent.pool.remove( toS );
			super( selfname, former.parties, former.items );
			//former now free'd
		}

	}

	public GrowingEvent(String selfname, List<GameCharacter> p, List<GameItem> i){
		String toS;

		toS = "(" + name + ")";

		if( GameEvent.pool.containsKey( toS ) ) {
			//inherit all of its parties and items
			GameEvent former = GameEvent.pool.remove( toS );
			super( selfname, former.parties, former.items );
			//former now free'd
		}
		
		if( p!=null ) this.parties.addAll( p );
		if( i!=null ) this.items.addAll( i );
		
	}
	
    //return description of event
	public abstract String describe();

	//play out the event
	public abstract void unfold(GameCharacter player, Scanner in);

	//returns array of all next possible events, from which the user will choose
	public abstract String[] nextEvents();
	
}