package aksar.inji.outthecloset;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import aksar.inji.outthecloset.Clothes;
import aksar.inji.outthecloset.database.ClothesBaseHelper;
import aksar.inji.outthecloset.database.ClothesCursorWrapper;
import aksar.inji.outthecloset.database.ClothesDbSchema;
import aksar.inji.outthecloset.database.ClothesDbSchema.ClothesTable;

public class ClothesLab {

	private static ClothesLab sClothesLab;

	//private List<Clothes> mClothes;
	private Context mContext;
	private SQLiteDatabase mDatabase;

	public static ClothesLab get(Context context) {
		if (sClothesLab == null) {
			sClothesLab = new ClothesLab(context);
		}
		return sClothesLab;
	}

	private ClothesLab(Context context) {
	    mContext = context.getApplicationContext();
	    mDatabase = new ClothesBaseHelper(mContext)
                .getWritableDatabase();

		//mClothes = new ArrayList<>();

	}

	public void addClothes(Clothes clothes){
		//mClothes.add(clothes);
        ContentValues values = getContentValues(clothes);

        mDatabase.insert(ClothesTable.NAME, null, values);
	}

	public void deleteClothes(UUID clothesId) {
		mDatabase.delete(ClothesTable.NAME,
				ClothesTable.Cols.UUID + " = ?",
					new String[] { clothesId.toString() }
				);
	}

	public List<Clothes> getClothesByBrand(UUID brandId) {

		List<Clothes> clothes = new ArrayList<>();

		ClothesCursorWrapper cursor = queryClothes(
				ClothesTable.Cols.brandId + " = ?",
				new String[] { brandId.toString() }
		);

		try {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				clothes.add(cursor.getClothes());
				cursor.moveToNext();
			}
		} finally {
			cursor.close();
		}
		//return mClothes;
		return clothes;
	}

	public List<Clothes> getClothes() {

	    List<Clothes> clothes = new ArrayList<>();

	    ClothesCursorWrapper cursor = queryClothes(null, null);

	    try {
	        cursor.moveToFirst();
	        while (!cursor.isAfterLast()) {
	            clothes.add(cursor.getClothes());
	            cursor.moveToNext();
            }
        } finally {
	        cursor.close();
        }
		//return mClothes;
        return clothes;
	}

	public Clothes getClothe(UUID id) {

	    ClothesCursorWrapper cursor = queryClothes(
	            ClothesTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

	    try {
	        if (cursor.getCount() == 0) {
	            return null;
            }

            cursor.moveToFirst();
	        return cursor.getClothes();
        } finally {
	        cursor.close();
        }
		/*
	    for (Clothes clothes : mClothes) {
			if (clothes.getmId().equals(id)) {
				return clothes;
			}
		}
		*/
	}

	public void updateClothes(Clothes clothes) {
	    String uuidString = clothes.getmId().toString();
	    ContentValues values = getContentValues(clothes);

	    mDatabase.update(ClothesTable.NAME, values,
                ClothesTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

	private static ContentValues getContentValues(Clothes clothes) {
	    ContentValues values = new ContentValues();
	    values.put(ClothesTable.Cols.brandId, clothes.getmBrandId().toString());
	    values.put(ClothesTable.Cols.UUID, clothes.getmId().toString());
	    values.put(ClothesTable.Cols.NAME, clothes.getmName());
	    values.put(ClothesTable.Cols.COST, clothes.getmCost());
	    values.put(ClothesTable.Cols.COLOR, clothes.getmColor());
	    values.put(ClothesTable.Cols.SIZE, clothes.getmSize());
	    values.put(ClothesTable.Cols.NOTES, clothes.getmNotes());
	    values.put(ClothesTable.Cols.DIY, "false");
	    values.put(ClothesTable.Cols.DATE, clothes.getmDate());

	    return values;
    }

    //private Cursor queryClothes(String whereClause, String[] whereArgs) {
	private ClothesCursorWrapper queryClothes(String whereClause, String[] whereArgs) {
	       Cursor cursor = mDatabase.query(
	            ClothesTable.NAME,
                null, //columns ~ null selects all
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

	    return new ClothesCursorWrapper(cursor);
    }

    /*
    *
    * PHOTO METHODS
     */
    public File getPhotoFile(Clothes clothes) {
    	File filesDir = mContext.getFilesDir();
    	return new File(filesDir, clothes.getPhotoFilename());
	}
}