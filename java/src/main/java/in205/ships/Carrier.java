package in205.ships;
/**
 * Carrier
 */
public class Carrier extends AbstractShip {
    public Carrier(Orientation orientation){
        super('C', "Carrier", orientation, 5);
    }
    public Carrier(){
        this(Orientation.EAST);
    }
}