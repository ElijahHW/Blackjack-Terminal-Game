package com.elijahcode;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    //Creating the Instance cards
    private final ArrayList<Card> cards;
    //Deck Constructor
    public Deck(){
        //Create a new deck of playing cards
        this.cards = new ArrayList<>();
    }
    //Generating the deck,
    //adding 52 playing cards to the deck
    public void createFullDeck(){
        //Generate Cards
        //Loop Through Suits
        for(Suit cardSuit : Suit.values()){
            //Loop through Values
            for(Value cardValue : Value.values()){
                //Add new card to the mix
                this.cards.add(new Card(cardSuit,cardValue));
            }
        }
    }
    //Shuffle deck of cards
     public void shuffle() {
         Collections.shuffle(cards);
        for (int i = 0; i <= cards.size()-1;i++); //Unsure if an empty for loop is bad or not...?
    }
    //Remove a card from the deck
    public void removeCard(int i){
        this.cards.remove(i);
    }
    //Get card from deck
    public Card getCard(int i){
        return this.cards.get(i);
    }

    //Add card to deck
    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    //Draw a top card from deck
    public void draw(Deck comingFrom){
        //Add card to this deck from whatever deck its coming from
        this.cards.add(comingFrom.getCard(0));
        //Remove the card in the deck its coming from
        comingFrom.removeCard(0);
    }

    //Use to print out deck
    public String toString(){
        StringBuilder cardListOutput = new StringBuilder();
        for(Card aCard : this.cards){
            cardListOutput.append("\n").append(aCard.toString());
        }
        return cardListOutput.toString();
    }

    public void moveAllToDeck(Deck moveTo){
        int thisDeckSize = this.cards.size();
        //put cards in moveTo deck
        for(int i = 0; i < thisDeckSize; i++){
            moveTo.addCard(this.getCard(i));
        }
        //empty out the deck
        for(int i = 0; i < thisDeckSize; i++){
            this.removeCard(0);
        }
    }

    public int deckSize(){
        return this.cards.size();
    }

    //Calculate the value of deck
    public int cardsValue(){
        int totalValue = 0;
        int aces = 0;
        //For every card in the deck
        for(Card aCard : this.cards){
            //Switch of possible values
            switch(aCard.getValue()){
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING:
                    totalValue += 10; break;
                case ACE: aces += 1; break;
            }
        }
        //Determine the total current value with aces
        //Aces worth 11 or 1 - if 11 would go over 21 make it worth 1
        for(int i = 0; i < aces; i++){
            //If they're already at over 10 getting an ace valued at 11 would put them up to 22, so make ace worth one
            if (totalValue > 10){
                totalValue += 1;
            }
            else{
                totalValue += 11;
            }
        }
        //Return
        return totalValue;
    }
}
