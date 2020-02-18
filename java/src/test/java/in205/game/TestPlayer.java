package in205.game;

import java.util.ArrayList;

import org.junit.Test;

import in205.game.exceptions.orientaionException;
import in205.game.exceptions.outOfBoardException;
import in205.game.exceptions.shipsOverlapException;
import in205.ships.*;

/**
 * TestPlayer
 */
public class TestPlayer {
    public static void main(String[] args) {
        ArrayList<AbstractShip> myShips = new ArrayList<>();

        AbstractShip D = new Destroyer();
        AbstractShip S1 = new Submarine();
        AbstractShip S2 = new Submarine();
        AbstractShip B = new Battleship();
        AbstractShip C = new Carrier();

        myShips.add(D);
        myShips.add(S1);
        myShips.add(S2);
        myShips.add(B);
        myShips.add(C);

        Board b1 = new Board("player 1");
        Board b2 = new Board("player 2");

        Player p = new Player(b1, b2, myShips);
        AbstractShip DN = new Destroyer(Orientation.NORTH);
        try {
            b2.putShip(DN, 1, 0);
        } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
        }

        p.putShips();
        int[] coords={}; 
        p.sendHit(coords);
        p.sendHit(coords);
        p.sendHit(coords);


        p.board.print();

    }

    
}