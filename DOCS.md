# Game Documentation

## Core principles
This game framework is built around 4 core abstract classes:

 - GameCharacter, whose instances carry out actions and interact with the Game World. This includes the PlayerCharacter
 - GameEvent, whose subclasses list and arrange the opportunites available to GameCharacters
 - GameSkill, whose subclasses coordinate and carry out actions
 - GameItem, whose subclasses and instances augment any of the above

Implementing a subclass to any of the above classes will extend the game, provided that a way to interact with the new class has also been provided. By convention, a subclass of GameCharacter should have the '-Character' suffix and will have the identification string (IDS; see below) 'NAME@ID' where NAME and ID are replaced by the corresponding attributes. Likewise, GameItem/GameEvent/GameSkill should have the '-Item'/'-Event'/'-Skill' suffix and will have IDS 'ITEM#ID'/'!EVENT'/'(SKILL)' where ITEM/EVENT/SKILL is the class name without the suffix, respectively. I apologize for abusing the word respectively. Note that GameCharacter and GameItem instances all have unique IDS, while GameEvent and GameSkill only have a unique IDS per subclass; this GameEvent and GameSkill should only have 1 active instance at any given moment in the game. 

## Game mechanics
The following mechanics exist to simplify programming:
 - Identification String (IDS): all instances of the core classes will be identified by a specially formatted string returned by toString(). Each core class contains a public static HashMap pool<String,E> which maps the IDS to the object itself. This mechanics allows any relevant object in the game to be accessed by passing around arrays or list of strings. The format and retrieval method for each core class is outlined below
|    Class      |   IDS   |      Retrieval Method       |                                   Notes                                |
|===============|=========|=============================|========================================================================|
| GameCharacter | NAME@ID | GameCharacter.pool.get(IDS) | where NAME and ID is the *.name and *.id attribute respectively        |
| GameItem      | ITEM#ID | GameItem.pool.get(IDS)      | where ITEM is the class name without the suffix and ID is id attribute |
| GameEvent     | !EVENT  | GameEvent.pool.get(IDS)     | where EVENT is the class name without the suffix                       |
| GameSkill     | (SKILL) | GameSkill.pool.get(IDS)     | where SKILL is the class name without the suffix                       |
 - Each of the core classes constructors automatically add the instance into the pool; GameCharacters and GameItems always have unique instances and IDS. GameEvent and GameSkill classes should only have 1 instance; a new instance will overwrite the previous instance. A GrowingEvent subclass (which extends GameEvent) can be used to inherit the previous instances attributes. 
 - The game's first event is the StartEvent, which is then followed by the !Town and !Debug.
 - Each event's nextEvent() method should return an array of IDS to the next possible event; each event must also be initialized
 - Wherever possible, a GameSkill subclass should resolve any interactions within the game (i.e. woodcutting).
 - The Game class provides static utility functions; see below

## Abstract methods of each class
#### GameCharacter
#### GameEvent
#### GameSkill
#### GameItem

## Method References
#### GameCharacter
#### GameEvent
#### GameSkill
#### GameItem

