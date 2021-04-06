package edu.cnm.deepdive.blackjack.model.types;

public class Card implements Comparable<Card> {

  private final Rank rank;
  private final Suit suit;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public Rank getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  public String getSymbol() {
    return String.format("%s%s",rank.getSymbol(), suit.getSymbol());
  }

  @Override
  public String toString() {
//    return String.format("%s %s", rank, suit);
    return getSymbol();
  }

  @Override
  public int compareTo(Card other) {
    int comparison = suit.compareTo(other.suit);
    if (comparison == 0) {
      comparison = rank.compareTo(other.rank);
    }
    return comparison;
  }

}
