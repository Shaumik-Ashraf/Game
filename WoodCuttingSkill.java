//WoodCuttingSkill

import java.util.*;

public class WoodCuttingSkill extends GameSkill {

	public WoodCuttingSkill() {
		super("WoodCutting");
	}

	//return description of skill
	public String describe() {
		return("You chop some wood; it says \"thank you\"; you chop it again for talking back.");
	}

	//targets all TreeItems in event
	public String[] select(GameCharacter user, GameEvent event, Scanner in) {
		ArrayList<String> trees = new ArrayList<String>();
		String[] ret;

		//get trees
		for(GameItem gi : event.items) {
			if( gi.is("Tree") ) {
				trees.add( gi.toString() );
			}
		}
		ret = new String[trees.size()];
		trees.toArray(ret);

		System.out.println("You find a total of " + ret.length + " trees in the area.");
		return( ret );
	}

	//activate skill effect,
	public void activate(GameCharacter user, GameEvent event, String[] targets, Scanner in) {
		//requires axe item in user

		boolean hasAxe = false;
		GameItem wood;
		GameItem tree;
		int numTrees = targets.length;

		//search for axe
		for(GameItem gi : user.items) {
			if( gi.is("Axe") ) {
				hasAxe = true;
				break;
			}
		}
		for(int i=0; i<user.equips.size() && !hasAxe; i++) {
			if( user.equips.get(i).is("Axe") ) {
				hasAxe = true;
				break;
			}
		}

		if( !hasAxe ) {
			System.out.println("You need an Axe.");
		}
		else {
			int i = 0;
			for(String s : targets) { //actual woodcutting...
				tree = GameItem.pool.get(s);
				event.destroyItem(tree);
				wood = new WoodItem();
				//could insert rand here
				user.gain( wood.toString() );
				System.out.println("You got some wood.");
				numTrees--;
				if( numTrees > 1 ) {
					if( !Game.promptContinue(in) ) {
						break;
					}
				}
			}
		}

		System.out.println("You're done cutting wood.");

	} //close method

}
