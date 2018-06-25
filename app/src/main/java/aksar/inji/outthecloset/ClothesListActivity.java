package aksar.inji.outthecloset;

import android.support.v4.app.Fragment;

import aksar.inji.outthecloset.ClothesListFragment;
import aksar.inji.outthecloset.SingleFragmentActivity;

public class ClothesListActivity extends SingleFragmentActivity {
	
	@Override
	protected Fragment createFragment() {
		return new ClothesListFragment();
	}
}