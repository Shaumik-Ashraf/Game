//FishingRodItem

public class FishingRodItem extends GameItem {

	public FishingRodItem() {
		super("FishingRod");
	}

	//return description of item
	public String describe() {
		return("Catching dumb basses since 2000 B.C.");
	}

	//any effects done on item as time passes (1/turn)
	public void standby() {}

	//execute effect of item if equipped
	public void equipEffect(GameCharacter equipee) {
		equipee.str += 2;
		equipee.dex += 1;
	}

	//undo effect of equipping item
	public void unequipEffect(GameCharacter exequipee) {
		exequipee.str -= 2;
		exequipee.dex -= 1;
	}

	//execute effect of item if used on another character
	public void characterEffect(GameCharacter gchar) {}

	//execute effect of item if used an another item
	public void itemEffect(GameItem otherItem) {}

}
