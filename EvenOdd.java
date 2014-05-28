class EvenOdd
 // TODO: Determine a formula for AIMove
 //       Clean up directory structure
 //       Expand to infinitely many players
 //       Develop AI that runs AIMove randomly that gathers and stores statistics
{
    int pile;
    int p1;
    int p2;
    int playerMove; //Turns - 1: Player 1 || 2: Player 2
    int winningPlayer; // Used for switching players for new game 
    boolean computerPlayer1; // True: AI || False: Human Control
    boolean computerPlayer2; // True: AI || False: Human Control
    int computerDifficulty; // 1: Random || 2: AI
    int trackPlayer1Wins;
    int trackPlayer2Wins;
    boolean tallyUpdatedThisGame;
    int whichPlayerJustWent;
    boolean p1IsOdd;
    boolean p2IsOdd;
    
    
    /**
     * Set up the game!
     * Set the initial pile to 15 and the players' piles to
     * zero.
     */  
    EvenOdd()
    {
        this.pile= 15;
        this.p1= 0;
        this.p2= 0;
        this.playerMove= 1;
        this.computerPlayer1= false; 
        this.computerPlayer2= false; 
        this.winningPlayer = 0;
        this.computerDifficulty = 2;
        this.trackPlayer1Wins = 0;
        this.trackPlayer2Wins = 0;
        p1IsOdd = false;
        p2IsOdd = false;
    }
    
   /**
    * switchPlayerMove: none-->none
    * Alternates turns between the players, called from within each move function.
    */
    void switchPlayerMove()
    {   
        if (this.playerMove==1)
            this.playerMove=2;
        else
            this.playerMove=1;
    
    }
    
    /**
     * p1move : amount (int) -> boolean
     * 
     * Given the amount to move, return whether
     * it is a valid move and adjust the main pile and p1's pile
     * accordingly.
     */
    boolean p1move(int amount)
    {
        if (amount >= 1 && amount <= 3 && amount <= this.pile && this.playerMove == 1
        && this.checkGameOver() ==false)
        {
            this.pile= this.pile - amount;
            this.p1= this.p1 + amount;
            switchPlayerMove();
            
            // Keeps track of whether a player has an even or odd amount at 
            // any given time.
             if (this.p1%2==1)
                this.p1IsOdd = true;
             else if (this.p1%2==0)
                this.p1IsOdd = false;
            whichPlayerJustWent = 1;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * p2move : amount (int) -> boolean
     * 
     * Given the amount to move, return whether
     * it is a valid move and adjust the pile and p2
     * accordingly.
     */
    boolean p2move(int amount)
    {
        if (amount >= 1 && amount <= 3 && amount <= this.pile && this.playerMove == 2
            && this.checkGameOver()==false)
        {
            this.pile= this.pile - amount;
            this.p2= this.p2 + amount;
            switchPlayerMove();
            
            // Keeps track of whether a player has an even or odd amount at 
            // any given time.
             if (this.p2%2==1)
                this.p2IsOdd = true;
             else if (this.p2%2==0)
                this.p2IsOdd = false;
            whichPlayerJustWent = 2;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * checkGameOver : none -> boolean
     * 
     * Return whether or not the game is over.
     */
    boolean checkGameOver()
    {
        if (this.pile == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * whoWon : none -> String
     * 
     * Return which player won and increment the tally.
     */
    String whoWon()
    {
        if (this.checkGameOver() == true)
        {
            if (this.p1%2==1)
            {
                updateTally(1);
                return "Player 1";
            }
            else
            {
                updateTally(2);
                return "Player 2";
            }
        }  
        else
        {
            //Should never happen!
            return "";
        }
    }
    
    /**
     * updateTally: winner (int) --> none
     * 
     * Given the player who won, update their tally by 
     * one if it has not already been incremented this game.
     */
    public void updateTally(int winner)
    {
        if (tallyUpdatedThisGame==false)
        {
            if (winner==1)
            {
                trackPlayer1Wins = trackPlayer1Wins + 1;
            }
            else if (winner==2)
            {
                trackPlayer2Wins = trackPlayer2Wins + 1;
            }
            playerMove=0;
            tallyUpdatedThisGame = true;
        }
        else
        {
            // Do nothing
        }
    }
    
    /**
     * AIMove : none --> int
     * 
     * Return an integer indicating how many the computer takes from the pile.
     * 
     * Computer Difficulty 1: Random pick
     *      Picks a random number between 1 and the amount in the pile.
     *      
     * Computer Difficulty 2: Strategic pick
     *      Analyzes the state the player is in by examining how many
     *      are left in the pile and whether the amount currently held by
     *      the player is even or odd. By doing so, several "no-win" scenarios
     *      are revealed. Were both players to play perfect games, these conditions
     *      would always lead a player to a loss. The AI is then based around the 
     *      idea of avoiding those specific conditions with each move. 
     *      
     *      The winning condition is essentially decided based upon the state of the
     *      player (whether an even or odd amount is held) when there are 4 or 5 left in
     *      the pile - if the player has an even amount when it is their turn to pull from the pile and there are 
     *      5 remaining, there is no possible way to win. A similar condition exists if the player
     *      has an odd amount when 4 are remaining in the pile. The rest of the suggested moves are based around
     *      avoiding landing in this condition as well as forcing the opponent into this position.
     *      
     *      If both players play perfect games, the player that goes first will always win.
     *     
     *      The no-win conditions are marked.
     *      
     *      
     *      
     */
    public int AIMove()
    {
        int computerMove = 0;
        if (this.computerDifficulty == 1)
        {
            
            /* Pick a random number within the constraints of how much is
             * left in the pile
             */
            if (this.pile>=3)
                computerMove = (int) (Math.random()*2.9999999) +1;
            else if (this.pile>=2)
                computerMove = (int) (Math.random()*1.9999999) +1;
            else if (this.pile==1)
                computerMove = 1;
        }
        
        else if (this.computerDifficulty == 2)
        {
            if (playerMove == 1)
            {
                if (this.p1IsOdd == true)
                {
                    switch (this.pile)
                    {
                        case 1: computerMove = 1; // No-win
                                break;
                        case 2: computerMove = 2;
                                break;
                        case 3: computerMove = 2;
                                break;
                        case 4: computerMove = 1; // No-win
                                break;
                        case 5: computerMove = 1;
                                break;
                        case 6: computerMove = 1;
                                break;
                        case 7: computerMove = 3;
                                break;
                        case 8: computerMove = 3;
                                break;
                        case 9: computerMove = 1; // No-win
                                break;
                        case 10: computerMove = 2;
                                break;
                        case 11: computerMove = 2;
                                break;
                        case 12: computerMove = 1; //
                                break;
                        case 13: computerMove = 1;
                                break;
                        case 14: computerMove = 1;
                                break;
                        case 15: computerMove = 3;
                                break;
                    }
                }
                else if (this.p1IsOdd == false)
                {
                    switch (this.pile)
                    {
                        case 1: computerMove = 1;
                                break;
                        case 2: computerMove = 1;
                                break;
                        case 3: computerMove = 3;
                                break;
                        case 4: computerMove = 3;
                                break;
                        case 5: computerMove = 1; // No-win
                                break;
                        case 6: computerMove = 2;
                                break;
                        case 7: computerMove = 2;
                                break;
                        case 8: computerMove = 1; // No-win
                                break;
                        case 9: computerMove = 1;
                                break;
                        case 10: computerMove = 1;
                                break;
                        case 11: computerMove = 3;
                                break;
                        case 12: computerMove = 3;
                                break;
                        case 13: computerMove = 1; //
                                break;
                        case 14: computerMove = 2;
                                break;
                        case 15: computerMove = 2;
                                break;
                    }
                }
                
                
            }
            else if (playerMove == 2)
            {
                if (this.p2IsOdd == true)
                {
                    switch (this.pile)
                    {
                        case 1: computerMove = 1; // No-win
                                break;
                        case 2: computerMove = 2;
                                break;
                        case 3: computerMove = 2;
                                break;
                        case 4: computerMove = 1; // No-win
                                break;
                        case 5: computerMove = 1;
                                break;
                        case 6: computerMove = 1;
                                break;
                        case 7: computerMove = 3;
                                break;
                        case 8: computerMove = 3;
                                break;
                        case 9: computerMove = 1; // No-win
                                break;
                        case 10: computerMove = 2;
                                break;
                        case 11: computerMove = 2;
                                break;
                        case 12: computerMove = 1; // No-win
                                break;
                        case 13: computerMove = 1;
                                break;
                        case 14: computerMove = 1;
                                break;
                        case 15: computerMove = 3;
                                break;
                    }
                }
                else if (this.p2IsOdd == false)
                {
                    switch (this.pile)
                    {
                        case 1: computerMove = 1;
                                break;
                        case 2: computerMove = 1;
                                break;
                        case 3: computerMove = 3;
                                break;
                        case 4: computerMove = 3;
                                break;
                        case 5: computerMove = 1; // No-win
                                break;
                        case 6: computerMove = 2;
                                break;
                        case 7: computerMove = 2;
                                break;
                        case 8: computerMove = 1; // No-win
                                break;
                        case 9: computerMove = 1;
                                break;
                        case 10: computerMove = 1;
                                break;
                        case 11: computerMove = 3;
                                break;
                        case 12: computerMove = 3;
                                break;
                        case 13: computerMove = 1; // No-win
                                break;
                        case 14: computerMove = 2;
                                break;
                        case 15: computerMove = 2;
                                break;
                    }
                }
                
            }
        }
            
    return computerMove;
    }
    
    
    /////////////////////////////////////////////////////////////////////
    ///////////////          Getters  ///////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /**
     * Getter to return pile values
     * 1 --> Player 1's pile
     * 2 --> Player 2's pile
     * 0 --> Amount left in pile
     */
    public int getPile(int whichPile)
    {
        if (whichPile == 1)
            return this.p1;
        else if (whichPile == 2)
            return this.p2;
        else
            return this.pile;

    }
    
    /**
     * Other getters
     */
    public int getPlayer1 ()
    {
        return this.p1;
    }
    public int getPlayer2 ()
    {
        return this.p2;
    }
    public int getPile ()
    {
        return this.pile;
    }
    public int getPlayerMove ()
    {
        return this.playerMove;
    }
    
}
