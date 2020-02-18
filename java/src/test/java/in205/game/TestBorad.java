package in205.game;
import org.junit.Test;

import in205.game.exceptions.*;
import in205.ships.*;

public class TestBorad {
    public static void main(String[] args) {
        String name = "hello";
        Board b = new Board(name, 8);
        AbstractShip bat = new Battleship();
        AbstractShip sub = new Submarine();
        try {
            b.putShip(bat, 3, 3);
            b.putShip(sub, 1, 1);
            b.setHit(true, 4, 6);
            b.setHit(false, 4, 7);
            if (b.hasShip(1, 1)) {
                try {
                    b.getShipState(1, 1).addStrike();
                } catch (doubleStrikeException e) {
                    // TODO
                }
            }            
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }
        b.print();
        // Board b2 = new Board("b2");
        // b2.print();

    }

    @Test(expected = outOfBoardException.class)
    public void TestOutOfBoardEastException() throws outOfBoardException, orientaionException, shipsOverlapException {
        AbstractShip BE = new Battleship(Orientation.EAST);
        Board myBoard = new Board("test exception",10);
        myBoard.putShip(BE, 9, 9);
    }

    @Test(expected = outOfBoardException.class)
    public void TestOutOfBoardWestException() throws outOfBoardException, orientaionException, shipsOverlapException {
        AbstractShip CW = new Carrier(Orientation.WEST);
        Board myBoard = new Board("test exception",10);
        myBoard.putShip(CW, 0, 2);
    }

    @Test(expected = outOfBoardException.class)
    public void TestOutOfBoardNorthException() throws outOfBoardException, orientaionException, shipsOverlapException {
        AbstractShip DN = new Destroyer(Orientation.NORTH);
        Board myBoard = new Board("test exception",10);
        myBoard.putShip(DN, 0, 0);
    }

    @Test(expected = outOfBoardException.class)
    public void TestOutOfBoardSouthException() throws outOfBoardException, orientaionException, shipsOverlapException {
        AbstractShip SS = new Submarine(Orientation.SOUTH);
        Board myBoard = new Board("test exception",10);
        myBoard.putShip(SS, 9, 5);
    }

    @Test(expected = shipsOverlapException.class)
    public void TestOvverlap() throws outOfBoardException, orientaionException, shipsOverlapException {
        AbstractShip a = new Carrier(Orientation.SOUTH);
        AbstractShip b = new Submarine(Orientation.WEST);

        Board myBoard = new Board("Test overlaping", 8);

        myBoard.putShip(a, 2, 3);
        myBoard.putShip(b, 4, 5);
     }
     @Test(expected = doubleStrikeException.class)
     public void TestdoubleStrike() throws outOfBoardException, orientaionException, shipsOverlapException, doubleStrikeException {
        AbstractShip SW = new Submarine(Orientation.WEST);
        Board myBoard = new Board("test exception",10);
        myBoard.putShip(SW, 4, 4);
        myBoard.getShipState(4, 3).addStrike();
        myBoard.getShipState(4, 3).addStrike();
     }
}