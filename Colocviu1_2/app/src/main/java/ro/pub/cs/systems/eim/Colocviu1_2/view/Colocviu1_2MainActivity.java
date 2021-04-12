package ro.pub.cs.systems.eim.Colocviu1_2.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ro.pub.cs.systems.eim.Colocviu1_2.R;
import ro.pub.cs.systems.eim.Colocviu1_2.general.Constants;

public class Colocviu1_2MainActivity extends AppCompatActivity {
    private EditText nextTermEditText;
    private Button addButton;
    private TextView allTermsTextView;
    private Button computeButton;

    private int sum;
    private boolean isModified = false;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            String allTerms = allTermsTextView.getText().toString();

            switch (v.getId()) {
                case R.id.add_button:
                    String nextTerm = nextTermEditText.getText().toString();

                    if (!nextTerm.equals("")) {
                        if (allTerms.isEmpty()) {
                            allTermsTextView.setText(nextTerm);
                        } else {
                            allTermsTextView.setText(allTerms + " + " + nextTerm);
                        }
                    }

                    isModified = true;
                    break;
                case R.id.compute_button:
                    if (isModified) {
                        isModified = false;
                        Intent navigateToSecondaryActivity = new Intent(Colocviu1_2MainActivity.this, Colocviu1_2SecondaryActivity.class);
                        navigateToSecondaryActivity.putExtra(Constants.ALL_TERMS, allTerms);
                        startActivityForResult(navigateToSecondaryActivity, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    } else {
                        Toast.makeText(Colocviu1_2MainActivity.this, "Computed sum didn't change since last time, it is " + sum, Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_2_main);

        nextTermEditText = findViewById(R.id.next_term_edit_text);
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(buttonClickListener);
        allTermsTextView = findViewById(R.id.all_terms_text_view);
        computeButton = findViewById(R.id.compute_button);
        computeButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.SUM)) {
                sum = savedInstanceState.getInt(Constants.SUM);
            } else {
                sum = 0;
            }
        } else {
            sum = 0;
        }

        Log.d(Constants.MAIN_ACTIVITY_TAG, "Sum is " + sum);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            sum = data.getIntExtra(Constants.ADD_RESULT, 0);
            Toast.makeText(this, "Computed sum is " + sum, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(Constants.SUM, sum);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        if (savedInstanceState.containsKey(Constants.SUM)) {
            sum = savedInstanceState.getInt(Constants.SUM);
        } else {
            sum = 0;
        }
    }
}