/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour.server;

/**
 *
 * @author tmst
 */
public class PlayingFieldModel {

    private final int numberOfColumns;
    private final int numberOfRows;
    private String[][] playingField;
    private int numberOfMarks = 0;

    public PlayingFieldModel(int pNumberOfColumns, int pNumberOfRows) {
        numberOfColumns = pNumberOfColumns;
        numberOfRows = pNumberOfRows;
        playingField = new String[numberOfColumns][numberOfRows];
        this.cleanPlayingField();
    }

    /**
     * Resets all cells in the model to empty.
     */
    public void cleanPlayingField() {
        for (int column = 0; column < numberOfColumns; column++) {
            for (int row = 0; row < numberOfRows; row++) {
                playingField[column][row] = "";
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
                if (playingField[pColumn][row] == "") {
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
    public void setMark(String playerId, int pColumn, int pRow) {
        if (playingField[pColumn][pRow].equals("")) {

            // Using a seperate marking character for each player.
            playingField[pColumn][pRow] = playerId;

            numberOfMarks++;
        } else {
            System.err.println("Fehler bei Zeichensetzen: Feld schon markiert");
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
            returnString = returnString + "[";
            for (int column = 0; column < numberOfColumns; column++) {
                returnString = returnString + playingField[column][row] + ", ";
            }
            returnString = returnString + "]" + System.getProperty("line.separator");
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

                String mark1 = playingField[position][row];
                String mark2 = playingField[position + 1][row];
                String mark3 = playingField[position + 2][row];
                String mark4 = playingField[position + 3][row];

                if (!mark1.equals("") && mark1.equals(mark2) && mark2.equals(mark3) && mark3.equals(mark4)) {

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

                    String mark1 = playingField[column][position];
                    String mark2 = playingField[column][position + 1];
                    String mark3 = playingField[column][position + 2];
                    String mark4 = playingField[column][position + 3];

                    if (!mark1.equals("") && mark1.equals(mark2) && mark2.equals(mark3) && mark3.equals(mark4)) {
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
                        String mark1 = playingField[column][row];
                        String mark2 = playingField[column + 1][row + 1];
                        String mark3 = playingField[column + 2][row + 2];
                        String mark4 = playingField[column + 3][row + 3];

                        if (!mark1.equals("") && mark1.equals(mark2) && mark2.equals(mark3) && mark3.equals(mark4)) {
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
                        String mark1 = playingField[column][row];
                        String mark2 = playingField[column - 1][row + 1];
                        String mark3 = playingField[column - 2][row + 2];
                        String mark4 = playingField[column - 3][row + 3];

                        if (!mark1.equals("") && mark1.equals(mark2) && mark2.equals(mark3) && mark3.equals(mark4)) {
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
