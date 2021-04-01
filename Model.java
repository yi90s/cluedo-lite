import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Model{
    private AnswerCards answers = null;
    private ArrayList<IPlayer> players = null;
    private ArrayList<Card> deck = null;

    public Model(ArrayList<IPlayer> players, ArrayList<Card> deck){
        this.players = players;
        this.deck = deck;
        answers = new AnswerCards();
    }


    private void pickAnswers(){ 
        Collections.shuffle(deck);        //shuffle the deck to provide randomness
        Iterator<Card> it = deck.iterator();
        
        while(it.hasNext()){
            Card curCard = it.next();
            if(curCard.getType() == CardType.LOCATION && this.answers.location == null){
                this.answers.location = curCard;
                it.remove();
            }else if(curCard.getType() == CardType.WEAPON && this.answers.weapon == null){
                this.answers.weapon = curCard;
                it.remove();
            }else if(curCard.getType() == CardType.SUSPECT && this.answers.suspect == null){
                this.answers.suspect = curCard;
                it.remove();
            }else{

            }
        }
    }

    private void distributeCards(){
        Collections.shuffle(players);

        int id = 0;
        int numCardsPerPlayer = deck.size() / players.size();
        for(IPlayer player : players){
            player.setUp(players.size(), id, ppl, places, weapons);
        }
    }


    public void start(){
        pickAnswers();
        distributeCards();
    }

}

class AnswerCards{
    public Card suspect = null;
    public Card weapon = null;
    public Card location = null;
}