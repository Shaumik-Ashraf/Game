//FishingSkill.java
//requires user to have a "Fishing" Item
//will remove FishItem from event and give RawFishItem to user

import java.util.*;

public class FishingSkill {

	public FishingSkill() {
		super("Fishing");
	}

	//return description of skill
	public String describe() {
		return("A man's battle with nature - to eat or starve");
	}

	//manage io and return array for activate()'s arg
	public String[] select(GameCharacter user, GameEvent event, Scanner in) {
		//returns ids of all "Fish" items in the event
		ArrayList<String> ret = new ArrayList<String>();

		for(GameItem gi : event.items) {
			if( gi.is("Fish") ) { 
				ret.add( gi.toString() );
			}
		}
		return( Game.listToArray(ret) );
	}

	//activate skill effect,
	public void activate(GameCharacter user, GameEvent event, String[] targets, Scanner in) {
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

}




