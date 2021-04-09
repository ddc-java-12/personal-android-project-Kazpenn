package edu.cnm.deepdive.blackjack.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.blackjack.databinding.FragmentHistoryBinding;
import edu.cnm.deepdive.blackjack.model.entity.Play;
import edu.cnm.deepdive.blackjack.viewmodel.PlayViewModel;
import org.jetbrains.annotations.NotNull;

public class HistoryFragment extends Fragment {

  private FragmentHistoryBinding binding;
  private PlayViewModel viewModel;

  public static HistoryFragment newInstance() {
    HistoryFragment fragment = new HistoryFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentHistoryBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity()).get(PlayViewModel.class);
    getLifecycle().addObserver(viewModel);
    viewModel.getHistory().observe(getViewLifecycleOwner(), (plays) -> {
      ArrayAdapter<Play> adapter =
          new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, plays);
      binding.historyList.setAdapter(adapter);
    });
  }
}