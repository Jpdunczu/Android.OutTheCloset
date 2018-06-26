package aksar.inji.outthecloset;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Josh on 6/13/2018.
 */

public class Clothes {

    private UUID mId;
    private String mName;
    private Date mDate;
    private String mCost;
    private String mSize;
    private String mColor;
    private String mBrand;
    private String mNotes;
    private Boolean mDIY;

    public Clothes() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
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
