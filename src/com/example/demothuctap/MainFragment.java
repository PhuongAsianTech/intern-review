package com.example.demothuctap;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.demothuctap.DetailFragment.onClickSave;

public class MainFragment extends Fragment {

	private ArrayList<Person> mArray = new ArrayList<Person>();
	private ListViewAdapter mAdapter = null;
	private ListView lvData;
	private Dialog mDialog;
	private FragmentManager mManager;
	public static boolean check = false;
	private String[] mArrayName = { "Brunch this weekend?", "Summer BBQ",
			"Oui Oui", "Birthday Git", "Recipe to try", "Giants game",
			"Giants game" };
	private String[] mArrayDescription = { "Description Brunch this weekend?",
			"Description Summer BBQ", "Description Oui Oui",
			"Description Birthday Git", "Description Recipe to try",
			"Description Giants game", "Description Giants game" };
	private int[] mArrayImg = { R.drawable.ic_listview_item1,
			R.drawable.ic_listview_item2, R.drawable.ic_listview_item3,
			R.drawable.ic_listview_item4, R.drawable.ic_listview_item5,
			R.drawable.ic_listview_item6, R.drawable.ic_listview_item7 };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		mManager = getActivity().getSupportFragmentManager();
		lvData = (ListView) view.findViewById(R.id.lvShowData);
		if (mArray.size() == 0) {

			loadListView();

		}
		mAdapter = new ListViewAdapter(getActivity().getApplicationContext(),
				mArray);
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
						DetailFragment detail = new DetailFragment(mArray
								.get(position));
						FragmentTransaction trans = mManager.beginTransaction();
						trans.replace(R.id.content_frame, detail);
						trans.commit();
						check = true;

						detail.setOnClickSave(new onClickSave() {

							@Override
							public void onCLick(Person person) {
								mArray.get(position).setTextName(
										person.getTextName());
								mArray.get(position).setTextDescrition(
										person.getTextDescrition());
								mAdapter.notifyDataSetChanged();
							}
						});

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
	/**
	 * Show dialog alert
	 * @param mPosition input position select item listview
	 */
	public void DialogShow(final int mPosition) {
		mDialog = new Dialog(getActivity());
		mDialog.setContentView(R.layout.custom_dialog);
		mDialog.setTitle("This is Menu Dialog!");
		TextView btnOk = (TextView) mDialog.findViewById(R.id.btnDialogOk);
		TextView btnCancel = (TextView) mDialog
				.findViewById(R.id.btnDialogCancel);
		TextView tvQuestion = (TextView) mDialog
				.findViewById(R.id.tvQuestionDialog);
		String mText = "Are you sure you want to delete <b>"
				+ mArrayName[mPosition] + "</b> ?";
		tvQuestion.setText(Html.fromHtml(mText));
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
	/**
	 * load data in listview
	 */
	public void loadListView() {
		Person mPerson1 = new Person(R.drawable.ic_listview_item1,
				"Brunch this weekend?", "Description Brunch this weekend?");
		mArray.add(mPerson1);
		Person mPerson2 = new Person(R.drawable.ic_listview_item2,
				"Summer BBQ", "Description Summer BBQ");
		mArray.add(mPerson2);
		Person mPerson3 = new Person(R.drawable.ic_listview_item3, "Oui Oui",
				"Description Oui Oui");
		mArray.add(mPerson3);
		Person mPerson4 = new Person(R.drawable.ic_listview_item4,
				"Birthday Git", "Description Birthday Git");
		mArray.add(mPerson4);
		Person mPerson5 = new Person(R.drawable.ic_listview_item5,
				"Recipe to try", "Description Recipe to try");
		mArray.add(mPerson5);
		Person mPerson6 = new Person(R.drawable.ic_listview_item6,
				"Giants game", "Description Giants game");
		mArray.add(mPerson6);
		Person mPerson7 = new Person(R.drawable.ic_listview_item7,
				"Giants game", "Description Giants game");
		mArray.add(mPerson7);

	}
	
	/**
	 * Set load more list view
	 */
	public void LoadMoreListView(){
		
	}
	/**
	 * 
	 * @author dnp_it
	 * Remote Data Task
	 */
	private class RemoteDataTask extends AsyncTask<Void, Void, Void>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//Create aprogressdialog
			ProgressDialog mProgressDialog = new ProgressDialog(getActivity().getApplicationContext());
			mProgressDialog.setTitle("Load More Item ");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			//show progressdialog
			mProgressDialog.show();
			
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			//location listview last item
			int mposition = lvData.getLastVisiblePosition();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected Void doInBackground(Void... params) {
			return null;
		}
		
	}
}
