package in205.game.exceptions;

/**
 * doubleStrikeException
 * raise Exception if a strike was made in the same place twice
 */
 public class doubleStrikeException extends Exception{
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public doubleStrikeException(){
        System.out.println("you can't hit the ship twice in the same place");
    }

     
 }