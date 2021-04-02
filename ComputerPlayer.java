import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ComputerPlayer extends Player implements IPlayer{

    @Override
    public void setUp(int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons) {
        super.setUp(numPlayers, index, ppl, places, weapons);
    }

    //AI strategy for responding suggestion: randomly show a card if has any matched cards.
    @Override
    public Card canAnswer(Guess g, IPlayer ip) {
        ArrayList<Card> suggestionMatchedCards = new ArrayList<>();
        if(this.getOwnedCards().contains(g.getLocation())){
            suggestionMatchedCards.add(g.getLocation());
        }

        if(this.getOwnedCards().contains(g.getSuspect())){
            suggestionMatchedCards.add(g.getSuspect());
        }

        if(this.getOwnedCards().contains(g.getWeapon())){
            suggestionMatchedCards.add(g.getWeapon());
        }

        Collections.shuffle(suggestionMatchedCards);

        return suggestionMatchedCards.isEmpty() ? null : suggestionMatchedCards.get(0);
    }

    @Override
    public Guess getGuess() {
        Random rand = new Random();
        return new Guess(this.getAllSuspects().get(rand.nextInt(this.getAllSuspects().size())),
        this.getAllWeapons().get(rand.nextInt(this.getAllWeapons().size())),
        this.getAllLocations().get(rand.nextInt(this.getAllLocations().size())),
        false);
    }

}