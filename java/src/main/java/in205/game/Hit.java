package in205.game;

import java.util.NoSuchElementException;

public enum Hit {
    MISS(-1, "Miss"),
    STIKE(-2, "Hit"),
    DESTROYER(2, "Destroyer"),
    SUBMARINE(3, "Submarine"),
    BATTLESHIP(4, "Battleship"),
    CARRIER(5, "Carrier");

    /* ***
     * Attributs
     */
    private int value;
    private String label;

    /* ***
     * Constructeur
     */
    Hit(int value, String label) {
        this.value = value;
        this.label = label;
    }

    /* ***
     * Méthodes
     */
    public static Hit fromInt(int value) {
        for (Hit hit : Hit.values()) {
            if (hit.value == value) {
                return hit;
            }
        }
        throw new NoSuchElementException("no enum for value " + value);
    }

    public String toString() {
        return this.label;
    }
};
