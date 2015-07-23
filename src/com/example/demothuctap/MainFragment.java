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
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.demothuctap.DetailFragment.onClickSave;

/**
 * 
 * @author dnp_it
 * Create Fragment contain view Main layout fragment
 * load data listview in fragment 
 * action funtion item listview edit ,delete item listview
 * load more listview, show dialog action
 */
public class MainFragment extends Fragment {

	private ArrayList<Person> mArray = new ArrayList<Person>();
	private ListViewAdapter mAdapter = null;
	private ListView lvData;
	private Dialog mDialog;
	private FragmentManager mManager;
	public boolean isLoadMore = false;
	public static boolean sCheck = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		mManager = getActivity().getSupportFragmentManager();
		// set listview
		lvData = (ListView) view.findViewById(R.id.lvShowData);
		if (mArray.size() == 0) {
			loadListView();
		}
		mAdapter = new ListViewAdapter(getActivity().getApplicationContext(),
				mArray);
		lvData.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				ImageView imgedit = (ImageView) view
						.findViewById(R.id.imgButtonEdit);
				// action onclick item icon edit
				imgedit.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						DetailFragment detail = new DetailFragment(mArray
								.get(position));
						FragmentTransaction trans = mManager.beginTransaction();
						trans.replace(R.id.content_frame, detail);
						trans.commit();
						sCheck = true;
						// action onclick item button save
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
				// action onclick item icon delete
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
		// action scroll listview
		lvData.setOnScrollListener(new OnScrollListener() {
			private int currentState;
			private int currentFistVisiableItem;
			private int currentVisiableItemCount;
			private int currentTotalCount;

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				this.currentState = scrollState;
				this.isCompleteScroll();
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				this.currentFistVisiableItem = firstVisibleItem;
				this.currentVisiableItemCount = visibleItemCount;
				this.currentTotalCount = totalItemCount;
			}

			//
			private void isCompleteScroll() {
				if (this.currentFistVisiableItem
						+ this.currentVisiableItemCount == this.currentTotalCount
						&& this.currentState == this.SCROLL_STATE_IDLE) {
					new RemoteDataTask().execute();
				}
			}
		});

		lvData.setAdapter(mAdapter);

		return view;
	}

	/**
	 * 
	 * Show dialog alert
	 * @param mPosition input position select item listview
	 * 
	 */
	private void DialogShow(final int mPosition) {
		mDialog = new Dialog(getActivity());
		mDialog.setContentView(R.layout.custom_dialog);
		mDialog.setTitle("This is Menu Dialog!");
		TextView btnOk = (TextView) mDialog.findViewById(R.id.btnDialogOk);
		TextView btnCancel = (TextView) mDialog
				.findViewById(R.id.btnDialogCancel);
		TextView tvQuestion = (TextView) mDialog
				.findViewById(R.id.tvQuestionDialog);
		String mText = "Are you sure you want to delete <b>"
				+ mArray.get(mPosition).getTextName() + "</b> ?";
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
	private void loadListView() {
		Person mPerson1 = new Person(R.drawable.img_listview_item1,
				"Brunch this weekend?", "Description Brunch this weekend?");
		mArray.add(mPerson1);
		Person mPerson2 = new Person(R.drawable.img_listview_item2,
				"Summer BBQ", "Description Summer BBQ");
		mArray.add(mPerson2);
		Person mPerson3 = new Person(R.drawable.img_listview_item3, "Oui Oui",
				"Description Oui Oui");
		mArray.add(mPerson3);
		Person mPerson4 = new Person(R.drawable.img_listview_item4,
				"Birthday Git", "Description Birthday Git");
		mArray.add(mPerson4);
		Person mPerson5 = new Person(R.drawable.img_listview_item5,
				"Recipe to try", "Description Recipe to try");
		mArray.add(mPerson5);
		Person mPerson6 = new Person(R.drawable.img_listview_item6,
				"Giants game", "Description Giants game");
		mArray.add(mPerson6);
		Person mPerson7 = new Person(R.drawable.img_listview_item7,
				"Giants game", "Description Giants game");
		mArray.add(mPerson7);
		Person mPerson8 = new Person(R.drawable.img_listview_item7,
				"Giants game", "Description Giants game");
		mArray.add(mPerson8);
		Person mPerson9 = new Person(R.drawable.img_listview_item7,
				"Giants game", "Description Giants game");
		mArray.add(mPerson9);

	}

	/**
	 * 
	 * @author dnp_it Remote Data Task
	 * action load more item listview
	 */
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		private ProgressDialog mProgressDialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create aprogressdialog
			mProgressDialog = new ProgressDialog(getActivity());
			mProgressDialog.setTitle("Load more item listview");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
				loading();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			isLoadMore = true;
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			isLoadMore = false;
			mAdapter.notifyDataSetChanged();
			if (mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
		}
	}
	
	/**
	 * add item data load more
	 */
	private void loading() {
		Person mPerson = new Person(R.drawable.img_listview_item1,
				"Brunch this weekend?", "Description Brunch this weekend?");
		mArray.add(mPerson);
	}
}
