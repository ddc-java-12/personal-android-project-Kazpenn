package edu.cnm.deepdive.blackjack.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.blackjack.model.dao.PlayDao;
import edu.cnm.deepdive.blackjack.model.entity.Play;
import edu.cnm.deepdive.blackjack.service.PlayDatabase.Converters;
import java.util.Date;

@Database(
    entities = Play.class,
    version = 1
)
@TypeConverters(Converters.class)
public abstract class PlayDatabase extends RoomDatabase {

  private static final String DB_NAME = "play_db";

  private static Application context;

  public static void setContext(Application context) {
    PlayDatabase.context = context;
  }

  public static PlayDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract PlayDao getPlayDao();

  private static class InstanceHolder {
    private static final PlayDatabase INSTANCE =
        Room.databaseBuilder(context, PlayDatabase.class, DB_NAME)
        .build();
  }

  public static class Converters {

    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }

  }

}
