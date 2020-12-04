package battleship;

public class Coordinates {
    private String a;
    private String b;
    private int aX;
    private int aY;
    private int bX;
    private int bY;
    private boolean isValid;
    private int length;

    public Coordinates(String a, String b) {
        if (checkIfInputIsValid(a, b)) {
            this.a = a;
            this.b = b;
            translateCoordinates();
        }
    }

    public Coordinates(String a) {
       if (checkIfInputIsValidOne(a)){
           this.a = a;
           translateOneCoordinates();
       }
    }

    public String getA() {
        return a;
    }

    private boolean checkIfInputIsValidOne(String a) {
        String regex = "^[A-J](?:[1-9]|10)$";
        if (a.toUpperCase().matches(regex)) {
            return true;
        }
        return false;
    }

    private void translateOneCoordinates() {
        isValid = true;
        String[] aParts = a.split("(?<=\\D)(?=\\d)");
        aX = aParts[0].toUpperCase().charAt(0) - 65;
        aY = Integer.parseInt(aParts[1]) - 1;
    }

    public int getaX() {
        return aX;
    }

    public int getaY() {
        return aY;
    }

    public int getbY() {
        return bY;
    }

    public int getbX() {
        return bX;
    }

    public int getLength() {
        return length + 1;
    }

    public boolean isValid() {
        return isValid;
    }

    public void translateCoordinates() {
        isValid = true;
        String[] aParts = a.split("(?<=\\D)(?=\\d)");
        aX = aParts[0].toUpperCase().charAt(0) - 65;
        aY = Integer.parseInt(aParts[1]) - 1;
        String[] bParts = b.split("(?<=\\D)(?=\\d)");
        bX = bParts[0].toUpperCase().charAt(0) - 65;
        bY = Integer.parseInt(bParts[1]) - 1;
        if (isHorizontal()) {
            //if input was entered in reverse, it swaps the start and end variables
            if (aY > bY) {
                int temp = aY;
                aY = bY;
                bY = temp;
            }
            //and then calculates the length
            length = bY - aY;
        } else if (isVertical()) {
            if (aX > bX) {
                int temp = aX;
                aX = bX;
                bX = temp;
            }
            length = bX - aX;
        }
    }

    private boolean checkIfInputIsValid(String a, String b) {
        String regex = "^[A-J](?:[1-9]|10)$";
        if (a.toUpperCase().matches(regex) && b.toUpperCase().matches(regex)) {
            return true;
        }
        return false;
    }

    public boolean isHorizontal() {
        return aX == bX;
    }

    public boolean isVertical() {
        return aY == bY;
    }


}