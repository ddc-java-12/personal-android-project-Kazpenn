package edu.cnm.deepdive.blackjack.model.entity;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import edu.cnm.deepdive.blackjack.model.types.Hand;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@Entity(
    foreignKeys = @ForeignKey(
        entity = Play.class,
        parentColumns = "play_id",
        childColumns = "split_id",
        onDelete = ForeignKey.CASCADE
    )
)
public class Play {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "play_id")
  private long id;

  private int winnings;

  private int wager;

  @ColumnInfo(name = "dealer_points")
  private int dealerPoints;

  @ColumnInfo(name = "player_points")
  private int playerPoints;

  @ColumnInfo(name = "dealer_cards")
  private int dealerCards;

  @ColumnInfo(name = "player_cards")
  private int playerCards;

  @ColumnInfo(name = "split_id", index = true)
  private Long splitId;

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

  public int getWager() {
    return wager;
  }

  public void setWager(int wager) {
    this.wager = wager;
  }

  public int getDealerPoints() {
    return dealerPoints;
  }

  public void setDealerPoints(int dealerPoints) {
    this.dealerPoints = dealerPoints;
  }

  public int getPlayerPoints() {
    return playerPoints;
  }

  public void setPlayerPoints(int playerPoints) {
    this.playerPoints = playerPoints;
  }

  public int getDealerCards() {
    return dealerCards;
  }

  public void setDealerCards(int dealerCards) {
    this.dealerCards = dealerCards;
  }

  public int getPlayerCards() {
    return playerCards;
  }

  public void setPlayerCards(int playerCards) {
    this.playerCards = playerCards;
  }

  public Long getSplitId() {
    return splitId;
  }

  public void setSplitId(Long splitId) {
    this.splitId = splitId;
  }

  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }

  @SuppressLint("DefaultLocale")
  @NonNull
  @Override
  public String toString() {
    return String.format("%s %d(%d) / %d(%d) : %s",
        timestamp,
        dealerPoints,
        dealerCards,
        playerPoints,
        playerCards,
        winner()
    );
  }

  private String winner() {
    String result;
    if (playerPoints > Hand.HAND_LIMIT) {
      result = "dealer wins";
    } else if (dealerPoints > Hand.HAND_LIMIT) {
      result = "player wins";
    } else if (playerPoints > dealerPoints) {
      result = "player wins";
    } else if (dealerPoints > playerPoints) {
      result = "dealer wins";
    } else if (dealerPoints == Hand.HAND_LIMIT) {
      if (dealerCards == 2) {
        result = "dealer wins";
      } else if (playerCards == 2) {
        result = "player wins";
      } else {
        result = "push";
      }
    } else {
      result = "push";
    }
    return result;
  }
}
