package battleship;

import java.util.HashMap;
import java.util.Map;

public enum Ship {
    CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    private final int length;
    private final String name;
    private char[] status;
    private Map<Integer, String> statusShip = new HashMap<>();

    private Ship(String name, int length) {
        this.name = name;
        this.length = length;
        status = new char[length];
    }

    public String getName() {
        return this.name;
    }

    public int getLength() {
        return this.length;
    }

    public boolean isAlive(){
        boolean result = true;
        if (status[length] == '1') return false;
        return  result;
    }

    public void placeShipOnDesk(Coordinates coordinates){
        statusShip.put(1, coordinates.getA());
    }


}