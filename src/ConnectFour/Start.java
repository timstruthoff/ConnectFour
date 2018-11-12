package ConnectFour;

import javax.swing.JOptionPane;

/**
 * Write a description of class Start here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Start {

    public Start() {
        
        // Prepare options which the user can select when starting the app.
        Object[] options = {"Start server and play as a client.", "Only play as a client", "Cancel"};
        
        // Display dialogue for selecting if user only wants to play as a client or also wants to start a server.
        int input = JOptionPane.showOptionDialog(null, "What do you want to do?", "Connect Four! Start of game",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

        switch (input) {
            
            // User selected "Start server and play as a client".
            case 0:
                
                // Start new game server
                GameServer s = new GameServer();
                break;
                
            // User selected "Only play as a client".
            case 1:
                
                // Display dialogue for selecting a server ip.
                String serverIP = (String) JOptionPane.showInputDialog(null, "Wie lautet die Server-IP?", "Gleich geht's los!", JOptionPane.QUESTION_MESSAGE, null, null, "127.0.0.1");
                
                
                // Check if server ip is valid.
                if ((serverIP != null) && (serverIP.length() > 6)) {
                    
                    // Start new game window with selected server ip.
                    GameClient client = new GameClient(serverIP);
                    GameWindow window = new GameWindow(client);
                    ConnectFourGame gameLogic = new ConnectFourGame();
                
                    gameLogic.setWindow(window);
                    gameLogic.setClient(client);
                    
                }   
                break;
            default:
                System.exit(0);
        }
    }

    public static void main(String[] args) {
        Start s = new Start();
    }
}
