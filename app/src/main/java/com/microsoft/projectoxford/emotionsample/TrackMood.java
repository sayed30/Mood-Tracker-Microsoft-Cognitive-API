package com.microsoft.projectoxford.emotionsample;


import android.opengl.EGLDisplay;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.*;
import android.content.Context;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import android.app.Activity;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import android.view.inputmethod.InputMethodManager;
import com.microsoft.projectoxford.emotionsample.helper.SharedPreference;
import java.io.BufferedWriter;
import java.util.Date;
import java.text.DateFormat;
public class TrackMood extends ActionBarActivity {
    private SharedPreference sharedPreference;
    private Button saveButton;
    private Button goSaved;
    private String text;
    final Activity context = this;
    private BufferedWriter buff = null;
    static ArrayList<String> list = new ArrayList<String>();
    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    //String MY_FILE_NAME = "mytextfile.txt";
    // Create a new output file stream
   // FileOutputStream fileos = openFileOutput(MY_FILE_NAME, Context.MODE_PRIVATE);
    // Create a new file input stream.
   // FileInputStream fileis = openFileInput(MY_FILE_NAME);

    public TrackMood() throws FileNotFoundException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        final TextView tv1 = (TextView) findViewById(R.id.textMood);
        tv1.setText(RecognizeActivity.stringMax);
        saveButton = (Button) findViewById(R.id.buttonSave);
        goSaved = (Button) findViewById(R.id.goSave);
        sharedPreference = new SharedPreference();
        final EditText ed = (EditText) findViewById(R.id.editText3); //possiable can't append to a fianl String
        //text = ed.getText().toString();
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                text = ed.getText().toString();
                // Hides the soft keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ed.getWindowToken(), 0);

                // Save the text in SharedPreference
                sharedPreference.save(context, text);
                Toast.makeText(context,
                        getResources().getString(R.string.saved),
                        Toast.LENGTH_LONG).show();
               list.add(text);
                if(!(text.equals(""))) {
                    try {
                        FileOutputStream fileout = openFileOutput(("mytextfile.txt"), MODE_APPEND);
                        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);

                        outputWriter.append("On " + " " + currentDateTimeString + " You felt: " +
                                RecognizeActivity.stringMax + " because " + text);
                        outputWriter.append("\n\r");
                        outputWriter.close();
                        // fileout.close();

                        //display file saved message
                        Toast.makeText(getBaseContext(), "File saved successfully!",
                                Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        goSaved.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, saved.class);
                // Start next activity
                startActivity(intent);
            }
        });


    }

}