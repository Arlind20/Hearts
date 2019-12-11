package Hearts;

import java.util.Random;

public class Bot extends Player {
    private Hand hand;
    private Hand playableCards = new Hand();
    private String name;

    Bot(String name) {
        hand = super.getHand();
        this.name = name;
    }

    public String toString() {
        return name;
    }

    private void findPlayableCards(String cardType, boolean start) {
        playableCards.clearHand();
        for (int i = 0; i < hand.length(); i++) {
            if (hand.get(i).get_suit().equals(cardType)) {
                playableCards.addToHand(hand.get(i));
            }
        }
        if (playableCards.length() == 0) {
            for (int i = 0; i < hand.length(); i++) {
                if (start) {
                    if (!hand.get(i).get_suit().equals(("HEARTS"))) {
                        playableCards.addToHand(hand.get(i));
                    }
                } else{
                    playableCards.addToHand(hand.get(i));
                }
            }
        }
    }


    public Card drawCard(String cardType, boolean start) {
        Random rand = new Random();
        Card card;

        findPlayableCards(cardType, start);
        if (playableCards.length() == 1) {
            int cardIndex = rand.nextInt(1);
            card = playableCards.get(cardIndex);
            hand.delete(card);
        } else {
            int cardIndex = rand.nextInt(playableCards.length());
            card = playableCards.get(cardIndex);
            hand.delete(card);
        }

        System.out.println(name + " Threw " + card.toString());
        return card;
    }
}
