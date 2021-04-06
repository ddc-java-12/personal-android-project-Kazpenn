package edu.cnm.deepdive.blackjack.view;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.State;

public class OverlapDecoration extends RecyclerView.ItemDecoration {

  private final int verticalOffset;
  private final int horizontalOffset;

  public OverlapDecoration() {
    this(0, 0);
  }

  public OverlapDecoration(int verticalOffset, int horizontalOffset) {
    this.verticalOffset = verticalOffset;
    this.horizontalOffset = horizontalOffset;
  }

  @Override
  public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
      @NonNull RecyclerView parent, @NonNull State state) {
    int itemPosition = parent.getChildAdapterPosition(view);
    if (itemPosition == 0) {
      super.getItemOffsets(outRect, view, parent, state);
    } else {
      outRect.set(horizontalOffset, verticalOffset, 0, 0);
    }
  }
}
