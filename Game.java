import acm.program.*;
import acm.graphics.*;
import acm.gui.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.awt.*;


public class Game extends Program
{
    JButton p1take1, p1take2, p1take3;
    JButton p2take1, p2take2, p2take3;
    JButton player1AI, player2AI, suggestedMove;
    JButton newGame, changeDifficulty;
    JLabel p1pile, p2pile, pile, p1Wins, p2Wins;
    JLabel title, difficultyLevel;
    
    TablePanel gameArea;
    VPanel gameBoard;
    GCanvas doodleSpace;
    
    ImageIcon iconMog1;
    ImageIcon iconKefka1;
    ImageIcon iconMog2;
    ImageIcon iconKefka2;
    
    GImage Mog;
    GImage Kefka;
    GImage Background;
    
    EvenOdd eo;
    
    Color custom1;
    Color custom2;
    
    
    public Game()
    {
        this.custom1= new Color(33,33,33);
        this.custom2= new Color(171,171,171);
        this.eo= new EvenOdd();
        this.start();
    }
    
    public void init()
    {
        this.gameArea= new TablePanel(7, 3);
        
        this.iconMog1 = new ImageIcon("Mog1.png");
        this.iconKefka1 = new ImageIcon("Kefka1.png");
        this.iconMog2 = new ImageIcon("Mog2.png");
        this.iconKefka2 = new ImageIcon("Kefka2.png");
        
        this.Mog = new GImage("Mog1.png");
        this.Kefka = new GImage("Kefka1.png");
        
        this.Background = new GImage("Landscape.png");
        
        this.setTitle("Even and Odd Game");
        
        this.title= new JLabel("Even and Odd");
        this.difficultyLevel= new JLabel("Computer Difficulty: Strategic");
        
        this.pile= new JLabel("" + eo.getPile(0));
        this.p1pile= new JLabel("" + eo.getPile(1));
        this.p2pile= new JLabel("" + eo.getPile(2));
        
        this.p1Wins= new JLabel("Player 1 Wins: 0");
        this.p2Wins= new JLabel("Player 2 Wins: 0");
        
        this.p1take1= new JButton("Take 1",this.iconMog1);
        this.p1take1.setActionCommand("p1t1");
        this.p1take1.setRolloverEnabled(true);
        this.p1take1.setRolloverIcon(this.iconMog2);
        
        this.p1take2= new JButton("Take 2",this.iconMog1);
        this.p1take2.setActionCommand("p1t2");
        this.p1take2.setRolloverEnabled(true);
        this.p1take2.setRolloverIcon(this.iconMog2);
        
        this.p1take3= new JButton("Take 3",this.iconMog1);
        this.p1take3.setActionCommand("p1t3");
        this.p1take3.setRolloverEnabled(true);
        this.p1take3.setRolloverIcon(this.iconMog2);
        
        this.p2take1= new JButton("Take 1", this.iconKefka1);
        this.p2take1.setActionCommand("p2t1");
        this.p2take1.setEnabled(false);
        this.p2take1.setRolloverEnabled(true);
        this.p2take1.setRolloverIcon(this.iconKefka2);
        
        this.p2take2= new JButton("Take 2",this.iconKefka1);
        this.p2take2.setActionCommand("p2t2");
        this.p2take2.setEnabled(false);
        this.p2take2.setRolloverEnabled(true);
        this.p2take2.setRolloverIcon(this.iconKefka2);
        
        this.p2take3= new JButton("Take 3",this.iconKefka1);
        this.p2take3.setActionCommand("p2t3");
        this.p2take3.setEnabled(false);
        this.p2take3.setRolloverEnabled(true);
        this.p2take3.setRolloverIcon(this.iconKefka2);
        
        this.player2AI= new JButton("Turn Player 2 AI On");
        this.player2AI.setActionCommand("p2ai");
        this.suggestedMove= new JButton("Get a Hint!");
        this.suggestedMove.setActionCommand("sugmove");
        
        this.newGame= new JButton("New Game");
        this.newGame.setActionCommand("ng");
        this.Kefka.scale(5);
        
        this.changeDifficulty= new JButton("Turn down the difficulty!");
        this.changeDifficulty.setActionCommand("changedif");
        this.changeDifficulty.setEnabled(false);
        
        gameArea.add(p1pile);
        gameArea.add(pile);
        gameArea.add(p2pile);
        gameArea.add(p1take1);
        gameArea.add(newGame);
        gameArea.add(p2take1);
        gameArea.add(p1take2);
        gameArea.add(new JLabel(" "));
        gameArea.add(p2take2);
        gameArea.add(p1take3);
        gameArea.add(new JLabel(" "));
        gameArea.add(p2take3);  
        gameArea.add(p1Wins);
        gameArea.add(new JLabel(""));
        gameArea.add(p2Wins);
        gameArea.add(suggestedMove);
        gameArea.add(changeDifficulty);
        gameArea.add(player2AI);
        gameArea.add(difficultyLevel);
        
        pile.setFont(new Font("Courier New", Font.BOLD, 22));
        p1pile.setFont(new Font("Courier New", Font.BOLD, 22));
        p2pile.setFont(new Font("Courier New", Font.BOLD, 22));

        pile.setHorizontalAlignment(SwingConstants.CENTER);
        p1pile.setHorizontalAlignment(SwingConstants.LEFT);
        p2pile.setHorizontalAlignment(SwingConstants.RIGHT);
        
        pile.setForeground(Color.green);
        p1pile.setForeground(Color.blue);
        p2pile.setForeground(Color.red);
        title.setForeground(this.custom2);
        p1Wins.setForeground(this.custom2);
        p2Wins.setForeground(this.custom2);
        
        doodleSpace= new GCanvas();
        gameBoard= new VPanel(4,2);
        
        doodleSpace.setBackground(this.custom1);
        gameArea.setBackground(this.custom1);
        gameBoard.setBackground(this.custom1);
        
        
        doodleSpace.setPreferredSize(new Dimension(this.getWidth(),(this.getHeight()/2)));
        gameBoard.add(doodleSpace);
        gameBoard.add(gameArea);
        this.add(gameBoard);
        this.addActionListeners();
        doodleSpace.add(this.Background);
        
        this.Background.setSize(doodleSpace.getWidth(),doodleSpace.getHeight()+50);

        this.title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setBounds(0,0,400,500);
        title.setFont(new Font("Courier New", Font.BOLD, 18));
        
        doodleSpace.add(title,gameBoard.getWidth()/2,0);
        doodleSpace.add(Kefka, doodleSpace.getWidth()/4, 10);
        
    }
    
