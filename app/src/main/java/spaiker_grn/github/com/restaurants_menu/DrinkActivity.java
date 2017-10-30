package spaiker_grn.github.com.restaurants_menu;

import android.app.Fragment;
import android.content.Intent;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView mName;
    TextView mDescription;
    IDescriptionClass mIDescriptionClass;

    public static String EXTRA_DRINK_NO = "drinkNo";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        final Intent intent = getIntent();
        final int itemNumber = intent.getIntExtra(EXTRA_DRINK_NO, -1);

        final Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_DRINK_NO, itemNumber);
        final DrinkDescriptionFragment fragment = new DrinkDescriptionFragment();
        fragment.setArguments(bundle);

        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();



/*        mIDescriptionClass = Drink.drinks[itemNumber];

        mImageView = (ImageView) findViewById(R.id.photo_image_view);
        mName = (TextView) findViewById(R.id.name_text_view);
        mDescription = (TextView) findViewById(R.id.description_text_view);

        mImageView.setImageResource(mIDescriptionClass.getImageResourceId(itemNumber));
        mImageView.setContentDescription(mIDescriptionClass.getName(itemNumber));

        mName.setText(mIDescriptionClass.getName(itemNumber));

        mDescription.setText(mIDescriptionClass.getDescription(itemNumber));

    */
    }
}
