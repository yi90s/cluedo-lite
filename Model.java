import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Model{
    private ArrayList<Card> deck = null;
    private AnswerCards answers = null;
    private ArrayList<Card> allSuspects = null;
    private ArrayList<Card> allWeapons = null;
    private ArrayList<Card> allLocations = null;
    private ArrayList<IPlayer> players = null;
    private ArrayList<IPlayer> outPlayers = null;
    private int currPlayerId = -1;
    private boolean gameOver = false;

    public Model(ArrayList<IPlayer> players, ArrayList<Card> deck){
        this.players = players;
        this.deck = deck;
        answers = new AnswerCards();
        outPlayers = new ArrayList<>();
        allSuspects = new ArrayList<Card>(deck.stream().filter(card -> card.getType() == CardType.SUSPECT).collect(Collectors.toList()));
        allWeapons = new ArrayList<Card>(deck.stream().filter(card -> card.getType() == CardType.WEAPON).collect(Collectors.toList()));
        allLocations = new ArrayList<Card>(deck.stream().filter(card -> card.getType() == CardType.LOCATION).collect(Collectors.toList()));
    }

    private void eliminatePlayer(IPlayer p){
        outPlayers.add(p);
    }

    private IPlayer getNextActivePlayer(){
        IPlayer nextActivePlayer = players.get((this.currPlayerId+1)%this.players.size());
        while(outPlayers.contains(nextActivePlayer)){
            nextActivePlayer = players.get((nextActivePlayer.getIndex()+1)%this.players.size());
        }
        this.currPlayerId = nextActivePlayer.getIndex();
        return nextActivePlayer;
    }

    private boolean isTrueAccusation(Guess accusation){
        return accusation.getSuspect().getValue().equals(this.answers.suspect.getValue())
            && accusation.getWeapon().getValue().equals(this.answers.weapon.getValue())
            && accusation.getLocation().getValue().equals(this.answers.location.getValue()) ;
    }

    public void start(){

        //set up each player in this game
        for(int i = 0; i < players.size(); i++){
            players.get(i).setUp(this.players.size(), i, this.allSuspects, this.allLocations, this.allWeapons);
        }

        //randomly pick 3 cards from each type as answers
        Collections.shuffle(allSuspects);
        Collections.shuffle(allWeapons);
        Collections.shuffle(allLocations);
        this.answers.suspect = allSuspects.get(0);
        deck.remove(this.answers.suspect);
        this.answers.weapon = allWeapons.get(0);
        deck.remove(this.answers.weapon);
        this.answers.location = allLocations.get(0);
        deck.remove(this.answers.location);

        //distribute the entire deck to players
        Collections.shuffle(deck);
        for(int i = 0; i < deck.size(); i++){
            players.get(i%players.size()).setCard(deck.get(i));
        }

        while(!this.gameOver){
            //ask active player for their guess
            IPlayer curPlayer = getNextActivePlayer();
            System.out.println("\nCurrent turn: " + curPlayer.getIndex());
            Guess curGuess = curPlayer.getGuess();
            System.out.println("Player " + curPlayer.getIndex() + ": " + curGuess.toString());

            if(curGuess.isAccusation()){ //if current guess is an accusation
                if(isTrueAccusation(curGuess)){ 
                    this.gameOver = true;
                    System.out.println("Player " + curPlayer.getIndex() + " won the game. And the correct guess is " + curGuess.toString());
                }else{
                    eliminatePlayer(curPlayer);
                    System.out.println("Player " + curPlayer.getIndex() + " made a bad accusation and was removed from the game.");
                    if(players.size() - outPlayers.size() == 1){ //game over if only one player remains
                        System.out.println("Player " + curPlayer.getIndex() + " won the game as the last survivor.");
                        this.gameOver = true;
                    }
                }
            }else{ //if current guess is a suggestion

                //ask rest of the players for information
                Card respond = null;
                IPlayer respondPlayer = null;
                for(int askedPlayerId = ((currPlayerId+1) % this.players.size()); 
                    askedPlayerId != currPlayerId && respond == null; 
                    askedPlayerId = ((askedPlayerId+1) % this.players.size())){

                        IPlayer askedPlayer = this.players.get(askedPlayerId);
                        respond = askedPlayer.canAnswer(curGuess, curPlayer);
                        
                        System.out.println("Asking player " + askedPlayer.getIndex() + ".");
                        if(respond != null){
                            respondPlayer = askedPlayer;
                        }
                }

                curPlayer.receiveInfo(respondPlayer, respond);
            }
        }

    }

}

class AnswerCards{
    public Card suspect = null;
    public Card weapon = null;
    public Card location = null;
}