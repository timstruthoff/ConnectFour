package ConnectFour.client;

import EgJavaLib2.egSas.*;

public class Field extends Sprite{
    
    private Rectangle innen;
    private Rectangle rand;
    private Circle kreis;
    
    private int spalte;
    private int zeile;
    
    private int breite;
    
    public Field(int pX, int pY) {
        breite = 50;
        spalte = -1;
        zeile = -1;
        rand = new Rectangle(pX, pY, breite, breite, "black");
        innen = new Rectangle(pX+2, pY+2, breite-4, breite-4, "white");
        kreis = new Circle(pX+2, pY+2, (breite-4)/2, "white");
        this.add(rand, innen, kreis);
    }
    
    public void setzeFarbe(String pZeichen){
        kreis.setHexColor(pZeichen);
    }
    
    public int gibBreite(){
        return breite;
    }
    
    public void setzeDaten(int pSpalte, int pZeile){
        spalte = pSpalte;
        zeile = pZeile;
    }
    
    public int gibZeile(){
        return zeile;
    }
    
    public int gibSpalte(){
        return spalte;
    }

    
    
}
