package edu.cnm.deepdive.blackjack.adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import edu.cnm.deepdive.blackjack.R;
import edu.cnm.deepdive.blackjack.controller.HistoryFragment;
import edu.cnm.deepdive.blackjack.controller.PlayFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to one of the
 * sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

  @StringRes
  private static final int[] TAB_TITLES = new int[]{R.string.tab_play, R.string.tab_history};
  private final Context mContext;

  public SectionsPagerAdapter(Context context, FragmentManager fm) {
    super(fm);
    mContext = context;
  }

  @Override
  public Fragment getItem(int position) {
    // getItem is called to instantiate the fragment for the given page.
    // Return a PlaceholderFragment (defined as a static inner class below).
    switch (position) {
      case 0:
        return PlayFragment.newInstance();
      case 1:
        return HistoryFragment.newInstance();
    }
    return null;
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return mContext.getResources().getString(TAB_TITLES[position]);
  }

  @Override
  public int getCount() {
    // Show 2 total pages.
    return 2;
  }
}