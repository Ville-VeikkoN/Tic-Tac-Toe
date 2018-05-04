/**
* TicTacToeUi runs the game until there is a winner or the gameboard is full.
* 
* <p> Asks the size of the gameboard and
* runs the game until there is the winner
* or the gameboard is full.
*
* @author      Ville-Veikko Nieminen <ville-veikko.nieminen@cs.tamk.fi>
* @version     2017.1209
* @since       1.6          
*/
import java.util.Scanner;

class TicTacToeUi {
    /**
    * Runs the game until there is a winner or gameboard is full.
    *
    * <p> Asks the size of the gameboard and the needed amount of chars in a line 
    * for a win and blocks invalid inputs. Runs the game until there is a winner or the gameboard is full.
    *
    * @param args Command line parameters. Not used.
    */
    public static void main(String [] args) {
        String coordinates = "";
        Scanner input = new Scanner(System.in);

        while(TicTacToe.getHeigth() < 2) {
            System.out.println("Give the heigth of the gameboard (minimum heigth is 3)");
            TicTacToe.setHeigth(input.nextLine());
        }
        while(TicTacToe.getWidth() < 2) {
            System.out.println("Give the width of the gameboard (minimum width is 3)");
            TicTacToe.setWidth(input.nextLine());
        }
        while(TicTacToe.getHowManytoWin() < 2) {
            if(TicTacToe.getHeigth()*TicTacToe.getWidth()>=100) {
                System.out.println("Because size of the gameboard, you need to get 5 characters in line to win the game");
                TicTacToe.setHowManytoWin("5");
            } else {
                System.out.println("How many 'x' or '0' you need to get in a row to win? suggestion is 3");
                TicTacToe.setHowManytoWin(input.nextLine());
            }
        }

        Player user = new Player();
        AI computer = new AI();
        char [][] gameboard;
        gameboard = new char[TicTacToe.getHeigth()][TicTacToe.getWidth()];

        TicTacToe.intializeGameboard(gameboard);
        TicTacToe.printGameboard(gameboard);
        while(TicTacToe.isGameOn(gameboard) == true) {
            System.out.println("User's turn. Give coordinates for the 'x'. First heigth then width. Separate them with ','");
            coordinates = input.nextLine();
            while(user.isUserInputValid(coordinates, gameboard) == false) {
                System.out.println("Input is not valid. Give only integers that is shown in the coordinates and separate them with ','");
                coordinates = input.nextLine();
            }
            user.setX(gameboard);
            if(TicTacToe.isGameOn(gameboard)==true) {
                computer.set0(gameboard);
            }
            TicTacToe.printGameboard(gameboard);
        }
        System.out.println(TicTacToe.whoWins());
    }
}

// End of file