    /**
     * gameUpdate: none-->none
     * 
     * Called between turns, updates all of the labels.
     * Note - calls button update as well.
     */
    public void gameUpdate()
    {
        this.p1pile.setText("" + eo.getPlayer1());
        this.pile.setText("" + eo.getPile());
        this.p2pile.setText("" + eo.getPlayer2());
        this.title.setText("Player " + eo.playerMove + "'s Move");
        if (this.eo.checkGameOver() == true)
        {
            title.setText(this.eo.whoWon() + " wins!");
        }
        this.p1Wins.setText("P1 Wins: " + eo.trackPlayer1Wins);
        this.p2Wins.setText("P2 Wins: " + eo.trackPlayer2Wins);
        buttonUpdate();
    }
    
    /**
     * buttonUpdate: none-->none
     * Called from gameUpdate, enables buttons that are available
     * and disables buttons that are not.
     */
    public void buttonUpdate()
    {
        
        if (this.eo.playerMove == 1)
        {
            this.p2take1.setEnabled(false);
            this.p2take2.setEnabled(false);
            this.p2take3.setEnabled(false);
            
            if (this.eo.pile>=1){
                this.p1take1.setEnabled(true);}
            else{
                this.p1take1.setEnabled(false);}
                
            if (this.eo.pile>=2){
                this.p1take2.setEnabled(true);}
            else{
                this.p1take2.setEnabled(false);}
                
            if (this.eo.pile>=3){
                this.p1take3.setEnabled(true);}
            else{
                this.p1take3.setEnabled(false);}
        }
        
        // The computer needs no buttons!
        else if (this.eo.playerMove == 2 && this.eo.computerPlayer2 == false)
        {
            this.p1take1.setEnabled(false);
            this.p1take2.setEnabled(false);
            this.p1take3.setEnabled(false);
            
            if (this.eo.pile>=1){
                this.p2take1.setEnabled(true);}
            else{
                this.p1take1.setEnabled(false);}
                
            if (this.eo.pile>=2){
                this.p2take2.setEnabled(true);}
            else{
                this.p1take2.setEnabled(false);}
                
            if (this.eo.pile>=3){
                this.p2take3.setEnabled(true);}
            else{
                this.p1take3.setEnabled(false);}
        }   
        
        else if (this.eo.playerMove == 0)
        {
            this.p1take1.setEnabled(false);
            this.p1take2.setEnabled(false);
            this.p1take3.setEnabled(false);
        
            this.p2take1.setEnabled(false);
            this.p2take2.setEnabled(false);
            this.p2take3.setEnabled(false);
        }
        
        // You can't change the difficulty of a human opponent!
        if (this.eo.computerPlayer2 == false)
            this.changeDifficulty.setEnabled(false);
        else if (this.eo.computerPlayer2 == true)
            this.changeDifficulty.setEnabled(true);
        
    }

