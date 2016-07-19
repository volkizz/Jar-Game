public class Jar {
    private String mItemType;
    private int mMaxItemForJar;

    public Jar(String itemType, int maxItemForJar)  {
        this.mItemType = itemType;
        this.mMaxItemForJar = maxItemForJar;
    }

    public String getMItemType() {
        return mItemType;
    }

    public int getMaxItemForJar() {
        return mMaxItemForJar;
    }

}
