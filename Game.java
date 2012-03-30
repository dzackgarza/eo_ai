import acm.program.*;
import acm.graphics.*;
import acm.gui.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.*;


public class Game extends Program
{
    JButton p1take1, p1take2, p1take3;
    JButton p2take1, p2take2, p2take3;
    JButton player1AI, player2AI, developAI;
    JButton newGame;
    JLabel p1pile, p2pile, pile, p1Wins, p2Wins;
    
    TablePanel gameArea;
    Color goldenrod3;
    VPanel gameBoard;
    GCanvas doodleSpace;
    
    ImageIcon Mog;
    GImage Kefka;
    
    
    EvenOdd eo;
    
    JLabel title;
    
    public Game()
    {
        this.goldenrod3= new Color(205, 155, 29);
        this.eo= new EvenOdd();
        this.start();
    }
    
    public void init()
    {
        this.gameArea= new TablePanel(6, 3);
        this.Mog = new ImageIcon("Mog.png");
        this.Kefka = new GImage("Kefka.png");
        
        this.setTitle("Even and Odd Game");
        
        this.pile= new JLabel("" + eo.getPile(0));
        this.p1pile= new JLabel("" + eo.getPile(1));
        this.p2pile= new JLabel("" + eo.getPile(2));
        
        this.p1Wins= new JLabel("Player 1 Wins: 0");
        this.p2Wins= new JLabel("Player 2 Wins: 0");

        this.p1take1= new JButton("Take 1");
        this.p1take1.setActionCommand("p1t1");
        this.p1take2= new JButton("Take 2");
        this.p1take2.setActionCommand("p1t2");
        this.p1take3= new JButton("Take 3");
        this.p1take3.setActionCommand("p1t3");
        
        this.p2take1= new JButton(this.Mog);
        this.p2take1.setActionCommand("p2t1");
        this.p2take1.setEnabled(false);
        this.p2take2= new JButton("Take 2");
        this.p2take2.setActionCommand("p2t2");
        this.p2take2.setEnabled(false);
        this.p2take3= new JButton("Take 3");
        this.p2take3.setActionCommand("p2t3");
        this.p2take3.setEnabled(false);
        
        this.player1AI= new JButton("P1 AI");
        this.player1AI.setActionCommand("p1ai");
        this.player2AI= new JButton("P2 AI");
        this.player2AI.setActionCommand("p2ai");
        this.developAI= new JButton("AI Dev");
        this.developAI.setActionCommand("devai");
        
        this.newGame= new JButton("New Game");
        this.newGame.setActionCommand("ng");
        
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
        gameArea.add(player1AI);
        gameArea.add(developAI);
        gameArea.add(player2AI);
        
        pile.setFont(new Font("Old English Text MT", Font.BOLD, 22));
        p1pile.setFont(new Font("Old English Text MT", Font.BOLD, 22));
        p2pile.setFont(new Font("Old English Text MT", Font.BOLD, 22));
        
        pile.setHorizontalAlignment(SwingConstants.CENTER);
        p1pile.setHorizontalAlignment(SwingConstants.CENTER);
        p2pile.setHorizontalAlignment(SwingConstants.CENTER);
        
        pile.setForeground(this.goldenrod3);
        p1pile.setForeground(Color.blue);
        p2pile.setForeground(Color.green);
        
        doodleSpace= new GCanvas();
        gameBoard= new VPanel();
        gameBoard.add(doodleSpace);
        gameBoard.add(gameArea);
        
        this.add(gameBoard);
        
        this.addActionListeners();
        
        doodleSpace.setBackground(Color.white);
        
        this.title= new JLabel("Even and Odd!");


        title.setFont(new Font("Stencil", Font.BOLD, 16));
        doodleSpace.add(title);
        doodleSpace.add(Kefka, 200, 10);
        
    }
    
    public void gameUpdate()
    {
        this.p1pile.setText("" + eo.getPlayer1());
        this.pile.setText("" + eo.getPile());
        this.p2pile.setText("" + eo.getPlayer2());
        this.title.setText("Player " + eo.playerMove + "'s Move");
        this.eo.isGameOver();
        if (this.eo.isGameOver == true)
        {
            title.setText(this.eo.whoWon() + " wins!");
        }
        this.p1Wins.setText("P1 Wins: " + eo.trackPlayer1Wins);
        this.p2Wins.setText("P2 Wins: " + eo.trackPlayer2Wins);
        buttonUpdate();
    }
    
    public void buttonUpdate()
    {
        
        if (this.eo.playerMove == 1)
        {
            this.p2take1.setEnabled(false);
            this.p2take2.setEnabled(false);
            this.p2take3.setEnabled(false);
            
            if (this.eo.pile>=1){
                this.p1take1.setEnabled(true);}
            if (this.eo.pile>=2){
                this.p1take2.setEnabled(true);}
            if (this.eo.pile>=3){
                this.p1take3.setEnabled(true);}
        }
        
        else if (this.eo.playerMove == 2)
        {
            this.p1take1.setEnabled(false);
            this.p1take2.setEnabled(false);
            this.p1take3.setEnabled(false);
            
            if (this.eo.pile>=1){
                this.p2take1.setEnabled(true);}
            if (this.eo.pile>=2){
                this.p2take2.setEnabled(true);}
            if (this.eo.pile>=3){
                this.p2take3.setEnabled(true);}
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
        
    }

    
    public void actionPerformed(ActionEvent event)
    {
        String cmd= event.getActionCommand();
        
        if (cmd.equals("p1t1"))
        {
            this.eo.p1move(1);
        } 
        else if (cmd.equals("p1t2"))
        {
            this.eo.p1move(2);
        }
        else if (cmd.equals("p1t3"))
        {
            this.eo.p1move(3);
        }
        else if (cmd.equals("p2t1"))
        {
            this.eo.p2move(1);
        }
        else if(cmd.equals("p2t2"))
        {
            this.eo.p2move(2);
        }
        else if (cmd.equals("p2t3"))
        {
            this.eo.p2move(3);
        }
        else if (cmd.equals("ng"))
        {
            this.resetGame();
        }
        else if (cmd.equals("p1ai"))
        {
            this.eo.computerPlayer1 = true;
        }
        else if (cmd.equals("p2ai"))
        {
            this.eo.computerPlayer2 = true;
        }
        else if (cmd.equals("devai"))
        {
            developAI();
        }
        else
        {
            // This should never happen!
        }
        
        this.gameUpdate();
    }
    
    /**
     * Reset all of the variables for testing purposes or a new game.
     * Player turn is not reset in order to prevent winning streaks
     */
    void resetGame ()
    {
        this.eo.isGameOver = false;
        this.eo.pile= 15;
        this.eo.p1= 0;
        this.eo.p2= 0;
        
        // Losing player always gets first move
        if (this.eo.winningPlayer==1){
            this.eo.playerMove = 2;}
        else if (this.eo.winningPlayer==2){
            this.eo.playerMove = 1;}
            
        //Computer moves automatically if they lost the last game
        buttonUpdate();
    }
    
    public void developAI()
    {
        if (this.eo.isGameOver==true);
            this.resetGame();
        // Holds all possible amounts left in piles
        int [] holdMoves = new int[15];
        for (int i=0; i<15; i++){
            holdMoves [i] = i+1;
        }
        
        while (this.eo.pile>0){
            this.eo.p1move(this.eo.AIMove());
            this.eo.p2move(this.eo.AIMove());
            gameUpdate();
        }
        
    }
    
}