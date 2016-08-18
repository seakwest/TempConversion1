package com.evaristo.farhenheittocelsius;


import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;



import java.text.NumberFormat;

//upload me a
// the quic bonw fox jumped over the lzy dog

public class MainActivity extends Activity implements OnEditorActionListener {

    private EditText farhenheitEditText;
    private TextView celciusTextView;

    private String farhenheitString;

    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        farhenheitEditText = (EditText) findViewById(R.id.fahrenheitEditText);
        celciusTextView = (TextView) findViewById(R.id.celsiusTextView);

        farhenheitEditText.setOnEditorActionListener(this);

        savedValues = getSharedPreferences("SavedValues",MODE_PRIVATE);


    }

    //on pause to save data
    @Override
    public void onPause() {
        Editor editor = savedValues.edit();
        editor.putString("farhenheitString", farhenheitString);
        editor.commit();

        super.onPause();
    }

    //on resume to continue with saved data
    @Override
    public void onResume() {
        super.onResume();

        farhenheitString = savedValues.getString("farhenheitString", "");
        farhenheitEditText.setText(farhenheitString);
        calculateAndDisplay();
    }

    //calculate and display method for temp conversion
    private void calculateAndDisplay() {
        // get farhenheit
        farhenheitString = farhenheitEditText.getText().toString();
        float farhenheit;
        if (farhenheitString.equals("")) {
            farhenheit = 0;
        }
        else {
            farhenheit = Float.parseFloat(farhenheitString);
        }

        //calculate celsius from fahrenheit
        float celsius = farhenheit - 32 * 5 / 9;

        //display calculation
        NumberFormat decimal = NumberFormat.getInstance();
        celciusTextView.setText(decimal.format(celsius));


    }



    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        calculateAndDisplay();

        // hide soft keyboard
        return false;
    }




}
