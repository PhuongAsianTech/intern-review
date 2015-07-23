package com.example.demothuctap;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author dnp_it Create DetailFragment
 * show information persion 
 * action item save and cancel in fragment
 */
public class DetailFragment extends Fragment {
	private FragmentManager mManager;
	private EditText mEdtName, mEdtDescription;
	private Person mPerson;
	//create interface of funtion save
	interface onClickSave {
		void onCLick(Person person);
	}

	public onClickSave mSave;

	public void setOnClickSave(onClickSave save) {
		this.mSave = save;
	}

	public DetailFragment(Person mPerson) {
		this.mPerson = mPerson;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.fragment_detail, container,
				false);
		RelativeLayout rlImg = (RelativeLayout) mView
				.findViewById(R.id.rlImagePersion);
		rlImg.setBackgroundResource(mPerson.getImg());
		TextView tvNamePersion = (TextView) mView
				.findViewById(R.id.tvNamePersion);
		tvNamePersion.setText(mPerson.getTextName().toString());
		mEdtName = (EditText) mView.findViewById(R.id.edtUserName);
		mEdtDescription = (EditText) mView.findViewById(R.id.edtDescription);
		mEdtName.setText(mPerson.getTextName().toString());
		mEdtDescription.setText(mPerson.getTextDescrition().toString());
		mManager = getActivity().getSupportFragmentManager();
		TextView btnSave = (TextView) mView.findViewById(R.id.btnSave);
		TextView btnCancel = (TextView) mView.findViewById(R.id.btnCancel);
		//action cancel button
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction trans = mManager.beginTransaction();
				trans.replace(R.id.content_frame, MainActivity.sFragmentMain);
				trans.commit();
			}
		});
		//action save button
		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mSave != null) {
					mPerson.setTextName(mEdtName.getText().toString());
					mPerson.setTextDescrition(mEdtDescription.getText()
							.toString());
					mSave.onCLick(mPerson);
					FragmentTransaction trans = mManager.beginTransaction();
					trans.replace(R.id.content_frame, MainActivity.sFragmentMain);
					trans.commit();
				}
			}
		});
		return mView;
	}
}
