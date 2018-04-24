//HuntingSkill
/* string: (Hunting)
 * DO LATER AND RESTRUCTURE BASED ON CHICKENS
 *
 */

import java.util.*;

public class HuntingSkill extends GameSkill {

    //public String name;  //should be same as descendant's class name w/o Skill suffix; UNIQUE

    public HuntingSkill() {
        super("(Hunting)");
    }

    //return description of skill
    public String describe() {
        return("Hunting skill");
    }

    //manage io and return array for activate()'s arg
    public String[] select(GameCharacter user, GameEvent event, Scanner in) {
        //search through all event.items and select backgroundItems which could be hunted
        String[] huntable = new String[] {"deer", "bear", "chicken"};
        ArrayList<String> arr = new ArrayList<String>();
        String[] ret;

        for(GameItem gi : event.items) {
            for(String s : huntable) {
                if( gi.is(s) ) {
                    arr.add( gi.toString() );
                }
            }
        }

        for(int i=0; i<arr.size(); i++) {
            if( Game.rng()>8 ) {
                arr.remove(i);
            }
        }

        ret = new String[ arr.size() ];
        arr.toArray(ret);

        return(ret);
    }

    //activate skill effect,
    public void activate(GameCharacter user, GameEvent event, String[] idStrings, Scanner in) {

        GameItem toHunt;
        int x;

        if( idStrings.length == 0 ) {
            System.out.println("You found nothing.");
        }
        else if( idStrings.length==1 ) {
            toHunt = GameItem.pool.get(idStrings[0]);
            System.out.println("You found " + toHunt);
            if( Game.rng() < 5 ) {
                System.out.println("It got away.");
            }
            else {
                event.items.remove( event.items.indexOf( toHunt ) );
                toHunt.destroy();
                user.gain( new RawMeatItem().toString() );
            }
        }
        else {
            System.out.println("You found multiple creatures; which one do you hunt?");
            x = Game.prompt(in, idStrings);
            toHunt = GameItem.pool.get( idStrings[x] );
            System.out.println("You stalk the " + toHunt);
            if( Game.rng() < 5 ) {
                System.out.println("It got away.");
            }
            else {
                event.items.remove( event.items.indexOf( toHunt ) );
                toHunt.destroy();
                user.gain( new RawMeatItem().toString() );
            }
        }

    }

}
