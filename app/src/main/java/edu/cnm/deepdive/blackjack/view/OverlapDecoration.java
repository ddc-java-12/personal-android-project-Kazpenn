package edu.cnm.deepdive.blackjack.view;

public class OverlapDecoration {

  private final int verticalOffset;
  private final int horizontalOffset;

  public OverlapDecoration() {
    this(0,0);
  }

  public OverlapDecoration(int verticalOffset, int horizontalOffset) {
    this.verticalOffset = verticalOffset;
    this.horizontalOffset = horizontalOffset;
  }


}
