package Hearts;

import java.util.concurrent.TimeUnit;

public class Hearts {
    private static Deck deck = new Deck();
    private static Player player = new Player();
    private static Bot bot1 = new Bot("Jeff");
    private static Bot bot2 = new Bot("Bob");
    private static Bot bot3 = new Bot("Mike");


    public static void main(String[] args) throws InterruptedException {
        playTrick();
    }

    private static void playTrick() throws InterruptedException {
        int startTrick;
        String cardType;
        int pause = 1;

        while (player.getAmtPoints() < 50 && bot1.getAmtPoints() < 50 && bot2.getAmtPoints() < 50 && bot3.getAmtPoints() < 50) {
            deck.shuffleDeck();
            dealCards();

            startTrick = findStart();
            cardType = "CLUBS";

            for (int x = 0; x < 13; x++) {
                Trick trick = new Trick();
                int next = 0;

                TimeUnit.SECONDS.sleep(pause + 1);
                System.out.println("Starting Trick...\n");
                TimeUnit.SECONDS.sleep(pause);

                showPoints();

                switch (startTrick) {
                    case 1:
                        showHand(player, cardType, true);
                        trick.add(player.drawCard(), 1);
                        cardType = trick.getCardType(0);
                        next = 2;
                        break;
                    case 2:
                        trick.add(bot1.drawCard(cardType, true), 2);
                        TimeUnit.SECONDS.sleep(pause);
                        cardType = trick.getCardType(0);
                        next = 3;
                        break;
                    case 3:
                        trick.add(bot2.drawCard(cardType, true), 3);
                        TimeUnit.SECONDS.sleep(pause);
                        cardType = trick.getCardType(0);
                        next = 4;
                        break;
                    case 4:
                        trick.add(bot3.drawCard(cardType, true), 4);
                        TimeUnit.SECONDS.sleep(pause);
                        cardType = trick.getCardType(0);
                        next = 1;
                        break;
                }
                for (int i = 0; i < 3; i++) {
                    switch (next) {
                        case 1:
                            showHand(player, cardType, false);
                            trick.add(player.drawCard(), 1);
                            TimeUnit.SECONDS.sleep(pause);
                            next = 2;
                            break;
                        case 2:
                            trick.add(bot1.drawCard(cardType, false), 2);
                            TimeUnit.SECONDS.sleep(pause);
                            next = 3;
                            break;
                        case 3:
                            trick.add(bot2.drawCard(cardType, false), 3);
                            TimeUnit.SECONDS.sleep(pause);
                            next = 4;
                            break;
                        case 4:
                            trick.add(bot3.drawCard(cardType, false), 4);
                            TimeUnit.SECONDS.sleep(pause);
                            next = 1;
                            break;
                    }
                }
                int winner = trick.findWinner();
                switch (winner) {
                    case 1:
                        System.out.println("\n" + player.toString() + " Won!");
                        player.addPoints(trick.amtPoints());
                        break;
                    case 2:
                        System.out.println("\n" + bot1.toString() + " Won!");
                        bot1.addPoints(trick.amtPoints());
                        break;
                    case 3:
                        System.out.println("\n" + bot2.toString() + " Won!");
                        bot2.addPoints(trick.amtPoints());
                        break;
                    case 4:
                        System.out.println("\n" + bot3.toString() + " Won!");
                        bot3.addPoints(trick.amtPoints());
                        break;
                }
                startTrick = winner;
                cardType = "";
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            }
            showPoints();
        }
        findWinner();
    }

    private static void dealCards() {
        // Deals cards between the 4 players
        for (int i = 0; i < 13; i++) {
            player.addCard(deck.draw_card());
        }
        for (int i = 0; i < 13; i++) {
            bot1.addCard(deck.draw_card());
        }
        for (int i = 0; i < 13; i++) {
            bot2.addCard(deck.draw_card());
        }
        for (int i = 0; i < 13; i++) {
            bot3.addCard(deck.draw_card());
        }
        player.sortHand();
        bot1.sortHand();
        bot2.sortHand();
        bot3.sortHand();
    }

    public static void showPoints() {
        System.out.println(player.toString() + " Amt Points: " + player.getAmtPoints());
        System.out.println(bot1.toString() + " Amt Points: " + bot1.getAmtPoints());
        System.out.println(bot2.toString() + " Amt Points: " + bot2.getAmtPoints());
        System.out.println(bot3.toString() + " Amt Points: " + bot3.getAmtPoints() + "\n");
    }

    public static int findStart() {
        int startTrick = 0;
        if (player.hasCard("CLUBS", "2")) {
            startTrick = 1;
        } else if (bot1.hasCard("CLUBS", "2")) {
            startTrick = 2;
        } else if (bot2.hasCard("CLUBS", "2")) {
            startTrick = 3;
        } else if (bot3.hasCard("CLUBS", "2")) {
            return 4;
        }
        return startTrick;
    }

    private static void showHand(Player p, String cardType, boolean start) {
        System.out.println(p.toString() + "'s Hand:");
        System.out.println("Amt Points: " + p.getAmtPoints());
        for (int i = 0; i < p.getHand().length(); i++) {
            if (p.getCardAtIndex(i).get_suit().equals(cardType)) {
                System.out.println("[" + (i + 1) + "]: " + p.getCardAtIndex(i));
            } else if (!p.hasCardOfSuit(cardType)) {
                if (start) {
                    if (p.getCardAtIndex(i).get_suit().equals("HEARTS")) {
                        System.out.println("--" + p.getCardAtIndex(i) + "--");
                    } else {
                        System.out.println("[" + (i + 1) + "]: " + p.getCardAtIndex(i));
                    }
                } else {
                    System.out.println("[" + (i + 1) + "]: " + p.getCardAtIndex(i));
                }
            } else {
                System.out.println("--" + p.getCardAtIndex(i) + "--");
            }
        }
    }

    private static void findWinner() {
        int playerPoints = player.getAmtPoints();
        int bot1Points = bot1.getAmtPoints();
        int bot2Points = bot2.getAmtPoints();
        int bot3Points = bot3.getAmtPoints();
        if (playerPoints < bot1Points && playerPoints < bot2Points && playerPoints < bot3Points) {
            System.out.println("\n\n\nPlayer Wins!!!!");
        }
        if (bot1Points < playerPoints && bot1Points < bot2Points && bot1Points < bot3Points) {
            System.out.println("\n\n\n" + bot1.toString() + " Wins!!!!");
        }
        if (bot2Points < playerPoints && bot2Points < bot1Points && bot2Points < bot3Points) {
            System.out.println("\n\n\n" + bot2.toString() + " Wins!!!!");
        }
        if (bot3Points < playerPoints && bot3Points < bot1Points && bot3Points < bot2Points) {
            System.out.println("\n\n\n" + bot1.toString() + " Wins!!!!");
        }
    }
}
