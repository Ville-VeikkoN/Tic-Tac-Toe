/**
* Player sets the 'x' to gameboard.
*
* <p>Checks that the input is valid and if it is, 
* then sets it to gameboard.
*
* @author      Ville-Veikko Nieminen
* @version     2017.1209
* @since       1.6     
*/
class Player {
    /** 
    * Vertical coordinate for the 'x' or '0'.
    */
    protected static int placeX;
    /** 
    * Horizontal coordinate for the 'x' or '0'
    */
    protected static int placeY;
    /**
    * Checks if the user input for the 'x' is valid.
    *
    * <p> Checks that there is only integers in the input
    * and that the integers is separated with ','. Then
    * checks that coordinates can be found in the gameboard.
    * Returns true or false if the input is valid or not.
    *
    * @param a User input for the 'x' in String.
    * @param gameboard Char array which is the gameboard.
    *
    * @return true or false depending if the input is valid or not.
    */
    public boolean isUserInputValid(String a, char [][] gameboard) {
                
        a = a+",";
        boolean firstCoord = true;
        int indexNum = 0;
        placeX = -1;
        placeY = -1;

        while(indexNum < a.length()) {
            String s = "";
            char c = a.charAt(indexNum);
            while(c != ',') {
                s += c;
                indexNum++;
                c = a.charAt(indexNum);
            }
            try {
                if(firstCoord == true) {
                    placeX = Integer.parseInt(s);
                    firstCoord = false;
                } else {
                    placeY = Integer.parseInt(s);
                }
                indexNum++;
                
                if(placeX>=0 && placeX<TicTacToe.getHeigth() && placeY>=0 && placeY<TicTacToe.getWidth() && gameboard[placeX][placeY] == ' ') {
                    return true;
                } 
            }catch (NumberFormatException ex) {
                System.out.println("Not a number or you did not use ',' to separate coordinates.");
                return false;
            }
        }
        return false;
    }

    /** 
    * Set the 'x' to the gameboard.
    *
    * @param gameboard Char array which is the gameboard.
    */
    public void setX(char [][] gameboard) {

        gameboard[placeX][placeY] = 'x';
    }
}

/**
* AI sets the '0' to the gameboard.
*
* <p> AI extends the Player class to get the latest 'x' coordinates.
*
* @author      Ville-Veikko Nieminen
* @version     2017.1209
* @since       1.6   
*/
class AI extends Player {
    /**
    * Tells if the '0' is set or not.
    */
    private static boolean is0Set;

