package edu.cnm.deepdive.blackjack.model.types;

import java.security.SecureRandom;

public class Session {

  // TODO Have more than 1 player hand

  private Shoe shoe;

  private Hand dealerHand;

  private Hand playerHand;

  private State state;

  public Session() {
    state = State.COMPLETED;
  }

  public void newHand() {
    if (state != State.COMPLETED) {
      throw new IllegalStateException();
    }
    if (shoe == null || shoe.isExhausted()) {
      shoe = new Shoe(6, 0.25, new SecureRandom());
    }
    dealerHand = new Hand();
    playerHand = new Hand();
    playerHand.add(shoe.deal());
    playerHand.add(shoe.deal());
    dealerHand.add(shoe.deal());
    dealerHand.add(shoe.deal());
    state = State.PLAYING;
  }

  public boolean hit() {
    if (state != State.PLAYING) {
      throw new IllegalStateException();
    }
    boolean completed = false;
    if (playerHand.getValue() < Hand.HAND_LIMIT) {
      playerHand.add(shoe.deal());
      if (playerHand.getValue() >= 21) {
        stand();
      }
      completed = true;
    }
    return completed;
  }

  public void stand() {
    if (state != State.PLAYING) {
      throw new IllegalStateException();
    }
    while (dealerHand.getValue() < 17) {
      dealerHand.add(shoe.deal());
    }
    state = State.COMPLETED;
  }

  public Hand getDealerHand() {
    return dealerHand;
  }

  public Hand getPlayerHand() {
    return playerHand;
  }

  public State getState() {
    return state;
  }

  public enum State {
    PLAYING, COMPLETED
  }

}
