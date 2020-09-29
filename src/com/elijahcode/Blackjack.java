package com.elijahcode;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Blackjack {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Welcome to Blackjack!");

        //playingDeck will be the deck the dealer holds
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        //playerCards will be the cards the player has in their hand
        Deck playerCards = new Deck();
        //playerMoney holds players cash
        double playerMoney = 100.0;
        //dealerCards will be the cards the dealer has in their hand
        Deck dealerCards = new Deck();

        //Scanner for user input
        Scanner userInput = new Scanner(System.in);

        //Play the game while the player has money
        //Game loop
        while(playerMoney>0){
            //Take Bet
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            boolean endRound = false;
            if(playerBet > playerMoney){
                //Break if they bet too much
                TimeUnit.SECONDS.sleep(1);
                System.out.println("You cannot bet more than you have.");
                break;
            }

            System.out.println("Dealing...");
            TimeUnit.SECONDS.sleep(2);
            //Player gets two cards
            playerCards.draw(playingDeck);
            playerCards.draw(playingDeck);

            //Dealer gets two cards
            dealerCards.draw(playingDeck);
            dealerCards.draw(playingDeck);

            //While loop for drawing new cards
            while(true)
            {
                //Display player cards
                System.out.println("Your Hand:" + playerCards.toString());
                TimeUnit.SECONDS.sleep(1);
                //Display Value
                System.out.println("Your hand is currently valued at: " + playerCards.cardsValue());
                TimeUnit.SECONDS.sleep(1);
                //Display dealer cards
                System.out.println("Dealer Hand: " + dealerCards.getCard(0).toString() + " + [hidden card]");
                TimeUnit.SECONDS.sleep(1);
                //What do they want to do
                System.out.println("Would you like to (1)Hit or (2)Stand");
                TimeUnit.SECONDS.sleep(1);
                int response = userInput.nextInt();
                //They hit
                if(response == 1){
                    playerCards.draw(playingDeck);
                    System.out.println("You draw a:" + playerCards.getCard(playerCards.deckSize()-1).toString());
                    TimeUnit.SECONDS.sleep(1);
                    //Bust if they go over 21
                    if(playerCards.cardsValue() > 21){
                        System.out.println("Bust. Currently valued at: " + playerCards.cardsValue());
                        TimeUnit.SECONDS.sleep(1);
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }

                //Stand
                if(response == 2){
                    break;
                }

            }

            //Reveal Dealer Cards
            System.out.println("Dealer Cards:" + dealerCards.toString());
            TimeUnit.SECONDS.sleep(1);
            //See if dealer has more points than player
            if((dealerCards.cardsValue() > playerCards.cardsValue())&& !endRound){
                System.out.println("Dealer beats you " + dealerCards.cardsValue() + " to " + playerCards.cardsValue());
                TimeUnit.SECONDS.sleep(1);
                playerMoney -= playerBet;
                endRound = true;
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
                System.out.println("Dealer Busts. You win!");
                TimeUnit.SECONDS.sleep(1);
                playerMoney += playerBet;
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
                System.out.println("You win the hand.");
                TimeUnit.SECONDS.sleep(1);
                playerMoney += playerBet;
            }
            else if(!endRound) //dealer wins
            {
                System.out.println("Dealer wins.");
                TimeUnit.SECONDS.sleep(1);
                playerMoney -= playerBet;
            }

            //End of hand - put cards back in deck
            playerCards.moveAllToDeck(playingDeck);
            dealerCards.moveAllToDeck(playingDeck);
            System.out.println("End of Hand.");
            TimeUnit.SECONDS.sleep(1);

        }
        //Game is over
        System.out.println("Game over! You lost all your money. :(");
        TimeUnit.SECONDS.sleep(1);

        //Close Scanner
        userInput.close();

    }


}
