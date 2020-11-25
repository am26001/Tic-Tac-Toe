// program allows two people to play the game TicTacToe, it uses methods which are called in "main" to emulate the game
// @author Alan Martinez - Lopez  

package ttt;

import java.util.Scanner;

public class TTT {

    // "main" method calls other methods allowing the program and game to run (NOTE: "\n" is for readability)
    public static void main(String[] args) {

        // create scanner to take user input - call it "scnr"
        Scanner scnr = new Scanner(System.in);

        // call "createEmptyBoard" method to assign the 2d array values to "board"
        char[][] board = createEmptyBoard();

        // call "printBoard" method to print out "board" 2d array
        printBoard(board);

        // create character var for player token, set to 'x' - call it "token"
        char token = 'x';

        // create boolean var to determine if game should continue, set to false - call it "endGame"
        boolean endGame = false;

        // create boolean var to determine if player's move is valid, set to false - call it "validMove"
        boolean validMove = false;

        // create integer var to keep track of the rounds, set to 0 - call it "turns"
        int turns = 0;

        // create integer var to determine which number gets replaced with the player's token, set to 0 - call it "playerNum"
        int playerNum = 0;

        // create loop to show the result of the game
        do {
            // create loop in case the user does an invalid move, exit loop until valid move is made by calling "validMove" method to update boolean var "validMove" 
            while (!validMove) {
                System.out.println("\nPick a number to place your token");
                playerNum = scnr.nextInt();
                validMove = validMove(board, validMove, playerNum);
            } // end loop 

            // call "updateBoard" method to replace numbers with the appropriate player token and store updated 2d array elements in "board"
            board = updateBoard(board, token, playerNum);
            // call "printBoard" method to print "board" or the updated 2d array
            printBoard(board);
            // alternate between player x and o 
            if (token == 'x') {
                token = 'o';
            } else if (token == 'o') {
                token = 'x';
            }
            // call "stopGame" method to update boolean var "endGame", determines if game continues
            endGame = stopGame(board, endGame);
            // increment turns by 1 if valid move is made to determine if game continues, draw factor
            turns++;
            //reset boolean var "validMove" to check if the player's move is valid before entering the loop again
            validMove = false;
            
        } while ((!endGame) && (turns != 9)); // end do while loop

        // call "declareOutcome" method to reveal the result of the game
        declareOutcome(board, endGame);

    }//end "main" method

    // "createEmptyBoard" method, generates a 17 x 17 2d array of characters, method return type is a 2d array of characters without using a parameter
    public static char[][] createEmptyBoard() {

        // create 2d array of characters, 17 x 17 - call it "A"
        char[][] A = new char[17][17];

        // create loop to assign 2d array elements with the appropriate character with conditional statements, first loop assigns rows and second loop assigns columns, known length: 17
        for (int row = 0; row < 17; row++) {
            for (int col = 0; col < 17; col++) {
                // 5th and 11th column element in the 2d array are assigned with the character, '|'
                if ((col == 5) || (col == 11)) {
                    A[row][col] = '|';
                  // takes care of the overlapping characters, assign them with '|' instead of '-'
                } else if (((row == 5) && (col == 5)) || ((row == 11) && (col == 11))) {
                    A[row][col] = '|';
                } // 5th and 11th row element in the 2d array are assigned with the character, '-'
                else if ((row == 5) || (row == 11)) {
                    A[row][col] = '-';
                } // assign the remaining row and column elements in the 2d array with the character, ' ' 
                else {
                    A[row][col] = ' ';
                }

            } // end second loop
        } // end first loop

        // manually assign the row and column elements with the appropriate number character that appears on the board, helps the player understand where they should place their token 
        A[2][2] = '1';
        A[2][8] = '2';
        A[2][14] = '3';
        A[8][2] = '4';
        A[8][8] = '5';
        A[8][14] = '6';
        A[14][2] = '7';
        A[14][8] = '8';
        A[14][14] = '9';

        // return the 2d array back to "main" method
        return A;

    } // end "createEmptyBoard" method

