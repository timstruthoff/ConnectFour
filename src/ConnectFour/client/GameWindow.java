package ConnectFour.client;

import EgJavaLib2.egSas.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GameWindow extends SasApp {

    private int numberOfColumns = 7;
    private int numberOfRows = 7;

    private Cell[][] playingField = new Cell[numberOfColumns][numberOfRows];

    private Text resultGraphic = new Text(500, 250, "Game not active");

    private int arrowXStart = 50;
    private int arrowYStart = 85;
    private int arrowXSpacing = 50;
    private String arrowActiveColor = "black";
    private String arrowInactiveColor = "white";
    private Arrow[] arrowGraphics = new Arrow[numberOfColumns];

    private boolean showControls = false;

    private PlayerNamesDisplay playerNamesDisplay = new PlayerNamesDisplay();

    private ConnectFourGame gameLogic;

    public GameWindow() {

        this.drawPlayingField();
        this.drawArrows();
        myView.setSize(1000, 600);
    }

    public void setGameLogic(ConnectFourGame pGameLogic) {
        gameLogic = pGameLogic;
    }

    public String askForPlayerName() {
        return (String) JOptionPane.showInputDialog(null, "What is your name?", "", JOptionPane.QUESTION_MESSAGE, null, null, "");
    }

    public void drawArrows() {

        for (int i = 0; i < numberOfColumns; i++) {
            arrowGraphics[i] = new Arrow();
            arrowGraphics[i].setColor(this.arrowInactiveColor);
            arrowGraphics[i].moveTo(this.arrowXStart + i * this.arrowXSpacing, this.arrowYStart);
            arrowGraphics[i].setColumn(i);
        }
    }

    /**
     * Set the color of all arrows
     *
     * @param pColor The color name as a string. Must be one of the permitted
     * color names in EGJavaLib2.
     */
    public void setArrowColor(String pColor) {
        for (Arrow arrow : arrowGraphics) {
            arrow.setColor(pColor);
        }
    }

    public void drawPlayingField() {
        int xStart = 50;
        int yStart = 150;

        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                Cell f = new Cell(xStart, yStart);
                f.setPosition(column, row);
                playingField[column][row] = f;
                xStart = xStart + 50;
            }
            xStart = 50;
            yStart = yStart + 50;
        }
    }

    /**
     * Add a player to the window.
     *
     * @param pName The name of the new player.
     */
    public void addPlayer(String pName) {
        this.playerNamesDisplay.addPlayer(pName);
    }

    public void setActivePlayer(String pName) {
        this.playerNamesDisplay.setActivePlayer(pName);
    }

    public void setResultGraphicText(String pText) {
        resultGraphic.setText(pText);
    }

    public void mouseClicked() {
        Object o = myView.getLastClicked();
        if (o instanceof Arrow) {
            Arrow p = (Arrow) o;

            gameLogic.drop(p.getColumn());
            System.out.println("Window: Drop in column " + p.getColumn());
        }
    }

    public void mouseMoved() {

        for (int i = 0; i < numberOfColumns; i++) {
            if (arrowGraphics[i] != null) {
                if (showControls) {
                    if (arrowGraphics[i].contains(myMouse.getX(), myMouse.getY())) {
                        arrowGraphics[i].setColor(this.gameLogic.getPlayerColor(this.gameLogic.getMyName()));
                    } else {
                        arrowGraphics[i].setColor(arrowActiveColor);
                    }
                }
            }
        }
    }

    /**
     * Change the color of a cell in the playing field to show that there is a
     * chip placed there.
     *
     * @param pColumn The column of the cell of which the color should be
     * changed.
     * @param pRow The row of the cell of which the color should be changed.
     * @param pColor The name of the new color as a string.
     */
    public void setFieldCellColor(int pColumn, int pRow, String pColor) {
        playingField[pColumn][pRow].setColor(pColor);
    }

    /**
     * Empty all playing filled cells.
     */
    public void resetPlayingField() {
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                playingField[column][row].setColor("white");
            }
        }

    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void showControls() {
        this.showControls = true;
        this.resultGraphic.setText("");
        this.setArrowColor(arrowActiveColor);
    }

    public void hideControls() {
        this.showControls = false;
        this.setArrowColor(arrowInactiveColor);
    }

}
