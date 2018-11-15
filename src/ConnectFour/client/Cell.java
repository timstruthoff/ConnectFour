package ConnectFour.client;

import EgJavaLib2.egSas.*;

/**
 * The graphics of a cell in the playing field.
 * @author tmst
 */
public class Cell extends Sprite{
    
    private Rectangle innerRectangle;
    private Rectangle borderRectangle;
    private Circle circle;
    
    private int column;
    private int row;
    
    private int width;
    
    public Cell(int pX, int pY) {
        width = 50;
        column = -1;
        row = -1;
        borderRectangle = new Rectangle(pX, pY, width, width, "black");
        innerRectangle = new Rectangle(pX+2, pY+2, width-4, width-4, "white");
        circle = new Circle(pX+2, pY+2, (width-4)/2, "white");
        this.add(borderRectangle, innerRectangle, circle);
    }
    
    public void setColor(String pZeichen){
        circle.setHexColor(pZeichen);
    }
    
    /**
     * Set the position of the cell in the playing field.
     * @param pColumn The new column position of the cell.
     * @param pRow The new row position of the cell.
     */
    public void setPosition(int pColumn, int pRow){
        column = pColumn;
        row = pRow;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getColumn(){
        return column;
    }

    
    
}
