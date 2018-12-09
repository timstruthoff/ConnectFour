/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour.server.PlayerStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Store and access all players in the game.
 */
public class PlayerStore {

    private List<Player> players = new ArrayList<Player>();

    public List<Player> getAllPlayers() {
        return players;
    }

    /**
     * Gets a player by the socket through which they connected.
     *
     * @param pIpAddress The IP address of player.
     * @param pPort The port of player.
     * @return A player object or null if not found.
     */
    public Player getPlayerBySocket(String pIpAddress, int pPort) {
        for (Player p : players) {
            if (p.getIpAddress().equals(pIpAddress) && p.getPort() == pPort) {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets a player by the name.
     *
     * @param pName The name to be checked.
     * @return A player object or null if not found.
     */
    public Player getPlayerByName(String pName) {
        for (Player p : players) {
            if (p.getName().equals(pName)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets a player by the name.
     *
     * @param pID The UUID of a player.
     * @return A player object or null if not found.
     */
    public Player getPlayerByID(String pID) {
        for (Player p : players) {
            if (p.getID().equals(pID)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets a player by the name.
     *
     * @param pNumber The number of a player.
     * @return A player object or null if not found.
     */
    public Player getPlayerByNumber(int pNumber) {
        if (pNumber >= 0 && pNumber < players.size()) {
            return players.get(pNumber);
        } else {
            return null;
        }
    }

    /**
     * Adds a new player to the game.
     *
     * @param pName The name of the new player.
     * @param pClientIP
     * @param pPort
     * @return
     */
    public Player addPlayer(String pName, String pClientIP, int pPort) {
        Player p = new Player(pName, pClientIP, pPort);
        players.add(p);
        return p;
    }

    /**
     * Get the number of players in th player store
     *
     * @return The number of players.
     */
    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     * Get the position of the player in the player list.
     *
     * @return The position of the player.
     */
    public int getNumberOfPlayer(Player pPlayer) {
        return players.indexOf(pPlayer);
    }

    public List<Player> getAllExcept(Player pException) {
        List<Player> returnList = new ArrayList<Player>();

        for (Player p : players) {
            if (p != pException) {
                returnList.add(p);
            }
        }

        return returnList;
    }
}
