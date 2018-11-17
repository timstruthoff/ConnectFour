/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour.server;

import ConnectFour.server.PlayerStore.Player;

/**
 *
 * @author tmst
 */
public class PlayingFieldModel {

    private final int numberOfColumns;
    private final int numberOfRows;
    private Player[][] playingField;
    private int numberOfMarks = 0;

    public PlayingFieldModel(int pNumberOfColumns, int pNumberOfRows) {
        numberOfColumns = pNumberOfColumns;
        numberOfRows = pNumberOfRows;
        playingField = new Player[numberOfColumns][numberOfRows];
        this.cleanPlayingField();
    }

    /**
     * Resets all cells in the model to empty.
     */
    public void cleanPlayingField() {
        for (int column = 0; column < numberOfColumns; column++) {
            for (int row = 0; row < numberOfRows; row++) {
                playingField[column][row] = null;
            }
        }
    }

    /**
     * Check if a number is describing a column on the playing field.
     *
     * @param pColumn The column number to be checked.
     * @return Boolean indicating whether the column number is valid.
     */
    public boolean isValidColumnNumber(int pColumn) {
        return pColumn < numberOfColumns && pColumn >= 0;
    }

    /**
     * Get the lowest free spot in a column in the playing field.
     *
     * @param pColumn The column number.
     * @return The row number of the free spot or -1 if no free spot was fund
     */
    public int getFreeRowInColumn(int pColumn) {
        if (this.isValidColumnNumber(pColumn)) {
            for (int row = numberOfRows - 1; row >= 0; row--) {
                if (playingField[pColumn][row] == null) {
                    return row;
                }
            }
            return -1;
        } else {
            throw new IllegalArgumentException("Invalid column number provided while getting free row number!");
        }
    }

    /**
     * Checks if all cells on the playing field are full.
     *
     * @return Boolean indicating whether all cells are full.
     */
    public boolean isFieldFull() {
        return numberOfMarks == (numberOfColumns * numberOfRows);
    }

    /**
     * Sets a mark on a specific position on the playing field. This mark is
     * stored in the string array with a specific character for each player.
     *
     * @param pPlayerNumber The number of the player for whom the mark should be
     * set. The number 0 is for player one and the number 1 is for player two.
     * @param pColumn The column where the mark should be set.
     * @param pRow The row where the mark should be set.
     */
    public void setMark(Player pPlayer, int pColumn, int pRow) {
        if (playingField[pColumn][pRow] == null) {

            // Using a seperate marking character for each player.
            playingField[pColumn][pRow] = pPlayer;

            numberOfMarks++;
        } else {
            System.err.println("Error setting mark: Cell already marked!");
        }

    }

    /**
     * Get the playing field as a string.
     *
     * @return
     */
    public String toString() {
        String returnString = "";
        for (int row = 0; row < numberOfRows; row++) {
            returnString = returnString + System.getProperty("line.separator") + "[ ";
            for (int column = 0; column < numberOfColumns; column++) {
                if (playingField[column][row] == null) {
                    returnString = returnString + "-, ";
                } else {
                    returnString = returnString + playingField[column][row].getName() + ", ";
                }
            }
            returnString = returnString + " ]";
        }
        return returnString;
    }

    /**
     * Checks if the game has ended and then returns the winner
     *
     * @return Boolean indicating whether the game has ended.
     */
    public boolean hasGameEnded() {
        boolean ended = false;

        System.out.println("Checking horizontally.");

        // Check for streaks of four in horizontal direction.
        for (int row = 0; row < numberOfRows; row++) {
            for (int position = 0; position < (numberOfColumns - 3); position++) {

                Player mark2 = playingField[position + 1][row];
                Player mark1 = playingField[position][row];
                Player mark3 = playingField[position + 2][row];
                Player mark4 = playingField[position + 3][row];

                if (mark1 != null && mark1 == mark2 && mark2 == mark3 && mark3 == mark4) {

                    ended = true;
                    break;
                }
            }
        }

        if (ended == false) {

            System.out.println("Checking vertically.");

            // Check for streaks of four in vertical direction.
            for (int column = 0; column < numberOfColumns; column++) {
                for (int position = 0; position < (numberOfRows - 3); position++) {

                    Player mark1 = playingField[column][position];
                    Player mark2 = playingField[column][position + 1];
                    Player mark3 = playingField[column][position + 2];
                    Player mark4 = playingField[column][position + 3];

                    if (mark1 != null && mark1 == mark2 && mark2 == mark3 && mark3 == mark4) {
                        ended = true;
                        break;
                    }
                }
            }
        }

        if (ended == false) {

            System.out.println("Checking in diagonal direction from top left to bottom right.");

            // Check for streaks of four in diagonal direction from top left to bottom right.
            for (int column = 0; column < numberOfColumns; column++) {
                for (int row = 0; row < numberOfRows; row++) {
                    if (column + 3 < numberOfColumns && row + 3 < numberOfRows) {
                        Player mark1 = playingField[column][row];
                        Player mark2 = playingField[column + 1][row + 1];
                        Player mark3 = playingField[column + 2][row + 2];
                        Player mark4 = playingField[column + 3][row + 3];

                        if (mark1 != null && mark1 == mark2 && mark2 == mark3 && mark3 == mark4) {
                            ended = true;
                            break;
                        }
                    }
                }
            }

        }

        if (ended == false) {

            System.out.println("Checking in diagonal direction from top right to bottom left.");

            // Check for streaks of four in diagonal direction from bottom right to top left.
            for (int column = numberOfColumns - 1; column >= 0; column--) {
                for (int row = 0; row < numberOfRows; row++) {
                    if (column - 3 >= 0 && row + 3 < numberOfRows) {
                        Player mark1 = playingField[column][row];
                        Player mark2 = playingField[column - 1][row + 1];
                        Player mark3 = playingField[column - 2][row + 2];
                        Player mark4 = playingField[column - 3][row + 3];

                        if (mark1 != null && mark1 == mark2 && mark2 == mark3 && mark3 == mark4) {
                            ended = true;
                            break;
                        }
                    }
                }
            }

        }

        return ended;
    }
}