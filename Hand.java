package Hearts;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<Card>();

    Hand(){
    }

    public ArrayList<Card> getHand(){
        return this.hand;
    }

    public void addToHand(Card card){
        this.hand.add(card);
    }

    public void clearHand(){
        this.hand.clear();
    }

    public int length(){
        return hand.size();
    }

    public Card get(int i){
        return this.hand.get(i);
    }

    public void remove(int i) {
        this.hand.remove(i);
    }

    public void delete(Card card) {
        hand.remove(card);
    }

    public String toString() {
        StringBuilder hand = new StringBuilder();
        for (Card aHand : this.hand) {
            hand.append(aHand.toString()).append("\n");
        }
        return hand.toString();
    }

}
