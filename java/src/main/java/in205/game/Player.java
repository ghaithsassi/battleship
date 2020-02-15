package in205.game;

import java.io.Serializable;
import java.util.List;

import in205.game.exceptions.*;
import in205.ships.*;

public class Player {
    /*
     * ** Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /*
     * ** Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /*
     * ** Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given
     * coodrinates.
     * 
     * @throws shipsOverlapException
     * @throws orientaionException
     * @throws outOfBoardException
     */
    public void putShips(){
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getSize());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            
            //set ship orientation
            Orientation myOrientation;
            switch(res.orientation){
                case "n":
                    myOrientation = Orientation.NORTH;
                    break;
                case "s":
                    myOrientation = Orientation.SOUTH;
                    break;
                case "e":
                    myOrientation = Orientation.EAST;
                    break;
                default:
                    myOrientation = Orientation.WEST;
                    break;
            }
            s.setOrientation(myOrientation);

            //put ship at given position
            try {
                board.putShip(s, res.y, res.x);
            } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
                //e.printStackTrace();
                --i;
            }

            //when ship placement successful
            ++i;
            done = i == 5;

            board.print();
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done = false;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
