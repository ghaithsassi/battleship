package in205.game;

import in205.game.exceptions.*;
import in205.ships.*;


public interface IBoard { 

    /**
     * Get the size of the grids contained in the Board
     * @return the size of the grids contained in the Board
     */
    int getSize();

    /**
    * Put the given ship at the given position
    * @param ship The ship to place on the board
    * @param x
    * @param y
    */
    void putShip(AbstractShip ship, int x, int y)throws outOfBoardException,orientaionException,shipsOverlapException;

    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return true if a ship is located at the given position
     */
    boolean hasShip(int x, int y) throws outOfBoardException;

    /**
     * Set the state of the hit at a given position
     * @param hit true if the hit must be set to successful
     * @param x
     * @param y
     */
    void setHit(boolean hit, int x, int y) throws outOfBoardException;

    /**
     * Get the state of a hit at the given position
     * @param x
     * @param y
     * @return true if the hit is successful
     */
    Boolean getHit(int x, int y) throws outOfBoardException;

    /**
    * Sends a hit at the given position
    * @param x
    * @param y
    * @return status for the hit (eg : strike or miss)
    */
    Hit sendHit(int x, int y) throws outOfBoardException, doubleStrikeException;

}
