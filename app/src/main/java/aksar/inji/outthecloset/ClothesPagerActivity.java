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

public class ClothesPagerActivity extends AppCompatActivity {
    private static final String EXTRA_CLOTHES_ID =
            "com.aksar.inji.outthecloset.clothes_id";

    private ViewPager mViewPager;
    private List<Clothes> mClothes;

    public static Intent newIntent(Context packageContext, UUID clothesId) {
        Intent intent = new Intent(packageContext, ClothesPagerActivity.class);
        intent.putExtra(EXTRA_CLOTHES_ID, clothesId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_pager);

        UUID clothesId = (UUID) getIntent()
            .getSerializableExtra(EXTRA_CLOTHES_ID);

        mViewPager = (ViewPager) findViewById(R.id.clothes_view_pager);

        mClothes = ClothesLab.get(this).getClothes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Clothes clothes = mClothes.get(position);
                return ClothesFragment.newInstance(clothes.getmId());
            }

            @Override
            public int getCount() {
                return mClothes.size();
            }
        });
    }
}
