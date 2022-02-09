import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class TileBoard {

    private StackPane pane;
    private InfoCenter infoCenter;
    private Tile[][] tiles = new Tile[3][3];

    private char playerTurn = 'X';
    private boolean isEndGame = false;

    public TileBoard(InfoCenter infoCenter) {
        this.infoCenter = infoCenter;
        pane = new StackPane();
        pane.setMinSize(Constants.APP_WIDTH, Constants.TILE_BOARD_HEIGHT);
        pane.setTranslateX(Constants.APP_WIDTH / 2);
        pane.setTranslateY((Constants.TILE_BOARD_HEIGHT / 2) + Constants.INFO_CENTER_HEIGHT);

        addAllTiles();


    }

    private void addAllTiles() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Tile tile = new Tile();
                tile.getStackPane().setTranslateX((col * 100) - 100);
                tile.getStackPane().setTranslateY((row * 100) - 100);
                pane.getChildren().add(tile.getStackPane());
                tiles[row][col] = tile;
            }
        }


        }



    public void changePlayerTurn() {
        if (playerTurn == 'X') {
            playerTurn = 'O';
        } else {
            playerTurn = 'X';

        }
        infoCenter.updateMessage("Player " + playerTurn + "'s turn");
    }

    public String getPlayerTurn() {
        return String.valueOf(playerTurn);

    }

    public StackPane getStackPane() {
        return pane;
    }

    public void checkForWin() {
        checkRowsForWin();
        checkColsForWin();
        checkLeftDiagonalForWin();
        checkRightDiagonalForWin();
        checkForTie();

    }

    private void checkForTie() {
        if (!isEndGame);
         for (int row = 0; row < 3; row++) {
             for (int col = 0; col < 3; col++) {
                 if (tiles[row][col].getValue().isEmpty()) {
                     return;
                 }
             }
         }

         isEndGame = true;
         infoCenter.updateMessage("It is a Tie!");
         infoCenter.showStartButton();

    }

    private void checkRightDiagonalForWin() {
        if (!isEndGame);
        if ((tiles[0][2].getValue().equals(tiles[1][1].getValue())
               && tiles[0][2].getValue().equals(tiles[2][0].getValue()) && !tiles[0][2].getValue().isEmpty())) {
            String win = tiles[0][2].getValue();
            endGame(win);
            return;
        }
    }

    private void checkLeftDiagonalForWin() {
        if (!isEndGame);
        if (tiles[0][0].getValue().equals(tiles[1][1].getValue()) &&
                tiles[0][0].getValue().equals(tiles[2][2].getValue()) &&
                !tiles[0][0].getValue().isEmpty()) {
            String win = tiles[0][0].getValue();
            endGame(win);
            return;

        }
    }



    private void checkColsForWin() {
        if (!isEndGame) {
            for (int col = 0; col < 3; col++) {
                if (tiles[0][col].getValue().equals(tiles[1][col].getValue()) &&
                        (tiles[0][col].getValue().equals(tiles[2][col].getValue()) &&
                                !tiles[0][col].getValue().isEmpty())) {
                    String win = tiles[0][col].getValue();
                    endGame(win);
                    return;
                }
            }
        }
    }


    private void checkRowsForWin() {
        for (int row = 0; row < 3; row++) {
            if (tiles[row][0].getValue().equals(tiles[row][1].getValue()) &&
            (tiles[row][0].getValue().equals(tiles[row][2].getValue())) &&
                    !tiles[row][0].getValue().isEmpty()) {
                String win = tiles[row][0].getValue();
                endGame(win);
                return;

            }
        }
    }

    private void endGame(String win) {
        isEndGame = true;
        infoCenter.updateMessage("Player" + win + " wins");
        infoCenter.showStartButton();

    }

    public void startGameOver() {
        isEndGame = false;
        playerTurn = 'X';
        for (int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
               tiles[row][col].setValue("");
            }
        }

    }




    private class Tile {

        private StackPane pane;
        private Label label;

        public Tile() {
        pane = new StackPane();
        pane.setMinSize(100,100);

        Rectangle border = new Rectangle();
        border.setHeight(100);
        border.setWidth(100);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        pane.getChildren().add(border);

        label = new Label("");
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(24));
        pane.getChildren().add(label);

        pane.setOnMouseClicked(event -> {
           if (label.getText().isEmpty() && !isEndGame) {
               label.setText(getPlayerTurn());
               changePlayerTurn();
               checkForWin();
           }
            });
    }

    public StackPane getStackPane() {
            return pane;
    }

    public String getValue() {
            return label.getText();
    }

    public void setValue(String value) {
            label.setText(value);
    }
}
}
