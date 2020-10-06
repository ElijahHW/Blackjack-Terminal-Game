//Welcome to BlackJack
//Glossary of blackjack terms used in this application can be found on this link:
//https://en.wikipedia.org/wiki/Glossary_of_blackjack_terms

package com.elijahcode;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Blackjack {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Flavor.BLUE_BOLD +
                "    ____  __    ___   ________ __    _____   ________ __\n" +
                "   / __ )/ /   /   | / ____/ //_/   / /   | / ____/ //_/\n" +
                "  / __  / /   / /| |/ /   / ,< __  / / /| |/ /   / ,<   \n" +
                " / /_/ / /___/ ___ / /___/ /| / /_/ / ___ / /___/ /| |  \n" +
                "/_____/_____/_/  |_\\____/_/ |_\\____/_/  |_\\____/_/ |_|  \n");
        System.out.println("~~Instructions~~");
        System.out.println("~ Dealer will stand on 17 and above,");
        System.out.println("~ hole card = hidden card.");
        System.out.println("----------------------------------------------" + Flavor.RESET);

        //Pre-config for data
        int totalWins = 0;
        int totalLosses = 0;
        int totalPlays;

        //Setting playing deck..
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        //playerCards - will be the cards the player has in their hand
        Deck playerCards = new Deck();

        //balance - holds amount of cash/credits for player
        int balance = (int) 100.00;

        //dealerCards - the cards the dealer has in their hand
        Deck dealerCards = new Deck();

        //checks for user input
        Scanner userInput = new Scanner(System.in);

        //Play the game as long as balance is grater than zero
        //Game loop
        gameLoop:
        while (balance > 0){
            //Let player place bet
            System.out.println("You have" + Flavor.GREEN_BOLD + " $" + balance + Flavor.RESET + ", how much would you like to bet?");
            int playerBet = (int) userInput.nextDouble();
            boolean endRound = false;
            //Exit if player tries to bet more than they have
            if (playerBet > balance){
                System.out.println("You cannot bet more than you have, try again...");
                TimeUnit.SECONDS.sleep(1);
                continue gameLoop;
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.print("Dealing cards.");
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(".");
            //Player gets two cards
            playerCards.draw(playingDeck);
            playerCards.draw(playingDeck);

            //Dealer gets two cards
            dealerCards.draw(playingDeck);
            dealerCards.draw(playingDeck);

            //While loop for drawing new cards
            while(true){
                //Display player cards
                System.out.println("────────────────────");
                System.out.println(Flavor.WHITE_BOLD + "Your Hand:" + Flavor.RESET + playerCards.toString());
                TimeUnit.SECONDS.sleep(1);
                //Display Value
                System.out.println("Your hand is currently valued at: " + playerCards.cardsValue());
                TimeUnit.SECONDS.sleep(2);
                //Display dealer cards
                System.out.println("────────────────────");
                System.out.println("Dealer Hand: " + dealerCards.getCard(0).toString() + " + [hole card]");
                System.out.println("────────────────────");
                TimeUnit.SECONDS.sleep((long) 1.5);
                
                //What do they want to do
                System.out.println("Would you like to (1)Hit, (2)Stand, (3)Surrender");
                //Reads userInput choice, three choices, hit, stand or surrender
                int response = userInput.nextInt();

                //User chooses (1)hit
                if(response == 1){
                    playerCards.draw(playingDeck);
                    System.out.println("You draw a: " + playerCards.getCard(playerCards.deckSize()-1).toString());
                    TimeUnit.SECONDS.sleep(2);
                    //Bust if they go over 21
                    if(playerCards.cardsValue() > 21){
                        System.out.println(Flavor.RED_BOLD + "You Bust. " + Flavor.RESET + "Currently valued at: " + playerCards.cardsValue());
                        TimeUnit.SECONDS.sleep(2);
                        balance -= playerBet;
                        totalLosses++;
                        endRound = true;
                        break;
                    }
                }
                //User chooses (2)Stand
                if(response == 2){
                    break;
                }
                //User chooses (3)Surrender
                if(response == 3) {
                    //applies half of the bet to the current balance
                    playerBet = playerBet / 2;
                    balance -= playerBet;

                    //End of hand - put cards back in deck
                    playerCards.moveAllToDeck(playingDeck);
                    dealerCards.moveAllToDeck(playingDeck);
                    System.out.println(Flavor.WHITE_BOLD + "End of Hand." + Flavor.RESET);
                    TimeUnit.SECONDS.sleep(1);

                    System.out.println("You choose to surrender, you return with half of your bet... your balance is now $" + Flavor.GREEN_BOLD + balance + Flavor.RESET);
                    System.out.println("You can choose to, (1)bet again or (2)call it a day");
                    //Reads userInput choice, (1)bet again or (2)call it a day.
                    int responseTwo = userInput.nextInt();
                    if (responseTwo == 1) {
                        continue gameLoop;
                    }
                    if (responseTwo == 2) {
                        System.out.println("Hope to see you another time!");
                        TimeUnit.SECONDS.sleep(3);
                        endRound = true;
                        System.exit(0);
                    }
                }
            }
            //Reveal Dealer Cards
            System.out.println("────────────────────");
            System.out.println("Dealer Cards:" + dealerCards.toString());
            System.out.println("────────────────────");
            TimeUnit.SECONDS.sleep(1);

            //See if dealer has more points than player
            if((dealerCards.cardsValue() > playerCards.cardsValue()) && !endRound){
                System.out.println("Dealer beats you " + dealerCards.cardsValue() + " to " + playerCards.cardsValue());
            }
            //Dealer hits at 16 stands at 17
            while((dealerCards.cardsValue() < 17) && !endRound){
                dealerCards.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerCards.getCard(dealerCards.deckSize()-1).toString());
                TimeUnit.SECONDS.sleep(1);
            }
            //Display value of dealer
            System.out.println("Dealers hand value: " + dealerCards.cardsValue());
            TimeUnit.SECONDS.sleep(1);

            //Determine if dealer busted
            if((dealerCards.cardsValue()>21)&& !endRound){
                System.out.println("Dealer Busts. " + Flavor.GREEN_BOLD + "You win!" + Flavor.RESET);
                TimeUnit.SECONDS.sleep(1);
                balance += playerBet;
                totalWins++;
                endRound = true;
            }
            //Determine if push
            if((dealerCards.cardsValue() == playerCards.cardsValue()) && !endRound){
                System.out.println("Push.");
                TimeUnit.SECONDS.sleep(1);
                endRound = true;
            }
            //Determine if player wins
            if((playerCards.cardsValue() > dealerCards.cardsValue()) && !endRound){
                System.out.println(Flavor.GREEN_BOLD + "You win the hand." + Flavor.RESET);
                TimeUnit.SECONDS.sleep(1);
                balance += playerBet;
                totalWins++;
            }
            //dealer wins
            else if(!endRound){
                System.out.println("Dealer wins.");
                TimeUnit.SECONDS.sleep(1);
                balance -= playerBet;
                totalLosses++;
            }
            //End of hand - put cards back in deck
            playerCards.moveAllToDeck(playingDeck);
            dealerCards.moveAllToDeck(playingDeck);
            System.out.println(Flavor.WHITE_BOLD + "End of Hand." + Flavor.RESET);
            TimeUnit.SECONDS.sleep(1);
            totalPlays = totalWins + totalLosses;

            //Some stats
            System.out.println(" ");
            System.out.println("────────────────────");
            System.out.println("Here are your stats:");
            System.out.println("You have played " + totalPlays + " time(s),");
            System.out.println("With: " + totalWins + " wins" + " and " + totalLosses + " losses.");
            int winPercentage = 100 * totalWins / totalPlays;
            System.out.println("Your win percentage is: " + winPercentage + "%. ");
        }
        //Game is over
        System.out.println(Flavor.RED_BOLD + "You loose! :(" + Flavor.RESET);
        TimeUnit.SECONDS.sleep(1);
        //Close Scanner
        userInput.close();
    }
}
