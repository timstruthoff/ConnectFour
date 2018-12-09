/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour.client;

import EgJavaLib2.egSas.Circle;
import EgJavaLib2.egSas.Text;
import java.util.ArrayList;
import java.util.List;

/**
 * Store all player name text graphics. Also handles dynamic positioning.
 */
public class PlayerNamesDisplay {

    private int numberOfPlayers = 0;

    private List<String> playerNames = new ArrayList<String>();
    private List<Text> playerNameTexts = new ArrayList<Text>();

    private int playerTextVerticalSpacing = 50;
    private int playerTextXStart = 500;
    private int playerTextYStart = 150;

    private int activePlayerIndicatorXOffset = -35; // The amount by which the player indicator is moved to the right in respect to the player name text.
    private int activePlayerIndicatorSize = 10;

    private Circle activePlayerIndicator;

    /**
     * Create a display.
     */
    public PlayerNamesDisplay() {
        activePlayerIndicator = new Circle(0, 0, activePlayerIndicatorSize, "green");
        this.resetIndicator();
    }

    /**
     * Add a new player name.
     *
     * @param pName The name of the new player.
     */
    public void addPlayer(String pName) {
        playerNames.add(pName);

        int yCoordinate = playerTextYStart + numberOfPlayers * playerTextVerticalSpacing;

        Text t = new Text(playerTextXStart, yCoordinate, pName);
        playerNameTexts.add(t);

        numberOfPlayers++;
    }

    /**
     * Reset the current player indicator to its original position.
     */
    public void resetIndicator() {

        int x = playerTextXStart + activePlayerIndicatorXOffset;
        int y = playerTextYStart - activePlayerIndicatorSize / 2;
        activePlayerIndicator.moveTo(x, y);
    }

    /**
     * Move the active player indicator to a different player.
     *
     * @param pPlayerName The player to which the indicator should be moved.
     */
    public void setActivePlayer(String pPlayerName) {

        if (pPlayerName == null) {
            activePlayerIndicator.setTransparency(0);
        } else {

            // Search for the player name text.
            int index = playerNames.indexOf(pPlayerName);
            if (index < 0) {
                throw new IllegalArgumentException("Player " + pPlayerName + "not found!");
            }

            System.out.println("Index: " + index);

            // Calculate the new position of the indicator.
            int indicatorYCoordinate = playerTextYStart + index * playerTextVerticalSpacing - activePlayerIndicatorSize / 2;
            int indicatorXCoordinate = playerTextXStart + activePlayerIndicatorXOffset;
            System.out.println("Coordianates: x: " + indicatorXCoordinate + " y: " + indicatorYCoordinate);

            activePlayerIndicator.moveTo(indicatorXCoordinate, indicatorYCoordinate);
            activePlayerIndicator.setTransparency(1);
        }
    }
}
