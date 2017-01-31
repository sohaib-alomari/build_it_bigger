package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.JokesLibrary;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

import stock.omari.com.showjoke.ShowJokeActivity;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    JokesLibrary joker= new JokesLibrary();
    String jokeText=joker.getJoke();

    public void tellJoke(View view) {


        new EndpointsAsyncTask(new JokeRetrievalHandler()).execute();
    }



    private class JokeRetrievalHandler implements EndpointsAsyncTask.JokeReceivedListener {

        @Override
        public void onJokeRetrieved(String joke) {
            Intent intent = new Intent(MainActivity.this, ShowJokeActivity.class);
            intent.putExtra(ShowJokeActivity.intentKey, joke);
            startActivity(intent);
        }
    }


}


