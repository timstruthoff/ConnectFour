package ConnectFour.client;

import EgJavaLib2.netzwerk.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Write a description of class NetworkingClient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NetworkingClient extends Client {

    private int id = (int) (Math.random() * 899 + 100);
    private GameWindow window;
    private ConnectFourGame gameLogic;

    String newline = System.getProperty("line.separator");

    /**
     * Constructor for objects of class SpielClient
     *
     * @param pServerIP
     */
    public NetworkingClient(String pServerIP) {
        super(pServerIP, 1234);
        this.initialSetup();
    }

    public void setGameLogic(ConnectFourGame pGameLogic) {
        gameLogic = pGameLogic;
    }

    public void initialSetup() {
        this.send("START");
    }

    /**
     *
     * @param pMessage
     */
    @Override
    public void processMessage(String pMessage) {

        System.out.println("\\\\ Server to client " + id + ":" + newline + "\\\\ " + pMessage);

        List<String> messageParts = Arrays.asList(pMessage.split(" "));
        String command = messageParts.get(0);
        List<String> parameters = messageParts.subList(1, messageParts.size());

        switch (command) {
            case "OK":
                break;
            case "NEWENEMY":
                this.onNewPlayer(parameters.get(0));
                break;
            case "DROPPED":
                this.onDropped(parameters);
                break;
            case "END":
                this.onEnd(parameters.get(0));
                break;
            default:
                break;
        }
    }

    public void onNewPlayer(String pName) {
        gameLogic.addPlayer(pName);
    }

    public void onDropped(List<String> p) {
        if (p.size() != 3) {
            throw new IllegalArgumentException("Invalid number of arguments in dropped command from server.");
        }

        String playerName = p.get(0);
        int column = Integer.parseInt(p.get(1));
        if (column < 0 && column >= window.getNumberOfColumns()) {
            throw new IllegalArgumentException("Invalid column number " + column + ".");
        }

        int row = Integer.parseInt(p.get(2));
        if (row < 0 && row >= window.getNumberOfRows()) {
            throw new IllegalArgumentException("Invalid row number " + row + ".");
        }

        this.gameLogic.setMark(playerName, column, row);

        System.out.println(Arrays.toString(p.toArray()));
        System.out.println("Name: " + playerName + " column: " + column + " row: " + row);
    }

    public void onEnd(String pWinner) {
        this.gameLogic.onGameEnd(pWinner);
    }

    public void setWindow(GameWindow pWindow) {
        window = pWindow;
    }

    public void sendPlayerName(String pName) {
        this.send("LOGIN " + pName);
    }

    public void sendDrop(int pColumn) {
        this.send("DROP " + pColumn);
    }

}
