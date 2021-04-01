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
        this.answers.weapon = allWeapons.get(0);
        this.answers.location = allLocations.get(0);

        //distribute the entire deck to players
        Collections.shuffle(deck);
        for(int i = 0; i < deck.size(); i++){
            players.get(i%players.size()).setCard(deck.get(i));
        }

        while(!this.gameOver){
            //ask active player for their guess
            IPlayer curPlayer = getNextActivePlayer();
            System.out.println("Current turn: " + curPlayer.getIndex());
            Guess curGuess = curPlayer.getGuess();
            System.out.println("Player " + curPlayer.getIndex() + ": " + curGuess.toString());

            if(curGuess.isAccusation()){ //if current guess is an accusation
                if(isTrueAccusation(curGuess)){ 
                    this.gameOver = true;
                    System.out.println("Player " + curPlayer.getIndex() + " won the game.");
                }else{
                    eliminatePlayer(curPlayer);
                    System.out.println("Player " + curPlayer.getIndex() + " made a bad accusation and was removed from the game.");
                    if(players.size() - outPlayers.size() == 1){ //game over if only one player remains
                        this.gameOver = true;
                    }
                }
            }else{ //if current guess is a suggestion

                //ask rest of the players for information
                Card respond = null;
                for(int respondPlayerId = (currPlayerId+1 % this.players.size()); 
                    respondPlayerId != currPlayerId && respond == null; 
                    respondPlayerId = (respondPlayerId+1 % this.players.size())){

                        IPlayer respondPlayer = this.players.get(respondPlayerId);
                        respond = respondPlayer.canAnswer(curGuess, curPlayer);
                        System.out.println("Asking player " + respondPlayer.getIndex() + ".");
                        curPlayer.receiveInfo(respond == null ? null : respondPlayer, respond);
                        if(respond != null){
                            System.out.println("Player " + respondPlayer.getIndex() + " answered.");
                        }

                }

                if(respond == null){
                    System.out.println("No one could answer.");
                }
            }
        }

        //TODO: inform all players about the outcome of the game (player and guess if someone guessed correctly, player only if all other players are eliminated)

    }

}

class AnswerCards{
    public Card suspect = null;
    public Card weapon = null;
    public Card location = null;
}