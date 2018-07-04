package aksar.inji.outthecloset;

import android.support.v4.app.Fragment;

public class ClothesActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new newClothesFragment();
    }
}
