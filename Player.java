import java.util.ArrayList;

public abstract class Player {
    private int playerId;
    private int numPlayers;
    private ArrayList<Card> allSuspects;
    private ArrayList<Card> allLocations;
    private ArrayList<Card> allWeapons;
    private ArrayList<Card> ownedCards;
    
    public void setUp( int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons ){
        this.numPlayers = numPlayers;
        this.playerId = index;
        allSuspects = ppl;
        allLocations = places;
        allWeapons = weapons;
        ownedCards = new ArrayList<Card>();
    }

    public int getPlayerId() {
        return playerId;
    }

    public ArrayList<Card> getAllSuspects() {
        return allSuspects;
    }
    
    public ArrayList<Card> getAllLocations() {
        return allLocations;
    }

    public ArrayList<Card> getAllWeapons() {
        return allWeapons;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getIndex(){
        return this.playerId;
    }

    public ArrayList<Card> getOwnedCards(){
        return this.ownedCards;
    }

    public void setCard(Card c){
        this.ownedCards.add(c);
    }

    public void receiveInfo(IPlayer ip, Card c) {
        if(c != null && ip != null){
            System.out.println("Player " + String.valueOf(ip.getIndex()) + " refuted your suggestion by showing you " + c.toString());
        }else{
            System.out.println("No one could refute your suggestion.");
        }
    }

}
