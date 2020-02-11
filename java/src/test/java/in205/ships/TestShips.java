package in205.ships;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestShips {
    @Test
    public void testConstructor() {

        AbstractShip B = new Battleship();
        AbstractShip C = new Carrier();
        AbstractShip D = new Destroyer();
        AbstractShip S = new Submarine();

        assertEquals(B.toString(),"B");
        assertEquals(B.getName(),"Battleship");
        Integer BattleshipSize = 4;
        assertEquals(B.getSize(), BattleshipSize);
        assertEquals(B.getOrientation(), Orientation.EAST);


        assertEquals(C.toString(),"C");
        assertEquals(C.getName(),"Carrier");
        Integer CarrierSize = 5;
        assertEquals(C.getSize(), CarrierSize);
        assertEquals(C.getOrientation(), Orientation.EAST);


        assertEquals(D.toString(),"D");
        assertEquals(D.getName(),"Destroyer");
        Integer DestroyerSize = 2;
        assertEquals(D.getSize(), DestroyerSize);
        assertEquals(D.getOrientation(), Orientation.EAST);

        assertEquals(S.toString(),"S");
        assertEquals(S.getName(),"Submarine");
        Integer SubmarineSize = 3;
        assertEquals(S.getSize(), SubmarineSize);
        assertEquals(S.getOrientation(), Orientation.EAST);



    }
    
}