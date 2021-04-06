package edu.cnm.deepdive.blackjack.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.blackjack.model.types.Card;
import edu.cnm.deepdive.blackjack.model.types.Hand;
import edu.cnm.deepdive.blackjack.model.types.Shoe;
import edu.cnm.deepdive.blackjack.service.PlayRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class PlayViewModel extends AndroidViewModel {

  private static final int DEFAULT_NUM_DECKS = 6;
  private static final double DEFAULT_MARKER_POSITION = 0.25;

  private final MutableLiveData<Hand> dealerHand;
  private final MutableLiveData<Hand> playerHand;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final PlayRepository repository;

  private Shoe shoe;

  public PlayViewModel(@NonNull Application application) {
    super(application);
    repository = new PlayRepository(application);
    dealerHand = new MutableLiveData<>();
    playerHand = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    newGame();
  }

  public void newGame() {
    if (shoe == null || shoe.isExhausted()) {
      shoe = new Shoe(DEFAULT_NUM_DECKS, DEFAULT_MARKER_POSITION, new SecureRandom());
    }
    Hand dealerHand = new Hand();
    Hand playerHand = new Hand();
    dealerHand.add(shoe.deal());
    dealerHand.add(shoe.deal());
    playerHand.add(shoe.deal());
    playerHand.add(shoe.deal());
    // TODO Check for Dealer Blackjack
    this.dealerHand.setValue(dealerHand);
    this.playerHand.setValue(playerHand);
  }

  public LiveData<Hand> getDealerHand() {
    return dealerHand;
  }

  public LiveData<Hand> getPlayerHand() {
    return playerHand;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }
}