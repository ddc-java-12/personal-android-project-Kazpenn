package edu.cnm.deepdive.blackjack.model.types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Hand {

  private static final int SOFT_INCREMENT = 10;
  private static final Map<Rank, Integer> RANK_VALUES;
  private static final int HAND_LIMIT = 21;

  private final List<Card> cards = new LinkedList<>();

  private int value;
  private boolean soft;

  static {
    Map<Rank, Integer> work = new HashMap<>();
    work.put(Rank.ACE, 1);
    work.put(Rank.TWO, 2);
    work.put(Rank.THREE, 3);
    work.put(Rank.FOUR, 4);
    work.put(Rank.FIVE, 5);
    work.put(Rank.SIX, 6);
    work.put(Rank.SEVEN, 7);
    work.put(Rank.EIGHT, 8);
    work.put(Rank.NINE, 9);
    work.put(Rank.TEN, 10);
    work.put(Rank.JACK, 10);
    work.put(Rank.QUEEN, 10);
    work.put(Rank.KING, 10);
    RANK_VALUES = Collections.unmodifiableMap(work);
  }

  public void add(Card card) {
    cards.add(card);
    //noinspection ConstantConditions
    value += RANK_VALUES.get(card.getRank());
    if (card.getRank() == Rank.ACE && !soft && value + SOFT_INCREMENT <= HAND_LIMIT) {
      soft = true;
      value += SOFT_INCREMENT;
    } else if (value > HAND_LIMIT && soft) {
      soft = false;
      value -= SOFT_INCREMENT;
    }
  }

  public boolean isBusted() {
    return value > HAND_LIMIT;
  }

  public boolean isBlackjack() {
    return value == HAND_LIMIT && cards.size() == 2;
  }

  public boolean isSoft() {
    return soft;
  }

  public List<Card> getCards() {
    return Collections.unmodifiableList(cards);
  }

  public int size() {
    return cards.size();
  }
}
