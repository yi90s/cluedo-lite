import java.util.ArrayList;

public class ComputerPlayer extends Player implements IPlayer{

    @Override
    public void setUp(int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons) {
        super.setUp(numPlayers, index, ppl, places, weapons);
    }

    @Override
    public Card canAnswer(Guess g, IPlayer ip) {
        return null;
    }

    @Override
    public Guess getGuess() {
        return null;
    }

    @Override
    public void receiveInfo(IPlayer ip, Card c) {
    }

}