    // "printBoard" method prints the 2d array and uses a parameter to get info from "main" method
    public static void printBoard(char[][] A) {

        // create loop to print the 2d array, first loop prints rows and second loop prints columns, length is known (17)
        for (int row = 0; row < 17; row++) {
            for (int col = 0; col < 17; col++) {
                System.out.print(A[row][col]);
            } // end second loop
            System.out.println(); // new line for rows
        } // end first loop

    } // end "printBoard" method

    // "updateBoard" method replaces numbers on the board with the appropriate player token ('x' or 'o'), method return type is a 2d array of characters and uses a parameter to get info
    public static char[][] updateBoard(char[][] A, char token, int playerNum) {

        // create switch statement to determine which 2d array elements are reassigned with a new character, replaces the numbers on the board with the appropriate player token
        switch (playerNum) {
            case 1:
                A[2][2] = token;
                break;
            case 2:
                A[2][8] = token;
                break;
            case 3:
                A[2][14] = token;
                break;
            case 4:
                A[8][2] = token;
                break;
            case 5:
                A[8][8] = token;
                break;
            case 6:
                A[8][14] = token;
                break;
            case 7:
                A[14][2] = token;
                break;
            case 8:
                A[14][8] = token;
                break;
            case 9:
                A[14][14] = token;
                break;
            default:
                break;
        }

        // return the updated 2d array back to "main" method
        return A;

    } // end method "updateBoard"

    // "stopGame" method determines if game should continue depending on where the tokens lie on the board, update boolean, method return type is boolean and uses a parameter to get info
    public static boolean stopGame(char[][] A, boolean endGame) {

        // all possible outcomes for player x and o (straight or diagonally), if one player wins via the TicTacToe rules, set boolean to true (this stops the game)
        if ((A[2][2] == 'x') && (A[2][8] == 'x') && (A[2][14] == 'x')) {
            endGame = true;
        } else if ((A[2][2] == 'o') && (A[2][8] == 'o') && (A[2][14] == 'o')) {
            endGame = true;
        } else if ((A[8][2] == 'x') && (A[8][8] == 'x') && (A[8][14] == 'x')) {
            endGame = true;
        } else if ((A[8][2] == 'o') && (A[8][8] == 'o') && (A[8][14] == 'o')) {
            endGame = true;
        } else if ((A[14][2] == 'x') && (A[14][8] == 'x') && (A[14][14] == 'x')) {
            endGame = true;
        } else if ((A[14][2] == 'o') && (A[14][8] == 'o') && (A[14][14] == 'o')) {
            endGame = true;
        } else if ((A[2][2] == 'x') && (A[8][2] == 'x') && (A[14][2] == 'x')) {
            endGame = true;
        } else if ((A[2][2] == 'o') && (A[8][2] == 'o') && (A[14][2] == 'o')) {
            endGame = true;
        } else if ((A[2][8] == 'x') && (A[8][8] == 'x') && (A[14][8] == 'x')) {
            endGame = true;
        } else if ((A[2][8] == 'o') && (A[8][8] == 'o') && (A[14][8] == 'o')) {
            endGame = true;
        } else if ((A[2][14] == 'x') && (A[8][14] == 'x') && (A[14][14] == 'x')) {
            endGame = true;
        } else if ((A[2][14] == 'o') && (A[8][14] == 'o') && (A[14][14] == 'o')) {
            endGame = true;
        } else if ((A[2][2] == 'x') && (A[8][8] == 'x') && (A[14][14] == 'x')) {
            endGame = true;
        } else if ((A[2][2] == 'o') && (A[8][8] == 'o') && (A[14][14] == 'o')) {
            endGame = true;
        } else if ((A[2][14] == 'x') && (A[8][8] == 'x') && (A[14][2] == 'x')) {
            endGame = true;
        } else if ((A[2][14] == 'o') && (A[8][8] == 'o') && (A[14][2] == 'o')) {
            endGame = true;
        }

        // return the boolean back to "main" method
        return endGame;

    } // end method "stopGame"

