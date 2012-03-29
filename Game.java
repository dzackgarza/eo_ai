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
    JButton newGame;
    JLabel p1pile, p2pile, pile;
    
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
        this.gameArea= new TablePanel(4, 3);
        this.Mog = new ImageIcon("Mog.png");
        this.Kefka = new GImage("Kefka.png");
        
        this.setTitle("Even and Odd Game");
        
        this.pile= new JLabel("" + eo.getPile(0));
        this.p1pile= new JLabel("" + eo.getPile(1));
        this.p2pile= new JLabel("" + eo.getPile(2));

        this.p1take1= new JButton("Take 1");
        this.p1take1.setActionCommand("p1t1");
        this.p1take2= new JButton("Take 2");
        this.p1take2.setActionCommand("p1t2");
        this.p1take3= new JButton("Take 3");
        this.p1take3.setActionCommand("p1t3");
        
        this.p2take1= new JButton(this.Mog);
        this.p2take1.setActionCommand("p2t1");
        this.p2take2= new JButton("Take 2");
        this.p2take2.setActionCommand("p2t2");
        this.p2take3= new JButton("Take 3");
        this.p2take3.setActionCommand("p2t3");
        
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
        
        p1pile.setFont(new Font("Old English Text MT", Font.BOLD, 36));
        p2pile.setFont(new Font("Old English Text MT", Font.BOLD, 36));
        pile.setFont(new Font("Old English Text MT", Font.BOLD, 36));
        p1pile.setHorizontalAlignment(SwingConstants.CENTER);
        p2pile.setHorizontalAlignment(SwingConstants.CENTER);
        pile.setHorizontalAlignment(SwingConstants.CENTER);
        p1pile.setForeground(Color.blue);
        p2pile.setForeground(Color.green);
        pile.setForeground(this.goldenrod3);
        
        doodleSpace= new GCanvas();
        gameBoard= new VPanel();
        gameBoard.add(doodleSpace);
        gameBoard.add(gameArea);
        this.add(gameBoard);
        
        this.addActionListeners();
        
        doodleSpace.setBackground(Color.black);
        
        this.title= new JLabel("Even and Odd!");

        title.setFont(new Font("Stencil", Font.BOLD, 22));
        doodleSpace.add(title);
        doodleSpace.add(Kefka, 200, 10);
        
    }
    
    public void actionPerformed(ActionEvent event)
    {
        String cmd= event.getActionCommand();
        
        if (cmd.equals("p1t1"))
        {
            this.eo.p1move(1);
            this.p1pile.setText("" + eo.getPlayer1());
            this.pile.setText("" + eo.getPile());
        } 
        else if (cmd.equals("p1t2"))
        {
            this.eo.p1move(2);
            this.p1pile.setText("" + eo.getPlayer1());
            this.pile.setText("" + eo.getPile());
            
        }
        else if (cmd.equals("p1t3"))
        {
            this.eo.p1move(3);
            this.p1pile.setText("" + eo.getPlayer1());
            this.pile.setText("" + eo.getPile());
        }
        else if (cmd.equals("p2t1"))
        {
            this.eo.p2move(1);
            this.p2pile.setText("" + eo.getPlayer2());
            this.pile.setText("" + eo.getPile());
        }
        else if(cmd.equals("p2t2"))
        {
            this.eo.p2move(2);
            this.p2pile.setText("" + eo.getPlayer2());
            this.pile.setText("" + eo.getPile());
        }
        else if (cmd.equals("p2t3"))
        {
            this.eo.p2move(3);
            this.p2pile.setText("" + eo.getPlayer2());
            this.pile.setText("" + eo.getPile());
        }
        else if (cmd.equals("ng"))
        {
            this.eo= new EvenOdd();
            this.p1pile.setText("" + eo.getPlayer1());
            this.p2pile.setText("" + eo.getPlayer2());
            this.pile.setText("" + eo.getPile());
        }
        else
        {
            // This should never happen!
        }

        this.title.setText("Player " + eo.playerMove + "'s Move");
        if (this.eo.isGameOver() == true)
        {
            title.setText(this.eo.whoWon() + " wins!");
        }
    }
}