package aksar.inji.outthecloset;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

import aksar.inji.outthecloset.ClothesListFragment;
import aksar.inji.outthecloset.SingleFragmentActivity;

public class ClothesListActivity extends SingleFragmentActivity {

	public static final String EXTRA_BRAND_ID =
			"com.aksar.inji.outthecloset.brand_id";
	
	@Override
	protected Fragment createFragment() {

		return new ClothesListFragment();
	}

	public static Intent newIntent(Context context, UUID brandId) {
		Intent intent = new Intent(context, ClothesListActivity.class);
		intent.putExtra(EXTRA_BRAND_ID, brandId);
		return intent;
	}
}