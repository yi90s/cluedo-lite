import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        Guess g = null;
        List<Card> unkownSuspects = this.getAllSuspects().stream().filter(sus -> !this.getOwnedCards().contains(sus) && !this.getInformedCards().contains(sus) ).collect(Collectors.toList());
        List<Card> unkownWeapons = this.getAllWeapons().stream().filter(w -> !this.getOwnedCards().contains(w) && !this.getInformedCards().contains(w)).collect(Collectors.toList());
        List<Card> unkownLocations = this.getAllLocations().stream().filter(l -> !this.getOwnedCards().contains(l) && !this.getInformedCards().contains(l) ).collect(Collectors.toList());
        int unkownCardsCount = unkownSuspects.size() + unkownWeapons.size() + unkownLocations.size();

        if(unkownCardsCount == 3){ //player has enough information to make a valid accusation
            g = new Guess(unkownSuspects.get(0), unkownWeapons.get(0), unkownLocations.get(0), true);

        }else {  
            g = new Guess(unkownSuspects.get(0), unkownWeapons.get(0), unkownLocations.get(0), false);
            
        }

        return g;
    }

    @Override
    public void receiveInfo(IPlayer ip, Card c){
        if(c != null){
            this.addInformedCards(c);
        }
    }

}