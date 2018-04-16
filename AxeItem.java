//AxeItem

public class AxeItem extends GameItem {

	public AxeItem() {
		super("Axe");
	}

	public String describe() {
		return("Cuts things");
	}

	public void standby() {

	}

	public void characterEffect(GameCharacter character) {

	}

	public void equipEffect(GameCharacter equipee) {
		equipee.str += 3;
	}

	public void unequipEffect(GameCharacter exequipee) {
		exequipee.str -= 3;
	}

	public void itemEffect(GameItem item) {

	}

}
