package viergewinnt;


/**
 * Beschreiben Sie hier die Klasse.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class VierGewinntSpiel
{
    private String[][] spielfeld = new String[7][7];
    private boolean spielGestartet;
    private Spieler spielerEins;
    private Spieler spielerZwei;
    private Spieler aktuellerSpieler;
    private int anzahlSpieler;
    private int anzahlZeichen;
    private String ergebnisDesSpiels;

    private SpielServer server;

    public VierGewinntSpiel()
    {
        spielGestartet = false;
        anzahlSpieler = 0;
        anzahlZeichen = 0;
        spielerEins = new Spieler("Spieler 1");
        spielerZwei = new Spieler("Spieler 2");

        for(int spalte = 0; spalte < 7; spalte++){
            for(int zeile = 0; zeile < 7; zeile++){
                spielfeld[spalte][zeile] = " ";
            } 
        }

    }

    public void setzeServer(SpielServer pServer){
        server = pServer;
    }

    public void spielStarten(){
        spielGestartet = true;
        aktuellerSpieler = spielerEins;
    }

    public void setzeZeichen(int pSpalte, int pZeile){
        if(spielfeld[pSpalte][pZeile].equals(" ")){
            if(aktuellerSpieler == spielerEins){
                spielfeld[pSpalte][pZeile] = "X";
                anzahlZeichen++;
                this.spielerWechsel();
            }
            else if(aktuellerSpieler == spielerZwei){ 
                spielfeld[pSpalte][pZeile] = "O";
                anzahlZeichen++;
                this.spielerWechsel();
            }
            else{
                System.err.println("Fehler bei Zeichensetzen: kein aktueller Spieler");
            }
        }
        else{
            System.err.println("Fehler bei Zeichensetzen: Feld schon markiert");
        }

    }

    public void spielerWechsel(){
        if(aktuellerSpieler == spielerEins){
            aktuellerSpieler = spielerZwei;
        }
        else if(aktuellerSpieler == spielerZwei){ 
            aktuellerSpieler = spielerEins;
        }
        else{
            System.err.println("Fehler bei Spielerwechsel: kein aktueller Spieler");            
        }
    }

    public void checkeAufEnde(){
        boolean sieg = false;

        for(int zeile = 0; zeile < 7; zeile++){
            for(int position = 0; position < 4; position++){

                String zeichen1 = spielfeld[position][zeile];
                String zeichen2 = spielfeld[position + 1][zeile];
                String zeichen3 = spielfeld[position + 2][zeile];
                String zeichen4 = spielfeld[position + 3][zeile];

                if(zeichen1.equals(zeichen2) && zeichen2.equals(zeichen3) && zeichen3.equals(zeichen4)){
                    sieg = true;
                    break;
                }
            }
        }

        if(sieg == false){
            for(int spalte = 0; spalte < 7; spalte++){
                for(int position = 0; position < 4; position++){

                    String zeichen1 = spielfeld[spalte][position];
                    String zeichen2 = spielfeld[spalte][position + 1];
                    String zeichen3 = spielfeld[spalte][position + 2];
                    String zeichen4 = spielfeld[spalte][position + 3];

                    if(zeichen1.equals(zeichen2) && zeichen2.equals(zeichen3) && zeichen3.equals(zeichen4)){
                        sieg = true;
                        break;
                    }
                }
            }
        }

        if(sieg == false){
            for(int spalte = 0; spalte < 7; spalte++){
                for(int zeile = 0; zeile < 7; zeile++){
                    if(spalte + 3 < 7 && zeile + 3 < 7){
                        String zeichen1 = spielfeld[spalte][zeile];
                        String zeichen2 = spielfeld[spalte+1][zeile + 1];
                        String zeichen3 = spielfeld[spalte+2][zeile + 2];
                        String zeichen4 = spielfeld[spalte+3][zeile + 3];

                        if(zeichen1.equals(zeichen2) && zeichen2.equals(zeichen3) && zeichen3.equals(zeichen4)){
                            sieg = true;
                            break;
                        }
                    }
                }
            }

        }
        
        if(sieg == false){
            for(int spalte = 6; spalte >= 0; spalte--){
                for(int zeile = 6; zeile >= 0; zeile--){
                    if(spalte - 3 >= 0 && zeile  - 3 >= 0){
                        String zeichen1 = spielfeld[spalte][zeile];
                        String zeichen2 = spielfeld[spalte-1][zeile - 1];
                        String zeichen3 = spielfeld[spalte-2][zeile - 2];
                        String zeichen4 = spielfeld[spalte-3][zeile - 3];

                        if(zeichen1.equals(zeichen2) && zeichen2.equals(zeichen3) && zeichen3.equals(zeichen4)){
                            sieg = true;
                            break;
                        }
                    }
                }
            }

        }     
        this.spielEnde(sieg);       
    }

    
    public void spielEnde(boolean pSieg){
        ergebnisDesSpiels = "Spiel läuft noch!";
        if(pSieg){
            spielGestartet = false;
            ergebnisDesSpiels = "Das Spiel ist vorbei! Sieger ist: "+ aktuellerSpieler.gibName(); 
        }
        else{
            if(anzahlZeichen == 49){
                ergebnisDesSpiels = "Das Spiel ist vorbei! Unentschieden, es gibt keinen Sieger!"; 
            }

        }
    }

    public String gibNameSpielerEins(){
        return spielerEins.gibName();
    }

    public String gibNameSpielerZwei(){
        return spielerZwei.gibName();
    }

    public void spielerHinzuFügen(String pName){
        if(anzahlSpieler == 0){
            spielerEins.setzeName(pName);
        }
        else if(anzahlSpieler == 1){
            spielerZwei.setzeName(pName);
        }
        else{
            System.err.println("Fehler bei Spieler Hinzufügen: Spiel voll");
        }

    }

   
}
