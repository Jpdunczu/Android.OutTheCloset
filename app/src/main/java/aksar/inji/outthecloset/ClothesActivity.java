package aksar.inji.outthecloset;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class ClothesActivity extends SingleFragmentActivity {

    public static final String EXTRA_BRAND_ID =
            "com.aksar.inji.outthecloset.brand_id";

    @Override
    protected Fragment createFragment() {
        UUID brandId = (UUID) getIntent().getSerializableExtra(EXTRA_BRAND_ID);
        return newClothesFragment.newClothesInstance(brandId);
    }

    public static Intent newIntent(Context context, UUID brandId) {
        Intent intent = new Intent(context, ClothesActivity.class);
        intent.putExtra(EXTRA_BRAND_ID, brandId);
        return intent;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
