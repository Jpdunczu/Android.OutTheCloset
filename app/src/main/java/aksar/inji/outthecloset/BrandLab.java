package aksar.inji.outthecloset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import aksar.inji.outthecloset.database.ClothesBaseHelper;
import aksar.inji.outthecloset.database.ClothesCursorWrapper;
import aksar.inji.outthecloset.database.ClothesDbSchema;
import aksar.inji.outthecloset.database.ClothesDbSchema.BrandTable;

public class BrandLab {
    private static BrandLab sBrandLab;

    //private List<Brands> mBrands;
    private Context mContext;
    private SQLiteDatabase mDataBase;


    public static BrandLab get(Context context) {
        if (sBrandLab == null ) {
            sBrandLab = new BrandLab(context);
        }
        return sBrandLab;
    }

    private BrandLab (Context context) {
        mContext = context.getApplicationContext();
        mDataBase = new ClothesBaseHelper(mContext).getWritableDatabase();
        //mBrands = new ArrayList<>();
    }

    public List<Brands> getmBrands() {
        List<Brands> brands = new ArrayList<>();

        ClothesCursorWrapper cursor = queryBrands(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                brands.add(cursor.getBrands());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        //return mBrands;
        return brands;
    }

    public void addBrand(Brands brands) {
        ContentValues values = getContentValues(brands);

        mDataBase.insert(BrandTable.BRAND_NAME,null, values);
    }

    public void updateBrand(Brands brands) {
        String uuidString = brands.getmId().toString();
        ContentValues values = getContentValues(brands);

        mDataBase.update(BrandTable.BRAND_NAME, values,
                BrandTable.Cols.brandId + " = ?",
                new String[] { uuidString } );
    }

    private ClothesCursorWrapper queryBrands(String whereClause, String[] whereArgs) {
        Cursor cursor = mDataBase.query(
                BrandTable.BRAND_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ClothesCursorWrapper(cursor);
    }

    public Brands getBrand(UUID id) {
        /*
        for (Brands brand : mBrands ) {
            if( brand.getmId().equals(id) ) {
                return brand;
            }
        }
        */

        ClothesCursorWrapper cursorWrapper = queryBrands(
                BrandTable.Cols.brandId + " = ?",
                new String[] { id.toString() }
        );

        try {
            if ( cursorWrapper.getCount() == 0 ) {
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getBrands();
        } finally {
            cursorWrapper.close();
        }
    }

    private static ContentValues getContentValues(Brands brands) {
        ContentValues values = new ContentValues();
        values.put(BrandTable.Cols.brandId, brands.getmId().toString());
        values.put(BrandTable.Cols.brandName, brands.getmBrandName());
        values.put(BrandTable.Cols.COUNT, String.valueOf(brands.getmBrandCount()));
        values.put(BrandTable.Cols.WORTH, brands.getmBrandWorth());

        return values;
    }
}
