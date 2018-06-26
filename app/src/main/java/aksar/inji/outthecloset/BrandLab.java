package aksar.inji.outthecloset;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BrandLab {
    private static BrandLab sBrandLab;

    private List<Brands> mBrands;

    public static BrandLab get(Context context) {
        if (sBrandLab == null ) {
            sBrandLab = new BrandLab(context);
        }
        return sBrandLab;
    }

    private BrandLab (Context context) {
        mBrands = new ArrayList<>();
    }

    public List<Brands> getmBrands() {
        return mBrands;
    }

    public Brands getBrand(UUID id) {
        for (Brands brand : mBrands ) {
            if( brand.getmId().equals(id) ) {
                return brand;
            }
        }

        return null;
    }
}
