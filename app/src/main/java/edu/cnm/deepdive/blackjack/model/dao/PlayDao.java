package edu.cnm.deepdive.blackjack.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.blackjack.model.entity.Play;
import edu.cnm.deepdive.blackjack.model.pojo.PlayWithSplit;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface PlayDao {

  @Insert
  Single<Long> insert(Play play);

  @Insert
  Single<List<Long>> insert(Collection<Play> plays);

  @Update
  Single<Integer> update(Play play);

  @Delete
  Single<Integer> delete(Play play);

  @Delete
  Single<Integer> delete(Play... plays);

  @Delete
  Single<Integer> delete(Collection<Play> plays);

  @Query("SELECT * FROM Play ORDER BY timestamp DESC")
  LiveData<List<Play>> selectAll();

  @Transaction
  @Query("SELECT * FROM Play WHERE play_id = :playId")
  LiveData<PlayWithSplit> selectOne(long playId);
}
