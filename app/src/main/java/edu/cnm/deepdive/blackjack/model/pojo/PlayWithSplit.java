package edu.cnm.deepdive.blackjack.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.blackjack.model.entity.Play;

public class PlayWithSplit extends Play {
  @Relation(
      entity = Play.class,
      parentColumn = "play_id",
      entityColumn = "split_id"
  )
  private Play split;

  public Play getSplit() {
    return split;
  }

  public void setSplit(Play split) {
    this.split = split;
  }
}
