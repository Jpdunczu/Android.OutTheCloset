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

	public Cothes getClothe(UUID id) {
		for (Clothes clothes : mClothes) {
			if (clothes.getId().equals(id)) {
				return clothes;
			}
		}

		return null;
	}
}