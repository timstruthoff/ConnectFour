package viergewinnt;

import EgJavaLib2.egSas.*;

public class SpielFenster extends SasApp{
    
    private Feld[][] felder = new Feld[7][7];
    
    private Text spielerEins = new Text(500,150,"Name Spieler 1");
    private Text spielerZwei = new Text(500,200,"Name Spieler 2");
    private Text ergebnis = new Text(500,250,"Spiel nicht aktiv");
    private Circle aktiv = new Circle(465,155,10, "green");
    private Pfeil[] pfeilArray = new Pfeil[7];
    private String spielerFarbe;
    
    private int anzahlProSpalte[] = {0,0,0,0,0,0,0};
    
    private SpielClient client;
    
    public SpielFenster(String pServerIP) {
        this.zeichneSpielfeld();
        spielerFarbe = "black";
        this.zeichnePfeile();
        myView.setSize(1000,600);
        client = new SpielClient(pServerIP);
        client.setzeFenster(this);
    }
    
    public void zeichnePfeile(){
        int xStart = 50;
        int yStart = 85;
        
        for(int i = 0; i < 7; i++){
            pfeilArray[i] = new Pfeil();
            pfeilArray[i].setzeFarbe(spielerFarbe);
            pfeilArray[i].moveTo(xStart, yStart);
            pfeilArray[i].setzeSpalte(i);
            xStart = xStart + 50;
        }
    }

    public void zeichneSpielfeld(){
        int xStart = 50;
        int yStart = 150;
        
        for(int zeile = 0; zeile < 7; zeile++){
            for(int spalte = 0; spalte < 7; spalte++){
                Feld f = new Feld(xStart, yStart);
                f.setzeDaten(spalte, zeile);
                felder[spalte][zeile] = f;
                xStart = xStart + 50;
            }
            xStart = 50;
            yStart = yStart + 50;
        }
    }
    
    public void spielerEinsAktiv(){
        aktiv.setHidden(false);
        aktiv.moveTo(465,155);
    }
    
    public void spielerZweiAktiv(){
        aktiv.setHidden(false);
        aktiv.moveTo(465,205);
    }
    
    public String erfrageName(){
        return myView.zeigeEingabeDialog("Wie heißt du?");
    }
    
    public void setzeErgebnisText(String pText){
        ergebnis.setText(pText);
    }
    
    public void mouseClicked(){
        Object o = myView.getLastClicked();
        if( o instanceof Pfeil){
            Pfeil p = (Pfeil) o;
            this.einwerfen(p.gibSpalte(), spielerFarbe);
            //myView.zeigeInfoDialog("Es wurde folgende Spalte ausgewählt: "+p.gibSpalte());
        }
    }
    
    public void mouseMoved(){
        if(pfeilArray[6] != null){
           for(int i = 0; i < 7; i++){
            if(pfeilArray[i].contains(myMouse.getX(), myMouse.getY())){
                pfeilArray[i].setzeFarbe(spielerFarbe);
            }
            else{
                pfeilArray[i].setzeFarbe("black");
            }
        } 
        }
        
    }
    
    public void einwerfen(int pSpalte, String pFarbe){
        int zeile = 6 - anzahlProSpalte[pSpalte];
        felder[pSpalte][zeile].setzeFarbe(pFarbe);
        anzahlProSpalte[pSpalte] = anzahlProSpalte[pSpalte] + 1;
    }
    
    public void verändereFeld(int pSpalte, int pZeile, String pFarbe){
        felder[pSpalte][pZeile].setzeFarbe(pFarbe);
    }
    
    public void setzeSpielerFarbe(String pFarbe){
        spielerFarbe = pFarbe;
    }
    
    
    
    
    
}
