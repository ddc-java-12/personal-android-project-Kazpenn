package edu.cnm.deepdive.blackjack.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.blackjack.R;
import edu.cnm.deepdive.blackjack.databinding.FragmentPlayBinding;
import edu.cnm.deepdive.blackjack.viewmodel.PlayViewModel;
import org.jetbrains.annotations.NotNull;

public class PlayFragment extends Fragment {

  private static final String ARG_SECTION_NUMBER = "section_number";

  private PlayViewModel playViewModel;
  private FragmentPlayBinding binding;

  public static PlayFragment newInstance() {
    return new PlayFragment();
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentPlayBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    playViewModel = new ViewModelProvider(getActivity()).get(PlayViewModel.class);
  }
}