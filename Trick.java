package Hearts;

import java.util.ArrayList;

public class Trick{
    private ArrayList<Card> trick = new ArrayList<Card>();
    private ArrayList<Integer> player = new ArrayList<>();

    Trick() {
    }

    public void add(Card card, int player) {
        this.trick.add(card);
        this.player.add(player);
    }

    public String getCardType(int i) {
        return trick.get(i).get_suit();
    }

    public int findWinner() {
        String cardType = trick.get(0).get_suit();
        int[] cardValues = new int[4];
        int cardInt = 0;
        int highCard = 0;

        for (int i = 0; i < trick.size(); i++) {
            if (trick.get(i).get_suit().equals(cardType)) {
                cardValues[i] += getValueOf(trick.get(i));
            }
        }

        for (int i = 0; i < trick.size(); i++) {
            if (cardValues[i] > highCard) {
                cardInt = i;
                highCard = cardValues[i];
            }
        }

        return player.get(cardInt);
    }

    public int amtPoints() {
        int amtPoints = 0;
            for (int i = 0; i < trick.size(); i++) {
                if (trick.get(i).get_suit().equals("HEARTS")) {
                    amtPoints += 1;
                }
                if (trick.get(i).get_suit().equals("SPADES") && trick.get(i).get_value().equals("Q")) {
                    amtPoints += 13;
                }
            }
        return amtPoints;
    }

    public int getValueOf(Card card) {
        if (card.get_value().equals("K")) {
            return 13;
        } else if (card.get_value().equals("Q")) {
            return 12;
        } else if (card.get_value().equals("J")) {
            return 11;
        } else if (card.get_value().equals("A")) {
            return 14;
        } else {
            return Integer.parseInt(card.get_value());
        }
    }
}
