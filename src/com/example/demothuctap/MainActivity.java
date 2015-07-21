package com.example.demothuctap;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ArrayList<Person> mArray = new ArrayList<Person>();
	private ListViewAdapter mAdapter = null;
	private ListView lvData;
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
