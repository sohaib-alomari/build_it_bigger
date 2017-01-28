package stock.omari.com.showjoke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowJokeActivity extends AppCompatActivity {

    public static String intentKey="pass";

    public ShowJokeActivity(){


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayjoke);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            TextView jokeView = (TextView) findViewById(R.id.joke_view);
            jokeView.setText(extras.getString(intentKey));
        }
    }



    }

