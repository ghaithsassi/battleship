package in205.game;

import static org.junit.Assert.assertEquals;

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
                    b.sendHit(1,2);
                    b.sendHit(1,1);
                    Hit myHit = b.sendHit(1,3);
                    System.out.print(myHit);
                    System.out.println(" is sunk");
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
        Board myBoard = new Board("test exception", 10);
        myBoard.putShip(BE, 9, 9);
    }

    @Test(expected = outOfBoardException.class)
    public void TestOutOfBoardWestException() throws outOfBoardException, orientaionException, shipsOverlapException {
        AbstractShip CW = new Carrier(Orientation.WEST);
        Board myBoard = new Board("test exception", 10);
        myBoard.putShip(CW, 0, 2);
    }

    @Test(expected = outOfBoardException.class)
    public void TestOutOfBoardNorthException() throws outOfBoardException, orientaionException, shipsOverlapException {
        AbstractShip DN = new Destroyer(Orientation.NORTH);
        Board myBoard = new Board("test exception", 10);
        myBoard.putShip(DN, 0, 0);
    }

    @Test(expected = outOfBoardException.class)
    public void TestOutOfBoardSouthException() throws outOfBoardException, orientaionException, shipsOverlapException {
        AbstractShip SS = new Submarine(Orientation.SOUTH);
        Board myBoard = new Board("test exception", 10);
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
    public void TestdoubleStrike()
            throws outOfBoardException, orientaionException, shipsOverlapException, doubleStrikeException {
        AbstractShip SW = new Submarine(Orientation.WEST);
        Board myBoard = new Board("test exception", 10);
        myBoard.putShip(SW, 4, 4);
        myBoard.sendHit(4, 3);
        myBoard.sendHit(4, 3);
    }

    @Test
    public void TestSendHits() {
        AbstractShip DN = new Destroyer(Orientation.NORTH);
        Board myBoard = new Board("Test sendHits");
        try {
            myBoard.putShip(DN, 3, 4);
            Hit myHit = myBoard.sendHit(3, 4);
            assertEquals(Hit.STIKE, myHit);
            myHit = myBoard.sendHit(1, 1);
            assertEquals(Hit.MISS, myHit);
            assertEquals(false, DN.isSunk());
            myHit = myBoard.sendHit(2, 4);
            assertEquals(Hit.DESTROYER, myHit);
            assertEquals(true, DN.isSunk());

        } catch (outOfBoardException | orientaionException | shipsOverlapException | doubleStrikeException e) {

        }
        
     }
}