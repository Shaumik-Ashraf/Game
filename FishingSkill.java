//FishingSkill.java
//adds FishingExp in the user.cookies, which is increased each time skill is used
//with "Fishing" item: if( dex + 1 + (fishingExp%10) ) { catchFish }
//without "Fishing" item: if( dex + 1 + (fishingExp%10) ) { catchFish }
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
		GameItem fish;
		GameItem rawFish;
		int fishingExp;
		int fishCaught = 0;
		boolean hasFishingRod = false;

		if( !user.cookies.containsKey("FishingExp" ) {
			user.cookies.put("FishingExp", "0");
			fishingExp = 0;
		}
		else {
			try { 
				fishingExp = Integer.parseInt( user.cookies.get("FishingExp") );
			} catch(Exception e) { ; } //shouldn't occur
		}

		for( GameItem gi : user.items ) {
			if( gi.is("Fishing") ) {
				hasFishingRod = true;
				break;
			}
		}
		if( !hasFishingRod ) {
			for( GameItem gi : user.equips ) {
				if( gi.is("Fishing") ) {
					hasFishingRod = true;
					break;
				}
			}
		}

		if( hasFishingRod ) {
			for(String s : targets) {
				fish = GameItem.pool.get(s);
				event.destroyItem(fish);
				System.out.println("You cast the line...");
				if( user.dex + 1 + (fishingExp%10) > Game.rng() ) {
					System.out.println("You caught a fish!");
					fishCaught++;
					user.gainItem( new RawFishItem() );
				}
				else {
					System.out.println("The fish got away (-_-).");
				}
				if( !Game.promptContinue(in) ) {
					break;
				}
			}
		}
		else {
			System.out.println("Since you have no fishing item, you try to catch Fish#* with your bare hands...");
			for(String s : targets) {
				fish = GameItem.pool.get(s);
				event.destroyItem(fish);
				if( user.dex - 1 + (fishingExp%10) > Game.rng() ) {
					System.out.println("You caught a fish!");
					fishCaught++;
					user.gainItem( new RawFishItem() );
				}
				else {
					System.out.println("The fish got away (-_-).");
				}
				if( !Game.promptContinue(in) ) {
					break;
				}
			}
		}

		//add fishingExp
		fishingExp += (Game.rng() + fishCaught);
		user.cookies.put("FishingExp", "" + fishingExp);

	} //close activate()

}




