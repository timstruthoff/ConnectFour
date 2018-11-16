/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour.server;

import ConnectFour.server.PlayerStore.Player;
import ConnectFour.server.PlayerStore.PlayerStore;
import java.util.List;

/**
 *
 * @author tmst
 */
public class ServerGameLogic {

    private GameServer gameServer;
    private PlayerStore playerStore = new PlayerStore();
    private int currentPlayerNumber = 0;
    private Player currentPlayer;

    public ServerGameLogic(GameServer pGameServer) {
        gameServer = pGameServer;

    }

    /**
     * Add a new player to the game.
     *
     * @param pName
     * @param pIpAddress
     * @param pPort
     * @return The player.
     */
    public Player addPlayer(String pName, String pIpAddress, int pPort) {
        Player p = playerStore.addPlayer(pName, pIpAddress, pPort);
        return p;
    }

    public PlayerStore getPlayerStore() {
        return playerStore;
    }

    public Player getActivePlayer() {
        return currentPlayer;
    }

    public List<Player> getInactivePlayers() {
        return playerStore.getAllExcept(currentPlayer);
    }

    public void nextPlayer() {
        currentPlayerNumber++;
        currentPlayer = playerStore.getPlayerByNumber(this.currentPlayerNumber);
    }

}
