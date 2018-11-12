package viergewinnt;

import javax.swing.JOptionPane;
/**
 * Write a description of class Start here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Start
{

    public Start()
    {
        Object[] optionen = {"Server starten + als Client mitspielen", "Nur als Client mitspielen","Abbrechen"};

        int eingabe =   JOptionPane.showOptionDialog(null, "Was möchtest du machen?", "Vier gewinnt! Spielstart", 
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionen, optionen[1]);

        if(eingabe == 0){
            
            SpielServer s = new SpielServer();

        }
        else if(eingabe == 1){
            
            String serverIP = (String)JOptionPane.showInputDialog( null, "Wie lautet die Server-IP?", "Gleich geht's los!", JOptionPane.QUESTION_MESSAGE,null, null, "127.0.0.1");
            if ((serverIP != null) && (serverIP.length() >6)) {
                SpielFenster f = new SpielFenster(serverIP);
            }
            
        }
        else{
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        Start s = new Start();
    }
}
