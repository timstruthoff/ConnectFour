package ConnectFour;

import EgJavaLib2.egSas.*;

public class GameWindow extends SasApp{
    
    private Field[][] felder = new Field[7][7];
    
    private Text spielerEins = new Text(500,150,"Name Spieler 1");
    private Text spielerZwei = new Text(500,200,"Name Spieler 2");
    private Text ergebnis = new Text(500,250,"Spiel nicht aktiv");
    private Circle aktiv = new Circle(465,155,10, "green");
    private Arrow[] pfeilArray = new Arrow[7];
    private String spielerFarbe;
    
    private int anzahlProSpalte[] = {0,0,0,0,0,0,0};
    
    private GameClient client;
    
    public GameWindow(String pServerIP) {
        this.zeichneSpielfeld();
        spielerFarbe = "black";
        this.zeichnePfeile();
        myView.setSize(1000,600);
        client = new GameClient(pServerIP);
        client.setWindow(this);
    }
    
    public void zeichnePfeile(){
        int xStart = 50;
        int yStart = 85;
        
        for(int i = 0; i < 7; i++){
            pfeilArray[i] = new Arrow();
            pfeilArray[i].setColumn(spielerFarbe);
            pfeilArray[i].moveTo(xStart, yStart);
            pfeilArray[i].setColumn(i);
            xStart = xStart + 50;
        }
    }

    public void zeichneSpielfeld(){
        int xStart = 50;
        int yStart = 150;
        
        for(int zeile = 0; zeile < 7; zeile++){
            for(int spalte = 0; spalte < 7; spalte++){
                Field f = new Field(xStart, yStart);
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
        if( o instanceof Arrow){
            Arrow p = (Arrow) o;
            this.einwerfen(p.getColumn(), spielerFarbe);
            //myView.zeigeInfoDialog("Es wurde folgende Spalte ausgewählt: "+p.getColumn());
        }
    }
    
    public void mouseMoved(){
        if(pfeilArray[6] != null){
           for(int i = 0; i < 7; i++){
            if(pfeilArray[i].contains(myMouse.getX(), myMouse.getY())){
                pfeilArray[i].setColumn(spielerFarbe);
            }
            else{
                pfeilArray[i].setColumn("black");
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
