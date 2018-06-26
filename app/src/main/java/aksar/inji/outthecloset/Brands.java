package aksar.inji.outthecloset;

import java.util.UUID;

public class Brands {
    private UUID mId;
    private String mBrandName;
    private String mBrandWorth;
    private String mBrandCount;

    public Brands() {
        mId = UUID.randomUUID();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmBrandName() {
        return mBrandName;
    }

    public void setmBrandName(String mBrandName) {
        this.mBrandName = mBrandName;
    }

    public String getmBrandWorth() {
        return mBrandWorth;
    }

    public void setmBrandWorth(String mBrandWorth) {
        this.mBrandWorth = mBrandWorth;
    }

    public String getmBrandCount() {
        return mBrandCount;
    }

    public void setmBrandCount(String mBrandCount) {
        this.mBrandCount = mBrandCount;
    }
}
