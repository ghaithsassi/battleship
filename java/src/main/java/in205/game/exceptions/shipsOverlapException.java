package in205.game.exceptions;

public class shipsOverlapException extends Exception{

    private static final long serialVersionUID = 1L;
    public shipsOverlapException(){
        System.out.println("you can't place there is already an other ship");
    }
    
}