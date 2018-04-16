//WoodCuttingSkill

public class WoodCuttingSkill extends GameSkill {

	public WoodCuttingSkill() {
		try {
			super("WoodCutting");
		}
		catch {
			//skill already exists
		}
	}

	//return description of skill
	public String describe() {
		return("You chop some wood; it says \"thank you\"; you chop it again for talking back.");
	}

	//targets all TreeItems in event
	public String[] selectTargets(GameCharacter user,
				      GameEvent event,
				      Scanner in) {
		ArrayList<String> trees = new ArrayList<String>();
		for(GameItem gi : event.items) {
			if( gi.is("Tree") ) {
				trees.add( gi.toString() );
			}
		}
		System.out.println("You find a total of " + trees.size() + " trees in the area.");
		return( trees.toArray() );
	}

	//activate skill effect,
	public void activate(GameCharacter user, String[] idStrings) {
		//requires axe item in user

		boolean hasAxe = false;
		GameItem wood;
		GameItem tree;
		String s;

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
			for(String s : idStrings) { //actual woodcutting...
				tree = GameItem.pool.get(s);
				tree.destroy();
				wood = new WoodItem();
				//could insert rand here
				user.gain( wood.toString() );
				System.out.println("You got some wood.");
				if( !s.equals(idStrings[idStrings.length-1]) ) {
					if( !Game.promptContinue() ) {
						break;
					}
				}
			}
		}

		System.out.println("You're done cutting wood.");

	} //close method

}
