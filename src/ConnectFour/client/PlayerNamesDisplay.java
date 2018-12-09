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
 *
 * @author tmst
 */
public class PlayerNamesDisplay {

    private int numberOfPlayers = 0;

    private List<String> playerNames = new ArrayList<String>();
    private List<Text> playerNameTexts = new ArrayList<Text>();

    private int playerTextVerticalSpacing = 50;
    private int playerTextXStart = 500;
    private int playerTextYStart = 150;

    private int activePlayerIndicatorXOffset = -35; // The amount the player indicator is moved to the right in respect to the player name texts.
    private int activePlayerIndicatorSize = 10;

    private Circle activePlayerIndicator;

    public PlayerNamesDisplay() {
        activePlayerIndicator = new Circle(0, 0, activePlayerIndicatorSize, "green");
        this.resetIndicator();
    }

    public void addPlayer(String pName) {
        playerNames.add(pName);

        int yCoordinate = playerTextYStart + numberOfPlayers * playerTextVerticalSpacing;

        Text t = new Text(playerTextXStart, yCoordinate, pName);
        playerNameTexts.add(t);

        numberOfPlayers++;
    }

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

            activePlayerIndicator.setTransparency(1);
            int index = playerNames.indexOf(pPlayerName);
            if (index < 0) {
                throw new IllegalArgumentException("Player " + pPlayerName + "not found!");
            }

            System.out.println("Index: " + index);
            int indicatorYCoordinate = playerTextYStart + index * playerTextVerticalSpacing - activePlayerIndicatorSize / 2;
            int indicatorXCoordinate = playerTextXStart + activePlayerIndicatorXOffset;
            System.out.println("Coordianates: x: " + indicatorXCoordinate + " y: " + indicatorYCoordinate);

            activePlayerIndicator.moveTo(indicatorXCoordinate, indicatorYCoordinate);
        }
    }
}
