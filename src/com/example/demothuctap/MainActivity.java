package com.example.demothuctap;

import java.util.ArrayList;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private ActionBar mActionBar;
	private FragmentManager mManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// hide actionbar
		mActionBar = getSupportActionBar();
		mActionBar.hide();
		//
		mManager = getSupportFragmentManager();
		FragmentTransaction trans = mManager.beginTransaction();
		trans.replace(R.id.content_frame, new MainFragment());
		trans.commit();
		ImageView btnBack = (ImageView) findViewById(R.id.imgIcRowsActionBar);
		btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		if(MainFragment.check==false){
			finish();
		}else{
			FragmentTransaction trans = mManager.beginTransaction();
			trans.replace(R.id.content_frame, new MainFragment());
			trans.commit();
			MainFragment.check = !MainFragment.check;
		}
	}

	
}
