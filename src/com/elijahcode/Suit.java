package com.elijahcode;

public enum Suit {
    SPADES("\033[1;30m"), HEARTS("\033[1;31m"), DIAMONDS("\033[1;31m"), CLUBS("\033[1;30m");

    private String icon;

    Suit(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
