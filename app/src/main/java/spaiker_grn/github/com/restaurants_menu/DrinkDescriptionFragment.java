package spaiker_grn.github.com.restaurants_menu;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public DrinkDescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drink_description, null);

        int itemNumber =1;

        mImageView = (ImageView) view.findViewById(R.id.photo_image_view);
        mName = (TextView) view.findViewById(R.id.name_text_view);
        mDescription = (TextView) view.findViewById(R.id.description_text_view);

        mImageView.setImageResource(mIDescriptionClass.getImageResourceId(itemNumber));
        mName.setText(mIDescriptionClass.getName(itemNumber));
        mDescription.setText(mIDescriptionClass.getDescription(itemNumber));

        return view;
    }

}
