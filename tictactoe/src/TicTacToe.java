/** 
* TicTacToe checks if the game is still running and sets the gameboard.
*
* <p> Sets the given gameboard sizes,
* intialize gameboard, prints gameboard,
* keep tracking if there is a winner or
* if the gameboard is full.
*
* @author      Ville-Veikko Nieminen
* @version     2017.1209
* @since       1.6          
*/

class TicTacToe {
    /**
    * Heigth of the gameboard.
    */
    private static int heigth;
    /** 
    * Width of the gameboard.
    */
    private static int width;
    /** 
    * Amount of chars needed to win.
    */
    private static int howManytoWin;
    /** 
    * Boolean that changes to true if user ('x') is the winner.
    */
    private static boolean winnerIsX;
    /** 
    * Boolean that changes to ture if comp. ('0') is the winner.
    */
    private static boolean winnerIs0;
    /**
    * Checks if the given height is valid.
    * 
    * @param x  given height of the gameboard in String
    */
    public static void setHeigth(String x) {
        try {
            int h = Integer.parseInt(x);
            if(h>=3) {
                heigth = h;
            } else {
                throw new NumberFormatException();
            }
        } catch(NumberFormatException ex) {
            System.out.println("Input is not valid. Give only the integer bigger than 2");
        }
    }
    /**
    * Checks if the given width is valid.
    * 
    * @param x given width of the gameboard in String.
    * 
    */
    public static void setWidth(String x) {
        try {
            int w = Integer.parseInt(x);
            if(w>=3) {
                width = w;
            } else {
                throw new NumberFormatException();
            }
        } catch(NumberFormatException ex) {
            System.out.println("Input is not valid. Give only the integer bigger than 2");
        }
    }

    /** 
    * Gives the heigth when asked.
    *
    * @return       height of the gameboard.
    */
    public static int getHeigth() {
        return heigth;
    }
    /** 
    * Gives the width when asked.
    *
    * @return       width of the gameboard
    */
    public static int getWidth() {
        return width;
    }
    /** 
    * Sets the amount of chars to win.
    *
    * <p> checks if the given amount of characters in line order to win
    * is valid.  
    * Max value is bigger value from heigth and width.
    *
    * @param x  given amount of characters in line order to win in String.
    *
    */
    public static void setHowManytoWin(String x) {
        int maxChars;
        if(getHeigth() > getWidth()) {
            maxChars = getHeigth();
        } else {
            maxChars = getWidth();
        }
        try {
            int s = Integer.parseInt(x);
            if(s>=3 && s<=maxChars) {
                howManytoWin = s;
            } else {
                throw new NumberFormatException();
            } 
        }catch(NumberFormatException ex) {
                System.out.println("Input is not valid. Minimum value is 3 and maximum value is "+maxChars);
        }   
    }

