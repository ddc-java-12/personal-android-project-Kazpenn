package edu.cnm.deepdive.blackjack.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.blackjack.R;
import edu.cnm.deepdive.blackjack.viewmodel.PageViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayFragment extends Fragment {

  private static final String ARG_SECTION_NUMBER = "section_number";

  private PageViewModel pageViewModel;

  public static PlayFragment newInstance(int index) {
    PlayFragment fragment = new PlayFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(ARG_SECTION_NUMBER, index);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    pageViewModel = new ViewModelProvider(getActivity()).get(PageViewModel.class);
    int index = 1;
    if (getArguments() != null) {
      index = getArguments().getInt(ARG_SECTION_NUMBER);
    }
    pageViewModel.setIndex(index);
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_main, container, false);
    final TextView textView = root.findViewById(R.id.section_label);
    return root;
  }
}