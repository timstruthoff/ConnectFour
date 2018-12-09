package ConnectFour.client;

import EgJavaLib2.egSas.*;

/**
 * The graphics of a cell in the playing field.
 */
public class Cell extends Sprite {

    private Rectangle innerRectangle;
    private Rectangle borderRectangle;
    private Circle circle;

    private int column;
    private int row;

    private int width;

    /**
     * Create a cell.
     *
     * @param pX The horizontal coordinate of the cell.
     * @param pY The vertical coordinate of the cell.
     */
    public Cell(int pX, int pY) {
        width = 50;
        column = -1;
        row = -1;
        borderRectangle = new Rectangle(pX, pY, width, width, "black");
        innerRectangle = new Rectangle(pX + 2, pY + 2, width - 4, width - 4, "white");
        circle = new Circle(pX + 2, pY + 2, (width - 4) / 2, "white");
        this.add(borderRectangle, innerRectangle, circle);
    }

    /**
     * Change the color of the cell.
     *
     * @param pColorName The name of the new color.
     */
    public void setColor(String pColorName) {
        circle.setHexColor(pColorName);
    }

    /**
     * Set the position of the cell in the playing field.
     *
     * @param pColumn The new column position of the cell.
     * @param pRow The new row position of the cell.
     */
    public void setPosition(int pColumn, int pRow) {
        column = pColumn;
        row = pRow;
    }

    /**
     * Get the row in which the cell is.
     *
     * @return The row number
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column in which the cell is.
     *
     * @return column row number
     */
    public int getColumn() {
        return column;
    }

}
