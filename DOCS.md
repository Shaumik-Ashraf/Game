# Game Documentation

## Core principles
This game framework is built around 4 core abstract classes:

 - GameCharacter, whose instances carry out actions and interact with the Game World. This includes the PlayerCharacter
 - GameEvent, whose subclasses list and arrange the opportunites available to GameCharacters
 - GameSkill, whose subclasses coordinate and carry out actions
 - GameItem, whose subclasses and instances augment any of the above

Implementing a subclass to any of the above classes will extend the game, provided that a way to interact with the new class has 
also been provided. By convention, a subclass of GameCharacter should have the '-Character' suffix and will have the identification 
string (IDS; see below) 'NAME@ID' where NAME and ID are replaced by the corresponding attributes. Likewise, GameItem/GameEvent/GameSkill
should have the '-Item'/'-Event'/'-Skill' suffix and will have IDS 'ITEM#ID'/'!EVENT'/'(SKILL)' where ITEM/EVENT/SKILL is the class name 
without the suffix, respectively. I apologize for abusing the word respectively. Note that GameCharacter and GameItem instances all have 
unique IDS, while GameEvent and GameSkill only have a unique IDS per subclass; this GameEvent and GameSkill should only have 1 active 
instance at any given moment in the game. 

## Game mechanics
The following mechanics exist to simplify programming:
 - Identification String (IDS): all instances of the core classes will be identified by a specially formatted string returned by toString().
Each core class contains a public static HashMap pool<String,E> which maps the IDS to the object itself. This mechanics allows any relevant object in 
the game to be accessed by passing around arrays or list of strings. The format and retrieval method for each core class is outlined below

|    Class      |   IDS   |      Retrieval Method       |                                   Notes                                |
|---------------|---------|-----------------------------|------------------------------------------------------------------------|
| GameCharacter | NAME@ID | GameCharacter.pool.get(IDS) | where NAME and ID is the *.name and *.id attribute respectively        |
| GameItem      | ITEM#ID | GameItem.pool.get(IDS)      | where ITEM is the class name without the suffix and ID is id attribute |
| GameEvent     | !EVENT  | GameEvent.pool.get(IDS)     | where EVENT is the class name without the suffix                       |
| GameSkill     | (SKILL) | GameSkill.pool.get(IDS)     | where SKILL is the class name without the suffix                       |

 - Each of the core classes constructors automatically add the instance into the pool; GameCharacters and GameItems always have unique instances 
and IDS. GameEvent and GameSkill classes should only have 1 instance; a new instance will overwrite the previous instance. A GrowingEvent subclass 
(which extends GameEvent) can be used to inherit the previous instances attributes. 

 - The game's first event is the StartEvent, which is then followed by the !Town and !Debug.

 - Each event's nextEvent() method should return an array of IDS to the next possible event; each event must also be initialized

 - Wherever possible, a GameSkill subclass should resolve any interactions within the game (i.e. woodcutting).

 - The Game class provides static utility functions; see below

## Abstract methods of each class

#### GameCharacter
```
	public abstract String describe();

	public abstract String[] die();
	//returns an array of IDS for use by event/skill
	//defined as { return(null); } for class PlayerCharacter
```
#### GameEvent
```
	public abstract String describe();

	public abstract void unfold(GameCharacter player, Scanner in);
	//plays out the event;
	//options are presented to the player and corresponding code is executed

	public abstract String[] nextEvents();
	//returns an array of IDS to all next possible events
	//make sure the events have been initialized!
	//if null is returned, game ends
```
#### GameSkill
```
	public abstract String describe();

	public abstract String[] select(GameCharacter user, GameEvent event, Scanner in);
	//manages io and returns an array of IDS
	//the return value is the arguement for activate()'s targets parameter

	public abstract void activate(GameCharacter user, GameEvent event, String[] targets, Scanner in);
	//actually execute the skill and object interactions
	//alter stats, create/destroy items, etc.
```
#### GameItem
```
	public abstract String describe();

	public abstract void standby();
	//execute any effects as time passes; once/turn (aka once/event)

	public abstract void equipEffect(GameCharacter equipee);
	//execute effect of item if equipped

	public abstract void unequipEffect(GameCharacter exequipee);
	//undo effect of equipping item

	public abstract void characterEffect(GameCharacter gchar);
	//execute special effects of item if used on another character

	public abstract void itemEffect(GameItem otherItem);
	//execute any effect on another item(s)

	/* Note all the *Effect() methods exist for possible complex future implementations of GameItem
	 * All real effects should be executed by GameSkill's activate() and there is no guarantee
	 * that other skills will ever execute these methods despite using the item
	 */

```
## Method References

