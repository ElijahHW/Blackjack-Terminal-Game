package com.elijahcode;

public class Card {

    private final Suit suit;
    private final Value value;

    //Card Constructor
    public Card(Suit suit, Value value){
        this.suit = suit;
        this.value = value;
    }
    //Sets the 'template' of each card
    public String toString(){
        return this.value.toString() + " of " + this.suit.getIcon();
    }
    //checking value of each card to determine what value the player holds / value of hand
    public Value getValue(){
        return this.value;
    }



}