    /**
    * Returns the amount of char needed to win.
    *
    * @return   How many character must be in line to win.
    */
    public static int getHowManytoWin() {
        return howManytoWin;
    }
    /**
    * Intialize the gameboard.
    *
    * @param gameboard  Char array which is the gameboard.
    */
    public static void intializeGameboard(char [][] gameboard) {
        for(int i=0; i<getHeigth(); i++) {
            for(int j=0; j<getWidth(); j++) {
                gameboard[i][j]=' ';
            }
        }
    }
    /**
    * Prints the gameboard and puts square
    * brackets around every value of the array.
    *
    * @param gameboard  Char array which is the gameboard.
    */
    public static void printGameboard(char [][] gameboard) {
        int row = 0;
        int vertRow = 0;

        for(int i=0; i<getHeigth()+1; i++) {
            for(int j=0; j<getWidth()+1; j++) {
                if(i==0 && j!=0) {
                    if(row == 0) {
                        System.out.print("  ");
                    }
                    if(row < 10) {
                        System.out.print("  "+ row);
                    } else if (row >= 10 && row < 100) {
                        System.out.print(" "+row);
                    } else {
                        System.out.print(row);
                    }
                    row++;
                } else if(j==0&&i!=0) {
                    if(vertRow < 10) {
                        System.out.print(vertRow+"  ");
                    } else if(vertRow >= 10 && vertRow < 99) {
                        System.out.print(vertRow+" ");
                    } else {
                        System.out.print(vertRow);
                    }
                    vertRow++;
                } else if (i!=0 && j!=0){
                System.out.print("[" +gameboard[i-1][j-1] + "]");
                }
            }
            System.out.println("");
        }    
    }
    /**
    * Checks if the gameboard still has room.
    *
    * @param gameboard Char array which is the gameboard.
    *
    * @return   True or false depending it there is still room in the gameboard.
    */
    public static boolean isGameOn(char [][] gameboard) {
        for(int i=0; i<getHeigth(); i++) {
            for(int j=0; j<getWidth(); j++) {
                if(gameboard[i][j] == ' ' && isThereAWinner(gameboard) == false) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
    * Checks if there is a winner.
    * 
    * @param gameboard Array which is the gameboard.
    *
    * @return true or false depending if there is a winner or not.
    */
    public static boolean isThereAWinner(char [][] gameboard) {
        if(rowCheck(gameboard)==true || diagonalCheck(gameboard)==true || negDiagonalCheck(gameboard)==true || vertRowCheck(gameboard)==true) {
            return true;
        } else {
            return false;
        }
    }
    /** 
    * Checks the rows in case that there is a winner.
    * 
    * @param gameboard Array which is the gameboard.
    *
    * @return true or false depending if there is a winner or not.
    */
    public static boolean rowCheck(char[][]gameboard) {
        for(int i=0; i<getHeigth(); i++) {
            int amountOfX = 0;
            int amountOf0 = 0;
            for(int j=0; j<getWidth(); j++) {
                if(gameboard[i][j] == 'x') {
                    amountOfX ++;
                    amountOf0 = 0;
                } else if(gameboard[i][j] == '0'){
                    amountOf0 ++;
                    amountOfX = 0;
                } else {
                    amountOfX = 0;
                    amountOf0 = 0;
                }
                if(amountOfX == howManytoWin) {
                    winnerIsX = true;
                    return true;
                } else if (amountOf0 == howManytoWin) {
                    winnerIs0 = true;
                    return true;
                }
            }
        }
        return false;
    }
    /**
    * Cheks the diagonal rows in case that there is a winner
    *
    * @param gameboard Array which is the gameboard.
    *
    * @return true or false depending if there is a winner or not.
    */
    public static boolean diagonalCheck(char[][]gameboard) {
        int amountOfX = 0;
        int amountOf0 = 0;
        for(int i=0; i<getHeigth(); i++) {
            int x = i;
            for(int j=0; j<getWidth(); j++) {
                int y = j;
            boolean stillGoing = true;
                if(gameboard[i][j] == 'x') {
                    amountOfX++;
                    while(stillGoing == true && x<getHeigth()-1 && y<getWidth()-1) {  
                        x++;
                        y++;
                        if(gameboard[x][y] == 'x') {
                            amountOfX++;
                        } else {
                            stillGoing = false;
                        }
                    }
                }
                x =i;
                y =j;
                stillGoing = true;
                if(gameboard[i][j] == '0') {
                    amountOf0++;
                    while(stillGoing == true && x<getHeigth()-1 && y<getWidth()-1) { 
                        x++;
                        y++;
                        if(gameboard[x][y] == '0') {
                            amountOf0++;
                        } else {
                            stillGoing = false;
                        }
                    }
                }
                if(amountOfX == howManytoWin) {
                    winnerIsX = true;
                    return true;
                }
                if(amountOf0 == howManytoWin) {
                    winnerIs0 = true;
                    return true;
                }
                amountOfX = 0;
                amountOf0 = 0;
            }
        }
        return false;
    }

    /**
    * Checks the negative diagonal rows in case that there is a winner.
    *
    * @param gameboard Array which is the gameboard.
    *
    * @return true or false depending if there is a winner or not.
    */
    public static boolean negDiagonalCheck(char[][]gameboard) {
        int amountOfX = 0;
        int amountOf0 = 0;
        for(int i=0; i<getHeigth(); i++) {
            int x = i;
            for(int j=0; j<getWidth(); j++) {
                int y = j;
            boolean stillGoing = true;
                if(gameboard[i][j] == 'x') {                        
                    amountOfX++;                    
                    while(stillGoing == true && x>=0 && x<getHeigth()-1 && y>0) {          
                        x++;
                        y--;
                        if(gameboard[x][y] == 'x') {
                            amountOfX++;
                        } else {
                            stillGoing = false;
                        }
                    }
                }
                x =i;
                y =j;
                stillGoing = true;
                if(gameboard[i][j] == '0') {
                    amountOf0++;
                    while(stillGoing == true && x>=0 && x<getHeigth()-1 && y>0) { 
                        x++;
                        y--;
                        if(gameboard[x][y] == '0') {
                            amountOf0++;
                        } else {
                            stillGoing = false;
                        }
                    }
                }
                if(amountOfX == howManytoWin) {
                    winnerIsX = true;
                    return true;
                }
                if(amountOf0 == howManytoWin) {
                    winnerIs0 = true;
                    return true;
                }
                amountOfX = 0;
                amountOf0 = 0;
            }
        }
        return false;
    }

    /**
    * Checks the vertical rows in case that there is a winner.
    *
    * @param gameboard Array which is the gameboard.
    *
    * @return true or false depending if there is a winner or not.
    */
    public static boolean vertRowCheck(char[][]gameboard) {
        for(int i=0; i<getWidth(); i++) {
            int amountOfX = 0;
            int amountOf0 = 0;
            for(int j=0; j<getHeigth(); j++) {
                if(gameboard[j][i] == 'x') {
                    amountOfX ++;
                    amountOf0 = 0;
                } else if(gameboard[j][i] == '0'){
                    amountOf0 ++;
                    amountOfX = 0;
                } else {
                    amountOfX = 0;
                    amountOf0 = 0;
                }
                if(amountOfX == howManytoWin) {
                    winnerIsX = true;
                    return true;
                } else if (amountOf0 == howManytoWin) {
                    winnerIs0 = true;
                    return true;
                }
            }
        }
        return false;
    }
      
    /**
    * Checks that who is the winner.
    *
    * <p> Checks who is the winner and then 
    * returns the String about it. If there is
    * no winner then returns tie.
    *
    * @return Returns the string about who wins.
    *
    */
    public static String whoWins() {
        String x = "User wins ! Congratulations.";
        String o = "Computer wins. Better luck next time !";
        String tie = "It's a tie, go for a next round !";

        if(winnerIsX == true) {
            return x;
        } else if (winnerIs0 == true) {
            return o;
        } else {
            return tie;
        }
    }
}

// End of file