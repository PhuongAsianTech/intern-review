package com.example.demothuctap;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFragment extends Fragment{
	private String mName,mDescription;
	private int mImg;
	private FragmentManager mManager;
	private int position;
	private ArrayList<Person> arr;
	private EditText edtName,edtDescription;
	public DetailFragment(String name,int img,String description) {
		this.mName = name;
		this.mImg = img;
		this.mDescription = description;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.fragment_detail, container,false);
		RelativeLayout rlImg = (RelativeLayout) mView.findViewById(R.id.rlImagePersion);
		rlImg.setBackgroundResource(mImg);
		TextView tvNamePersion = (TextView) mView.findViewById(R.id.tvNamePersion);
		tvNamePersion.setText(mName);
		edtName = (EditText) mView.findViewById(R.id.edtUserName);
		edtName.setText(mName);
	    edtDescription = (EditText) mView.findViewById(R.id.edtDescription);
		edtDescription.setText(mDescription);
		mManager = getActivity().getSupportFragmentManager();
		TextView btnSave = (TextView) mView.findViewById(R.id.btnSave);
		TextView btnCancel = (TextView) mView.findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction trans = mManager.beginTransaction();
				trans.replace(R.id.content_frame, new MainFragment());
				trans.commit();
			}
		});
		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			//	Person s = new Person();
			//	mName = edtName.getText();
			}
		});
		return mView;
	}
}
