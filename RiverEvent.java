//GrowingEvent.java
//Only one instance allowed, which keeps items throughout
//a new constructor will inherit the previous instance's parties and items if any

import java.util.*;

public class RiverEvent extends GrowingEvent {

	/*inherits:
	public String name;
	public List<GameCharacter> parties;
	public List<GameItem> items;
	*/

	public RiverEvent() {
		super("RiverEvent");
		
		items.add( new FishItem() );
		items.add( new FishItem() );
		items.add( new FishItem() );
	}

        //return description of event
	public String describe() {
		return("You'll only find Magikarp and fishermen here.");
	}

	//play out the event
	public void unfold(GameCharacter player, Scanner in) {
		ArrayList<String> options = new ArrayList<String>();	
		int r = Game.rng(4); //r = 0, 1, 2, or 3
		for(int i=0; i<r; i++) {
			items.add( new FishItem() );
		}

		options.add("Talk to Fishermen");
		if( player.hasSkill("(Fishing)") ) { options.add("Fish"); }

		System.out.println("What do you do?");
		switch( Game.prompt(options, in, 0) ) {
			case 0: //talk
				if( Game.rng()<8 ) {
					System.out.println("...");
				}
				else {
					System.out.println("You seem to be patient; time to teach you how to fish");
					player.learn( new FishingSkill().toString() );
					player.gain( new FishingRodItem().toString() );
				}
				break; 
			case 1: //fish
				//!!!!!!!!!!!!!!!
				break;
			default:
				break;
		}

	}

	//returns array of all next possible events, from which the user will choose
	public String[] nextEvents() {
		new TownEvent();
		new RiverEvent();
		new DebugEvent();

		return( new String[] {"!TownEvent", "!RiverEvent", "!DebugEvent"} );
	}

}
