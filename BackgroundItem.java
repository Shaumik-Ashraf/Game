//BackgroundItem
//abstract class for items required on event for interaction but no other functionality

public abstract class BackgroundItem extends GameItem {

	public BackgroundItem(String name) {
		super(name);
	}

	//override destroy(Object)
	public void destroy(GameEvent from) {
		int i = from.items.indexOf( this );

		if( i!=-1 ) {
			from.items.remove(i);
		}
		GameItem.pool.remove( toString() );
	}

	//implement some as empty methods
	public void standby() {}

	public void equipEffect(GameCharacter g) {}

	public void unequipEffect(GameCharacter g) {}

	public void characterEffect(GameCharacter g) {}

	public void itemEffect(GameItem g) {}

	//abstract
	public abstract String describe();

}
