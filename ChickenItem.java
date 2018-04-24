//ChickenItem.java
/* Note maybe change into character, utilize die() method
 * Or keep track of all events containing chicken for destroy()
 */

import java.util.*;

public class ChickenItem extends GameItem {

    int age;

    public ChickenItem() {
        super("Chicken");
    }

    //return description of item
    public String describe() {
        return("Bok bok!");
    }

    //any effects done on item as time passes (1/turn)
    public void standby() {
        age++;
        //if( age>20 ) { //fails
        //    destroy();
        //}
    }

    //execute effect of item if equipped
    public void equipEffect(GameCharacter equipee) {}

    //undo effect of equipping item
    public void unequipEffect(GameCharacter exequipee) {}

    //execute effect of item if used on another character
    public void characterEffect(GameCharacter gchar) {}

    //execute effect of item if used an another item
    public void itemEffect(GameItem otherItem) {}

}

