package ConnectFour.client;

import EgJavaLib2.egSas.*;

/**
 * The graphic of an arrow.
 */
public class Arrow extends Sprite {

    private Polygon poly;
    private Rectangle rec;
    private int column;

    /**
     * The graphic of an arrow.
     */
    public Arrow() {
        rec = new Rectangle(100, 100, 50, 50, "black");
        rec.moveTo(100, 100);
        this.add(rec);
        poly = new Polygon(125, 150);
        poly.add(50, 0);
        poly.add(0, 50);
        poly.add(-50, 0);
        this.add(poly);

        this.scale(0.4, 0.4);

    }

    /**
     * Get the column to which the arrow belongs.
     *
     * @return The column number.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Set the column to which the arrow belongs.
     *
     * @param pColumn The column number.
     */
    public void setColumn(int pColumn) {
        column = pColumn;
    }

    /**
     * Change the color of the arrow.
     *
     * @param pColor The name of a new color.
     */
    public void setColor(String pColor) {
        poly.setHexColor(pColor);
        rec.setHexColor(pColor);
    }

}
