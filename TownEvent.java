//TownEvent

import java.util.*;

public class TownEvent extends GrowingEvent {

	public TownEvent() {
		super("TownEvent");
	}

	public TownEvent(List<GameCharacter> parties, List<GameItem> items) {
		super("TownEvent", parties, items);
	}

	public String describe() {
		return("The only town in the game.");
	}

	public void unfold(GameCharacter player, Scanner in) {
		String options[];
		String s;
		int i;

		System.out.println("You are in a town, the center hub of life");
		player.printStats();
		System.out.println("What do you do?");

		options = new String[] {"View Items/Skills",
					"Equip Items",
					"Unequip Items",
					"Destroy Items"};
		switch( Game.prompt(in, options) ) {
			case 0: //view
				player.printAll();
				break;
			case 1: //equip
				options = new String[ player.items.size() ];
				for(int j=0; j<options.length; j++) {
					options[j] = player.items.get(j).toString();
				}
				System.out.println("Items: " + player.items);
				do {
					System.out.println("Select an item to equip:");
					s = Game.prompt(in);
					i = Game.find(s, options);
					if( i==-1 ) {
						System.out.println("You don't have the item");
					}
					else {
						player.equip( options[i] );
					}
				} while( Game.promptContinue(in) );
				break;
			case 2: //unequip
				options = new String[ player.equips.size() ];
				for(int j=0; j<options.length; j++) {
					options[j] = player.equips.get(j).toString();
				}
				System.out.println("Equips: " + player.equips);
				do {
					System.out.println("What do you unequip?");
					s = Game.prompt(in);
					i = Game.find(s, options);
					if( i==-1 ) {
						System.out.println("That is not equipped right now.");
					}
					else {
						player.unequip( options[i] );
					}
				} while( Game.promptContinue(in) );
				break;
			case 3: //destroy
				ArrayList<GameItem> temp = new ArrayList<GameItem>();
				temp.addAll(player.items);
				temp.addAll(player.equips);
				options = new String[ temp.size() ];
				for(int j=0; j<options.length; j++) {
					options[j] = temp.get(j).toString();
				}
				System.out.println("Items/Equips: " + temp);
				do {
					System.out.println("What do you destroy?");
					s = Game.prompt(in);
					i = Game.find(s, options);
					if( i==-1 ) {
						System.out.println("That is not equipped right now.");
					}
					else {
						player.destroyItem( GameItem.pool.get(options[i]) );
					}
				} while( Game.promptContinue(in) );
				break;
			default:
				break;
		}

	}

	public String[] nextEvents() {

		new MarketEvent();
		new ForestEvent();
		new DebugEvent();

		return( new String[] {"!MarketEvent", "!ForestEvent", "!DebugEvent"} );
	}

}
