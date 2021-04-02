import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;


class ComputerPlayerTest{
    private Guess guess = null;
    private ArrayList<Card> allSuspects = null;
    private ArrayList<Card> allWeapons = null;
    private ArrayList<Card> allLocations = null;
    


    @BeforeEach
    void setUp(){
        allSuspects = new ArrayList<>();
        allWeapons = new ArrayList<>();
        allLocations = new ArrayList<>();

        allSuspects.add(new Card(CardType.SUSPECT, "Miss Scarlett"));
        allSuspects.add(new Card(CardType.SUSPECT, "Rev Green"));
        allSuspects.add(new Card(CardType.SUSPECT, "Colonel Mustard"));

        allWeapons.add(new Card(CardType.WEAPON, "Candlestick"));
        allWeapons.add(new Card(CardType.WEAPON, "Dagger"));
        allWeapons.add(new Card(CardType.WEAPON, "Lead Pipe"));

        allLocations.add(new Card(CardType.LOCATION, "Kitchen"));
        allLocations.add(new Card(CardType.LOCATION, "Ballroom"));
        allLocations.add(new Card(CardType.LOCATION, "Conservatory"));


        guess = new Guess(allSuspects.get(0), allWeapons.get(0), allLocations.get(0), false);
    }



    @Test
    void test1(){
        ComputerPlayer cp = new ComputerPlayer();
        cp.setUp(6, 0, this.allSuspects, this.allLocations, this.allWeapons);
        
        assertNull(cp.canAnswer(guess, null));
    }

    @Test
    void test2(){
        ComputerPlayer cp = new ComputerPlayer();
        cp.setUp(6, 0, this.allSuspects, this.allLocations, this.allWeapons);
        cp.setCard(this.guess.getSuspect());

        assertEquals(this.guess.getSuspect(), cp.canAnswer(guess, null));
    }

    @Test
    void test3(){
        ComputerPlayer cp = new ComputerPlayer();
        cp.setUp(6, 0, this.allSuspects, this.allLocations, this.allWeapons);
        cp.setCard(this.guess.getLocation());
        cp.setCard(this.guess.getSuspect());
        cp.setCard(this.guess.getWeapon());

        assert(cp.canAnswer(guess, null) == this.guess.getLocation() ||
        cp.canAnswer(guess, null) == this.guess.getWeapon() ||
        cp.canAnswer(guess, null) == this.guess.getSuspect() );
    }

    @Test
    void test4(){
        ComputerPlayer cp = new ComputerPlayer();
        cp.setUp(6, 0, this.allSuspects, this.allLocations, this.allWeapons);
        Card sus = this.allSuspects.get(0), loc = this.allLocations.get(0), weapon = this.allWeapons.get(0); 
        cp.setCard(sus);
        cp.setCard(loc);
        cp.setCard(weapon);

        Guess g = cp.getGuess();
        assert(g.getSuspect() != sus && g.getLocation() != loc && g.getWeapon() != weapon);
    }

    @Test
    void test5(){
        ComputerPlayer cp = new ComputerPlayer();
        cp.setUp(6, 0, this.allSuspects, this.allLocations, this.allWeapons);
        cp.setCard(this.allSuspects.get(1));
        cp.setCard(this.allSuspects.get(2));
        cp.setCard(this.allWeapons.get(1));
        cp.setCard(this.allWeapons.get(2));
        cp.setCard(this.allLocations.get(1));
        cp.setCard(this.allLocations.get(2));

        Guess g = cp.getGuess();
        assert(g.isAccusation());
        assert(g.getSuspect() == this.allSuspects.get(0));
        assert(g.getWeapon() == this.allWeapons.get(0));
        assert(g.getLocation() == this.allLocations.get(0));
    }

    @Test
    void test6(){
        ComputerPlayer cp = new ComputerPlayer();
        cp.setUp(6, 0, this.allSuspects, this.allLocations, this.allWeapons);
        cp.setCard(this.allSuspects.get(1));
        cp.setCard(this.allWeapons.get(1));
        cp.setCard(this.allWeapons.get(2));
        cp.setCard(this.allLocations.get(1));
        cp.setCard(this.allLocations.get(2));

        Guess g = cp.getGuess();
        assert(!g.isAccusation());

        cp.receiveInfo(null, this.allSuspects.get(2));
        g = cp.getGuess();
        assert(g.isAccusation());
        assert(g.getSuspect() == this.allSuspects.get(0));
        assert(g.getWeapon() == this.allWeapons.get(0));
        assert(g.getLocation() == this.allLocations.get(0));
    }

    @Test
    void test7(){
        HumanPlayer hp = new HumanPlayer();
        hp.setUp(6, 0, this.allSuspects, this.allLocations, this.allWeapons);
        hp.setCard(this.allSuspects.get(0));
        hp.setCard(this.allWeapons.get(1));
        hp.setCard(this.allLocations.get(2));

        Card respondCard = hp.canAnswer(this.guess, hp);
        assert(respondCard == this.allSuspects.get(0));
    }
}
