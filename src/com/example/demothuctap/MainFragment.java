package com.example.demothuctap;

import java.util.ArrayList;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainFragment extends Fragment{
	
	private ArrayList<Person> mArray = new ArrayList<Person>();
	private ListViewAdapter mAdapter = null;
	private ListView lvData;
	private Dialog mDialog;
	private FragmentManager mManager;
	public static boolean check=false;
	private String[] mArrayName = { "Brunch this weekend?", "Summer BBQ",
			"Oui Oui", "Birthday Git", "Recipe to try", "Giants game",
			"Giants game" };
	private String[] mArrayDescription ={"Description Brunch this weekend?", "Description Summer BBQ",
			"Description Oui Oui", "Description Birthday Git", "Description Recipe to try", "Description Giants game",
			"Description Giants game"};
	private int[] mArrayImg = { R.drawable.ic_listview_item1,
			R.drawable.ic_listview_item2, R.drawable.ic_listview_item3,
			R.drawable.ic_listview_item4, R.drawable.ic_listview_item5,
			R.drawable.ic_listview_item6, R.drawable.ic_listview_item7 };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container,false);
		mManager = getActivity().getSupportFragmentManager();
		lvData = (ListView) view.findViewById(R.id.lvShowData);
		loadListView();
		mAdapter = new ListViewAdapter(getActivity().getApplicationContext(), mArray);
		lvData.setAdapter(mAdapter);
		lvData.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				ImageView imgedit = (ImageView) view
						.findViewById(R.id.imgButtonEdit);
			imgedit.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						FragmentTransaction trans = mManager.beginTransaction();
						trans.replace(R.id.content_frame, new DetailFragment(mArrayName[position],mArrayImg[position],mArrayDescription[position]));
						trans.commit();
						check=true;
					}
				});
				ImageView imgdelete = (ImageView) view
						.findViewById(R.id.imgButtonDelete);
				imgdelete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						DialogShow(position);
					}
				});
			}

		});
		return view;
	}
	public void DialogShow(final int mPosition) {
		mDialog = new Dialog(getActivity());
		mDialog.setContentView(R.layout.custom_dialog);
		mDialog.setTitle("This is Menu Dialog!");
		TextView btnOk = (TextView) mDialog.findViewById(R.id.btnDialogOk);
		TextView btnCancel = (TextView) mDialog
				.findViewById(R.id.btnDialogCancel);
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDialog.cancel();
			}
		});
		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mArray.remove(mPosition);
				mAdapter.notifyDataSetChanged();
				mDialog.cancel();
			}
		});

		mDialog.show();

	}

	public void loadListView() {
		for (int i = 0; i < mArrayName.length; i++) {
			Person s = new Person();
			s.setImg(mArrayImg[i]);
			s.setTextName(mArrayName[i]);
			s.setTextDescrition(mArrayDescription[i]);
			mArray.add(s);
		}
	}

	
	
}
