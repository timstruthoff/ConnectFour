package viergewinnt;

import EgJavaLib2.egSas.*;

public class Pfeil extends Sprite{
    private Polygon poly;
    private Rectangle rec;
    private int spalte;

    public Pfeil(){
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
    public int gibSpalte(){
        return spalte;   
    }

    public void setzeSpalte(int pSpalte){
        spalte = pSpalte;
    }

    public void setzeFarbe(String pFarbe){
        poly.setHexColor(pFarbe);
        rec.setHexColor(pFarbe);
    }

}
