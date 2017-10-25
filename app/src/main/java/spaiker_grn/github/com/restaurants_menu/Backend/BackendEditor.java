package spaiker_grn.github.com.restaurants_menu.Backend;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;

import spaiker_grn.github.com.restaurants_menu.BuildConfig;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.GsonItem;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.GsonParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IItem;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IParser;
import spaiker_grn.github.com.restaurants_menu.R;
import spaiker_grn.github.com.restaurants_menu.httpClient.HttpClient;

public class BackendEditor extends AppCompatActivity {

    private static final String URL_POST = BuildConfig.BACKEND_URL + "/_ah/api/myBeanApi/v1/myBean?fields=description%2CimageSource%2Cname";

    TextView nameTextView;
    TextView descriptionTextView;
    TextView imageSourceTextView;

    EditText nameEditText;
    EditText descriptionEditText;
    EditText imageSourceEditText;

    Button getButton;
    Button setButton;
    Button delete;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend_editor);



        getButton = (Button) findViewById(R.id.getLastItemButton);
        setButton = (Button) findViewById(R.id.addNewItemButton);
        delete = (Button) findViewById(R.id.deleteButton);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        imageSourceEditText = (EditText) findViewById(R.id.imageSourceEditText);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        imageSourceTextView = (TextView) findViewById(R.id.imageSourceTextView);

        getButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        final MyResponseListener listener = new MyResponseListener();
                        new HttpClient().request(buildGet(), listener);

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                nameTextView.setText(listener.getGsonItem().getName());
                                descriptionTextView.setText(listener.getGsonItem().getDescription());
                                imageSourceTextView.setText(listener.getGsonItem().getImage());
                            }
                        });

                    }
                }).start();

            }
        });

        setButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                try {

                    final String result = new BackendAsyncTask().execute(URL_POST, JsonFromEditText()).get();
                    final GsonItem gsonItem = new Gson().fromJson(result, GsonItem.class);

                    nameTextView.setText(gsonItem.getName());
                    descriptionTextView.setText(gsonItem.getDescription());
                    imageSourceTextView.setText(gsonItem.getImage());

                } catch (final Exception e) {
                    e.getMessage();
                }

            }
        });

    }

    public String buildGet() {
        final Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.appendEncodedPath("/restaurants-menu-183209.appspot.com/_ah/api/myBeanApi/v1/myBean");
        builder.appendPath(nameEditText.getText().toString());

        return builder.build().toString();
    }

    public String JsonFromEditText() {
        final String name = nameEditText.getText().toString();
        final String description = descriptionEditText.getText().toString();
        final String imageSource = imageSourceEditText.getText().toString();

        return GetJson(name, description, imageSource);

    }

    public String GetJson(final String name, final String description, final String imageSource) {

        final JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("imageSource", imageSource);
            jsonObject.put("name", name);
            jsonObject.put("description", description);
        } catch (final JSONException pE) {
            pE.getMessage();
        }

        return jsonObject.toString();
    }

    private static class MyResponseListener implements HttpClient.ResponseListener {

        private IItem mGsonItem = new GsonItem();

        @Override
        public void onResponse(final InputStream pInputStream) {

            final IParser gsonParser = new GsonParser(pInputStream);
            try {
                mGsonItem = gsonParser.parse();
            } catch (final Exception pE) {
                pE.getMessage();
            }
        }

        IItem getGsonItem() {
            return mGsonItem;
        }

        @Override
        public void onError(final Throwable pThrowable) {

        }
    }

}





