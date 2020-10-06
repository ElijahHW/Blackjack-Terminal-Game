package com.elijahcode;

public enum Suit {
    SPADES(Flavor.BLACK_BOLD + "♠" + Flavor.RESET),
    HEARTS(Flavor.RED_BOLD + "♥" + Flavor.RESET),
    DIAMONDS(Flavor.RED_BOLD + "♦" + Flavor.RESET),
    CLUBS(Flavor.BLACK_BOLD + "♣" + Flavor.RESET);

    private final String icon;

    Suit(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}

