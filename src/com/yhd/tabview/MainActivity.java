package com.yhd.tabview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private OnClickListener[] onClicks = new OnClickListener[4];
	private TextView textView;
	private TabView tabView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.text);
		tabView = (TabView) findViewById(R.id.tab);
		onClicks[0] = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				textView.setText("this is  the  1");
				
			}
		};
		onClicks[1] = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				textView.setText("this is  the  2");
			}
		};
		onClicks[2] = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				textView.setText("this is  the  3");
			}
		};
		onClicks[3] = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				textView.setText("this is  the  4");
			}
		};
		tabView.setOnClicks(onClicks);
	}

}
