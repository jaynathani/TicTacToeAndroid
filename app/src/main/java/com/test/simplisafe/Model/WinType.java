package com.test.simplisafe.Model;

public enum WinType {

    WIN_FOUR_CORNERS("You won on four corners!"),
    WIN_VERTICAL("You won vertical column!"),
    WIN_HORIZONTAL("You won horizontal column!"),
    WIN_DIAGONAL("You won on diagonal!"),
    WIN_REVERSE_DIAGONAL("You won on reverse diagonal!"),
    WIN_2X2_MATRIX("You won a 2 x 2 matrix");

    private String message;

    WinType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
