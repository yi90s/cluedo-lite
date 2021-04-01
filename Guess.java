public class Guess {
    private Card suspect;
    private Card weapon;
    private Card location;
    private boolean isAccusation;
    
    public Guess(Card suspect, Card weapon, Card location, boolean isAccusation) {
        this.suspect = suspect;
        this.weapon = weapon;
        this.location = location;
        this.isAccusation = isAccusation;
    }

    public Card getSuspect() {
        return suspect;
    }

    public Card getWeapon() {
        return weapon;
    }

    public Card getLocation() {
        return location;
    }
    
    public boolean isAccusation(){
        return this.isAccusation;
    }
}
