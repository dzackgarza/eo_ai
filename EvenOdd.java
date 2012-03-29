class EvenOdd
{
    int pile;
    int p1;
    int p2;
    int playerMove;
    
    /**
     * Getters
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
        if (amount >= 1 && amount <= 3 && amount <= this.pile && this.playerMove == 1)
        {
            this.pile= this.pile - amount;
            this.p1= this.p1 + amount;
            switchPlayerMove();
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
        if (amount >= 1 && amount <= 3 && amount <= this.pile && this.playerMove == 2)
        {
            this.pile= this.pile - amount;
            this.p2= this.p2 + amount;
            switchPlayerMove();
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
    boolean isGameOver()
    {
        if (this.pile == 0)
            return true;
        else
            return false;
    }
    
    /**
     * Returns pile values
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
     * whoWon : none -> int
     * Return 1 if player 1 won, 2 if player 2 won,
     * or 0 if no one has won yet.
     */
    String whoWon()
    {
        if (this.isGameOver() == true)
            if (this.p1>this.p2)
                return "Player 1";
            else
                return "Player 2";
        else
            //Should never happen!
            return "";
    }
    
    
    /**
     * status : none -> String
     * Return a String of the form
     * "pile: 8, p1: 3, p2: 4"
     */
    String status()
    {
        return "Pile: " + this.pile + ", p1: " + this.p1 + ", p2: " + this.p2;
    }
    
    
    /**
    * switchPlayerMove: none-->none
    * Alternates turns between the players, called from within each move function.
    */
    void switchPlayerMove()
    {   
        if (this.playerMove== 1)
            this.playerMove=2;
        else if (this.playerMove == 2)
            this.playerMove=1;
    
    }
 

    
    /**
     * Reset all of the variables for testing purposes or a new game.
     */
    void resetGame ()
    {
        this.pile= 15;
        this.p1= 0;
        this.p2= 0;
        this.playerMove= 1;
    }
}