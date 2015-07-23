package com.example.demothuctap;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.provider.Telephony.Sms.Conversations;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author dnp_it
 * @create this class set adapter of listview
 * The class static myViewHolder create itemlistview
 * The methods setTextView(),setImageView() setting item listview 
 * 
 */
public class ListViewAdapter extends BaseAdapter {

	private ArrayList<Person> mArray = new ArrayList<Person>();
	private LayoutInflater mInflater;
	private Context mContext;
	private Dialog mdialog;
	private FragmentManager manager;
	private ArrayList<Person> myArray = null;
	/**
	 * 
	 * @param mContext
	 * @param mArray the ArrayList data item of listview
	 * 
	 */
	public ListViewAdapter(Context mContext, ArrayList<Person> mArray) {
		this.mContext = mContext;
		this.mArray = mArray;
		this.mInflater = LayoutInflater.from(this.mContext);
		myArray = new ArrayList<Person>();
		myArray.addAll(mArray);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mArray.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mArray.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public View getView(final int position,  View convertView, ViewGroup parent) {
		myViewHolder holder;
		mInflater = (LayoutInflater) mContext
				.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_list_data, null);
			holder = new myViewHolder();
			convertView.setTag(holder);
		} else {
			holder = (myViewHolder) convertView.getTag();
		}
		holder.img = setImageView(convertView, R.id.imgItemPeople,
				mArray.get(position).getImg());
		holder.txt_name = setTextView(convertView, R.id.tvTextNameItem, mArray
				.get(position).getTextName());
		return convertView;
	}
	/**
	 * set TextView item on listview
	 * @param view
	 * @param id the id of textview item
	 * @param text the string textview item
	 * @return the textview sitting
	 */
	public TextView setTextView(View view, int id, String text) {
		TextView tv = (TextView) view.findViewById(id);
		tv.setText(text);
		return tv;
	}
	/**
	 * set ImageView item on listview
	 * @param view
	 * @param id the id of imageview item
	 * @param img the id of image item sitting
	 * @return the imageview sitting
	 */
	public ImageView setImageView(View view, int id, int img) {
		ImageView iv = (ImageView) view.findViewById(id);
		iv.setImageResource(img);
		return iv;
	}
	/**
	 * 
	 * @author dnp_it
	 *	this class set holder data listview
	 */
	public static class myViewHolder {
		ImageView img,imgcheck;
		TextView txt_name, txt_class;
	}
}