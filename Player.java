package Hearts;

import java.util.*;

public class Player {
    private Hand hand = new Hand();
    private int amtPoints;
    private int amtChips;

    Player() {}

    public Hand getHand() {
        return hand;
    }

    public boolean hasCard(String suit, String value) {
        boolean bool = false;
        for (int i = 0; i < hand.length(); i++) {
            Card card = hand.get(i);
            if (card.get_suit().equals(suit) && card.get_value().equals(value)) {
                bool = true;
            }
        }
        return bool;
    }

    public boolean hasCardOfSuit(String suit) {
        boolean bool = false;
        for (int i = 0; i < hand.length(); i++) {
            Card card = hand.get(i);
            if (card.get_suit().equals(suit)) {
                bool = true;
            }
        }
        return bool;
    }

    public void addPoints(int points) {
        amtPoints += points;
    }

    public int getAmtPoints() {
        return amtPoints;
    }

    public String toString() {
        return "Player";
    }

    public void sortHand() {
        ArrayList<Card> clubs = new ArrayList<Card>();
        ArrayList<Card> diamonds = new ArrayList<Card>();
        ArrayList<Card> spades = new ArrayList<Card>();
        ArrayList<Card> hearts = new ArrayList<Card>();
        for (int i = 0; i < hand.length(); i++) {
            Card card = hand.get(i);
            if (card.get_suit().equals("CLUBS")) {
                clubs.add(card);
            }
            if (card.get_suit().equals("DIAMONDS")) {
                diamonds.add(card);
            }
            if (card.get_suit().equals("SPADES")) {
                spades.add(card);
            }
            if (card.get_suit().equals("HEARTS")) {
                hearts.add(card);
            }
        }
        this.hand.clearHand();
        for (Card club : clubs) {
            this.hand.addToHand(club);
        }
        for (Card diamond : diamonds) {
            this.hand.addToHand(diamond);
        }
        for (Card spade : spades) {
            this.hand.addToHand(spade);
        }
        for (Card heart : hearts) {
            this.hand.addToHand(heart);
        }
    }

    public void addCard(Card card) {
        this.hand.addToHand(card);
    }

    public Card getCardAtIndex(int i) {
        return this.hand.get(i);
    }

    public Card drawCard() {
        Card card;
        int cardIndex;
        Scanner input = new Scanner(System.in);
        System.out.println("Choose Card: ");
        cardIndex = input.nextInt() - 1;
        card = hand.get(cardIndex);
        hand.remove(cardIndex);
        return card;
    }
}
