//Player.java
//just a class alias

public class Player extends PlayerCharacter {
	public Player(String nam) {
		super(nam);
	}

	public Player(String nam, int[] stats) {
		super(nam,stats);
	}

	public Player(String nam, int[] stats, String desc) {
		super(nam, stats, desc);
	}
}
