package in205.game;

import static org.junit.Assert.assertArrayEquals;
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
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }
        b.print();
        // Board b2 = new Board("b2");
        // b2.print();

    }

    @Test
    public void TestBattleshipPlacement() {
        AbstractShip BN = new Battleship(Orientation.NORTH);
        AbstractShip BS = new Battleship(Orientation.SOUTH);
        AbstractShip BE = new Battleship(Orientation.EAST);
        AbstractShip BW = new Battleship(Orientation.WEST);

        Board myboard = new Board("test placement", 10);

        Character[][] expected = new Character[10][10];

        expected[0][0] = 'B';
        expected[1][0] = 'B';
        expected[2][0] = 'B';
        expected[3][0] = 'B';

        
        try {
            myboard.putShip(BN,3,0);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }

        expected[0][2] = 'B';
        expected[1][2] = 'B';
        expected[2][2] = 'B';
        expected[3][2] = 'B';
        
        try {
            myboard.putShip(BS,0,2);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }

        expected[5][0] = 'B';
        expected[5][1] = 'B';
        expected[5][2] = 'B';
        expected[5][3] = 'B';
        
        try {
            myboard.putShip(BE,5,0);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }

        expected[8][6] = 'B';
        expected[8][7] = 'B';
        expected[8][8] = 'B';
        expected[8][9] = 'B';
        
        try {
            myboard.putShip(BW,8,9);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }


    }
    
    @Test
    public void TestCarrierPlacement() {
        AbstractShip CN = new Carrier(Orientation.NORTH);
        AbstractShip CS = new Carrier(Orientation.SOUTH);
        AbstractShip CE = new Carrier(Orientation.EAST);
        AbstractShip CW = new Carrier(Orientation.WEST);

        Board myboard = new Board("test placement", 8);

        Character[][] expected = new Character[8][8];

        expected[0][0] = 'C';
        expected[1][0] = 'C';
        expected[2][0] = 'C';
        expected[3][0] = 'C';
        expected[4][0] = 'C';

        
        try {
            myboard.putShip(CN, 4, 0);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }

        expected[0][2] = 'C';
        expected[1][2] = 'C';
        expected[2][2] = 'C';
        expected[3][2] = 'C';
        expected[4][2] = 'C';
        
        try {
            myboard.putShip(CS, 0, 2);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }

        expected[6][0] = 'C';
        expected[6][1] = 'C';
        expected[6][2] = 'C';
        expected[6][3] = 'C';
        expected[6][4] = 'C';
        
        try {
            myboard.putShip(CE, 6,0);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }
        expected[7][3] = 'C';
        expected[7][4] = 'C';
        expected[7][5] = 'C';
        expected[7][6] = 'C';
        expected[7][7] = 'C';
        
        try {
            myboard.putShip(CW, 7, 7);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void TestDestroyerPlacement() {
        AbstractShip DN = new Destroyer(Orientation.NORTH);
        AbstractShip DS = new Destroyer(Orientation.SOUTH);
        AbstractShip DE = new Destroyer(Orientation.EAST);
        AbstractShip DW = new Destroyer(Orientation.WEST);

        Board myboard = new Board("test placement", 8);

        Character[][] expected = new Character[8][8];

        expected[0][0] = 'D';
        expected[1][0] = 'D';

        
        try {
            myboard.putShip(DN, 1, 0);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }

        expected[3][2] = 'D';
        expected[4][2] = 'D';
        
        try {
            myboard.putShip(DS, 3, 2);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }

        expected[6][0] = 'D';
        expected[6][1] = 'D';
        
        try {
            myboard.putShip(DE, 6,0);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }

        expected[7][6] = 'D';
        expected[7][7] = 'D';
        
        try {
            myboard.putShip(DW, 7, 7);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void TestSubmarinePlacement() {
        AbstractShip SN = new Submarine(Orientation.NORTH);
        AbstractShip SS = new Submarine(Orientation.SOUTH);
        AbstractShip SE = new Submarine(Orientation.EAST);
        AbstractShip SW = new Submarine(Orientation.WEST);

        Board myboard = new Board("test placement", 10);

        Character[][] expected = new Character[10][10];

        expected[0][0] = 'S';
        expected[1][0] = 'S';
        expected[2][0] = 'S';

        
        try {
            myboard.putShip(SN,2,0);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }
        expected[0][2] = 'S';
        expected[1][2] = 'S';
        expected[2][2] = 'S';
        try {
            myboard.putShip(SS,0,2);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }

        expected[5][0] = 'S';
        expected[5][1] = 'S';
        expected[5][2] = 'S';
        
        try {
            myboard.putShip(SE,5,0);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }

        expected[8][7] = 'S';
        expected[8][8] = 'S';
        expected[8][9] = 'S';
        
        try {
            myboard.putShip(SW,8,9);
            assertArrayEquals(expected,myboard.getShips());
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
            e.printStackTrace();
        }


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
}