    /** 
    * Chooses the place for computer ('0').
    *
    * @param gameboard Char array which is the gameboard.
    */
    public void set0(char [][] gameboard) {
        int aiSetsIn;
        int h = TicTacToe.getHeigth();
        int w = TicTacToe.getWidth();
        int howManytoWin = TicTacToe.getHowManytoWin();
        is0Set = false;
        if(howManytoWin >= 4) {
            aiSetsIn = 3;
        } else {
            aiSetsIn = 2;                
        }

        aiDiagonal(gameboard,h,w,howManytoWin,aiSetsIn);
        aiNegDiagonal(gameboard,h,w,howManytoWin,aiSetsIn);
        aiRow(gameboard,h,w,howManytoWin,aiSetsIn);
        aiVertRow(gameboard,h,w,howManytoWin,aiSetsIn);
        aiRandom(gameboard,h,w);
    }                  
    /** 
    * Checks if AI is needed.
    * 
    * <p>If there is right amount on x's in diagonal rows, then
    * try to block the user from winning.
    *
    * @param gameboard Char array which is the gameboard.
    * @param h  Heigth of the gameboard.
    * @param w  Width of the gameboard.
    * @param howManytoWin   Amount of chars in line order to win.
    * @param aiSetsIn   Tells when AI sets in.
    */
    public void aiDiagonal(char [][] gameboard, int h, int w, int howManytoWin, int aiSetsIn) {
        int amountOfXs = 0;
        int x = 1;
        while(placeX-x >= 0 && placeY-x >= 0 && gameboard[placeX-x][placeY-x] == 'x' && is0Set == false) {
            amountOfXs++;
            if(placeX+1 < h && placeY+1 < w && gameboard[placeX+1][placeY+1] == ' ' && amountOfXs >= howManytoWin-aiSetsIn) {                 
                gameboard[placeX+1][placeY+1] = '0';
                is0Set = true;
            } else if (placeX-(x+1) >=0 && placeY-(x+1) >=0 && gameboard[placeX-(x+1)][placeY-(x+1)] == ' ' && amountOfXs >= howManytoWin-(aiSetsIn)) {
                gameboard[placeX-(x+1)][placeY-(x+1)] = '0';
                is0Set = true;
            }
            x++;
        }
        x=1;
        amountOfXs=0;

        while(placeX+x < h && placeY+x < w && gameboard[placeX+x][placeY+x] == 'x' && is0Set == false) {
            amountOfXs++;
            if(placeX-1 >= 0 && placeY-1 >= 0 && gameboard[placeX-1][placeY-1] == ' ' && amountOfXs >= howManytoWin-aiSetsIn) {
                gameboard[placeX-1][placeY-1] = '0';
                is0Set = true;
            } else if (placeX+(x+1) < h && placeY+(x+1) <w && gameboard[placeX+(x+1)][placeY+(x+1)] == ' ' && amountOfXs >= howManytoWin-(aiSetsIn)) {
                gameboard[placeX+(x+1)][placeY+(x+1)] = '0';
                is0Set = true;
            }
            x++;
        }
    }
    /** 
    * Checks if AI is needed.
    *
    * <p>If there is right amount on x's in negative diagonal rows, then
    * try to block the user from winning.
    *
    * @param gameboard Char array which is the gameboard.
    * @param h  Heigth of the gameboard.
    * @param w  Width of the gameboard.
    * @param howManytoWin   Amount of chars in line order to win.
    * @param aiSetsIn   Tells when AI sets in.
    */
    public void aiNegDiagonal(char [][] gameboard, int h, int w, int howManytoWin, int aiSetsIn) {
        int x = 1;
        int amountOfXs = 0;
        while(placeX+x < h && placeY-x >=0 && gameboard[placeX+x][placeY-x] == 'x' && is0Set == false) {
            amountOfXs++;
            if(placeX-1 >= 0 && placeY+1 < w && gameboard[placeX-1][placeY+1] == ' ' && amountOfXs >= howManytoWin-aiSetsIn) {
                gameboard[placeX-1][placeY+1] = '0';
                is0Set = true;
            }else if(placeX+(x+1) <h && placeY-(x+1) >=0 && gameboard[placeX+(x+1)][placeY-(x+1)] == ' ' && amountOfXs >= howManytoWin-(aiSetsIn)) {
                gameboard[placeX+(x+1)][placeY-(x+1)] = '0';
                is0Set = true;
            }
            x++;
        }
        x=1;
        amountOfXs=0;

        while(placeX-x >= 0 && placeY+x < w && gameboard[placeX-x][placeY+x] == 'x' && is0Set == false) {
            amountOfXs++;
            if(placeX+1 < h && placeY-1 >= 0 && gameboard[placeX+1][placeY-1] == ' ' && amountOfXs >= howManytoWin-aiSetsIn) {
                gameboard[placeX+1][placeY-1] = '0';
                is0Set = true;
            } else if (placeX-(x+1) >=0 && placeY+(x+1) <w && gameboard[placeX-(x+1)][placeY+(x+1)] == ' ' && amountOfXs >= howManytoWin-(aiSetsIn)) {
                gameboard[placeX-(x+1)][placeY+(x+1)] = '0';
                is0Set = true;
            }
            x++;
        }
    }
    /** 
    * Checks if AI is needed.
    *
    * <p>If there is right amount on x's in vertical rows, then
    * try to block the user from winning.
    *
    * @param gameboard Char array which is the gameboard.
    * @param h  Heigth of the gameboard.
    * @param w  Width of the gameboard.
    * @param howManytoWin   Amount of chars in line order to win.
    * @param aiSetsIn   Tells when AI sets in.
    */
    public void aiVertRow(char [][] gameboard, int h, int w, int howManytoWin, int aiSetsIn) {
        int x = 1;
        int amountOfXs = 0;
        while(placeX >= 0 && placeY-x >=0 && gameboard[placeX][placeY-x] == 'x' && is0Set == false) {
            amountOfXs++;
            if(placeX < h && placeY+1 < w && gameboard[placeX][placeY+1] == ' ' && amountOfXs >= howManytoWin-aiSetsIn) {
                gameboard[placeX][placeY+1] = '0';
                is0Set = true;
            } else if (placeX >=0 && placeY-(x+1) >=0 && gameboard[placeX][placeY-(x+1)] == ' ' && amountOfXs >= howManytoWin-(aiSetsIn)) {
                gameboard[placeX][placeY-(x+1)] = '0';
                is0Set = true;
            }
            x++;
        }
         x=1;
        amountOfXs = 0; 

        while(placeX >= 0 && placeY+x < TicTacToe.getWidth() && gameboard[placeX][placeY+x] == 'x' && is0Set == false) {
            amountOfXs++;
            if(placeX < h && placeY-1 >= 0 && gameboard[placeX][placeY-1] == ' ' && amountOfXs >= howManytoWin-aiSetsIn) { 
                gameboard[placeX][placeY-1] = '0';
                is0Set = true;
            } else if (placeX >=0 && placeY+(x+1) < w && gameboard[placeX][placeY+(x+1)] == ' ' && amountOfXs >= howManytoWin-(aiSetsIn)) {
                gameboard[placeX][placeY+(x+1)] = '0';
                is0Set = true;
            }
            x++;
        }
    }
    /** 
    * Checks if AI is needed.
    *
    * <p>If there is right amount on x's in horizontal rows, then
    * try to block the user from winning.
    *
    * @param gameboard Char array which is the gameboard.
    * @param h  Heigth of the gameboard.
    * @param w  Width of the gameboard.
    * @param howManytoWin   Amount of chars in line order to win.
    * @param aiSetsIn   Tells when AI sets in.
    */
    public void aiRow(char [][] gameboard, int h, int w, int howManytoWin, int aiSetsIn) {
        int amountOfXs = 0;
        int x = 1;
        while(placeX-x >= 0 && placeY >=0 && gameboard[placeX-x][placeY] == 'x' && is0Set == false) {
            amountOfXs++;
            if(placeX+1 < h && placeY < w && gameboard[placeX+1][placeY] == ' ' && amountOfXs >= howManytoWin-aiSetsIn) {
                gameboard[placeX+1][placeY] = '0';
                is0Set = true;
            } else if (placeX-(x+1) >=0 && placeY >=0 && gameboard[placeX-(x+1)][placeY] == ' ' && amountOfXs >= howManytoWin-(aiSetsIn)) {
                gameboard[placeX-(x+1)][placeY] = '0';
                is0Set = true;
            } 
            x++;   
        }
        x=1;
        amountOfXs = 0;

        while(placeX+x < h && placeY >=0 && gameboard[placeX+x][placeY] == 'x' && is0Set == false) {
            amountOfXs++;
            if(placeX-1 >=0 && placeY < w && gameboard[placeX-1][placeY] == ' ' && amountOfXs >= howManytoWin-aiSetsIn) {
                gameboard[placeX-1][placeY] = '0';
                is0Set = true;
            } else if (placeX+(x+1) <h && placeY >=0 && gameboard[placeX+(x+1)][placeY] == ' ' && amountOfXs >= howManytoWin-(aiSetsIn)) {
                gameboard[placeX+(x+1)][placeY] = '0';
                is0Set = true;
            } 
            x++;  

        }
    }
    /** 
    * Puts '0' into the gameboard by random if needed.
    *
    * <p> If AI didn't choose the place for '0', then 
    * aiRandom() chooses it randomly.
    *
    * @param gameboard Char array which is the gameboard.
    * @param h  Heigth of the gameboard.
    * @param w  Width of the gameboard.
    */
    public void aiRandom(char [][] gameboard, int h, int w) {
        while(is0Set == false) {
            placeX = (int)(Math.random()*h);
            placeY = (int)(Math.random()*w);
            if(gameboard[placeX][placeY] == ' ') {
                is0Set = true;
                gameboard[placeX][placeY] = '0';
            }
        }
    }
}
// End of file