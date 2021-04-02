import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HumanPlayer extends Player implements IPlayer{
    private Scanner sc;
    
    @Override
    public void setUp(int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons) {
        super.setUp(numPlayers, index, ppl, places, weapons);
        this.sc = new Scanner(System.in);

        //display the information of all cards
        System.out.println("Here are the names of all the suspects: ");
        System.out.println(String.join(", ",this.getAllSuspects().stream().map(card -> card.getValue()).collect(Collectors.toList())));
        System.out.println("Here are all the weapons:");
        System.out.println(String.join(", ",this.getAllWeapons().stream().map(card -> card.getValue()).collect(Collectors.toList())));
        System.out.println("Here are all the locations:");
        System.out.println(String.join(", ",this.getAllLocations().stream().map(card -> card.getValue()).collect(Collectors.toList())));
    }

    @Override
    public Card canAnswer(Guess g, IPlayer ip) {
        ArrayList<Card> suggestionMatchedCards = new ArrayList<>();
        Card showingCard = null;
        if(this.getOwnedCards().contains(g.getLocation())){
            suggestionMatchedCards.add(g.getLocation());
        }

        if(this.getOwnedCards().contains(g.getSuspect())){
            suggestionMatchedCards.add(g.getSuspect());
        }

        if(this.getOwnedCards().contains(g.getWeapon())){
            suggestionMatchedCards.add(g.getWeapon());
        }

        String suggestingPlayer = "Player "+ String.valueOf(ip.getIndex()) + " asked you about " + g.toString();
        //if no card matched
        if(suggestionMatchedCards.size() == 0){
            System.out.println( suggestingPlayer + ", but you couldn't answer.");
        }else if(suggestionMatchedCards.size() == 1){
            System.out.println( suggestingPlayer + ", you only have one card, " + suggestionMatchedCards.get(0).toString() + " , showed it to them.");
            showingCard = suggestionMatchedCards.get(0);
        }else{
            System.out.println( suggestingPlayer + ". Which do you show?");

            for(int i = 0; i < suggestionMatchedCards.size(); i++){
                System.out.println(String.valueOf(i) + " : " + suggestionMatchedCards.get(i).toString());
            }

            int chosenIdx = Integer.parseInt(sc.nextLine());
            showingCard = suggestionMatchedCards.get(chosenIdx);
        }

        return showingCard;
    }

    @Override
    public Guess getGuess() {
        System.out.println("It is your turn.");
        Guess guess = null;
        Card guessedSuspect = null, guessedWeapon = null, guessedLocation = null;
        boolean isAccusation = false;
        int chosenIdx = -1;
        
        System.out.println("Which person do you want to suggest?");
        for(int i = 0; i < this.getAllSuspects().size(); i++){
            System.out.println(String.valueOf(i) + " : " + this.getAllSuspects().get(i).toString());
        }
        chosenIdx = Integer.parseInt(sc.nextLine());
        guessedSuspect = this.getAllSuspects().get(chosenIdx);

        System.out.println("Which location do you want to suggest?");
        for(int i = 0; i < this.getAllLocations().size(); i++){
            System.out.println(String.valueOf(i) + " : " + this.getAllLocations().get(i).toString());
        }
        chosenIdx = Integer.parseInt(sc.nextLine());
        guessedLocation = this.getAllLocations().get(chosenIdx);

        System.out.println("Which weapon do you want to suggest?");
        for(int i = 0; i < this.getAllWeapons().size(); i++){
            System.out.println(String.valueOf(i) + " : " + this.getAllWeapons().get(i).toString());
        }
        chosenIdx = Integer.parseInt(sc.nextLine());
        guessedWeapon = this.getAllWeapons().get(chosenIdx);

        System.out.println("Is this an accusation (Y/N) ?");
        String yOrN = sc.nextLine();
        isAccusation = yOrN.toUpperCase().equals("Y") ? true : false; 

        guess = new Guess(guessedSuspect, guessedWeapon, guessedLocation, isAccusation);

        return guess;
    }

    @Override
    public void setCard(Card c){
        System.out.println("You received the card " + c.toString());
        super.setCard(c);
    }
}
