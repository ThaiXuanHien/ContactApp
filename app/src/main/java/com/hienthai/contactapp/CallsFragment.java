package com.hienthai.contactapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CallLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hienthai.contactapp.adapter.CallsAdapter;
import com.hienthai.contactapp.model.CallsModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CallsFragment extends Fragment {

    private RecyclerView rcv_calls;

    public CallsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calls, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcv_calls = view.findViewById(R.id.rcv_calls);
        rcv_calls.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv_calls.setAdapter(new CallsAdapter(getListCallLogs()));
    }

    private List<CallsModel> getListCallLogs() {

        List<CallsModel> callsModelList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        }

        Cursor cursor = getContext().getContentResolver()
                .query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");

        int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int phoneNumber = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);

        cursor.moveToFirst();

        while (cursor.moveToNext()) {

            Date dateCall = new Date(Long.parseLong(cursor.getString(date)));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            callsModelList.add(new CallsModel(cursor.getString(name),
                    cursor.getString(phoneNumber),
                    formatSecondDateTime(Integer.parseInt(cursor.getString(duration))),
                    sdf.format(dateCall)));
        }

        return callsModelList;
    }

    public static String formatSecondDateTime(int second) {
        if (second <= 0) return "";
        int h = second / 3600;
        int m = second % 3600 / 60;
        int s = second % 60; // Less than 60 is the second, enough 60 is the minute
        if (h <= 0) {
            return m + "m : " + s + "s";
        } else if (m <= 0) {
            return s + "s";
        } else {
            return h + "h : " + m + "m : " + s + "s";
        }
    }
}