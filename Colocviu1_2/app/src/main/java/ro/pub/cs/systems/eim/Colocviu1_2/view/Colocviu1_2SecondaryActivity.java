package ro.pub.cs.systems.eim.Colocviu1_2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

import ro.pub.cs.systems.eim.Colocviu1_2.general.Constants;

public class Colocviu1_2SecondaryActivity extends AppCompatActivity {
    private String allTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(Constants.ALL_TERMS)) {
            allTerms = intent.getStringExtra(Constants.ALL_TERMS);

            if (allTerms != null) {
                String[] terms = allTerms.split(" \\+ ");

                int result = 0;

                for (String term : terms) {
                    result += Integer.parseInt(term);
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra(Constants.ADD_RESULT, result);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            } else {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        } else {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
    }
}