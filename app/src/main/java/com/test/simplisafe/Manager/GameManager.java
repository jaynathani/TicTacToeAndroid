package com.test.simplisafe.Manager;

import android.widget.Button;

import com.test.simplisafe.Model.Board;
import com.test.simplisafe.Model.Game;

import static com.test.simplisafe.util.Constants.NO_TEXT;
import static com.test.simplisafe.util.Constants.PLAYER_O;
import static com.test.simplisafe.util.Constants.PLAYER_X;
import static com.test.simplisafe.util.Constants.rowSize;

public class GameManager {

    private Game game;

    public GameManager() {
        game = new Game();
    }

    public Game getGame() {
        return game;
    }

    public void incrementGameCount() {
        game.setTilesPlayed(game.getTilesPlayed() + 1);
    }

    /**
     * Resets the game and sets the default values for current player and tiles played.
     */
    public void resetGame() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                game.getBoard().buttons[i][j].setText(NO_TEXT);
                game.getBoard().buttons[i][j].setBackgroundResource(android.R.drawable.btn_default);
            }
        }
        game.getCurrentPlayer().setName(PLAYER_X);
        game.setTilesPlayed(0);
    }

    /**
     * Swaps the current player to the other player.
     */
    public void swapPlayer() {
        game.getCurrentPlayer().setName(PLAYER_X.equals(game.getCurrentPlayer().getName()) ?
                                        PLAYER_O :
                                        PLAYER_X);
    }

    /**
     * Checks whether the four corners are equal or not.
     * @return true if the four corners of the board are equal, otherwise false
     */
    public boolean isFourCheckWin() {
        Board board = game.getBoard();
        String currentPlayer = game.getCurrentPlayer().getName();

        return !currentPlayer.isEmpty()
                && currentPlayer.equals(board.getButtonText(0, 0))
                && currentPlayer.equals(board.getButtonText(0, rowSize - 1))
                && currentPlayer.equals(board.getButtonText(rowSize - 1, 0))
                && currentPlayer.equals(board.getButtonText(rowSize - 1, rowSize - 1));
    }

    /**
     * Set the background color of four check win tiles.
     */
    public void setFourCheckWinBackground(){
        setWinBackground(0, 0);
        setWinBackground(0, rowSize - 1);
        setWinBackground(rowSize - 1, 0);
        setWinBackground(rowSize - 1, rowSize - 1);
    }

    /**
     * Checks whether the vertical column in which the player played is equal or not
     * @param y column number of the board
     * @return true if there is a vertical win, otherwise false
     */
    public boolean isVerticalCheckWin(int y) {
        Board board = game.getBoard();
        String currentPlayer = game.getCurrentPlayer().getName();

        for (int i = 0; i < rowSize; i++) {
            if (!currentPlayer.equals(board.getButtonText(i, y))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Set the background color of the vertical check win tiles.
     * @param y column number of the board
     */
    public void setVerticalCheckWinBackground(int y){
        for (int i = 0; i < rowSize; i++) {
            setWinBackground(i, y);
        }
    }

    /**
     * Checks whether the horizontal row in which the player played is equal or not
     * @param x row number of the board
     * @return true if there is a horizontal win, otherwise false
     */
    public boolean isHorizontalCheckWin(int x) {
        Board board = game.getBoard();
        String currentPlayer = game.getCurrentPlayer().getName();

        for (int j = 0; j < rowSize; j++) {
            if (!currentPlayer.equals(board.getButtonText(x, j))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Set the background color of the horizontal check win tiles.
     * @param x row number of the board
     */
    public void setHorizontalCheckWinBackground(int x){
        for (int j = 0; j < rowSize; j++) {
            setWinBackground(x, j);
        }
    }

    /**
     * Checks whether the diagonal is equal or not
     * @return true if there is a diagonal win, otherwise false
     */
    public boolean isDiagonalCheckWin() {
        Board board = game.getBoard();
        String currentPlayer = game.getCurrentPlayer().getName();

        for (int i = 0; i < rowSize; i++) {
            if (!currentPlayer.equals(board.getButtonText(i, i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Set the background color of the diagonal win tiles.
     */
    public void setDiagonalCheckWinBackgroind(){
        for (int i = 0; i < rowSize; i++) {
            setWinBackground(i, i);
        }
    }

    /**
     * Checks whether the reverse diagonal is equal or not
     * @return true if there is a reverse diagonal win, otherwise false
     */
    public boolean isReverseDiagonalCheckWin() {
        Board board = game.getBoard();
        String currentPlayer = game.getCurrentPlayer().getName();

        for (int i = 0; i < rowSize; i++) {
            if (!currentPlayer.equals(board.getButtonText(i, rowSize - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Set the background color of the reverse diagonal win tiles.
     */
    public void setReverseDiagonalWinBackground(){
        for (int i = 0; i < rowSize; i++) {
            setWinBackground(i, rowSize - 1 - i);
        }
    }

    //TwoByTwo matrix check

    /**
     * Checks whether any two by two matrix around the played tile is equal
     * @param x row number of the board
     * @param y column number of the board
     * @return true if there is a twoByTwo win, otherwise false
     */
    public boolean isTwoByTowCheckWin(int x, int y) {
        //Check top left corner
        if (x == 0 && y == 0) {
            return quad3Check(x, y);
        }
        //Check top right corner
        else if (x == 0 && y == rowSize - 1) {
            return quad4Check(x, y);
        }
        //Check bottom right corner
        else if (x == rowSize - 1 && y == rowSize - 1) {
            return quad1Check(x, y);
        }
        //Check bottom left corner
        else if (x == rowSize - 1 && y == 0)
            return quad2Check(x, y);
        //Top Edge
        else if (x == 0 && y > 0 && y < rowSize - 1) {
            return quad3Check(x, y) || quad4Check(x, y);
        }
        //Bottom Edge
        else if (x == rowSize - 1 && y > 0 && y < rowSize - 1) {
            return quad1Check(x, y) || quad2Check(x, y);
        }
        //Left Edge
        else if (y == 0 && x > 0 && x < rowSize - 1) {
            return quad2Check(x, y) || quad3Check(x, y);
        }
        //Right Edge
        else if (y == rowSize - 1 && x > 0 && x < rowSize - 1) {
            return quad1Check(x, y) || quad4Check(x, y);
        }
        //Anywhere else
        else
            return quad1Check(x, y) || quad2Check(x, y) || quad3Check(x, y) || quad4Check(x, y);
    }

    //Quadrant 1 check

    /**
     * Checks whether the quadrant 1 is equal or not
     * @param x row number
     * @param y column number
     * @return true if quadrant 1 is equal otherwise false
     */
    private boolean quad1Check(int x, int y) {
        Board board = game.getBoard();
        String currentPlayer = game.getCurrentPlayer().getName();

        if(currentPlayer.equals(board.getButtonText(x, y))
                && currentPlayer.equals(board.getButtonText(x - 1, y))
                && currentPlayer.equals(board.getButtonText(x - 1, y - 1))
                && currentPlayer.equals(board.getButtonText(x, y - 1))){
            setQuad1WinBackground(x, y);
            return true;
        }
        return false;
    }

    /**
     * Sets the background color of the quad 1 winning tiles
     * @param x row number
     * @param y column number
     */
    private void setQuad1WinBackground(int x, int y){
        setWinBackground(x, y);
        setWinBackground(x - 1, y);
        setWinBackground(x - 1, y - 1);
        setWinBackground(x, y - 1);
    }

    /**
     * Checks whether the quadrant 2 is equal or not
     * @param x row number
     * @param y column number
     * @return true if quadrant 2 is equal otherwise false
     */
    private boolean quad2Check(int x, int y) {
        Board board = game.getBoard();
        String currentPlayer = game.getCurrentPlayer().getName();

        if(currentPlayer.equals(board.getButtonText(x, y))
                && currentPlayer.equals(board.getButtonText(x - 1, y))
                && currentPlayer.equals(board.getButtonText(x - 1, y + 1))
                && currentPlayer.equals(board.getButtonText(x, y + 1))){
            setQuad2WinBackground(x, y);
            return true;
        }
        return false;
    }

    /**
     * Sets the background color of the quad 2 winning tiles
     * @param x row number
     * @param y column number
     */
    private void setQuad2WinBackground(int x, int y){
        setWinBackground(x, y);
        setWinBackground(x - 1, y);
        setWinBackground(x - 1, y + 1);
        setWinBackground(x, y + 1);
    }

    /**
     * Checks whether the quadrant 3 is equal or not
     * @param x row number
     * @param y column number
     * @return true if quadrant 3 is equal otherwise false
     */
    private boolean quad3Check(int x, int y) {
        Board board = game.getBoard();
        String currentPlayer = game.getCurrentPlayer().getName();

        if(currentPlayer.equals(board.getButtonText(x, y))
                && currentPlayer.equals(board.getButtonText(x, y + 1))
                && currentPlayer.equals(board.getButtonText(x + 1, y + 1))
                && currentPlayer.equals(board.getButtonText(x + 1, y))){
            setQuad3WinBackground(x, y);
            return true;
        }
        return false;
    }

    /**
     * Sets the background color of the quad 3 winning tiles
     * @param x row number
     * @param y column number
     */
    private void setQuad3WinBackground(int x, int y){
        setWinBackground(x, y);
        setWinBackground(x, y + 1);
        setWinBackground(x + 1, y + 1);
        setWinBackground(x + 1, y);
    }

    /**
     * Checks whether the quadrant 4 is equal or not
     * @param x row number
     * @param y column number
     * @return true if quadrant 4 is equal otherwise false
     */
    private boolean quad4Check(int x, int y) {
        Board board = game.getBoard();
        String currentPlayer = game.getCurrentPlayer().getName();

        if(currentPlayer.equals(board.getButtonText(x, y))
                && currentPlayer.equals(board.getButtonText(x + 1, y))
                && currentPlayer.equals(board.getButtonText(x + 1, y - 1))
                && currentPlayer.equals(board.getButtonText(x, y - 1))){
            setQuad4WinBackground(x, y);
            return true;
        }
        return false;
    }

    /**
     * Sets the background color of the quad 4 winning tiles
     * @param x row number
     * @param y column number
     */
    private void setQuad4WinBackground(int x, int y){
        setWinBackground(x, y);
        setWinBackground(x + 1, y);
        setWinBackground(x + 1, y - 1);
        setWinBackground(x, y - 1);
    }

    /**
     * Checks whether there is a tie in the game or not.
     * @return true if there is a ties, otherwise false
     */
    public boolean isTie() {
        return game.getTilesPlayed() == rowSize * rowSize;
    }

    /**
     * Change background color to Red.
     * @param x row number
     * @param y column number
     */
    private void setWinBackground(int x, int y) {
        Button button = game.getBoard().getButton(x, y);
        button.setBackgroundColor(0xFFFF0000);
        game.getBoard().setButton(x, y, button);
    }

}
