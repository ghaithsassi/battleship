package in205.game;
import in205.ships.*;


/**
 * TestGame
 */
public class TestGame {

    public static void main(String[] args) {
        Board board = new Board("test AI game");
        board.print();

        
        AbstractShip D = new Destroyer();
        AbstractShip S1 = new Submarine();
        AbstractShip S2 = new Submarine();
        AbstractShip B = new Battleship();
        AbstractShip C = new Carrier();

        AbstractShip[] ships ={D,S1,S2,B,C};
        Integer totalShipsNbr = 5;

        BattleShipsAI ai = new BattleShipsAI(board, board);
        ai.putShips(ships);

        Integer destroyedShipsCount  = 0;
        
        int[] coords = new int[2];
        while(destroyedShipsCount < totalShipsNbr ){
            Hit hit = ai.sendHit(coords);
            if(hit != Hit.MISS && hit != Hit.STIKE){
                ++destroyedShipsCount;
            }
            System.out.println(coords);
            System.out.println(hit);
            board.print(); 
            sleep(1000);
        }

    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}