    // "declareOutcome" method prints which player won using conditional statements, method uses a parameter to get info 
    public static void declareOutcome(char[][] A, boolean endGame) {

        // print who wins via the TicTacToe rules, the game is a draw if no one wins 
        if ((A[2][2] == 'x') && (A[2][8] == 'x') && (A[2][14] == 'x')) {
            System.out.println("\nPlayer x won!");
        } else if ((A[2][2] == 'o') && (A[2][8] == 'o') && (A[2][14] == 'o')) {
            System.out.println("\nPlayer o won!");
        } else if ((A[8][2] == 'x') && (A[8][8] == 'x') && (A[8][14] == 'x')) {
            System.out.println("\nPlayer x won!");
        } else if ((A[8][2] == 'o') && (A[8][8] == 'o') && (A[8][14] == 'o')) {
            System.out.println("\nPlayer o won!");
        } else if ((A[14][2] == 'x') && (A[14][8] == 'x') && (A[14][14] == 'x')) {
            System.out.println("\nPlayer x won!");
        } else if ((A[14][2] == 'o') && (A[14][8] == 'o') && (A[14][14] == 'o')) {
            System.out.println("\nPlayer o won!");
        } else if ((A[2][2] == 'x') && (A[8][2] == 'x') && (A[14][2] == 'x')) {
            System.out.println("\nPlayer x won!");
        } else if ((A[2][2] == 'o') && (A[8][2] == 'o') && (A[14][2] == 'o')) {
            System.out.println("\nPlayer o won!");
        } else if ((A[2][8] == 'x') && (A[8][8] == 'x') && (A[14][8] == 'x')) {
            System.out.println("\nPlayer x won!");
        } else if ((A[2][8] == 'o') && (A[8][8] == 'o') && (A[14][8] == 'o')) {
            System.out.println("\nPlayer o won!");
        } else if ((A[2][14] == 'x') && (A[8][14] == 'x') && (A[14][14] == 'x')) {
            System.out.println("\nPlayer x won!");
        } else if ((A[2][14] == 'o') && (A[8][14] == 'o') && (A[14][14] == 'o')) {
            System.out.println("\nPlayer o won!");
        } else if ((A[2][2] == 'x') && (A[8][8] == 'x') && (A[14][14] == 'x')) {
            System.out.println("\nPlayer x won!");
        } else if ((A[2][2] == 'o') && (A[8][8] == 'o') && (A[14][14] == 'o')) {
            System.out.println("\nPlayer o won!");
        } else if ((A[2][14] == 'x') && (A[8][8] == 'x') && (A[14][2] == 'x')) {
            System.out.println("\nPlayer x won!");
        } else if ((A[2][14] == 'o') && (A[8][8] == 'o') && (A[14][2] == 'o')) {
            System.out.println("\nPlayer o won!");
        } else {
            System.out.println("\nNo player won, it's a draw!");
        }

    } // end method "declareOutcome"

    // "validMove" method determines if player's move is valid, method return type is boolean and uses parameter to get info
    public static boolean validMove(char[][] A, boolean validMove, int playerNum) {

        // create integer vars to act as the 2d array elements, set to 0 - call them "row" and "col"
        int row = 0, col = 0;

        // create switch statement to determine if the player's number or move is valid on the board
        switch (playerNum) {
            case 1:
                row = 2;
                col = 2;
                break;
            case 2:
                row = 2;
                col = 8;
                break;
            case 3:
                row = 2;
                col = 14;
                break;
            case 4:
                row = 8;
                col = 2;
                break;
            case 5:
                row = 8;
                col = 8;
                break;
            case 6:
                row = 8;
                col = 14;
                break;
            case 7:
                row = 14;
                col = 2;
                break;
            case 8:
                row = 14;
                col = 8;
                break;
            case 9:
                row = 14;
                col = 14;
                break;
            default:
                break;
        } // end switch

        // create conditional statement to determine if move is valid (number entered is 1 - 9 and letters must not be on the same spot), USE previous row and col values, update boolean
        if ((playerNum >= 1) && (playerNum <= 9) && (A[row][col] != 'x') && (A[row][col] != 'o')) {
            validMove = true;
        } else {
            System.out.println("Invalid move, place token somewhere else");
            validMove = false;
        }

        // return the boolean back to "main" method 
        return validMove;

    } // end "validMove" method
    
}//end class