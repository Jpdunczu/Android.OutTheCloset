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
    private String mNotes;
    private Boolean mDIY;
    private String brandName;
    private int position;

    public Clothes() {
        mId = UUID.randomUUID();
        mDate = LocalDateTime.now ( ).toString ().replace ( "T", " " );
        mBrandId = UUID.randomUUID();
        mName = "";
        mCost = "";
        mSize = "";
        mColor = "";
        mNotes = "";
        mDIY = false;
    }

    public Clothes(UUID id) {
        mId = id;
    }

    public void setmDate(String date) {
        this.mDate = date;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
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

    public String getmNotes() {
        return mNotes;
    }

    public void setmNotes(String mNotes) {
        this.mNotes = mNotes;
    }

    public Boolean getmDIY() {
        return mDIY;
    }

    public void setmDIY(String bool) {
        if ( bool.equals("true") ) {
            this.mDIY = true;
        } else if ( bool.equals("false") ) {
            this.mDIY = false;
        }
    }

    public String getBrandName() {
        return brandName;
    }
}
