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

        assertEquals(B,"B.toString()");
        assertEquals("Battleship",B.getName());
        Integer BattleshipSize = 4;
        assertEquals( BattleshipSize,B.getSize());
        assertEquals(Orientation.EAST,B.getOrientation() );


        assertEquals("C",C.toString());
        assertEquals("Carrier",C.getName());
        Integer CarrierSize = 5;
        assertEquals(CarrierSize,C.getSize() );
        assertEquals(Orientation.EAST,C.getOrientation());


        assertEquals("D",D.toString());
        assertEquals("Destroyer",D.getName());
        Integer DestroyerSize = 2;
        assertEquals( DestroyerSize,D.getSize());
        assertEquals(Orientation.EAST,D.getOrientation());

        assertEquals("S",S.toString());
        assertEquals("Submarine",S.getName());
        Integer SubmarineSize = 3;
        assertEquals(SubmarineSize,S.getSize());
        assertEquals(Orientation.EAST,S.getOrientation());



    }
    
}