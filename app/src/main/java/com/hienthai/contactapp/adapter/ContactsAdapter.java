package com.hienthai.contactapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.contactapp.R;
import com.hienthai.contactapp.model.ContactsModel;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<ContactsModel> contactsModelList;

    public ContactsAdapter(List<ContactsModel> contactsModelList) {
        this.contactsModelList = contactsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {

        ContactsModel contactsModel = contactsModelList.get(position);
        holder.txt_name_contacts.setText(contactsModel.getName());
        holder.txt_phone_number_contacts.setText(contactsModel.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return contactsModelList != null ? contactsModelList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name_contacts, txt_phone_number_contacts;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_name_contacts = itemView.findViewById(R.id.txt_name_contacts);
            txt_phone_number_contacts = itemView.findViewById(R.id.txt_phone_number_contacts);
        }
    }
}
