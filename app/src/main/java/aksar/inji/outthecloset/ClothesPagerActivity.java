package aksar.inji.outthecloset;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class ClothesPagerActivity extends AppCompatActivity
implements ClothesFragment.Callbacks {

    private static final String EXTRA_CLOTHES_ID =
            "com.aksar.inji.outthecloset.clothes_id";

    private static final String EXTRA_BRAND_ID =
            "com.aksar.inji.outthecloset.brand_id";

    private static final int REQUEST_POSITION = 0;

    private ViewPager mViewPager;
    private List<Clothes> mClothes;

    public static Intent newIntent(Context packageContext, UUID clothesId, UUID brandId) {
        Intent intent = new Intent(packageContext, ClothesPagerActivity.class);
        intent.putExtra(EXTRA_CLOTHES_ID, clothesId);
        intent.putExtra(EXTRA_BRAND_ID, brandId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_pager);
        mViewPager = (ViewPager) findViewById(R.id.clothes_view_pager);

            final UUID clothesId = (UUID) getIntent().getSerializableExtra(EXTRA_CLOTHES_ID);
            UUID brandId = (UUID) getIntent().getSerializableExtra(EXTRA_BRAND_ID);
            mClothes = ClothesLab.get(this).getClothesByBrand(brandId);

            for (int i = 0; i < mClothes.size(); i++) {
                if (mClothes.get(i).getmId().equals(clothesId)) {
                    mViewPager.setCurrentItem(i);
                    break;
                }
            }

        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Clothes clothes = mClothes.get(position);
                return ClothesFragment.newInstance(clothes.getmId(), position);
            }

            @Override
            public int getCount() {
                    return mClothes.size();
            }
        });
    }

    @Override
    public void onClothesUpdated(Clothes clothes, int position) {

    }
}
