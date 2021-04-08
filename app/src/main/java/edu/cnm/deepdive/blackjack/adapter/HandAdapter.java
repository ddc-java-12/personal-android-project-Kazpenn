package edu.cnm.deepdive.blackjack.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.blackjack.R;
import edu.cnm.deepdive.blackjack.adapter.HandAdapter.Holder;
import edu.cnm.deepdive.blackjack.model.types.Card;
import edu.cnm.deepdive.blackjack.model.types.Hand;
import edu.cnm.deepdive.blackjack.service.CardImageService;

public class HandAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final Hand hand;
  private final CardImageService cardImageService;

  private boolean holeCardVisible;


  public HandAdapter(Context context, Hand hand, boolean holeCardVisible) {
    this.context = context;
    this.hand = hand;
    this.holeCardVisible = holeCardVisible;
    cardImageService = CardImageService.getInstance();
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new Holder(LayoutInflater.from(context).inflate(R.layout.item_hand, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(hand.getCards().get(position), position > 0 || holeCardVisible);
  }

  @Override
  public int getItemCount() {
    return hand.size();
  }

  public void setHoleCardVisible(boolean holeCardVisible) {
    this.holeCardVisible = holeCardVisible;
  }

  class Holder extends RecyclerView.ViewHolder {

    public Holder(@NonNull View itemView) {
      super(itemView);
    }

    private void bind(Card card, boolean faceVisible) {
      if (faceVisible) {
        Picasso.get().load(cardImageService.getImage(card)).into((ImageView) itemView);
      } else {
        ((ImageView) itemView).setImageResource(R.drawable.ic_card_back);
      }
    }

  }
}
