package edu.cnm.deepdive.blackjack.model.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Shoe {

  private final List<Card> cards;
  private final int markerPosition;

  public Shoe(int numDecks, double markerPosition, Random rng) {
    cards = new ArrayList<>();
    for (int i = 0; i < numDecks; i++) {
      for (Suit suit : Suit.values()) {
        for (Rank rank : Rank.values()) {
          Card card = new Card(rank, suit);
          cards.add(card);
        }
      }
    }
    Collections.shuffle(cards, rng);
    this.markerPosition = (int) (markerPosition * cards.size());
  }

  public Card deal() {
    return cards.remove(0);
  }

  public boolean isExhausted() {
    return cards.size() <= markerPosition;
  }

}
