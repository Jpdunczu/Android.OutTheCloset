package aksar.inji.outthecloset.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import aksar.inji.outthecloset.database.ClothesDbSchema.ClothesTable;

import static aksar.inji.outthecloset.database.ClothesDbSchema.BrandTable;

public class ClothesBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "clothesBase.db";

    public static final String CREATE_CLOTHES_TABLE = "create table " + ClothesTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
            ClothesTable.Cols.brandId + ", " +
            ClothesTable.Cols.UUID + ", " +
            ClothesTable.Cols.NAME + ", " +
            ClothesTable.Cols.COST + ", " +
            ClothesTable.Cols.COLOR + ", " +
            ClothesTable.Cols.SIZE + ", " +
            //ClothesTable.Cols.DATE + ", " +
            ClothesTable.Cols.ICON + ", " +
            ClothesTable.Cols.NOTES + ", " +
            ClothesTable.Cols.DIY +
            ")";

    public static final String CREATE_BRAND_TABLE = "create table " + BrandTable.BRAND_NAME + "(" +
            "_id integer primary key autoincrement, " +
            BrandTable.Cols.brandId + ", " +
            BrandTable.Cols.brandName + ", " +
            BrandTable.Cols.COUNT + ", " +
            BrandTable.Cols.WORTH +
            ")";

    public ClothesBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CLOTHES_TABLE);
        db.execSQL(CREATE_BRAND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + ClothesTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BrandTable.BRAND_NAME);

        // create new tables
        onCreate(db);
    }
}
