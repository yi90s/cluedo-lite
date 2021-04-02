import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //initialize model class
        ArrayList<Card> initDeck = genDeck(); 
        ArrayList<IPlayer> players = genPlayers();
        Model game = new Model(players, initDeck);

        game.start();
    }

    private static ArrayList<Card> genDeck(){
        ArrayList<Card> deck = new ArrayList<>();

        //initialize suspect cards
        deck.add(new Card(CardType.SUSPECT, "Miss Scarlett"));
        deck.add(new Card(CardType.SUSPECT, "Rev Green"));
        deck.add(new Card(CardType.SUSPECT, "Colonel Mustard"));
        deck.add(new Card(CardType.SUSPECT, "Professor Plum"));
        deck.add(new Card(CardType.SUSPECT, "Mrs.Peacock"));
        deck.add(new Card(CardType.SUSPECT, "Mrs.White"));

        //initialize weapon cards
        deck.add(new Card(CardType.WEAPON, "Candlestick"));
        deck.add(new Card(CardType.WEAPON, "Dagger"));
        deck.add(new Card(CardType.WEAPON, "Lead Pipe"));
        deck.add(new Card(CardType.WEAPON, "Revolver"));
        deck.add(new Card(CardType.WEAPON, "Rope"));
        deck.add(new Card(CardType.WEAPON, "Wrench"));

        //initialize location cards
        deck.add(new Card(CardType.LOCATION, "Kitchen"));
        deck.add(new Card(CardType.LOCATION, "Ballroom"));
        deck.add(new Card(CardType.LOCATION, "Conservatory"));
        deck.add(new Card(CardType.LOCATION, "Billiard Room"));
        deck.add(new Card(CardType.LOCATION, "Library"));
        deck.add(new Card(CardType.LOCATION, "Study"));

        return deck;
    }    

    private static ArrayList<IPlayer> genPlayers(){
        ArrayList<IPlayer> players = new ArrayList<>();
        players.add(new HumanPlayer());
        System.out.println("Enter the number of computer players:");
        sc = new Scanner(System.in);
        int numComputerPlayers = Integer.parseInt(sc.nextLine());
        for(int i=0;i<numComputerPlayers;i++){
            players.add(new ComputerPlayer());
        }

        return players;
    }
}

