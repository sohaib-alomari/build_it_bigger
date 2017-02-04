package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private JokeReceivedListener jokeListener;

    public EndpointsAsyncTask(JokeReceivedListener listener) {
        jokeListener = listener;
    }





    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://backend-156907.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }




        try {
            return myApiService.jokeProvider().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    public void onPostExecute(String result) {
        jokeListener.onJokeRetrieved(result);

    }

    public interface JokeReceivedListener {

        void onJokeRetrieved(String joke);
    }
}