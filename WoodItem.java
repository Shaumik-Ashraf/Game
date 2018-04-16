//WoodItem.java


public class WoodItem extends GameItem {

	public WoodItem() {
		super("Wood");
	}

	public String describe() {
		return("Solid but still snapable; reliable yet roastable."); //TBD
	}

	public void standby() {}

	public void equipEffect(GameCharacter equipee) {
		equipee.str += 1;
	}

	public void unequipEffect(GameCharacter ex) {
		ex.str -= 1;
	}

	public void characterEffect(GameCharacter character) {
		System.out.println("You wack " + character + " with a piece of wood.");
		//...
	}

}
