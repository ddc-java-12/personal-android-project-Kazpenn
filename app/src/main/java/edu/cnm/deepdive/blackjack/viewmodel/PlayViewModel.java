package edu.cnm.deepdive.blackjack.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.blackjack.model.types.Card;
import edu.cnm.deepdive.blackjack.model.types.Hand;
import edu.cnm.deepdive.blackjack.model.types.Session;
import edu.cnm.deepdive.blackjack.model.types.Session.State;
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
  private final MutableLiveData<Session.State> state;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final PlayRepository repository;

  private Session session;

  public PlayViewModel(@NonNull Application application) {
    super(application);
    repository = new PlayRepository(application);
    dealerHand = new MutableLiveData<>();
    playerHand = new MutableLiveData<>();
    state = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    session = new Session();
    newGame();
  }

  public void newGame() {
    try {
      session.newHand();
      // TODO Check for Dealer Blackjack
      updateLiveData();
    } catch (Exception e) {
      postThrowable(e);
    }

  }

  private void updateLiveData() {
    dealerHand.setValue(session.getDealerHand());
    playerHand.setValue(session.getPlayerHand());
    state.setValue(session.getState());
  }

  public LiveData<Hand> getDealerHand() {
    return dealerHand;
  }

  public LiveData<Hand> getPlayerHand() {
    return playerHand;
  }

  public LiveData<State> getState() {
    return state;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void hit() {
    try {
      session.hit();
      updateLiveData();
    } catch (Exception e) {
      postThrowable(e);
    }
  }

  public void stand() {
    try {
      session.stand();
      updateLiveData();
    } catch (Exception e) {
      postThrowable(e);
    }
  }

  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }
}