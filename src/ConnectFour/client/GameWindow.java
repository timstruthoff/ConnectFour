package ConnectFour.client;

import EgJavaLib2.egSas.*;

public class GameWindow extends SasApp{
    
    private Field[][] playingField = new Field[7][7];
    
    private Text playerOneGraphic = new Text(500,150,"Name Spieler 1");
    private Text playerTwoGraphic = new Text(500,200,"Name Spieler 2");
    private Text resultGraphic = new Text(500,250,"Spiel nicht aktiv");
    private Circle activePlayerIndicatorGraphic = new Circle(465,155,10, "green");
    private Arrow[] arrowGraphics = new Arrow[7];
    private String playerColor;
    
    private int numberOfChipsInColumns[] = {0,0,0,0,0,0,0};
    
    private GameClient client;
    
    public GameWindow(GameClient gameClient) {
        this.drawPlayingField();
        playerColor = "black";
        this.drawArrows();
        myView.setSize(1000,600);
    }
    
    public void drawArrows(){
        int xStart = 50;
        int yStart = 85;
        
        for(int i = 0; i < 7; i++){
            arrowGraphics[i] = new Arrow();
            arrowGraphics[i].setColumn(playerColor);
            arrowGraphics[i].moveTo(xStart, yStart);
            arrowGraphics[i].setColumn(i);
            xStart = xStart + 50;
        }
    }

    public void drawPlayingField(){
        int xStart = 50;
        int yStart = 150;
        
        for(int row = 0; row < 7; row++){
            for(int column = 0; column < 7; column++){
                Field f = new Field(xStart, yStart);
                f.setzeDaten(column, row);
                playingField[column][row] = f;
                xStart = xStart + 50;
            }
            xStart = 50;
            yStart = yStart + 50;
        }
    }
    
    public void setPlayerOneActive(){
        activePlayerIndicatorGraphic.setHidden(false);
        activePlayerIndicatorGraphic.moveTo(465,155);
    }
    
    public void setPlayerTwoActive(){
        activePlayerIndicatorGraphic.setHidden(false);
        activePlayerIndicatorGraphic.moveTo(465,205);
    }
    
    public String showNameDialog(){
        return myView.zeigeEingabeDialog("Wie heißt du?");
    }
    
    public void setResultGraphicText(String pText){
        resultGraphic.setText(pText);
    }
    
    public void mouseClicked(){
        Object o = myView.getLastClicked();
        if( o instanceof Arrow){
            Arrow p = (Arrow) o;
            this.drop(p.getColumn(), playerColor);
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
     * Drop a chip in a column.
     * Calculates the position of the top spot and places a chip there.
     * @param pColumn
     * @param pColor 
     */
    public void drop (int pColumn, String pColor){
        
        // Calculate the position of the dropped chip.
        int row = 6 - numberOfChipsInColumns[pColumn];
        
        // Change the filed color at that position.
        this.setFieldCellColor(pColumn, row, pColor);
        
        // Increase the number of chips in that column.
        numberOfChipsInColumns[pColumn] = numberOfChipsInColumns[pColumn] + 1;
    }
    
    /**
     * Change the color of a cell in th eplaying field to show that there is a chip placed there.
     * @param pColumn The column of the cell of which the color should be changed.
     * @param pRow The row of the cell of which the color should be changed.
     * @param pColor The name of the new color as a string.
     */
    public void setFieldCellColor(int pColumn, int pRow, String pColor){
        playingField[pColumn][pRow].setzeFarbe(pColor);
    }
    
    /**
     * Change the color of the user's player.
     * @param pFarbe The name of the new color as a string.
     */
    public void setPlayerColor(String pFarbe){
        playerColor = pFarbe;
    }
    
    
    
    
    
}
