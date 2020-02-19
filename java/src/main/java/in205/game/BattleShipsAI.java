package in205.game;

import in205.ships.*;
import in205.game.exceptions.*;

import java.io.Serializable;
import java.util.*;

public class BattleShipsAI implements Serializable {

    /*
     * ** Attributs
     */

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * grid size.
     */
    private final int size;

    /**
     * My board. My ships have to be put on this one.
     */
    private final IBoard board;

    /**
     * My opponent's board. My hits go on this one to strike his ships.
     */
    private final IBoard opponent;

    /**
     * Coords of last known strike. Would be a good idea to target next hits around
     * this point.
     */
    private int lastStrike[];

    /**
     * If last known strike lead me to think the underlying ship has vertical
     * placement.
     */
    private Boolean lastVertical;

    /*
     * ** Constructeur
     */

    /**
     *
     * @param myBoard       board where ships will be put.
     * @param opponentBoard Opponent's board, where hits will be sent.
     */
    public BattleShipsAI(IBoard myBoard, IBoard opponentBoard) {
        this.board = myBoard;
        this.opponent = opponentBoard;
        size = board.getSize();
    }

    /*
     * ** Méthodes publiques
     */

    /**
     * Put the ships on owned board.
     * 
     * @param ships the ships to put
     */
    public void putShips(AbstractShip ships[]) {
        int row, col;
        Orientation o;
        Random rnd = new Random();
        Orientation[] orientations = Orientation.values();

        for (AbstractShip s : ships) {
            while (true) {
                // use Random to pick a random x, y & orientation
                row = rnd.nextInt(this.size);
                col = rnd.nextInt(this.size);
                o = orientations[rnd.nextInt(orientations.length)];
                s.setOrientation(o);
                try {
                    if (canPutShip(s, row, col)) {
                        board.putShip(s, row, col);
                        break;
                    }
                } catch (outOfBoardException | orientaionException | shipsOverlapException e) {
                }

            }

        }
    }

    /**
     *
     * @param coords array must be of size 2. Will hold the coord of the send hit.
     * @return the status of the hit.
     */
    public Hit sendHit(int[] coords) {
        int res[] = null;
        if (coords == null || coords.length < 2) {
            throw new IllegalArgumentException("must provide an initialized array of size 2");
        }

        // already found strike & orientation?
        if (lastVertical != null) {
            if (lastVertical) {
                res = pickVCoord();
            } else {
                res = pickHCoord();
            }

            if (res == null) {
                // no suitable coord found... forget last strike.
                lastStrike = null;
                lastVertical = null;
            }
        } else if (lastStrike != null) {
            // if already found a strike, without orientation
            // try to guess orientation
            res = pickVCoord();
            if (res == null) {
                res = pickHCoord();
            }
            if (res == null) {
                // no suitable coord found... forget last strike.
                lastStrike = null;
            }
        }

        if (lastStrike == null) {
            res = pickRandomCoord();
        }

        Hit hit=Hit.MISS;
        try {
            hit = opponent.sendHit(res[0], res[1]);
            board.setHit(hit != Hit.MISS, res[0], res[1]);
        } catch (outOfBoardException | doubleStrikeException e) {

        }

        if (hit != Hit.MISS) {
            if (lastStrike != null) {
                lastVertical = guessOrientation(lastStrike, res);
            }
            lastStrike = res;
        }

        coords[0] = res[0];
        coords[1] = res[1];
        return hit;
    }

    /*
     * *** Méthodes privées
     */

    private boolean canPutShip(AbstractShip ship, int row, int col) {
        Orientation o = ship.getOrientation();
        int dx = 0, dy = 0;
        if (o == Orientation.EAST) {
            if (col + ship.getSize() >= this.size) {
                return false;
            }
            dx = 1;
        } else if (o == Orientation.SOUTH) {
            if (row + ship.getSize() >= this.size) {
                return false;
            }
            dy = 1;
        } else if (o == Orientation.NORTH) {
            if (row + 1 - ship.getSize() < 0) {
                return false;
            }
            dy = -1;
        } else if (o == Orientation.WEST) {
            if (col + 1 - ship.getSize() < 0) {
                return false;
            }
            dx = -1;
        }

        int ix = col;
        int iy = row;

        for (int i = 0; i < ship.getSize(); ++i) {
            try {
                if (board.hasShip(iy, ix)) {
                    return false;
                }
            } catch (outOfBoardException e) {
                return false;
            }

            ix += dx;
            iy += dy;
        }

        return true;
    }

    private boolean guessOrientation(int[] c1, int[] c2) {
        return c1[0] == c2[0];
    }

    private boolean isUndiscovered(int row, int col) {
        try {
            return row >= 0 && row < size && col >= 0 && col < size && board.getHit(row, col) == null;
        } catch (outOfBoardException e) {
            return true;
        }
    }

    private int[] pickRandomCoord() {
        Random rnd = new Random();
        int row;
        int col;

        do {
            row = rnd.nextInt(size);
            col = rnd.nextInt(size);
        } while (!isUndiscovered(row, col));

        return new int[] { row, col };
    }

    /**
     * pick a coord verically around last known strike
     * 
     * @return suitable coord, or null if none is suitable
     */
    private int[] pickVCoord() {
        int row = lastStrike[0];
        int col = lastStrike[1];

        for (int iy : new int[] { row - 1, row + 1 }) {
            if (isUndiscovered(iy,col)) {
                return new int[] { iy ,col};
            }
        }
        return null;
    }

    /**
     * pick a coord horizontally around last known strike
     * 
     * @return suitable coord, or null if none is suitable
     */
    private int[] pickHCoord() {
        int row = lastStrike[0];
        int col = lastStrike[1];

        for (int ix : new int[] { col - 1, col + 1 }) {
            if (isUndiscovered(row,ix)) {
                return new int[] { row,ix};
            }
        }
        return null;
    }
}
