enum CardType{
    WEAPON,
    SUSPECT,
    LOCATION;

    public String toString(){
        return name();
    }
}

public class Card {
    private CardType type;
    private String value;

    public Card(CardType t, String value){
        this.type = t;
        this.value = value;
    }

    public String toString(){
        return value;
    }

    public CardType getType(){
        return this.type;
    }

    public String getValue(){
        return this.value;
    }
}

