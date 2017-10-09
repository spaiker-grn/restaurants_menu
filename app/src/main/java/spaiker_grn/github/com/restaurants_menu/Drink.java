package spaiker_grn.github.com.restaurants_menu;

final class Drink implements IDescriptionClass {

    private final String mName;
    private final String mDescription;
    private final int mImageResourceId;

    static final Drink[] drinks = {
            new Drink("Coffee", "A couple of coffee prepared from roasted coffee beans",
                    R.drawable.coffee),
            new Drink("Black tea", "Generally stronger in flavor than the less oxidized teas",
                    R.drawable.black_tea),
            new Drink("Green tea", "Made from Camellia sinensis leaves that have not undergone",
                    R.drawable.green_tea)
    };

    private Drink(final String name, final String description, final int imageResourceId) {
        this.mName = name;
        this.mDescription = description;
        this.mImageResourceId = imageResourceId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getName() {
        return mName;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public String toString() {
        return this.mName;
    }

    @Override
    public String getDescription(int item) {
        return drinks[item].getDescription();
    }

    @Override
    public String getName(int item) {
        return drinks[item].getName();
    }

    @Override
    public int getImageResourceId(int item) {
        return drinks[item].getImageResourceId();
    }

}

