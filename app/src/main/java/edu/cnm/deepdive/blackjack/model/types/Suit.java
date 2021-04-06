package edu.cnm.deepdive.blackjack.model.types;

public enum Suit {

  CLUBS('\u2663', Color.BLACK),
  DIAMONDS('\u2662', Color.RED),
  HEARTS('\u2661', Color.RED),
  SPADES('\u2660', Color.BLACK);

  private final char symbol;
  private final Color color;

  Suit(char symbol, Color color) {
    this.symbol = symbol;
    this.color = color;
  }

  public char getSymbol() {
    return symbol;
  }

  public Color getColor() {
    return color;
  }

  public char getInitial() {
    return name().charAt(0);
  }

  public enum Color {
    BLACK, RED
  }
}
