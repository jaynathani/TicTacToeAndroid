package com.test.simplisafe;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.simplisafe.Manager.GameManager;
import com.test.simplisafe.Model.Game;

import static com.test.simplisafe.Model.WinType.WIN_2X2_MATRIX;
import static com.test.simplisafe.Model.WinType.WIN_DIAGONAL;
import static com.test.simplisafe.Model.WinType.WIN_FOUR_CORNERS;
import static com.test.simplisafe.Model.WinType.WIN_HORIZONTAL;
import static com.test.simplisafe.Model.WinType.WIN_REVERSE_DIAGONAL;
import static com.test.simplisafe.Model.WinType.WIN_VERTICAL;
import static com.test.simplisafe.util.Constants.rowSize;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GameManager gameManager;
    private TextView currentPlayerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameManager = new GameManager();
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                gameManager.getGame().getBoard().setButton(i, j, findViewById(resID));
                gameManager.getGame().getBoard().getButton(i, j).setOnClickListener(this);
            }
        }

        Button buttonNewGame = findViewById(R.id.new_game_button);
        buttonNewGame.setOnClickListener(view -> {
            gameManager.resetGame();
            currentPlayerTextView.setText(gameManager.getGame().getCurrentPlayer().getName());
        });
        currentPlayerTextView = findViewById(R.id.current_player);
    }

    @Override
    public void onClick(View v) {
        Game game = gameManager.getGame();
        if (!((Button) v).getText().toString().isEmpty()) {
            return;
        }

        int gridNumber = Integer.parseInt(v.getTag().toString());
        int row = gridNumber / rowSize;
        int column = gridNumber - (rowSize * row);

        ((Button) v).setText(game.getCurrentPlayer().getName());
        gameManager.incrementGameCount();

        if (gameManager.isFourCheckWin()) {
            gameManager.setFourCheckWinBackground();
            displayWinMessage(WIN_FOUR_CORNERS.getMessage());
        }
        if (gameManager.isVerticalCheckWin(column)) {
            gameManager.setVerticalCheckWinBackground(column);
            displayWinMessage(WIN_VERTICAL.getMessage());
        }
        if (gameManager.isHorizontalCheckWin(row)) {
            gameManager.setHorizontalCheckWinBackground(row);
            displayWinMessage(WIN_HORIZONTAL.getMessage());
        }
        if (gameManager.isDiagonalCheckWin()) {
            gameManager.setDiagonalCheckWinBackgroind();
            displayWinMessage(WIN_DIAGONAL.getMessage());
        }
        if (gameManager.isReverseDiagonalCheckWin()) {
            gameManager.setReverseDiagonalWinBackground();
            displayWinMessage(WIN_REVERSE_DIAGONAL.getMessage());
        }
        if (gameManager.isTwoByTowCheckWin(row, column)) {
            displayWinMessage(WIN_2X2_MATRIX.getMessage());
        }
        if (gameManager.isTie()) {
            displayTieMessage();
        }

        gameManager.swapPlayer();
        currentPlayerTextView.setText(gameManager.getGame().getCurrentPlayer().getName());
    }

    public void displayWinMessage(String message) {
        new AlertDialog.Builder(MainActivity.this)
            .setPositiveButton("New Game", (dialog, which) -> {
                gameManager.resetGame();
                currentPlayerTextView.setText(gameManager.getGame().getCurrentPlayer().getName());
            })
            .setNegativeButton("Exit Game", (dialog, which) -> System.exit(1))
            .setMessage(message)
            .setTitle("Congratulations player " + gameManager.getGame().getCurrentPlayer().getName())
            .show();
    }

    public void displayTieMessage() {
        new AlertDialog.Builder(MainActivity.this)
            .setPositiveButton("New Game", (dialog, which) -> {
                gameManager.resetGame();
                currentPlayerTextView.setText(gameManager.getGame().getCurrentPlayer().getName());
            })
            .setNegativeButton("Exit Game", (dialog, which) -> System.exit(1))
            .setMessage("The game is a draw.")
            .setTitle("Game ended.")
            .show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