    /**
     * Handles the events generated from button clicks.
     */
    public void actionPerformed(ActionEvent event)
    {
        String cmd= event.getActionCommand();
        
        if (cmd.equals("p1t1"))
        {
            this.eo.p1move(1);
            checkAIMove();
            gameUpdate();
        } 
        else if (cmd.equals("p1t2"))
        {
            this.eo.p1move(2);
            checkAIMove();
            gameUpdate();
        }
        else if (cmd.equals("p1t3"))
        {
            this.eo.p1move(3);
            checkAIMove();
            gameUpdate();
        }
        else if (cmd.equals("p2t1"))
        {
            this.eo.p2move(1);
            gameUpdate();
        }
        else if(cmd.equals("p2t2"))
        {
            this.eo.p2move(2);
            gameUpdate();
        }
        else if (cmd.equals("p2t3"))
        {
            this.eo.p2move(3);
            gameUpdate();
        }
        else if (cmd.equals("ng"))
        {
            this.resetGame();
            checkAIMove();
            gameUpdate();
        }
        else if (cmd.equals("changedif"))
        {
            if (this.eo.computerDifficulty==1)
            {
                this.eo.computerDifficulty= 2;
                this.changeDifficulty.setText("Turn down the difficulty!");
                this.difficultyLevel.setText("Computer Difficulty: Strategic");
            }
            else if (this.eo.computerDifficulty==2)
            {
                this.eo.computerDifficulty= 1;
                this.changeDifficulty.setText("Turn up the difficulty!");
                this.difficultyLevel.setText("Computer Difficulty: Random");
            }
            buttonUpdate();
        }
        else if (cmd.equals("p2ai"))
        {
            // Switches the AI on and off
            if (this.eo.computerPlayer2==true)
            {
                this.eo.computerPlayer2 = false;
                this.player2AI.setText("Turn Player 2 AI On");
            }
            else if (this.eo.computerPlayer2==false)
            {
                this.eo.computerPlayer2 = true;
                this.player2AI.setText("Turn Player 2 AI Off");
                checkAIMove();
            }
            gameUpdate();
        }
        else if (cmd.equals("sugmove"))
        {
            getSuggestedMove();
        }
        else
        {
            // This should never happen!
        }
        

        
    }
    /**
     * checkAIMove(): 
     * 
     * Makes the move for the computer.
     * 
     * Should only run after player 1's turn or potentially at the
     * beginning of a new game. 
     */
    public void checkAIMove()
    {

        if ((this.eo.whichPlayerJustWent == 1 || this.eo.pile == 15) && this.eo.computerPlayer2==true)
        {
            this.eo.p2move(this.eo.AIMove());
        }
    }
    
    /**
     * Reset all of the variables for a new game.
     * Player's turns are switched in order to prevent winning streaks.
     */
    void resetGame ()
   {    
       // Losing player always gets first move
       if (this.eo.whoWon()=="Player 1"){
            this.eo.playerMove = 2;
        }
        else if (this.eo.whoWon()=="Player 2"){
            this.eo.playerMove = 1;}
            
        
        this.eo.pile= 15;
        this.eo.p1= 0;
        this.eo.p2= 0;
        this.eo.winningPlayer= 0;
        this.eo.tallyUpdatedThisGame = false;
        gameUpdate();    
        checkAIMove();


    }
    
    /**
     * getSuggestedMove:
     * 
     * Fills a label with a move the computer would make!
     */
    public void getSuggestedMove()
    {
        this.title.setText("Suggested Move: " + this.eo.AIMove());
        
    }
    
}