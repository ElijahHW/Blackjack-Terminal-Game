package com.elijahcode;

public enum Suit {
    SPADES("\u2660"), HEARTS("\u2665"), DIAMONDS("\u2666"), CLUBS("\u2663");

    private final String icon;

    Suit(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}