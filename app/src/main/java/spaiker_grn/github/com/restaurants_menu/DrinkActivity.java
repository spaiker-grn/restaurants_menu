package spaiker_grn.github.com.restaurants_menu;

import android.content.Intent;
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

        mIDescriptionClass = Drink.drinks[itemNumber];

        mImageView = (ImageView) findViewById(R.id.photo_image_view);
        mName = (TextView) findViewById(R.id.name_text_view);
        mDescription = (TextView) findViewById(R.id.description_text_view);

        mImageView.setImageResource(mIDescriptionClass.getImageResourceId(itemNumber));
        mImageView.setContentDescription(mIDescriptionClass.getName(itemNumber));

        mName.setText(mIDescriptionClass.getName(itemNumber));

        mDescription.setText(mIDescriptionClass.getDescription(itemNumber));

    }
}
