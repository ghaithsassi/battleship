package in205.game;

import java.util.List;
import java.util.Scanner;

import in205.game.exceptions.outOfBoardException;
import in205.ships.*;

/**
 * GameMode
 */
public class GameTwoPlayerMode extends Game {
    public GameTwoPlayerMode init() {
        if (!loadSave()) {
                System.out.println("enter player 1 name:");
                // use a scanner to read player  1 name
                sin = new Scanner(System.in);
                String player1Name = sin.nextLine();
    
                System.out.println("enter player 2 name:");
                // use a scanner to read player 2 name
                String player2Name = sin.nextLine();
                // init boards
                Board b1, b2;
                b1 = new Board(player1Name);
                b2 = new Board(player2Name);
    
                // init this.player1 & this.player2
                List<AbstractShip> shipsPlayer1 = createDefaultShips();
                player1 = new Player(b1, b2, shipsPlayer1);
                List<AbstractShip> shipsPlayer2 = createDefaultShips();
                player2 = new Player(b2, b1, shipsPlayer2);
    
                // place player ships
                b1.print();
                System.out.println(b1.getName()+" place your ships");
                player1.putShips(); 
                //clear screen
                System.out.print("\033[H\033[2J");
                b2.print();
                System.out.println(b2.getName()+" place your ships");
                player2.putShips();
                System.out.print("\033[H\033[2J");
            }
        return this;
    }
    public void run(){
        
        int[] coords = new int[2];
        Board b1 = player1.getBoard();
        Board b2 = player2.getBoard();
        Hit hitPlayer1=null;
        Hit hitPlayer2=null;
        
        boolean done;
        do {
            if(hitPlayer1==Hit.MISS || hitPlayer1==null){
                System.out.println(b1.getName()+" are you ready?");
                try {
                    sin.nextLine();
                } catch (Exception e) {
                }
            }
            b1.print();
            //display incomming hits for player 1
            if(hitPlayer2!=null && (hitPlayer1==Hit.MISS||hitPlayer1==null)){
                System.out.println(makeHitMessage(true /* incoming hit */, coords, hitPlayer2));
            }
            hitPlayer1 = player1.sendHit(coords); // player1 send a hit
            boolean strike = hitPlayer1 != Hit.MISS; // set this hit on his board (b1)
            try {
                b1.setHit(strike, coords[0], coords[1]);
            } catch (outOfBoardException e) {
                e.printStackTrace();
            }

            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hitPlayer1));
            save();

            if (!done && !strike) {
                try {
                    System.out.print(b1.getName()+" press enter:");
                    sin.nextLine();
                    System.out.print("\033[H\033[2J");
                    System.out.println(b2.getName()+" are you ready?");
                    sin.nextLine();
                } catch (Exception e) {
                }
                do {
                    b2.print();
                    if(hitPlayer1!=null && (hitPlayer2==Hit.MISS||hitPlayer2==null)){
                        System.out.println(makeHitMessage(true /* incoming hit */, coords, hitPlayer1));
                    }
                    hitPlayer2 = player2.sendHit(coords);
                    strike = hitPlayer2 != Hit.MISS;
                    try {
                        b2.setHit(strike, coords[0], coords[1]);
                    } catch (outOfBoardException e) {
                        e.printStackTrace();
                    }
                    done = updateScore();
                    System.out.println(makeHitMessage(false /* outgoing hit */, coords, hitPlayer2));
                    if (!done) {
                        save();
                    }

                } while (strike && !done);
                if(!done){
                    System.out.print(b2.getName()+" press enter:");
                    try {
                        sin.nextLine();
                        System.out.print("\033[H\033[2J");
                    } catch (Exception e) {
                    }
                }
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println("player " + (player1.lose ? 2 : 1) +" (" +(player1.lose?player2.getBoard().getName():player1.getBoard().getName())+") " + " wins");
        sin.close();
    }
    
}