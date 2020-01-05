package edu.tomerbu.lec4part2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.AlarmClock;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etYoutubeQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etYoutubeQuery = findViewById(R.id.edit_youtube);

        findViewById(R.id.button_search_youtube).setOnClickListener(v -> {
            String query = etYoutubeQuery.getText().toString();
            Uri uri = Uri.parse("https://www.youtube.com/results?search_query=" + query);


            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        findViewById(R.id.button_dial).setOnClickListener(v -> {

            //The first parameter of implicit "action": a string that describes "what we want to do"

            //URI: a general address:
            //tel:035465478646
            //file:pathtofile
            //geo:
            //URL: internet address - http://

            Uri telUri = Uri.parse("tel:0542045516");
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, telUri);

            startActivity(dialIntent);
        });

        findViewById(R.id.button_home_page).setOnClickListener(v -> {
            Uri webUri = Uri.parse("https://developer.android.com/guide");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
            startActivity(webIntent);
        });

        findViewById(R.id.button_map).setOnClickListener(v -> {
            Uri uri = Uri.parse("geo:0,0?q=34.99,-106.61(Hackeru)"); //coordinates: latitude, longitude

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            //show the chooser
            Intent chooser = Intent.createChooser(intent, "בעזרת איזו אפליקציה לפתוח?");

            startActivity(chooser);
        });

        findViewById(R.id.button_waze).setOnClickListener(v -> {
            String url = "https://waze.com/ul?q=Hawaii";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Intent openMarket = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
                startActivity(openMarket);
            }
        });

        findViewById(R.id.button_alarm).setOnClickListener(v -> {
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);

            intent.putExtra(AlarmClock.EXTRA_HOUR, 9).
                    putExtra(AlarmClock.EXTRA_MINUTES, 0).
                    putExtra(AlarmClock.EXTRA_MESSAGE, "Apps Dev Class");

            //test that there is at least one app in the phone that can handle the intent:
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent); //todo: if there is an activity in the phone that can handle the intent
            } else {
                Toast.makeText(this, "No Alarm clock", Toast.LENGTH_SHORT).show();
            }
        });
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
}
