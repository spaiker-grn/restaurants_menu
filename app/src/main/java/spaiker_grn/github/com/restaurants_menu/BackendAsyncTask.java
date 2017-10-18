package spaiker_grn.github.com.restaurants_menu;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

class BackendAsyncTask extends AsyncTask<String, String, String> {

    private static final String METHOD_POST = "POST";
    private static final String DATA_TYPE = "application/json";


    @Override
    protected String doInBackground(final String... params) {
        try {
            final String URL = params[0];
            final String Json = params[1];
            final URL urlAddress = new URL(URL);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) urlAddress.openConnection();
            httpURLConnection.setRequestMethod(METHOD_POST);
            httpURLConnection.setRequestProperty("Content-Type", DATA_TYPE);


            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            outputStreamWriter.write(Json);
            outputStreamWriter.flush();
            outputStreamWriter.close();

            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            final StringBuilder stringBuilder = new StringBuilder();

            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            bufferedReader.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString();
        } catch (final Exception e) {
            e.getMessage();
            return "Error";
        }
    }

}
