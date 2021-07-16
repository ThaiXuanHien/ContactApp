package com.hienthai.contactapp.adapter;


import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.contactapp.R;
import com.hienthai.contactapp.model.CallsModel;

import java.util.List;

public class CallsAdapter extends RecyclerView.Adapter<CallsAdapter.ViewHolder> {

    private List<CallsModel> callsModelList;

    public CallsAdapter(List<CallsModel> callsModelList) {
        this.callsModelList = callsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calls, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CallsAdapter.ViewHolder holder, int position) {

        CallsModel callsModel = callsModelList.get(position);
        holder.txt_name_calls.setText(callsModel.getName());
        holder.txt_phone_number_calls.setText(callsModel.getPhoneNumber());
        holder.txt_duration_calls.setText(callsModel.getDuration());
        holder.txt_date_calls.setText(callsModel.getDate());

    }

    @Override
    public int getItemCount() {
        return callsModelList != null ? callsModelList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name_calls, txt_phone_number_calls, txt_duration_calls, txt_date_calls;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_name_calls = itemView.findViewById(R.id.txt_name_calls);
            txt_phone_number_calls = itemView.findViewById(R.id.txt_phone_number_calls);
            txt_duration_calls = itemView.findViewById(R.id.txt_duration_calls);
            txt_date_calls = itemView.findViewById(R.id.txt_date_calls);
        }
    }
}
