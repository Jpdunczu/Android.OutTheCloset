package aksar.inji.outthecloset.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.math.BigDecimal;
import java.util.UUID;
import java.io.File;

import aksar.inji.outthecloset.Brands;
import aksar.inji.outthecloset.Clothes;
import aksar.inji.outthecloset.database.ClothesDbSchema.BrandTable;
import aksar.inji.outthecloset.database.ClothesDbSchema.ClothesTable;

public class ClothesCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ClothesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Clothes getClothes() {
        String brandIdString = getString(getColumnIndex(ClothesTable.Cols.brandId));
        String uuidString = getString(getColumnIndex(ClothesTable.Cols.UUID));
        String name = getString(getColumnIndex(ClothesTable.Cols.NAME));
        String cost = getString(getColumnIndex(ClothesTable.Cols.COST));
        String color = getString(getColumnIndex(ClothesTable.Cols.COLOR));
        String size = getString(getColumnIndex(ClothesTable.Cols.SIZE));
        String date = getString(getColumnIndex(ClothesTable.Cols.DATE));
        String notes = getString(getColumnIndex(ClothesTable.Cols.NOTES));
        String diy = getString(getColumnIndex(ClothesTable.Cols.DIY));

        Clothes clothes = new Clothes(UUID.fromString(uuidString));
        clothes.setmBrandId(UUID.fromString(brandIdString));
        clothes.setmName(name);
        clothes.setmCost(cost);
        clothes.setmSize(size);
        clothes.setmColor(color);
        clothes.setmDate(date);
        clothes.setmNotes(notes);
        clothes.setmDIY(diy);

        return clothes;
    }

    public Brands getBrands() {
        String brandId = getString(getColumnIndex(BrandTable.Cols.brandId));
        String brandName = getString(getColumnIndex(BrandTable.Cols.brandName));
        String brandCost = getString(getColumnIndex(BrandTable.Cols.WORTH));
        String brandCount = getString(getColumnIndex(BrandTable.Cols.COUNT));

        Brands brands = new Brands(UUID.fromString(brandId));
        brands.setmBrandWorth(brandCost);
        brands.setmBrandCount(Integer.valueOf(brandCount));
        brands.setmBrandName(brandName);

        return brands;
    }
}
