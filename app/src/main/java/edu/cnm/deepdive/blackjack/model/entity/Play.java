package edu.cnm.deepdive.blackjack.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class Play {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "game_id")
  private long id;

  @ColumnInfo(index = true)
  private int winnings;

  @ColumnInfo(index = true)
  private long dealer_wins;

  @ColumnInfo(index = true)
  private long player_wins;

  @ColumnInfo(index = true)
  private int push;

  @NonNull
  @ColumnInfo(index = true)
  private Date timestamp = new Date();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getWinnings() {
    return winnings;
  }

  public void setWinnings(int winnings) {
    this.winnings = winnings;
  }

  public long getDealer_wins() {
    return dealer_wins;
  }

  public void setDealer_wins(long dealer_wins) {
    this.dealer_wins = dealer_wins;
  }

  public long getPlayer_wins() {
    return player_wins;
  }

  public void setPlayer_wins(long player_wins) {
    this.player_wins = player_wins;
  }

  public int getPush() {
    return push;
  }

  public void setPush(int push) {
    this.push = push;
  }

  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }
}
