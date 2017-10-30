package spaiker_grn.github.com.restaurants_menu;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrinkDescriptionFragment extends Fragment {


    ImageView mImageView;
    TextView mName;
    TextView mDescription;
    IDescriptionClass mIDescriptionClass;
    Button mButton;

    int itemNumber;

    public DrinkDescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_drink_description, null);

        final Bundle bundle = this.getArguments();
        itemNumber = bundle.getInt(DrinkActivity.EXTRA_DRINK_NO);

        mIDescriptionClass = Drink.drinks[itemNumber];

        mButton = (Button) view.findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (itemNumber != 2) {
                    final Bundle bundle = new Bundle();
                    bundle.putInt(DrinkActivity.EXTRA_DRINK_NO, itemNumber+1);
                    final DrinkDescriptionFragment fragment = new DrinkDescriptionFragment();
                    fragment.setArguments(bundle);

                    final FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, fragment);
                    transaction.commit();
                }
            }
        });

        mImageView = (ImageView) view.findViewById(R.id.photo_image_view);
        mName = (TextView) view.findViewById(R.id.name_text_view);
        mDescription = (TextView) view.findViewById(R.id.description_text_view);

        mImageView.setImageResource(mIDescriptionClass.getImageResourceId(itemNumber));
        mName.setText(mIDescriptionClass.getName(itemNumber));
        mDescription.setText(mIDescriptionClass.getDescription(itemNumber));



        return view;
    }

}
