package edu.cnm.deepdive.blackjack.service;

import android.annotation.SuppressLint;
import android.app.Application;
import edu.cnm.deepdive.blackjack.model.types.Card;
import edu.cnm.deepdive.blackjack.model.types.Rank;
import edu.cnm.deepdive.blackjack.model.types.Suit;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CardImageService {

  private static final String FILENAME_FORMAT = "%C%C.png";
  private static final String CARD_IMAGE_SUBDIRECTORY = "card-images";
  private static final int THREAD_POOL_SIZE = 4;

  private static Application context;

  private final File imageDirectory;
  private final DeckOfCardsProxy proxy;
  private final Executor networkPool;

  private CardImageService() {
    imageDirectory = new File(context.getDataDir(), CARD_IMAGE_SUBDIRECTORY);
    imageDirectory.mkdirs();
    proxy = DeckOfCardsProxy.getInstance();
    networkPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    initialize();
  }

  public static void setContext(Application context) {
    CardImageService.context = context;
  }

  public File getImage(Card card) {
    String filename = String.format(FILENAME_FORMAT, card.getRank().getInitial(), card.getSuit().getInitial());
    return new File(imageDirectory, filename);
  }

  private void initialize() {
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        String filename = String.format(FILENAME_FORMAT, rank.getInitial(), suit.getInitial());
        File imageFile = new File(imageDirectory, filename);
        if (!imageFile.exists()) {
          download(filename);
        }
      }
    }
  }

  @SuppressLint("CheckResult")
  private void download(String filename) {
    //noinspection ResultOfMethodCallIgnored
    proxy
        .getCardImage(filename)
        .subscribeOn(Schedulers.from(networkPool))
        .subscribe(
            (body) -> {
              Path imageFile = new File(imageDirectory, filename).toPath();
              Files.copy(body.byteStream(), imageFile);
            },
            (throwable) -> {
              throw new RuntimeException(throwable);
              // FIXME fail in not crashing
            }
        );
  }

  public static CardImageService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private static final CardImageService INSTANCE = new CardImageService();
  }

}
