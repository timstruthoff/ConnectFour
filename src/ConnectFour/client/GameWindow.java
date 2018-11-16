package ConnectFour.client;

import EgJavaLib2.egSas.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GameWindow extends SasApp {

    private Cell[][] playingField = new Cell[7][7];

    private List<Text> playerNameTexts = new ArrayList<Text>();
    private int playerTextVerticalSpacing = 50;
    private int playerTextYStart = 150;
    private int playerTextXStart = 500;

    private Text resultGraphic = new Text(500, 250, "Game not active");
    private Circle activePlayerIndicatorGraphic = new Circle(465, 155, 10, "green");
    private Arrow[] arrowGraphics = new Arrow[7];

    private String playerOneColor = "blue";
    private String playerTwoColor = "red";

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
        int xStart = 50;
        int yStart = 85;

        for (int i = 0; i < 7; i++) {
            arrowGraphics[i] = new Arrow();
            arrowGraphics[i].moveTo(xStart, yStart);
            arrowGraphics[i].setColumn(i);
            xStart = xStart + 50;
        }
    }

    public void drawPlayingField() {
        int xStart = 50;
        int yStart = 150;

        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                Cell f = new Cell(xStart, yStart);
                f.setPosition(column, row);
                playingField[column][row] = f;
                xStart = xStart + 50;
            }
            xStart = 50;
            yStart = yStart + 50;
        }
    }

    public void setPlayerOneActive() {
        activePlayerIndicatorGraphic.setHidden(false);
        activePlayerIndicatorGraphic.moveTo(465, 155);
    }

    public void setPlayerTwoActive() {
        activePlayerIndicatorGraphic.setHidden(false);
        activePlayerIndicatorGraphic.moveTo(465, 205);
    }

    public void addPlayer(String pName) {
        int yCoordinate = playerTextYStart + playerNameTexts.size() * playerTextVerticalSpacing;
        Text t = new Text(playerTextXStart, yCoordinate, pName);
        playerNameTexts.add(t);
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

        /*for (int i = 0; i < 7; i++) {
            if (arrowGraphics[6] != null) {
                if (arrowGraphics[i].contains(myMouse.getX(), myMouse.getY())) {
                    arrowGraphics[i].setColor(playerOneColor);
                } else {
                    arrowGraphics[i].setColor("black");
                }
            }
        }*/
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

}
