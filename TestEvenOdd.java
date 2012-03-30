import tester.*;

class TestEvenOdd
{
    Game g = new Game();
    
    void testSetup(Tester t)
    {
        g.resetGame();
        t.checkExpect(g.eo.pile , 15);
        t.checkExpect(g.eo.p1 , 0);
        t.checkExpect(g.eo.p2 , 0);
    }
    

    void testP1move(Tester t)
    {
        g.resetGame();
        t.checkExpect(g.eo.p1move(3) , true);
        t.checkExpect(g.eo.pile , 12);
        t.checkExpect(g.eo.p1 , 3);
        t.checkExpect(g.eo.p2 , 0);
        t.checkExpect(g.eo.p1move(5) , false);
        t.checkExpect(g.eo.pile , 12);
        t.checkExpect(g.eo.p1 , 3);
        t.checkExpect(g.eo.p2 , 0);
    }
    
    void testP2move(Tester t)
    {
        g.resetGame();
        g.eo.playerMove = 2;
        t.checkExpect(g.eo.p2move(3) , true);
        t.checkExpect(g.eo.pile , 12);
        t.checkExpect(g.eo.p2 , 3);
        t.checkExpect(g.eo.p1 , 0);
        t.checkExpect(g.eo.p2move(5) , false);
        t.checkExpect(g.eo.pile , 12);
        t.checkExpect(g.eo.p2 , 3);
        t.checkExpect(g.eo.p1 , 0);
    }
    
    void testPlayerSwitching(Tester t)
    {
        g.resetGame();
        g.eo.p1move(1);
        t.checkExpect(g.eo.p1move(1) , false);
        t.checkExpect(g.eo.p2move(1) , true);
        
        g.eo.p2move(1);
        t.checkExpect(g.eo.p2move(1) , false);
        t.checkExpect(g.eo.p1move(1) , true);

        
    }
    
    void testGetters(Tester t)
    {
        g.resetGame();
        t.checkExpect(g.eo.getPlayerMove(), 1);
        
        g.eo.p1move(2);
        t.checkExpect(g.eo.getPlayerMove(), 2);
        t.checkExpect(g.eo.getPlayer1(), 2);
        
        g.eo.p2move(3);
        t.checkExpect(g.eo.getPlayerMove(), 1);
        t.checkExpect(g.eo.getPlayer2(), 3);
        
        t.checkExpect(g.eo.getPile(), 10);
    }
}
