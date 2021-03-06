//GameCharacter.java
/* playername@<id>
 *
 */

import java.util.*;

public abstract class GameCharacter {

	private static int idCounter = 0;
	public static HashMap<String,GameCharacter> pool = new HashMap<String,GameCharacter>();
	public static String default_name = "Unknown";

	private int id;
	public int str, dex, con, kno;
	public String name;
	public AbstractList<GameSkill> skills;
	public AbstractList<GameItem> equips;
	public AbstractList<GameItem> items;
	public HashMap<String,String> cookies;

	public GameCharacter() {
		id = GameCharacter.idCounter;
		GameCharacter.idCounter++;
		name = GameCharacter.default_name;

		skills = new ArrayList();
		equips = new LinkedList();
		items = new LinkedList();
		cookies = new HashMap();

		str = (int)(Math.random()*10);
		dex = (int)(Math.random()*10);
		con = (int)(Math.random()*10);
		kno = (int)(Math.random()*10);

		GameCharacter.pool.put( toString(), this );
	}

	public GameCharacter(String nam) {
		id = GameCharacter.idCounter;
		GameCharacter.idCounter++;
		name = nam;

		skills = new ArrayList();
		equips = new LinkedList();
		items = new LinkedList();
		cookies = new HashMap();

		str = (int)(Math.random()*10);
		dex = (int)(Math.random()*10);
		con = (int)(Math.random()*10);
		kno = (int)(Math.random()*10);

		GameCharacter.pool.put( toString(), this );
	}

	public String toString() {
		return( name + "@" + id );
	}

	public int getId() {
		return(id);
	}

	//create:
	//public void use(String ids)
	//public void examine(String ids)
	public void learn(String ids) {
		gain(ids);
	}

	public void gain(String ids) {
		if( ids.indexOf("#")!=-1 ) {
			gainItem( GameItem.pool.get(ids) );
		}
		else if( ids.indexOf("(")!=-1 ) {
			learnSkill( GameSkill.pool.get(ids) );
		}
		else {
			System.out.println(toString() + " cannot gain/learn " + ids);
		}
	}

	public boolean has(String ids) {
		if( ids.indexOf("#")!=-1 ) {
			for(GameItem gi : items) {
				if( gi.toString().equals(ids) ) {
					return(true);
				}
			}
			for(GameItem gi : items) {
				if( gi.toString().equals(ids) ) {
					return(true);
				}
			}
			return(false);
		} //close if item
		else if( ids.indexOf("(")!=-1 ) {
			for(GameSkill gs : skills) {
				if( gs.toString().equals(ids) ) {
					return(true);
				}
			}
			return(false);
		}
		else if( cookies.containsKey(ids) ) {
			return(true);
		}
		else return(false);
	}

	public void destroy(String ids) { //destroyItem() if item, or destroySkill if skill
		if( ids.indexOf("#") != -1 ) {
			destroyItem( GameItem.pool.get(ids) );
		}
		else if( ids.indexOf("(") != -1 ) {
			forgetSkill( GameSkill.pool.get(ids) );
		}
		else {
			//unrecognized; do nothing
		}
	}

	public void equip(String ids) { //must be in this.items
		equipItem( GameItem.pool.get(ids) );
	}

	public void unequip(String ids) {
		unequipItem( GameItem.pool.get(ids) );
	}

	public void learnSkill(GameSkill sk) {
		skills.add(sk);
	}

	public void forgetSkill(GameSkill sk) {
		skills.remove( skills.indexOf(sk) );
	}

	public void gainItem(GameItem it) {
		items.add(it);
	}

	public void giveItem(GameItem it, GameCharacter to) { //gives only from items
		int i = items.indexOf(it);
		to.items.add( items.remove(i) );
	}

	public void destroyItem(GameItem it) { //destroys from items or equips
		int i = items.indexOf(it);
		if( i==-1 ) {
			i = equips.indexOf(it);
			if( i!=-1 ) {
				equips.remove(i);
			}
		}
		else {
			items.remove(i);
		}
		it.destroy();
	}

	public void equipItem(GameItem it) {
		int i = items.indexOf(it);
		equips.add( items.remove(i) );
		it.equipEffect( this );
	}

	public void unequipItem(GameItem equipped) {
		int i = equips.indexOf( equipped );
		items.add( equips.remove(i) );
		equipped.unequipEffect( this );
	}

	public boolean hasSkill(String ids) {
		GameSkill gs = GameSkill.pool.get(ids);
		return( skills.indexOf(gs)!=-1 );
	}

	public boolean hasItem(String ids) { //searches items and equips
		GameItem gi = GameItem.pool.get(ids);
		return( (items.indexOf(gi)!=-1) || (equips.indexOf(gi)!=-1) );
	}

	public void destroy() { //destroy all of its own skills and items, and then itself
		for(GameSkill sk : skills) {
			forgetSkill(sk);
		}
		for(GameItem it : items) {
			destroyItem(it);
		}
		for(GameItem it : equips) {
			destroyItem(it);
		}
		GameCharacter.pool.remove( toString() );
	}
	
	public void printStats() {
		System.out.println("Character " + this + " summary:");
		System.out.println( describe() );
		System.out.println("Stats {str, dex, con, kno}: " + Game.arrayToString(new int[] {str,dex,con,kno}));
	}

	public void printAll() {
		System.out.println("Character " + this + " summary:");
		System.out.println( describe() );
		System.out.print("Stats {str,dex,con,kno}: ");
		System.out.println("{" + str + "," + dex + "," + con + "," + kno + "}");
		System.out.println("skills: " + skills);
		System.out.println("equips: " + equips);
		System.out.println("items: " + items);
		System.out.println("cookies: " + cookies);
	}

	public abstract String describe();

	//return array of idStrings for use by event/skill
	public abstract String[] die();

}

