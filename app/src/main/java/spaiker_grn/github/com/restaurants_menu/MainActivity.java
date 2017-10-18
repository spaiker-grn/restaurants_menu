package spaiker_grn.github.com.restaurants_menu;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button button;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.main_list);
        button = (Button) findViewById(R.id.backendButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                final Intent intent = new Intent(MainActivity.this, BackendEditor.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {

                final Intent intent;

                switch (position) {

                    case 0:
                        intent = new Intent(MainActivity.this, DrinkCategoryActivity.class);
                        startActivity(intent);
                        break;
                }

            }

        });

    }



}
