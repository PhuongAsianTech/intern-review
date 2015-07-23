package com.example.demothuctap;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
/**
 * 
 * @author dnp_it
 * Demo show data fragment and action item funtion in listview
 *
 */
public class MainActivity extends ActionBarActivity {
	private ActionBar mActionBar;
	private FragmentManager mManager;
	public static MainFragment sFragmentMain ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//hide actionbar
		mActionBar = getSupportActionBar();
		mActionBar.hide();
		sFragmentMain = new MainFragment();
		//show fragment main 
		mManager = getSupportFragmentManager();
		FragmentTransaction trans = mManager.beginTransaction();
		trans.replace(R.id.content_frame, sFragmentMain);
		trans.commit();
		ImageView btnBack = (ImageView) findViewById(R.id.imgIcRowsActionBar);
		btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}
	
	//set action item menu back 
	@Override
	public void onBackPressed() {
		if(MainFragment.sCheck==false){
			finish();
		}else{
			FragmentTransaction trans = mManager.beginTransaction();
			trans.replace(R.id.content_frame, sFragmentMain);
			trans.commit();
			MainFragment.sCheck = !MainFragment.sCheck;
		}
	}
}
