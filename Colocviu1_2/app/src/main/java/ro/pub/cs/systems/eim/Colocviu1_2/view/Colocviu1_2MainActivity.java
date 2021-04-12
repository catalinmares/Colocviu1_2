package ro.pub.cs.systems.eim.Colocviu1_2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ro.pub.cs.systems.eim.Colocviu1_2.R;

public class Colocviu1_2MainActivity extends AppCompatActivity {
    private EditText nextTermEditText;
    private Button addButton;
    private TextView allTermsTextView;
    private Button computeButton;

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
                case R.id.compute_button:
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
    }
}