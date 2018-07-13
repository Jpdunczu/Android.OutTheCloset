package aksar.inji.outthecloset;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Brands {
    private UUID mId;
    private String mBrandName;
    private String mBrandWorth;
    private String mBrandCount;
    private List<Clothes> mClothes;
    private BigDecimal mBrandWorthDec;

    public Brands() {
        mId = UUID.randomUUID();
        mBrandName = "";
        mBrandCount = "0";
        mBrandWorth = "0.00";
        mBrandWorthDec = new BigDecimal("0.00");
    }

    public Brands(UUID brandId) {
        mId = brandId;
        mBrandName = "";
        mBrandCount = "0";
        mBrandWorth = "0.00";
        mBrandWorthDec = new BigDecimal("0.00");
    }

    public Brands(String brandName) {
        mId = UUID.randomUUID();
        mBrandName = brandName;
        mBrandCount = "0";
        mBrandWorth = "0.00";
        mBrandWorthDec = new BigDecimal("0.00");
    }

    public void addClothes(List<Clothes> clothes){
        mClothes = clothes;
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

    public List<Clothes> getmClothes() {
        return mClothes;
    }

    public BigDecimal getmBrandWorthDec() {
        return mBrandWorthDec;
    }

    public void setmBrandWorthDec(String mBrandWorthDec) {
        BigDecimal value = new BigDecimal(mBrandWorthDec);
        this.mBrandWorthDec.add(value);
    }
}
