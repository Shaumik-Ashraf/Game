//GameSkill.java
/* string: (skillname)
 * all subclasses are unique and can only have one instance, whose mother alias is stored in pool
 * all players with skill will hold a reference to that skill
 * any reference to the skill can be determined by its unique name
 */

import java.util.*;

public abstract class GameSkill {

	public static HashMap<String, GameSkill> pool = new HashMap();

	public String name;  //should be same as descendant's class name; UNIQUE

	//throws exception when duplicate name occurs
	public GameSkill(String newSkillName) throws Exception {
		name = newSkillName;
		if( GameSkill.pool.containsKey( toString() ) ) {
			throw new Exception("GameSkill already exists");
		}
		GameSkill.pool.put( toString(), this );
	}

	public String toString() {
		return("(" + name + ")");
	}


	//return description of skill
	public abstract String describe();

	//change below:c
	//manage io and return array for activate()'s arguement
	public abstract String[] selectTargets(GameCharacter user, 
					       GameEvent event, 
					       Scanner in);

	//activate skill effect, typecasting as needed
	public abstract void activate(GameCharacter user, String[] idStrings);

}
