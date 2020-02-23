package in205.ships;
/**
 * Destroyer
 */
public class Destroyer extends AbstractShip {

    private static final long serialVersionUID = 1L;
    public Destroyer(Orientation orientation) {
        super('D',"Destroyer",orientation,2);
    }
    public Destroyer(){
        this(Orientation.EAST);
    }
}