//GrowingEvent.java
//Only one instance allowed, which keeps items throughout
//a new constructor will replace the party but keep items

public abstract class GrowingEvent extends GameEvent {

	/*inherits:
	public String name;
	public List<GameCharacter> parties;
	public List<GameItem> items;
	*/

	//creating new growing event with same name will inherit previous event
	public GameEvent(String selfname){
		String toS;
		idCounter++;

		parties = new LinkedList();
		items = new LinkedList();
		
		name = selfname;
		toS = "(" + name + ")";

		if( GameEvent.pool.containsKey( toS ) ) {
			//inherit all of its parties and items
			GameEvent former = GameEvent.pool.get( toS );
			//SEE List METHODS
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
	
    //return description of event
	public abstract String describe();

	//play out the event
	public abstract void unfold(GameCharacter player, Scanner in);

	//returns array of all next possible events, from which the user will choose
	public abstract String[] nextEvents();
	
}