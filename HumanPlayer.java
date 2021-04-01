import java.util.ArrayList;

public class HumanPlayer implements IPlayer{
    private int playerId;
    private ArrayList<Card> ppl;
    private ArrayList<Card> places;
    private ArrayList<Card> weapons;
    private int numPlayers;
    
    @Override
    public void setUp(int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons) {
        // TODO Auto-generated method stub
        this.numPlayers = numPlayers;
        this.playerId = index;
        this.ppl = ppl;
        this.places = places;
        this.weapons = weapons;
    }

    @Override
    public void setCard(Card c) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getIndex() {
        // TODO Auto-generated method stub
        
        return this.playerId;
    }

    @Override
    public Card canAnswer(Guess g, IPlayer ip) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Guess getGuess() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void receiveInfo(IPlayer ip, Card c) {
        // TODO Auto-generated method stub
        
    }
    

}
