
// Mood Journal (Saved Moods)
package com.microsoft.projectoxford.emotionsample;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import com.microsoft.projectoxford.emotionsample.helper.SharedPreference;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import android.view.MenuItem;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import android.widget.Toast;
import android.graphics.drawable.GradientDrawable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.util.List;
import java.io.FileWriter;
import java.io.BufferedWriter;
import android.text.method.ScrollingMovementMethod;
import java.io.DataInputStream;

public class saved extends ActionBarActivity {
    private TextView textTxt;
    private String text;
    Activity context = this;
    private SharedPreference sharedPreference;
    static final int READ_BLOCK_SIZE = 100;
    private TextView text1;
    private BufferedWriter writer;
    private BufferedReader reader;
    private Button buttonDel;
    private String hold;
    ArrayList<String> list = TrackMood.list;
    List<String> Names = new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        getSupportActionBar().setTitle("Emoji artwork provided by EmojiOne");
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout1);
        ll.setOrientation(LinearLayout.VERTICAL);
        buttonDel = (Button)findViewById(R.id.button);
        sharedPreference = new SharedPreference();
        text = sharedPreference.getValue(context);
        FileInputStream fis;
        final StringBuffer storedString = new StringBuffer();
        
        try {
            
            fis = openFileInput("mytextfile.txt");
            final DataInputStream dataIO = new DataInputStream(fis);
            final DataInputStream dataIOO = new DataInputStream(fis);
            String strLine = null;
            while ((strLine = dataIO.readLine()) != null) {
                final TextView textView1 = new TextView(this);
                textView1.setTextColor(this.getResources().getColor(R.color.background_material_light));
                GradientDrawable gd = new GradientDrawable();
                gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
                gd.setCornerRadius(5);
                gd.setStroke(1, 0xFF000000);
                ll.addView(textView1);
                String mood = strLine;
                int index = mood.lastIndexOf(':')+1;
                int indexEnd = mood.indexOf(" ",index+1);
                String result="";
                int i = index;
                
                while (i<=indexEnd){
                    result = result+mood.charAt(i);
                    if(result.equals(" Happy")){
                        textView1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mhappy,0,0,0);
                    }
                    if(result.equals(" Sad")){
                        textView1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.msad,0,0,0);
                    }
                    if(result.equals(" Surprised")){
                        textView1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mshocked,0,0,0);
                    }
                    if(result.equals(" Fearful")){
                        textView1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mfearful,0,0,0);
                    }
                    if(result.equals(" Disgusted")){
                        textView1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mdisquest,0,0,0);
                    }
                    if(result.equals(" Neutral")){
                        textView1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mneutral,0,0,0);
                    }
                    if(result.equals(" Contempt")){
                        textView1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mcontempt,0,0,0);
                    }
                    if(result.equals(" Angry")){
                        textView1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.msad,0,0,0);
                    }
                    i++;
                }
                textView1.setText(strLine);
            }
            dataIO.close();
            fis.close();
        } catch (Exception e) {
        }
        
        // Clears all Saved Moods
        buttonDel.setText("Clear All");
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popup = new PopupMenu(saved.this, buttonDel);
                popup.getMenuInflater().inflate(R.menu.pop_up, popup.getMenu());

                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(saved.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        hold = item.getTitle().toString();
                if(hold.equals("Yes")) {
                    try {
                        FileOutputStream fileout = openFileOutput(("mytextfile.txt"), MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(context, saved.class);
                    // Start next activity
                    startActivity(intent);
                }

                        return true;
                    }
                });

            }
        });
       
    }
    }
