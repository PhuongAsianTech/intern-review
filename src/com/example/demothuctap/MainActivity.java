package com.example.demothuctap;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ArrayList<Person> mArray = new ArrayList<Person>();
	private ListViewAdapter mAdapter = null;
	private ListView lvData;
	private Button btn_delete,btn_cancel,btn_edit;
	private Dialog mdialog;
	public FragmentManager manager;
	private String[] mArrayText = { "Brunch this weekend?", "Summer BBQ",
            "Oui Oui", "Birthday Git", "Recipe to try", "Giants game",
			"Giants game" };
	private int[] mArrayImg = { R.drawable.ic_listview_item1,
			R.drawable.ic_listview_item2, R.drawable.ic_listview_item3,
			R.drawable.ic_listview_item4, R.drawable.ic_listview_item5,
			R.drawable.ic_listview_item6, R.drawable.ic_listview_item7 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvData = (ListView) findViewById(R.id.lvShowData);
		loadListView();
		mAdapter = new ListViewAdapter(MainActivity.this, mArray);
		lvData.setAdapter(mAdapter);
		lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			//	DialogShow(position);
			}
			
		});
		mAdapter.notifyDataSetChanged();
	}
	public void DialogShow(final int position){
		mdialog = new Dialog(this);
		mdialog.setContentView(R.layout.custom_dialog);
		mdialog.setTitle("This is Menu Dialog!");
		btn_edit = (Button) mdialog.findViewById(R.id.btn_edit);
		btn_cancel = (Button) mdialog.findViewById(R.id.btn_cancel);
		btn_delete = (Button) mdialog.findViewById(R.id.btn_delete);
		btn_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mdialog.cancel();
			}
		});
		btn_delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mArray.remove(position);
				mAdapter.notifyDataSetChanged();
				mdialog.cancel();
			}
		});
		btn_edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mdialog.cancel();
				Intent  intentDetail = new Intent(MainActivity.this,DetailFragment.class);
				startActivity(intentDetail);
				
				
			}
		});
		
		mdialog.show();
		
	}
	
	public void loadListView() {
		for (int i = 0; i < mArrayText.length; i++) {
			Person s = new Person();
			s.setImg(mArrayImg[i]);
			s.setText(mArrayText[i]);
			mArray.add(s);
		}
	}

}
