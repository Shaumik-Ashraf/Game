//ForestEvent

import java.util.*;

public class ForestEvent extends GameEvent {

	public ForestEvent() {
		super("ForestEvent");
		items.add( new TreeItem() );
	}

	public ForestEvent(GameCharacter player) {
		super("ForestEvent", player);
		items.add( new TreeItem() );
	}

	public ForestEvent(List<GameCharacter> parties, List<GameItem> items) {
		super("ForestEvent", parties, items);
		items.add( new TreeItem() );
	}

	public String describe() {
		return("The forest, with all its wood and game and wildlife.");
	}

	public void unfold(Scanner in) {

		PlayerCharacter player = null;
		String buf;
		String s;

		items.add( new TreeItem() );
		items.add( new TreeItem() );

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

		switch(s) {
			case "Talk to the old man":
				player.learn( new WoodCuttingSkill().toString() );
				player.gainItem( new AxeItem() );
				System.out.println("Old man gave you an axe and taught you how to cut wood!");
				break;
			case "Cut wood":
				if( player.has("(WoodCutting)") ) {
					GameSkill woodcutting = GameSkill.pool.get("(WoodCutting)");
					String[] targets = woodcutting.select(player, this, in);
					woodcutting.activate(player, targets, in);
				}
				else {
					System.out.println("You do not know how to cut wood.");
				}
				break;
			case "Hunt":
				if( player.has("(Hunting)") ) {
					GameSkill skill = GameSkill.pool.get("(Hunting)");
					String[] targets = skill.select(player, this, in);
					skill.activate(player, targets, in);
				}
				else {
					System.out.println("You do not know how to hunt.");
				}
				break;
			case "Gather plants":
				if( player.has("(Gathering)") ) {
					GameSkill skill = GameSkill.pool.get("(Gathering)");
					String[] targets = skill.select(player, this, in);
					skill.activate(player, targets, in);
				}
				else {
					System.out.println("You would eat a poisonous 'shroom and die.");
				}
				break;
			default:
				//create HuntSkill, GatheringSkill, RawMeatItem, 
		}

	}

	public String[] nextEvents() {

		new TownEvent(players, items);

		return( new String[] {"!TownEvent"} );
	}

}
