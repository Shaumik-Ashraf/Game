//PlayerCharacter.java
//player's class - designed for 1P mode only rn

public class PlayerCharacter extends GameCharacter {

	String description;

	public PlayerCharacter(String nameArg) {
		super();
		name = nameArg;
		description = "A strange subspecies of humans known as Player";
		cookies.put("IsMainCharacter", "true");
	}

	public PlayerCharacter(String nameArg, int[] stats) {
		super();
		name = nameArg;
		if( stats.length > 3 ) {
			str = stats[0];
			dex = stats[1];
			con = stats[2];
			kno = stats[3];
		}
		description = "A strange subspecies of humans known as Player";
		cookies.put("IsMainCharacter", "true");
	}

	public PlayerCharacter(String nameArg, int[] stats, String desc) {
		name = nameArg;
		if( stats.length > 3 ) {
			str = stats[0];
			dex = stats[1];
			con = stats[2];
			kno = stats[3];
		}
		description = desc;
		cookies.put("IsMainCharacter", "true");
	}

	public String describe() {
		return(description);
	}

	public String[] die() {
		for(GameItem it : equips) {
			it.destroy( this );
		}
		for(GameItem it : items) {
			it.destroy( this );
		}
		System.gc();
		return( new String[] {"R.I.P.", name, description} );
	}

}
