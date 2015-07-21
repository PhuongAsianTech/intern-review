package com.example.demothuctap;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
 * @create adapter of listview
 */
public class ListViewAdapter extends BaseAdapter {

	private ArrayList<Person> mArray = new ArrayList<Person>();
	private LayoutInflater mInflater;
	private Context mContext;
	private Dialog mdialog;
	private FragmentManager manager;
	public ListViewAdapter(Context mContext, ArrayList<Person> mArray) {
		this.mContext = mContext;
		this.mArray = mArray;
		this.mInflater = LayoutInflater.from(this.mContext);
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
				.get(position).getText());
		holder.imgcheck = (ImageView) convertView.findViewById(R.id.imgIconChech);
		holder.imgcheck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mdialog = new Dialog(mContext);
				mdialog.setContentView(R.layout.custom_dialog);
				mdialog.setTitle("This is Menu Dialog!");
				Button btn_edit = (Button) mdialog.findViewById(R.id.btn_edit);
				Button btn_cancel = (Button) mdialog.findViewById(R.id.btn_cancel);
				Button btn_delete = (Button) mdialog.findViewById(R.id.btn_delete);
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
						//mAdapter.notifyDataSetChanged();
						mdialog.cancel();
					}
				});
				btn_edit.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						mdialog.cancel();
						Intent  intentDetail = new Intent(mContext,DetailFragment.class);
						mContext.startActivity(intentDetail);
						
					}
				});
				
				mdialog.show();
			}
		});
		return convertView;
	}

	public TextView setTextView(View view, int id, String text) {
		TextView tv = (TextView) view.findViewById(id);
		tv.setText(text);
		return tv;
	}

	public ImageView setImageView(View view, int id, int img) {
		ImageView iv = (ImageView) view.findViewById(id);
		iv.setImageResource(img);
		return iv;
	}

	public static class myViewHolder {
		ImageView img,imgcheck;
		TextView txt_name, txt_class;
	}
}