//RawFishItem.java

public class RawFishItem extends GameItem {

	public RawFishItem() {
		super("RawFish");
	}

	//return description of item
	public String describe() {
		return("Edible, but personally I prefer cooked.");
	}

	//any effects done on item as time passes (1/turn)
	public void standby() {
		//could implement rotting
	}

	//execute effect of item if equipped
	public void equipEffect(GameCharacter equipee) {}

	//undo effect of equipping item
	public void unequipEffect(GameCharacter exequipee) {}

	//execute effect of item if used on another character
	public void characterEffect(GameCharacter gchar) {
		gchar.con += 2;
	}

	//execute effect of item if used an another item
	public void itemEffect(GameItem otherItem) {}

}
