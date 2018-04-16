//GameSkill.java
/* string: (skillname)
 * all subclasses are unique and can only have one instance, whose mother alias is stored in pool
 * all players with skill will hold a reference to that skill
 * any reference to the skill can be determined by its unique name
 */

import java.util.*;

public abstract class GameSkill {

	public static HashMap<String, GameSkill> pool = new HashMap();

	public String name;  //should be same as descendant's class name w/o Skill suffix; UNIQUE

	public GameSkill(String newSkillName) {
		name = newSkillName;
		//will replace any previous instances; so skills shouldn't store any data
		GameSkill.pool.put( toString(), this );
	}

	public String toString() {
		return("(" + name + ")");
	}


	//return description of skill
	public abstract String describe();

	//manage io and return array for activate()'s arg
	public abstract String[] select(GameCharacter user, GameEvent event, Scanner in);

	//activate skill effect,
	public abstract void activate(GameCharacter user, String[] idStrings);

}
