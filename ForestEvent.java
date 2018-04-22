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
		ArrayList<String> options = new ArrayList<String>();

		items.add( new TreeItem() );
		items.add( new TreeItem() );

		player = Game.getPlayer();

		options.add("Talk to that generic old man who gives free stuff");
		if( player.hasSkill("(WoodCutting)") ) options.add("Cut wood");
		if( player.hasSkill("(Hunting)") ) options.add("Hunt");
		if( player.hasSkill("(Gathering)") ) options.add("Gather plants");


		System.out.println("You're in a forest.");
		System.out.println("What do you do now?");

		switch( Game.prompt(in, options, 0) ) {
			case 0: //talk to old man
				player.learn( new WoodCuttingSkill().toString() );
				player.gainItem( new AxeItem() );
				System.out.println("Old man gave you an axe and taught you how to cut wood!");
				break;
			case 1: //cut wood
				if( player.has("(WoodCutting)") ) {
					GameSkill woodcutting = GameSkill.pool.get("(WoodCutting)");
					String[] targets = woodcutting.select(player, this, in);
					woodcutting.activate(player, targets, in);
				}
				else {
					System.out.println("You do not know how to cut wood.");
				}
				break;
			case 2: //hunt
				if( player.has("(Hunting)") ) {
					GameSkill skill = GameSkill.pool.get("(Hunting)");
					String[] targets = skill.select(player, this, in);
					skill.activate(player, targets, in);
				}
				else {
					System.out.println("You do not know how to hunt.");
				}
				break;
			case 3: //gather
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
				break;
		} //close switch

	}

	public String[] nextEvents() {

		new TownEvent(players, items);

		return( new String[] {"!TownEvent", "!ForestEvent"} );
	}

}
