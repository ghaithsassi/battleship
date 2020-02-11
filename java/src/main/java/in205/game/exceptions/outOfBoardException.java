package in205.game.exceptions;

/**
 * outOfBoardException
 */
public class outOfBoardException extends Exception {

    private static final long serialVersionUID = 1L;

    public outOfBoardException() {
        System.out.println("out of board error");
    }
    
}