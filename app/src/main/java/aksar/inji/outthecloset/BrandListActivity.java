package aksar.inji.outthecloset;


import android.support.v4.app.Fragment;

public class BrandListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new BrandListFragment();
    }
}