#### Game
```
	public static String prompt(Scanner in);
	//prints ">>" and returns in.nextLine()
	
	public static int find(String s, String[] arr);
	/* String matching algorithm used by this game!
	 * returns first index (i) of where s matches arr; note s matches arr[i] if:
	 * s is an int within the range of arr, in which case i = parseInt(s)
	 * s is found within arr[i] (case insensitive)
	 * returns -1 if not found
	 */

	public static boolean promptContinue(Scanner in)
	//returns true if yes; returns false if no
	//assumes yes if only '\n' is scanned

	public static int prompt(Scanner in, String[] arr);
	//prints arr and then asks user to select one
	//uses Game.find() to return index of string in arr selected

	public static int prompt(Scanner in, ArrayList<String> arr, int ignore)
	//same as the method above but with an ArrayList<String> rather than String[]
	//use 0 for paramter ignore (or any other int)

	public static PlayerCharacter getPlayer();
	//returns alias to first GameCharacter with "IsMainCharacter" key in cookies

	public static int rng();
	//returns random number from 0 to 10

	public static int rng(int max);
	//returns random number from 0 to max

	public static String[] listToArray(AbstractList<String> list);
	//converts List<String> to String[]

	public static String arrayToString(int[] array);
	//converts int[] to printable String
```
#### GameCharacter
```
	public static HashMap<String,GameCharacter> pool;

	public int str, dex, con, kno;
	public String name;
	public AbstractList<GameSkill> skills;
	public AbstractList<GameItem> equips;
	public AbstractList<GameItem> items;
	public HashMap<String,String> cookies;

	public GameCharacter(String nam) {
	//constructor; subclasses must override
	//automatically puts into Gamecharacter.pool and generates stats

	public String toString();

	public int getId();

	public void learn(String ids);
	//identifies ids and adds to items/skills as appropriate
	//adds as a key to cookies of unidentified

	public void gain(String ids)
	//identifies ids and adds to items/skills as appropriate
	//adds as a key to cookies if unidentified

	public boolean has(String ids);
	//returns true if found in items/equips/skills/cookies.keys

	public void destroy(String ids);
	//finds ids among player's items/equips/skills and destroys it properly

	public void equip(String ids);
	//must be in items

	public void unequip(String ids);

	public void learnSkill(GameSkill sk);

	public void forgetSkill(GameSkill sk);

	public void gainItem(GameItem it);

	public void giveItem(GameItem it, GameCharacter to)
	//gives only from items

	public void destroyItem(GameItem it);
	//destroys from items or equips

	public void equipItem(GameItem it);

	public void unequipItem(GameItem equipped);

	public boolean hasSkill(String ids);

	public boolean hasItem(String ids);
	//searches items and equips

	public void destroy();
	//destroy all of its own skills and items, and then itself
	
	public void printStats();

	public void printAll();
```
#### GameEvent
```
	public static HashMap<String,GameEvent> pool;

	public String name;
	public List<GameCharacter> parties;
	public List<GameItem> items;

	/* Note: parties list should EXCLUDE the player
     * Use subclass GrowingEvent to maintain items in later occurences
     */

	public GameEvent(String selfname){
	//selfname should be same as subclass's class name
	//I.e: TownEvent's constructor: {super("TownEvent");}

	public String toString();

	public void add(String[] ids);
	//adds to parties/items as appropraite

	public void reset(List<GameCharacter> p, List<GameItem> i);

	public void destroy(String ids);

	public void destroyItem(GameItem item);

	public void destroyParty(GameCharacter party);
```
#### GameSkill
```
	public static HashMap<String, GameSkill> pool;

	public String name;

	public GameSkill(String newSkillName);
	//parameter should be same as descendant's class name w/o Skill suffix; UNIQUE
	//will replace any previous instances; so skills shouldn't store any data

	public String toString();
```
#### GameItem
```
	public static HashMap<String,GameItem> pool = new HashMap<String,GameItem>();

	public String name;
	private int id;

	public GameItem(String selfName);
	//selfName parameter should be same as subclass' name

	public String toString();

	public int getId();

	public void destroy();
	//DO NOT USE unless you know what you're doing

	public void equip(GameCharacter owner, GameCharacter equipee);

	public void unequip(GameCharacter equipee);

	public void give(GameCharacter to);

	public void give(GameCharacter from, GameCharacter to);

	public boolean is(String trueIfContainsInName);
	//new FishingRod().is("Fish") => true
	//case sensitive!
```

