package aksar.inji.outthecloset;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

import aksar.inji.outthecloset.ClothesListFragment;
import aksar.inji.outthecloset.SingleFragmentActivity;

public class ClothesListActivity extends SingleFragmentActivity
implements ClothesListFragment.Callbacks, ClothesFragment.Callbacks {

	public static final String EXTRA_BRAND_ID =
			"com.aksar.inji.outthecloset.brand_id";

	private int position;
	
	@Override
	protected Fragment createFragment() {
		UUID brandId = (UUID) getIntent().getSerializableExtra(EXTRA_BRAND_ID);
		return ClothesListFragment.newInstance(brandId);
	}

	public static Intent newIntent(Context context, UUID brandId) {
		Intent intent = new Intent(context, ClothesListActivity.class);
		intent.putExtra(EXTRA_BRAND_ID, brandId);
		return intent;
	}

    @Override
    public void onClothesSelected(Clothes clothes) {
        Intent intent = ClothesPagerActivity.newIntent(this, clothes.getmId(), clothes.getmBrandId());
        startActivity(intent);
    }

    @Override
    public void onClothesUpdated(Clothes clothes, int position) {
        ClothesListFragment listFragment = (ClothesListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        listFragment.updateSingleUI(position);
    }
}