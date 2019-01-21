package com.test.simplisafe.Model;

import static com.test.simplisafe.util.Constants.PLAYER_X;

public class Game {

    private Board board;
    private Player currentPlayer;
    private int tilesPlayed;

    public Game() {
        board = new Board();
        currentPlayer = new Player(PLAYER_X);
        tilesPlayed = 0;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getTilesPlayed() {
        return tilesPlayed;
    }

    public void setTilesPlayed(int tilesPlayed) {
        this.tilesPlayed = tilesPlayed;
    }
}
