package com.example.demothuctap;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
	
	@SuppressLint("InflateParams")
	@Override
	public View getView(final int position,  View convertView, ViewGroup parent) {
		myViewHolder holder;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_list_data, parent,false);
			holder = new myViewHolder();
			convertView.setTag(holder);
		} else {
			holder = (myViewHolder) convertView.getTag();
		}
		holder.imgPerson = setImageView(convertView, R.id.imgItemPeople,
				mArray.get(position).getImg());
		holder.tvName= setTextView(convertView, R.id.tvTextNameItem, mArray
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
	private TextView setTextView(View view, int id, String text) {
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
	private ImageView setImageView(View view, int id, int img) {
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
		public ImageView imgPerson;
		public TextView tvName;
	}
}