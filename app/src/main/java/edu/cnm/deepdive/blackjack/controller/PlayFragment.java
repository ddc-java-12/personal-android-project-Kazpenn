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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.blackjack.R;
import edu.cnm.deepdive.blackjack.adapter.HandAdapter;
import edu.cnm.deepdive.blackjack.databinding.FragmentPlayBinding;
import edu.cnm.deepdive.blackjack.model.types.Hand;
import edu.cnm.deepdive.blackjack.model.types.Session;
import edu.cnm.deepdive.blackjack.model.types.Session.State;
import edu.cnm.deepdive.blackjack.view.OverlapDecoration;
import edu.cnm.deepdive.blackjack.viewmodel.PlayViewModel;
import org.jetbrains.annotations.NotNull;

public class PlayFragment extends Fragment {

  private static final String ARG_SECTION_NUMBER = "section_number";

  private PlayViewModel playViewModel;
  private FragmentPlayBinding binding;
  private Session.State state;
  private Hand dealerHand;

  public static PlayFragment newInstance() {
    return new PlayFragment();
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentPlayBinding.inflate(inflater, container, false);
    binding.playerHand.addItemDecoration(
        new OverlapDecoration(0, (int) getResources().getDimension(R.dimen.card_overlap)));
    binding.playerHand.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    binding.dealerHand.addItemDecoration(
        new OverlapDecoration(0, (int) getResources().getDimension(R.dimen.card_overlap)));
    binding.dealerHand.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    binding.hit.setOnClickListener((v) -> playViewModel.hit());
    binding.stand.setOnClickListener((v) -> playViewModel.stand());
    binding.newGame.setOnClickListener((v) -> playViewModel.newGame());
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    playViewModel = new ViewModelProvider(getActivity()).get(PlayViewModel.class);
    getLifecycle().addObserver(playViewModel);
    playViewModel.getPlayerHand().observe(getViewLifecycleOwner(), (hand) -> {
      showHand(hand, true, binding.playerHand);
      // TODO Display the value of the players (maybe and dealers hand).
    });
    playViewModel.getDealerHand().observe(getViewLifecycleOwner(), (hand) -> {
      dealerHand = hand;
      showHand(hand, state == State.COMPLETED, binding.dealerHand);
    });

    playViewModel.getState().observe(getViewLifecycleOwner(), (state) -> {
      this.state = state;
      if (dealerHand != null) {
        showHand(dealerHand, state == State.COMPLETED, binding.dealerHand);
      }
      binding.hit.setVisibility((state == State.PLAYING) ? View.VISIBLE : View.INVISIBLE);
      binding.stand.setVisibility((state == State.PLAYING) ? View.VISIBLE : View.INVISIBLE);
      binding.newGame.setVisibility((state == State.COMPLETED) ? View.VISIBLE : View.INVISIBLE);
    });

  }

  private void showHand(Hand hand, boolean holeCardVisible, RecyclerView recyclerView) {
    HandAdapter adapter = new HandAdapter(getContext(), hand, holeCardVisible);
    recyclerView.setAdapter(adapter);
  }
}