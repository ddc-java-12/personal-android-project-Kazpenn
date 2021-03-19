package edu.cnm.deepdive.blackjack.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.blackjack.model.dao.PlayDao;
import edu.cnm.deepdive.blackjack.model.entity.Play;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class PlayRepository {

  private final Context context;

  private final PlayDao dao;

  public PlayRepository(Context context) {

    this.context = context;
    dao = PlayDatabase.getInstance().getPlayDao();
  }

  public Single<Play> save(Play play) {
    return (
        (play.getId() > 0)
            ? dao
                .update(play)
                .map((ignored) -> play)
            : dao
                .insert(play)
                .map((id) -> {
                  play.setId(id);
                  return play;
                })
    )
        .subscribeOn(Schedulers.io());
  }

  public LiveData<List<Play>> getAll() {
    return dao.selectAll();
  }
}
