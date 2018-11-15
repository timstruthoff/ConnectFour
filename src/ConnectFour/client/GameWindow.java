package ConnectFour.client;

import EgJavaLib2.egSas.*;
import javax.swing.JOptionPane;

public class GameWindow extends SasApp {

    private Cell[][] playingField = new Cell[7][7];

    private Text playerOneGraphic = new Text(500, 150, "Name Player 1");
    private Text playerTwoGraphic = new Text(500, 200, "Name Player 2");
    private Text resultGraphic = new Text(500, 250, "Game not active");
    private Circle activePlayerIndicatorGraphic = new Circle(465, 155, 10, "green");
    private Arrow[] arrowGraphics = new Arrow[7];

    private String playerOneColor = "blue";
    private String playerTwoColor = "red";

    private NetworkingClient client;

    public GameWindow(NetworkingClient gameClient) {
        this.drawPlayingField();
        this.drawArrows();
        myView.setSize(1000, 600);
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

    public void setPlayerOneName(String pName) {
        playerOneGraphic.setText(pName);
    }

    public void setPlayerTwoName(String pName) {
        playerTwoGraphic.setText(pName);
    }

    public String showNameDialog() {
        return myView.zeigeEingabeDialog("Wie heißt du?");
    }

    public void setResultGraphicText(String pText) {
        resultGraphic.setText(pText);
    }

    public void mouseClicked() {
        Object o = myView.getLastClicked();
        if (o instanceof Arrow) {
            Arrow p = (Arrow) o;
            // this.drop(p.getColumn(), playerColor);
            myView.zeigeInfoDialog("Es wurde folgende Spalte ausgewählt: " + (p.getColumn() + 1));
        }
    }

    /*public void mouseMoved(){
        if(pfeilArray[6] != null){
           for(int i = 0; i < 7; i++){
            if(arrowGraphics[i].contains(myMouse.getX(), myMouse.getY())){
                arrowGraphics[i].setColumn(playerColor);
            }
            else{
                arrowGraphics[i].setColumn("black");
            }
        }
        }

    }*/
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
