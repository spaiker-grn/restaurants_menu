package spaiker_grn.github.com.restaurants_menu;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends ListActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Drink.drinks);
        final ListView listView = getListView();
        listView.setAdapter(listAdapter);

    }


    protected void onListItemClick(final ListView l, final View v, final int position, final long id) {

        final Intent intent = new Intent(this, DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINK_NO, (int) id);
        startActivity(intent);

    }

}
