/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour.client;

import javax.swing.JOptionPane;

/**
 *
 * @author tmst
 */
public class GameClient {

    public GameClient() {

        // Display dialogue for selecting a server ip.
        String serverIP = (String) JOptionPane.showInputDialog(null, "What is your server ip?", "The game is starting soon!!", JOptionPane.QUESTION_MESSAGE, null, null, "127.0.0.1");

        // Check if server ip is valid.
        if ((serverIP != null) && (serverIP.length() > 6)) {
            // Start new game window with selected server ip.
            NetworkingClient client = new NetworkingClient(serverIP);
            GameWindow window = new GameWindow(client);
            ConnectFourGame gameLogic = new ConnectFourGame();

            gameLogic.setWindow(window);
            gameLogic.setClient(client);
        }

    }
}
