package ConnectFour;

import ConnectFour.server.GameServer;
import ConnectFour.client.GameClient;
import javax.swing.JOptionPane;

/**
 * Start the application
 */
public class Start {

    public Start() {

        System.out.println("Start: App started.");

        // Prepare options which the user can select when starting the app.
        Object[] options = {"Start server and play as a client.", "Only play as a client", "Cancel"};

        // Display dialogue for selecting if user only wants to play as a client or also wants to start a server.
        int input = JOptionPane.showOptionDialog(null, "What do you want to do?", "Connect Four! Start of game",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

        switch (input) {

            // User selected "Start server and play as a client".
            case 0:

                // Start new game server and new client.
                GameServer server = new GameServer();
                GameClient client = new GameClient();
                break;

            // User selected "Only play as a client".
            case 1:

                // Start new game server and new client.
                GameClient client2 = new GameClient();
                break;
            default:
                System.exit(0);
        }
    }

    public static void main(String[] args) {
        Start s = new Start();
    }
}
