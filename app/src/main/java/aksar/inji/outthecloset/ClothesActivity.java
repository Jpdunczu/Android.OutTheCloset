package aksar.inji.outthecloset;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class ClothesActivity extends SingleFragmentActivity {

    private static final String EXTRA_CLOTHES_ID =
            "com.aksar.inji.outthecloset.clothes_id";

    public static final String EXTRA_BRAND_ID =
            "com.aksar.inji.outthecloset.brand_id";

    //public static Intent newIntent(Context packageContext, UUID clothesId, UUID brandId) {
    public static Intent newIntent(Context packageContext, UUID clothesId) {
        Intent intent = new Intent(packageContext, ClothesActivity.class);
        intent.putExtra(EXTRA_CLOTHES_ID, clothesId);
        //intent.putExtra(EXTRA_BRAND_ID, brandId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        //return new ClothesFragment();

        UUID clothesId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CLOTHES_ID);
        return ClothesFragment.newInstance(clothesId);
    }
}
