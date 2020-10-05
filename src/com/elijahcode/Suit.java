package com.elijahcode;

public enum Suit {
    SPADES("♠"),
    HEARTS("♥"),
    DIAMONDS("♦"),
    CLUBS("♣");

    private String icon;

    Suit(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
