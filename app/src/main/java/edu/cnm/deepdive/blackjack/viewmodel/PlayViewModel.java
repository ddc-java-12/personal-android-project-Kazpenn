package edu.cnm.deepdive.blackjack.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class PlayViewModel extends AndroidViewModel {

  public PlayViewModel(
      @NonNull Application application) {
    super(application);
  }

}