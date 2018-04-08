//ForestEvent

import java.util.*;

public class ForestEvent extends GameEvent {

	public ForestEvent() {
		super("ForestEvent");
	}

	public ForestEvent(GameCharacter player) {
		super("ForestEvent", player);
	}

	public ForestEvent(List<GameCharacter> parties, List<GameItem> items) {
		super("ForestEvent", parties, items);
	}

	public String describe() {
		return("The forest, with all its wood and game and wildlife.");
	}

	public void unfold(Scanner in) {
		
		PlayerCharacter player = new PlayerCharacter("Temp");
		String buf;
		String s;

		for(int i=0; i<players.size(); i++) {
			if( players.get(i).cookies.containsKey("IsMainCharacter") ) {
				player = (PlayerCharacter)players.get(i);
			}
		}

		System.out.println("You're in a forest");
		System.out.println("What do you do now?");
				
		System.out.println("0) Talk to the old man");
		if( player.hasSkill("(WoodCutting)") ) System.out.println("1) Cut wood");	
		if( player.hasSkill("(Hunting)") ) System.out.println("2) Hunt");	
		if( player.hasSkill("(Gathering)") ) System.out.println("3) Gather plants");		

		buf = in.nextLine();
		s = Game.find(buf, new String[] {"Talk to the old man",
						"Cut wood",
						"Hunt",
						"Gather plants"});
		//note exploitable bug
		
		if( s.equals("Talk to the old man") ) {
			System.out.println("Old man taught you how to cut wood");
			//gain axe item (create new GameItem subclass)
		}
		else if( s.equals("Cut wood") ) {
			//gain wood, create wood gameitem
		}
		else;
			//create HuntSkill, GatheringSkill, WoodItem, RawMeatItem, 


	}

	public String[] nextEvents() {

		new TownEvent(players, items);

		return( new String[] {"!TownEvent"} );
	}

}