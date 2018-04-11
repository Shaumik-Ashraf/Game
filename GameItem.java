//GameItem.java
/* string: itemname#<id>
 *         <id> must be present
 */

import java.util.*;

public abstract class GameItem {

	private static int idCounter = 0;
	public static HashMap<String,GameItem> pool = new HashMap<String,GameItem>();

	public String name;  //same name as descendant
	private int id;

	public GameItem(String selfName) {
		name = selfName;
		id = idCounter;
		idCounter++;

		GameItem.pool.put(toString(), this);
	}

	public String toString() {
		return( name + "#" + id );
	}

	public int getId() {
		return( id );
	}

	public void destroy() { //remember to rm item from any lists
		GameItem.pool.remove( toString() );
	}

	public void destroy(GameCharacter owner) {
		int i = owner.items.indexOf( this );

		if( i==-1 ) {
			i = owner.equips.indexOf( this );
			if( i!=-1 ) {
				owner.equips.remove(i);
			}
		}
		else {
			owner.items.remove(i);
		}
		GameItem.pool.remove( toString() );
	}

	public void equip(Player owner, Player equipee) {
		int i = owner.items.indexOf(this);
		equipee.equips.add( equipee.items.remove(i) );
	}

	public void unequip(Player equipee) {
		int i = equipee.equips.indexOf( this );
		equipee.items.add( equipee.equips.remove(i) );
	}

	public void give(Player to) {
		to.items.add( this );
	}

	public void give(Player from, Player to) {
		int i = from.items.indexOf( this );
		to.items.add( from.items.remove(i) );

	}

	public boolean is(String trueIfContainsInName) {
		return( toString().contains( trueIfContainsInName) );
	}

	//return description of item
	public abstract String describe();

	//any effects done on item as time passes (1/turn)
	public abstract void standby();

	//execute effect of item if equipped
	public abstract void equipEffect(GameCharacter equipee);

	//undo effect of equipping item
	public abstract void unequipEffect(GameCharacter exequipee);

	//execute effect of item if used on another character
	public abstract void characterEffect(GameCharacter gchar);

	//execute effect of item if used an another item
	public abstract void itemEffect(GameItem otherItem);

}

