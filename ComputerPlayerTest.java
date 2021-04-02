import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;


class ComputerPlayerTest{
    private Guess guess = null;
    private ArrayList<Card> allSuspects = null;
    private ArrayList<Card> allWeapons = null;
    private ArrayList<Card> allLocations = null;


    @BeforeEach
    void setUp(){
        guess = new Guess(new Card(CardType.SUSPECT, "Miss Scarlett"), 
                            new Card(CardType.WEAPON, "Candlestick"), 
                            new Card(CardType.LOCATION, "Kitchen"), 
                            false);

        allSuspects = new ArrayList<>();
        allWeapons = new ArrayList<>();
        allLocations = new ArrayList<>();

        allSuspects.add(new Card(CardType.SUSPECT, "Miss Scarlett"));
        allSuspects.add(new Card(CardType.SUSPECT, "Rev Green"));
        allSuspects.add(new Card(CardType.SUSPECT, "Colonel Mustard"));
        allSuspects.add(new Card(CardType.SUSPECT, "Professor Plum"));
        allSuspects.add(new Card(CardType.SUSPECT, "Mrs.Peacock"));
        allSuspects.add(new Card(CardType.SUSPECT, "Mrs.White"));

        allWeapons.add(new Card(CardType.WEAPON, "Candlestick"));
        allWeapons.add(new Card(CardType.WEAPON, "Dagger"));
        allWeapons.add(new Card(CardType.WEAPON, "Lead Pipe"));
        allWeapons.add(new Card(CardType.WEAPON, "Revolver"));
        allWeapons.add(new Card(CardType.WEAPON, "Rope"));
        allWeapons.add(new Card(CardType.WEAPON, "Wrench"));

        allLocations.add(new Card(CardType.LOCATION, "Kitchen"));
        allLocations.add(new Card(CardType.LOCATION, "Ballroom"));
        allLocations.add(new Card(CardType.LOCATION, "Conservatory"));
        allLocations.add(new Card(CardType.LOCATION, "Billiard Room"));
        allLocations.add(new Card(CardType.LOCATION, "Library"));
        allLocations.add(new Card(CardType.LOCATION, "Study"));
    }



    @Test
    void test1(){
        ComputerPlayer cp = new ComputerPlayer();
        cp.setUp(6, 0, this.allSuspects, this.allLocations, this.allWeapons);
        
        assertNull(cp.canAnswer(guess, null));
    }

    @Test
    void test2(){
        
    }
}
