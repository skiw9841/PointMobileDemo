package com.calyx.pointmobiledemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.calyx.pointmobiledemo.R;
import com.calyx.pointmobiledemo.api.model.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PipelineAdapter extends RecyclerView.Adapter<PipelineAdapter.PersonHolder>
{
	private ArrayList<User> items = new ArrayList<>();
	private OnItemClickListener listener;

	@Override
	public PipelineAdapter.PersonHolder onCreateViewHolder(ViewGroup parent, int i) {

		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pipeline, parent, false);

		return new PipelineAdapter.PersonHolder(itemView);
	}

	@Override
	public void onBindViewHolder(PipelineAdapter.PersonHolder personHolder, int position) {

		User user = items.get(personHolder.getAdapterPosition());
		personHolder.tvItemName.setText(user.getFullName());
		personHolder.tvItemPhone.setText(user.ssn);
		personHolder.tvItemMail.setText(user.email);
		personHolder.tvItemCell.setText(user.cell);
		personHolder.tvitemGender.setText(user.gender);

		personHolder.itemView.setOnClickListener(view
				-> listener.onClick(user));
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public void updateView(User user) {
		int pos = getPosition(user);
		if(pos == RecyclerView.NO_POSITION) return;
		items.set(pos, user);
		notifyItemChanged(pos);
	}

	private int getPosition(User user) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getFullName().equals(user.getFullName())) {
				return i;
			}
		}
		return RecyclerView.NO_POSITION;
	}

	public void setItems(ArrayList<User> items) {
		this.items = items;
		notifyDataSetChanged();
	}

	public void setClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	public interface OnItemClickListener {
		void onClick(User user);
	}


	class PersonHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.pipeline_name)
		TextView tvItemName;

		@BindView(R.id.status)
		TextView tvItemPhone;

		@BindView(R.id.lock_exp)
		TextView tvItemMail;

		@BindView(R.id.loan)
		TextView tvItemCell;

		@BindView(R.id.property)
		TextView tvitemGender;

		PersonHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

}