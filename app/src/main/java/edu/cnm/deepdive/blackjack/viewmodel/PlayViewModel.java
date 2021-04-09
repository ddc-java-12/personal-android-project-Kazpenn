package edu.cnm.deepdive.blackjack.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.blackjack.model.entity.Play;
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

public class PlayViewModel extends AndroidViewModel implements LifecycleObserver {

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
      if (session.getState() == State.COMPLETED) {
        save();
      }
      updateLiveData();
    } catch (Exception e) {
      postThrowable(e);
    }

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

  public LiveData<List<Play>> getHistory() {
    return repository.getAll();
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void hit() {
    try {
      session.hit();
      if (session.getState() == State.COMPLETED) {
        save();
      }
      updateLiveData();
    } catch (Exception e) {
      postThrowable(e);
    }
  }

  public void stand() {
    try {
      session.stand();
      save();
      updateLiveData();
    } catch (Exception e) {
      postThrowable(e);
    }
  }

  private void save() {
    throwable.setValue(null);
    Play play = new Play();
    play.setDealerCards(session.getDealerHand().size());
    play.setDealerPoints(session.getDealerHand().getValue());
    play.setPlayerCards(session.getPlayerHand().size());
    play.setPlayerPoints(session.getPlayerHand().getValue());
    pending.add(
        repository
            .save(play)
            .subscribe(
                (p) -> {},
                this::postThrowable
            )
    );
  }

  private void updateLiveData() {
    dealerHand.setValue(session.getDealerHand());
    playerHand.setValue(session.getPlayerHand());
    state.setValue(session.getState());
  }

  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}