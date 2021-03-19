package edu.cnm.deepdive.blackjack.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.blackjack.model.entity.Play;
import edu.cnm.deepdive.blackjack.service.PlayRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

  private final PlayRepository repository;

  private final MutableLiveData<Throwable> throwable;

  public MainViewModel(@NonNull Application application) {
    super(application);
    repository = new PlayRepository(application);
    throwable = new MutableLiveData<>();
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<List<Play>> getPlays() {
    return repository.getAll();
  }

  public void save(Play play) {
    throwable.setValue(null);
    repository
        .save(play)
        .subscribe(
            (p) -> {/*TODO Populate live data to notify user of success */},
            this::postThrowable
        );
  }

  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }
}
