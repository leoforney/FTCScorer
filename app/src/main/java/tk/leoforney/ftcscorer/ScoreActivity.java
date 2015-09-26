package tk.leoforney.ftcscorer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ScoreActivity extends AppCompatActivity {

    static final String LOG_TAG = ScoreActivity.class.getSimpleName();

    RadioButton beacon_not_hit;
    RadioButton beacon_hit_first;
    RadioButton beacon_hit_second;

    RadioButton autoparking_none;
    RadioButton autoparking_floor;
    RadioButton autoparking_half;
    RadioButton autoparking_low;
    RadioButton autoparking_middle;
    RadioButton autoparking_high;

    RadioButton climber_1_1;
    RadioButton climber_1_2;
    RadioButton climber_1_3;
    CheckBox climber_duringteleop;

    EditText debris_floor;
    EditText debris_low;
    EditText debris_middle;
    EditText debris_high;

    RadioButton zipliners_0;
    RadioButton zipliners_1;
    RadioButton zipliners_2;
    RadioButton zipliners_3;

    RadioButton endparking_none;
    RadioButton endparking_half;
    RadioButton endparking_low;
    RadioButton endparking_middle;
    RadioButton endparking_high;

    CheckBox end_hang;
    CheckBox end_clear;

    TextView totalScore;
    Button updateScore;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        findViewById(R.id.beacon_not_hit).requestFocus();

        beacon_not_hit = (RadioButton) findViewById(R.id.beacon_not_hit);
        beacon_hit_first = (RadioButton) findViewById(R.id.beacon_hit_first);
        beacon_hit_second = (RadioButton) findViewById(R.id.beacon_hit_second);

        autoparking_none = (RadioButton) findViewById(R.id.autoparking_none);
        autoparking_floor = (RadioButton) findViewById(R.id.autoparking_floor);
        autoparking_half = (RadioButton) findViewById(R.id.autoparking_half);
        autoparking_low = (RadioButton) findViewById(R.id.autoparking_low);
        autoparking_middle = (RadioButton) findViewById(R.id.autoparking_middle);
        autoparking_high = (RadioButton) findViewById(R.id.autoparking_high);

        climber_1_1 = (RadioButton) findViewById(R.id.climber_1_1);
        climber_1_2 = (RadioButton) findViewById(R.id.climber_1_2);
        climber_1_3 = (RadioButton) findViewById(R.id.climber_1_3);
        climber_duringteleop = (CheckBox) findViewById(R.id.climber_duringteleop);

        debris_floor = (EditText) findViewById(R.id.debris_floor);
        debris_low = (EditText) findViewById(R.id.debris_low);
        debris_middle = (EditText) findViewById(R.id.debris_middle);
        debris_high = (EditText) findViewById(R.id.debris_high);

        zipliners_0 = (RadioButton) findViewById(R.id.zipliners_0);
        zipliners_1 = (RadioButton) findViewById(R.id.zipliners_1);
        zipliners_2 = (RadioButton) findViewById(R.id.zipliners_2);
        zipliners_3 = (RadioButton) findViewById(R.id.zipliners_3);

        endparking_none = (RadioButton) findViewById(R.id.endparking_none);
        endparking_half = (RadioButton) findViewById(R.id.endparking_half);
        endparking_low = (RadioButton) findViewById(R.id.endparking_low);
        endparking_middle = (RadioButton) findViewById(R.id.endparking_middle);
        endparking_high = (RadioButton) findViewById(R.id.endparking_high);

        end_hang = (CheckBox) findViewById(R.id.end_hang);
        end_clear = (CheckBox) findViewById(R.id.end_clear);

        totalScore = (TextView) findViewById(R.id.totalScore);
        updateScore = (Button) findViewById(R.id.updateScore);


        updateScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int score = getTotal();
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(score);
                String strI = sb.toString();
                totalScore.setText(strI);
            }
        });

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    int getTotal() {

        int total = 0;

        if (beacon_hit_first.isChecked()) {
            total = total + 20;
        }
        if (beacon_hit_second.isChecked()) {
            total = total + 40;
        }

        if (autoparking_floor.isChecked()) {
            total = total + 5;
        }
        if (autoparking_half.isChecked()) {
            total = total + 5;
        }
        if (autoparking_low.isChecked()) {
            total = total + 10;
        }
        if (autoparking_middle.isChecked()) {
            total = total + 20;
        }
        if (autoparking_high.isChecked()) {
            total = total + 40;
        }

        if (climber_1_1.isChecked()) {
            if (climber_duringteleop.isChecked()) {
                total = total + 40;
            } else {
                total = total + 20;
            }

        }

        if (climber_1_2.isChecked()) {
            if (climber_duringteleop.isChecked()) {
                total = total + 80;
            } else {
                total = total + 40;
            }
        }

        if (climber_1_3.isChecked()) {
            if (climber_duringteleop.isChecked()) {
                total = total + 120;
            } else {
                total = total + 60;
            }
        }

        if (!debris_floor.getText().toString().trim().equals("")) {
            int i = Integer.parseInt(debris_floor.getText().toString().trim());
            total = total + (1 * i);
        }
        if (!debris_low.getText().toString().trim().equals("")) {
            int i = Integer.parseInt(debris_low.getText().toString().trim());
            total = total + (5 * i);
        }
        if (!debris_middle.getText().toString().trim().equals("")) {
            int i = Integer.parseInt(debris_middle.getText().toString().trim());
            total = total + (10 * i);
        }
        if (!debris_high.getText().toString().trim().equals("")) {
            int i = Integer.parseInt(debris_high.getText().toString().trim());
            total = total + (15 * i);
        }

        if (zipliners_1.isChecked()) {
            total = total + 20;
        }
        if (zipliners_2.isChecked()) {
            total = total + 40;
        }
        if (zipliners_3.isChecked()) {
            total = total + 60;
        }

        if (endparking_half.isChecked()) {
            total = total + 5;
        }
        if (endparking_low.isChecked()) {
            total = total + 10;
        }
        if (endparking_middle.isChecked()) {
            total = total + 20;
        }
        if (endparking_high.isChecked()) {
            total = total + 40;
        }

        if (end_hang.isChecked()) {
            total = total + 80;
        }

        if (end_clear.isChecked()) {
            total = total + 20;
        }

        return total;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_draw) {
            Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
