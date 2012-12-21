package com.android.linearlightsoutmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChangeNumButtons extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.change_num_buttons);
	((RadioButton) findViewById(R.id.radio_3)).setOnClickListener(this);
	((RadioButton) findViewById(R.id.radio_5)).setOnClickListener(this);
	((RadioButton) findViewById(R.id.radio_7)).setOnClickListener(this);
	((RadioButton) findViewById(R.id.radio_9)).setOnClickListener(this);
	// Get the intent
	Intent passedIntent = this.getIntent();
	// Get the num buttons
	int passedNumButtons = passedIntent.getIntExtra(
		MainMenu.KEY_NUM_BUTTONS, -1);
	Log.d(MainMenu.LLOM, "Passed in num buttons = " + passedNumButtons);
	// Use the passed num buttons to set the right radio button to start
	// with
	RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
	switch (passedNumButtons) {
	case 3:
	    radioGroup.check(R.id.radio_3);
	    break;
	case 5:
	    radioGroup.check(R.id.radio_5);
	    break;
	case 7:
	    radioGroup.check(R.id.radio_7);
	    break;
	case 9:
	    radioGroup.check(R.id.radio_9);
	    break;
	}
    }

    @Override
    public void onClick(View v) {
	Intent dataReturnedIntent = new Intent();
	switch (v.getId()) {
	case R.id.radio_3:
	    Log.d(MainMenu.LLOM, "You clicked radio button 3");
	    dataReturnedIntent.putExtra(MainMenu.KEY_NUM_BUTTONS, 3);
	    break;
	case R.id.radio_5:
	    Log.d(MainMenu.LLOM, "You clicked radio button 5");
	    dataReturnedIntent.putExtra(MainMenu.KEY_NUM_BUTTONS, 5);
	    break;
	case R.id.radio_7:
	    Log.d(MainMenu.LLOM, "You clicked radio button 7");
	    dataReturnedIntent.putExtra(MainMenu.KEY_NUM_BUTTONS, 7);
	    break;
	case R.id.radio_9:
	    Log.d(MainMenu.LLOM, "You clicked radio button 9");
	    dataReturnedIntent.putExtra(MainMenu.KEY_NUM_BUTTONS, 9);
	    break;
	}
	setResult(Activity.RESULT_OK, dataReturnedIntent);
	finish();
    }
}
