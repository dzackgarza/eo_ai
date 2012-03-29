import tester.*;

class TestEvenOdd
{
    EvenOdd e = new EvenOdd();
    
    void testSetup(Tester t)
    {
        e.resetGame();
        t.checkExpect(e.pile , 15);
        t.checkExpect(e.p1 , 0);
        t.checkExpect(e.p2 , 0);
    }
    

    void testP1move(Tester t)
    {
        e.resetGame();
        t.checkExpect(e.p1move(3) , true);
        t.checkExpect(e.pile , 12);
        t.checkExpect(e.p1 , 3);
        t.checkExpect(e.p2 , 0);
        t.checkExpect(e.p1move(5) , false);
        t.checkExpect(e.pile , 12);
        t.checkExpect(e.p1 , 3);
        t.checkExpect(e.p2 , 0);
    }
    
    void testP2move(Tester t)
    {
        e.resetGame();
        e.playerMove = 2;
        t.checkExpect(e.p2move(3) , true);
        t.checkExpect(e.pile , 12);
        t.checkExpect(e.p2 , 3);
        t.checkExpect(e.p1 , 0);
        t.checkExpect(e.p2move(5) , false);
        t.checkExpect(e.pile , 12);
        t.checkExpect(e.p2 , 3);
        t.checkExpect(e.p1 , 0);
    }
    
    void testPlayerSwitching(Tester t)
    {
        e.resetGame();
        e.p1move(1);
        t.checkExpect(e.p1move(1) , false);
        t.checkExpect(e.p2move(1) , true);
        
        e.p2move(1);
        t.checkExpect(e.p2move(1) , false);
        t.checkExpect(e.p1move(1) , true);

        
    }
    
    void testGetters(Tester t)
    {
        e.resetGame();
        t.checkExpect(e.getPlayerMove(), 1);
        
        e.p1move(2);
        t.checkExpect(e.getPlayerMove(), 2);
        t.checkExpect(e.getPlayer1(), 2);
        
        e.p2move(3);
        t.checkExpect(e.getPlayerMove(), 1);
        t.checkExpect(e.getPlayer2(), 3);
        
        t.checkExpect(e.getPile(), 10);
    }
}
