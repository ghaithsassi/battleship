package in205.ships;
/**
 * Destroyer
 */
public class Destroyer extends AbstractShip {
    public Destroyer(Orientation orientation){
        super('D',"Destroyer",orientation,2);
    }
    public Destroyer(){
        this(Orientation.EAST);
    }
}