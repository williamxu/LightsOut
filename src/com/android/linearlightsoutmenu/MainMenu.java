package com.android.linearlightsoutmenu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener {

    private Button playButton;

    private int numButtons = 7;

    private static final int REQUEST_CODE_CHANGE_NUM_BUTTONS = 0;

    public static final String KEY_NUM_BUTTONS = "KEY_NUM_BUTTONS";

    public static final String LLOM = "LLOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main_menu);
	
	SharedPreferences pref = getPreferences(Activity.MODE_PRIVATE);
	numButtons = pref.getInt(KEY_NUM_BUTTONS, 7);
	
	playButton = (Button) findViewById(R.id.play_button);
	playButton.setOnClickListener(this);
	((Button) findViewById(R.id.change_num_button))
		.setOnClickListener(this);
	((Button) findViewById(R.id.about_button)).setOnClickListener(this);
	((Button) findViewById(R.id.exit_button)).setOnClickListener(this);
	updatePlayButtonText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.activity_main_menu, menu);
	return true;
    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	case R.id.play_button:
	    Log.d(LLOM, "Play");
	    Intent playIntent = new Intent(this, LightsOut.class);
	    playIntent.putExtra(KEY_NUM_BUTTONS, numButtons);
	    startActivity(playIntent);
	    break;
	case R.id.change_num_button:
	    Log.d(LLOM, "Change num buttons");
	    Intent changeIntent = new Intent(this, ChangeNumButtons.class);
	    // Add the number of buttons to the intent
	    changeIntent.putExtra(KEY_NUM_BUTTONS, numButtons);
	    startActivityForResult(changeIntent,
		    REQUEST_CODE_CHANGE_NUM_BUTTONS);
	    break;
	case R.id.about_button:
	    Log.d(LLOM, "About");
	    // make a new intent, explicit intent
	    // start activity with the intent
	    Intent aboutIntent = new Intent(this, About.class);
	    startActivity(aboutIntent);
	    break;
	case R.id.exit_button:
	    Log.d(LLOM, "Exit");
	    finish();
	    break;
	}
    }

    @Override
    protected void onPause() {
	super.onPause();
	SharedPreferences pref = getPreferences(Activity.MODE_PRIVATE);
	SharedPreferences.Editor editor = pref.edit();
	editor.putInt(KEY_NUM_BUTTONS, numButtons);
	editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	switch (requestCode) {
	case REQUEST_CODE_CHANGE_NUM_BUTTONS:
	    Log.d(LLOM, "Change buttons is done");
	    if (resultCode == Activity.RESULT_OK) {
		Log.d(LLOM, "Change buttons finished successfully.");
		numButtons = data.getIntExtra(KEY_NUM_BUTTONS, 7);
		Log.d(LLOM, "Passed back the value = " + numButtons);
		// update the play buttons text
		updatePlayButtonText();
	    } else {
		Log.d(LLOM, "User hit back or activity canceled.");
	    }
	    break;
	}
    }

    private void updatePlayButtonText() {
	Resources res = getResources();
	String newTitle = res.getString(R.string.play_format, numButtons);
	playButton.setText(newTitle);
    }
}
