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

	//manage io and return array for activate()'s arg
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
		//searches for Axe item in user
		//then calls Axe.itemEffect(tree)
		//could have also been programed directly, but discouraged...

		boolean hasAxe = false;
		GameItem axe;
		GameItem wood;
		String s;
		
		for(GameItem gi : user.items) {
			if( gi.is("Axe") ) {
				axe = gi;
				hasAxe = true;
				break;
			}
		}
		for(int i=0; i<user.equips.size() && !hasAxe; i++) {
			if( user.equips.get(i).is("Axe") ) {
				axe = user.equips.get(i);
				hasAxe = true;
				break;
			}	
		}

		if( !hasAxe ) {
			System.out.prinntln("You need an Axe.");
		}
		else {
			for(int i=0; i<idStrings.length; i++) { //actuall woodcutting...
				axe.itemEffect( GameItem.pool.get(idString[i]) );
				wood = new WoodItem();
				//could insert rand here
				user.gain( wood.toString() );
				System.out.println("You got some wood.");
				if( i+1 != idStrings.length ) {
					System.out.println("Would you like to continue? [Y/n]");
					s = Game.prompt(in);
					s = Game.find(s, new String[] {"yes","no"};
					if( s==null || s.equals("no") ) {
						break;
					}
				}
			}
		}

	}

}
