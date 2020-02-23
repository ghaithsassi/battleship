package in205.game;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import in205.game.exceptions.outOfBoardException;
import in205.ships.*;

public class Game implements Serializable{

    private static final long serialVersionUID = 1L;
    /*
     * *** Constante
     */
    public static File SAVE_FILE = new File("savegame.dat");

    /*
     * *** Attributs
     */
    protected Player player1;
    protected Player player2;
    protected transient Scanner sin;
    /*
     * *** Constructeurs
     */
    public Game() {
    }
    public Game init() {
        if (!loadSave()) {
                // init attributes
                System.out.println("enter your name:");
                // use a scanner to read player name
                sin = new Scanner(System.in);
                String playerName = sin.nextLine();

                // init boards
                Board b1, b2;
                b1 = new Board(playerName);
                b2 = new Board("AI");

                // init this.player1 & this.player2
                List<AbstractShip> shipsPlayer1 = createDefaultShips();
                player1 = new Player(b1, b2, shipsPlayer1);

                List<AbstractShip> shipsPlayer2 = createDefaultShips();
                player2 = new AIPlayer(b2, b1, shipsPlayer2);

                b1.print();
                // place player ships
                player1.putShips(); 
                player2.putShips();
                return this;
        }else{
            if (SAVE_FILE.exists()) {
                try {
                    ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(SAVE_FILE));
                    return (Game) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            
        }
        return this;
    }
    /*
     * *** Méthodes
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.getBoard();
        Hit hit;
        // main loop
        b1.print();
        boolean done;
        do {
            hit = player1.sendHit(coords); // player1 send a hit
            boolean strike = hit != Hit.MISS; // set this hit on his board (b1)
            
            try {
                b1.setHit(strike, coords[0], coords[1]);
            } catch (outOfBoardException e) {
                e.printStackTrace();
            }


            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!done && !strike) {
                do {
                    hit = player2.sendHit(coords);
                    strike = hit != Hit.MISS;
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();
                    if (!done) {
                        save();
                    }
                } while (strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println("player " + (player1.lose ? 2 : 1) +" (" +(player1.lose?player2.getBoard().getName():player1.getBoard().getName())+") " + " wins");
        sin.close();
    }



    protected void save() {

        
        try {
            if (!SAVE_FILE.exists()) {
            SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            }
            ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
            oos.writeObject(this) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static boolean loadSave() {
        
        if (SAVE_FILE.exists()) {
            try {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(SAVE_FILE));
                ois.readObject() ;
                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return false;
        
    
    }

    protected boolean updateScore() {
        for (Player player : new Player[] { player1, player2 }) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    protected String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
        case MISS:
            msg = hit.toString();
            break;
        case STIKE:
            msg = hit.toString();
            color = ColorUtil.Color.RED;
            break;
        default:
            msg = hit.toString() + " coulé";
            color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords[1])),
                (coords[0] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    protected static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new Battleship(),
                new Carrier() });
    }
}
