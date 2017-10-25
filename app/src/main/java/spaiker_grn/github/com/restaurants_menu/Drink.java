package spaiker_grn.github.com.restaurants_menu;

class Drink implements IDescriptionClass {

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

    private String getDescription() {
        return mDescription;
    }

    private String getName() {
        return mName;
    }

    private int getImageResourceId() {
        return mImageResourceId;
    }

    public String toString() {
        return this.mName;
    }

    @Override
    public String getDescription(final int item) {
        return drinks[item].getDescription();
    }

    @Override
    public String getName(final int item) {
        return drinks[item].getName();
    }

    @Override
    public int getImageResourceId(final int item) {
        return drinks[item].getImageResourceId();
    }

}

