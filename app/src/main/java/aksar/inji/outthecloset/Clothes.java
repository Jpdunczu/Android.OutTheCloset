package aksar.inji.outthecloset;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Josh on 6/13/2018.
 */

public class Clothes {

    private UUID mId;
    private UUID mBrandId;
    private String mName;
    private String mDate;
    private String mCost;
    private String mSize;
    private String mColor;
    private String mBrand;
    private String mNotes;
    private Boolean mDIY;

    public Clothes() {
        mId = UUID.randomUUID();
        mDate = LocalDateTime.now ( ).toString ().replace ( "T", " " );
    }

    public UUID getmId() {
        return mId;
    }

    public void setmBrandId (UUID brandId) {
        mBrandId = brandId;
    }

    public UUID getmBrandId () { return mBrandId; }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmCost() {
        return mCost;
    }

    public void setmCost(String mCost) {
        this.mCost = mCost;
    }

    public String getmSize() {
        return mSize;
    }

    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    public String getmColor() {
        return mColor;
    }

    public void setmColor(String mColor) {
        this.mColor = mColor;
    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public String getmNotes() {
        return mNotes;
    }

    public void setmNotes(String mNotes) {
        this.mNotes = mNotes;
    }

    public Boolean getmDIY() {
        return mDIY;
    }

    public void setmDIY(Boolean mDIY) {
        this.mDIY = mDIY;
    }

}
