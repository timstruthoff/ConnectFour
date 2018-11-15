package ConnectFour.client;

import EgJavaLib2.egSas.*;

public class Arrow extends Sprite{
    private Polygon poly;
    private Rectangle rec;
    private int column;

    public Arrow(){
        rec = new Rectangle(100,100, 50,50, "black");
        rec.moveTo(100,100);
        this.add(rec);
        poly = new Polygon(125, 150);
        poly.add(50,0);
        poly.add(0,50);
        poly.add(-50,0);
        this.add(poly);

        this.scale(0.4,0.4);    

    }
    public int getColumn(){
        return column;   
    }

    public void setColumn(int pColumn){
        column = pColumn;
    }

    public void setColumn(String pColor){
        poly.setHexColor(pColor);
        rec.setHexColor(pColor);
    }

}
