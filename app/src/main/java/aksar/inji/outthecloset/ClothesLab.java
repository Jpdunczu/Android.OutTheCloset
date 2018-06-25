package aksar.inji.outthecloset;

import android.content.Context;

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