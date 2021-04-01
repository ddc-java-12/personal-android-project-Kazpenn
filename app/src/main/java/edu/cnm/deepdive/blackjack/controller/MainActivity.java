package edu.cnm.deepdive.blackjack.controller;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.blackjack.databinding.ActivityMainBinding;
import edu.cnm.deepdive.blackjack.model.entity.Play;
import edu.cnm.deepdive.blackjack.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;

  private MainViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    binding.savePlay.setOnClickListener((v) -> {
      Play play = new Play();
      play.setPlayerPoints(Integer.parseInt(binding.playerPoints.getText().toString().trim()));
      play.setPlayerCards(Integer.parseInt(binding.playerCards.getText().toString().trim()));
      play.setDealerPoints(Integer.parseInt(binding.dealerPoints.getText().toString().trim()));
      play.setDealerCards(Integer.parseInt(binding.dealerCards.getText().toString().trim()));
      play.setWager(Integer.parseInt(binding.wager.getText().toString().trim()));
      play.setWinnings(Integer.parseInt(binding.winnings.getText().toString().trim()));
      viewModel.save(play);
    });
    setContentView(binding.getRoot());
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
  }
}