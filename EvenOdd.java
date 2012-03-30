class EvenOdd
{
    int pile;
    int p1;
    int p2;
    int playerMove; //Turns - 1: Player 1 || 2: Player 2
    int winningPlayer; // Used for switching players for new game
    boolean computerPlayer1; // True: AI || False: Human Control
    boolean computerPlayer2; // True: AI || False: Human Control
    boolean isGameOver;
    int computerDifficulty; // 1: Random || 2: AI
    int trackPlayer1Wins;
    int trackPlayer2Wins;
    boolean p1IsOdd;
    boolean p2IsOdd;
    
    
    /**
     * Set up the game!
     * Set the initial pile to 15 and the players to
     * zero.
     * 
     * EvenOdd e= new EvenOdd();
     * e.pile -> 15
     * e.p1 -> 0
     * e.p2 -> 0
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
        this.isGameOver = false;
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
     * Given the amount to move, return whether
     * it is a valid move and adjust the pile and p1
     * accordingly.
     * 
     * EvenOdd e = new EvenOdd();
     * e.p1move(3) -> true
     * e.pile -> 12
     * e.p1 -> 3
     * e.p2 -> 0
     * e.p1move(5) -> false
     * e.pile -> 12
     * e.p1 -> 3
     * e.p2 -> 0
     */
    boolean p1move(int amount)
    {
        if (amount >= 1 && amount <= 3 && amount <= this.pile && this.playerMove == 1
        && this.isGameOver ==false)
        {
            this.pile= this.pile - amount;
            this.p1= this.p1 + amount;
            switchPlayerMove();
            //if (computerPlayer2=true)
             //   p2move(this.AIMove());
             checkGameOver();
             if (this.p1%2==1)
                this.p1IsOdd = true;
             else if (this.p1%2==0)
                this.p1IsOdd = false;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * p2move : amount (int) -> boolean
     * Given the amount to move, return whether
     * it is a valid move and adjust the pile and p2
     * accordingly.
     * 
     * EvenOdd e = new EvenOdd();
     * e.p2move(3) -> true
     * e.pile -> 12
     * e.p2 -> 3
     * e.p1 -> 0
     * e.p2move(5) -> false
     * e.pile -> 12
     * e.p2 -> 3
     * e.p1 -> 0
     */
    boolean p2move(int amount)
    {
        if (amount >= 1 && amount <= 3 && amount <= this.pile && this.playerMove == 2
            && this.isGameOver==false)
        {
            this.pile= this.pile - amount;
            this.p2= this.p2 + amount;
            switchPlayerMove();
            checkGameOver();
            //if (computerPlayer1=true)
             //   p1move(this.AIMove());
             if (this.p2%2==1)
                this.p2IsOdd = true;
             else if (this.p2%2==0)
                this.p2IsOdd = false;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * isGameOver : none -> boolean
     * Return whether or not the game is over.
     * 
     */
    boolean checkGameOver()
    {
        if (this.pile == 0)
        {
            this.isGameOver= true;
            this.playerMove = 0;
            return true;
        }
        else
        {
            this.isGameOver= false;
            return false;
        }
    }
    
    
    /**
     * whoWon : none -> String
     * Return which player won.
     */
    String whoWon()
    {
        if (this.isGameOver == true)
        {
            if (this.p1%2==1)
            {
                winningPlayer = 1;
                trackPlayer1Wins = trackPlayer1Wins + 1;
                return "Player 1";
            }
            else
            {
                winningPlayer = 2;
                trackPlayer2Wins = trackPlayer2Wins + 1;
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
     * Return an integer indicating how many the computer takes.
     * Computer Difficulty 1: Random pick
     * Computer Difficulty 2: Strategic pick
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
                        case 1: computerMove = 1; //
                                break;
                        case 2: computerMove = 2;
                                break;
                        case 3: computerMove = 2;
                                break;
                        case 4: computerMove = 1; //
                                break;
                        case 5: computerMove = 1;
                                break;
                        case 6: computerMove = 1;
                                break;
                        case 7: computerMove = 3;
                                break;
                        case 8: computerMove = 3;
                                break;
                        case 9: computerMove = 1; //
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
                        case 5: computerMove = 1; //
                                break;
                        case 6: computerMove = 2;
                                break;
                        case 7: computerMove = 2;
                                break;
                        case 8: computerMove = 1; //
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
                        case 1: computerMove = 1; //
                                break;
                        case 2: computerMove = 2;
                                break;
                        case 3: computerMove = 2;
                                break;
                        case 4: computerMove = 1; //
                                break;
                        case 5: computerMove = 1;
                                break;
                        case 6: computerMove = 1;
                                break;
                        case 7: computerMove = 3;
                                break;
                        case 8: computerMove = 3;
                                break;
                        case 9: computerMove = 1; //
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
                        case 5: computerMove = 1; //
                                break;
                        case 6: computerMove = 2;
                                break;
                        case 7: computerMove = 2;
                                break;
                        case 8: computerMove = 1; //
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