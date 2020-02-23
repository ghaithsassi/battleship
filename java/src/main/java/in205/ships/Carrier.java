package in205.ships;
/**
 * Carrier
 */
public class Carrier extends AbstractShip {

    private static final long serialVersionUID = 1L;
    public Carrier(Orientation orientation) {
        super('C', "Carrier", orientation, 5);
    }
    public Carrier(){
        this(Orientation.EAST);
    }
}