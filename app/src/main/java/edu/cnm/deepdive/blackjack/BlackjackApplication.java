package edu.cnm.deepdive.blackjack;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.blackjack.service.CardImageService;
import edu.cnm.deepdive.blackjack.service.PlayDatabase;
import io.reactivex.schedulers.Schedulers;

/**
 * Initializes (in the {@link #onCreate()} method) application-level resources. This class
 * <strong>must</strong> be referenced in {@code AndroidManifest.xml}, or it will not be loaded and
 * used by the Android system.
 */
public class BlackjackApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    PlayDatabase.setContext(this);
    PlayDatabase.getInstance()
        .getPlayDao()
        .delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
    CardImageService.setContext(this);
    CardImageService.getInstance();
    Picasso.setSingletonInstance(
        new Picasso.Builder(this)
            .loggingEnabled(BuildConfig.DEBUG)
            .build()
    );
  }

}
