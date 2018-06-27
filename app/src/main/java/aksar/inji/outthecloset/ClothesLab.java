package aksar.inji.outthecloset;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import aksar.inji.outthecloset.Clothes;

public class ClothesLab {

	private static ClothesLab sClothesLab;

	private List<Clothes> mClothes;

	public static ClothesLab get(Context context) {
		if (sClothesLab == null) {
			sClothesLab = new ClothesLab(context);
		}
		return sClothesLab;
	}

	private ClothesLab(Context context) {

		mClothes = new ArrayList<>();

		for( int i = 0; i <100; i++ ) {
			Clothes clothes = new Clothes();
			clothes.setmBrand("Brand #" + i);
			clothes.setmColor("blue");
			clothes.setmCost("3.99");
			clothes.setmName("Clothing");
			clothes.setmNotes("Love this one.");
			clothes.setmSize("XS");
			mClothes.add(clothes);
		}
	}

	public List<Clothes> getClothes() {
		return mClothes;
	}

	public Clothes getClothe(UUID id) {
		for (Clothes clothes : mClothes) {
			if (clothes.getmId().equals(id)) {
				return clothes;
			}
		}

		return null;
	}
}