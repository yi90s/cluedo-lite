import java.util.ArrayList;

public class HumanPlayer extends Player implements IPlayer{
    
    @Override
    public void setUp(int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons) {
        super.setUp(numPlayers, index, ppl, places, weapons);
    }

    @Override
    public Card canAnswer(Guess g, IPlayer ip) {
        ArrayList<Card> suggestedCards = new ArrayList<>();
        Card showingCard = null;
        if(this.getOwnedCards().contains(g.getLocation())){
            suggestedCards.add(g.getLocation());
        }

        if(this.getOwnedCards().contains(g.getSuspect())){
            suggestedCards.add(g.getSuspect());
        }

        if(this.getOwnedCards().contains(g.getWeapon())){
            suggestedCards.add(g.getWeapon());
        }

        String suggestingPlayer = "Player "+ String.valueOf(ip.getIndex()) + "asked you about " + g.toString();
        //if no card matched
        if(suggestedCards.size() == 0){
            System.out.println( suggestingPlayer + ", but you couldn't answer.");
        }else if(suggestedCards.size() == 1){
            System.out.println( suggestingPlayer + ", you only have one card, " + suggestedCards.get(0).toString() + " , showed it to them.");
            showingCard = suggestedCards.get(0);
        }else{
            System.out.println( suggestingPlayer + ". Which do you show?");

            //TODO: show the card options and allows user to pick one
        }

        return showingCard;
    }

    @Override
    public Guess getGuess() {
        System.out.println("It is your turn.");
        //TODO: list all suspects, weapons and locations to users and allow them to pick
        //TODO: ask user if this guess is an accusation
        return null;
    }

    @Override
    public void receiveInfo(IPlayer ip, Card c) {
        if(c != null && ip != null){
            System.out.println("Player " + String.valueOf(ip.getIndex()) + " refuted your suggestion by showing you " + c.toString());
        }else{
            System.out.println("No one could refute your suggestion.");
        }
    }
    

}
