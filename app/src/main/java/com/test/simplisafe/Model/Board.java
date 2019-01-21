package com.test.simplisafe.Model;

import android.widget.Button;

import static com.test.simplisafe.util.Constants.rowSize;

public class Board {

    public Button[][] buttons;

    public Board() {
        buttons = new Button[rowSize][rowSize];
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public void setButtons(Button[][] buttons) {
        this.buttons = buttons;
    }

    public void setButton(int i, int j, Button button) {
        this.buttons[i][j] = button;
    }

    public Button getButton(int i, int j) {
        return buttons[i][j];
    }

    public String getButtonText(int i, int j) {
        return buttons[i][j].getText().toString();
    }

    public void setButtonText(int i, int j, String text){
        buttons[i][j].setText(text);
    }
}
