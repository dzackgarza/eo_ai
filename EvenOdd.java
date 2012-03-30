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
        this.computerPlayer1= true;
        this.computerPlayer2= true;
        this.winningPlayer = 0;
        this.computerDifficulty = 1;
        this.trackPlayer1Wins = 0;
        this.trackPlayer2Wins = 0;
        this.isGameOver = false;
    }
    
     /**
    * switchPlayerMove: none-->none
    * Alternates turns between the players, called from within each move function.
    */
    void switchPlayerMove()
    {   
        if (this.playerMove==1)
        {
            this.playerMove=2;
        }
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
        if (amount >= 1 && amount <= 3 && amount <= this.pile && this.playerMove == 1)
        {
            this.pile= this.pile - amount;
            this.p1= this.p1 + amount;
            switchPlayerMove();
            //if (computerPlayer2=true)
             //   p2move(this.AIMove());
             isGameOver();
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
            isGameOver();
            //if (computerPlayer1=true)
             //   p1move(this.AIMove());
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
    void isGameOver()
    {
        if (this.pile == 0){
            this.isGameOver= true;
            this.playerMove = 0;}
        else
            this.isGameOver= false;
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
        if (this.isGameOver == true)
            if (this.p1%2==1){
                winningPlayer = 1;
                trackPlayer1Wins = trackPlayer1Wins + 1;
                return "Player 1";}
            else{
                winningPlayer = 2;
                trackPlayer2Wins = trackPlayer2Wins + 2;
                return "Player 2";}
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
     * Return an integer indicating how many the computer takes.
     */
    public int AIMove()
    {
        int computerMove = 0;
        if (this.computerDifficulty == 1)
        {
        
        // Random never picked 3...
        computerMove = (int) (Math.random()*2.9999999) +1;
        
        //Addresses a bug in cases where the AI picks nothing
        if (computerMove>pile)
        {
            computerMove = computerMove - 1;
            if (computerMove>pile)
            {
                computerMove = computerMove - 1;
            }
            
        }
    }
    
        else if (this.computerDifficulty == 2){
            computerMove=2;}
    return computerMove;
    }
    
    
}