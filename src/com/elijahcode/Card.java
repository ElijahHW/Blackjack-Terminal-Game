package com.elijahcode;

public class Card {

    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value){
        this.suit = suit;
        this.value = value;
    }

    public String toString(){
        return this.value.toString() + " of " + this.suit.getIcon();
    }

    public Value getValue(){
        return this.value;
    }



}

