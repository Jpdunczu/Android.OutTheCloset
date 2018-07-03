package aksar.inji.outthecloset.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import aksar.inji.outthecloset.database.ClothesDbSchema.ClothesTable;

public class ClothesBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "clothesBase.db";

    public ClothesBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ClothesTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
            ClothesTable.Cols.brandId + ", " +
            ClothesTable.Cols.UUID + ", " +
            ClothesTable.Cols.NAME + ", " +
            ClothesTable.Cols.COST + ", " +
            ClothesTable.Cols.COLOR + ", " +
            ClothesTable.Cols.SIZE + ", " +
            ClothesTable.Cols.DATE + ", " +
            ClothesTable.Cols.NOTES + ", " +
            ClothesTable.Cols.DIY +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
