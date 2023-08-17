package com.example.outlab9;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Exam#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Exam extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView r1;
    EditText e1, e2;
    String s1, s2, s3, s4;
    MyCoreDatabase3 myData;
    Button dateButton, timeButton, saveButton, loadButton;
    ArrayList<String> d1 = new ArrayList<String>();
    ArrayList<String> d2 = new ArrayList<String>();
    ArrayList<String> d3 = new ArrayList<String>();
    ArrayList<String> d4 = new ArrayList<String>();

    MyOwnAdapter ad;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Exam() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Exam.
     */
    // TODO: Rename and change types and number of parameters
    public static Exam newInstance(String param1, String param2) {
        Exam fragment = new Exam();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exam, container, false);
        r1 = (RecyclerView) view.findViewById(R.id.myRecycler3);
        e1 = (EditText) view.findViewById(R.id.edit_exam1);
        e2 = (EditText) view.findViewById(R.id.edit_exam2);
        myData = new MyCoreDatabase3(getActivity());
        dateButton = (Button) view.findViewById(R.id.datebutton3);
        timeButton = (Button) view.findViewById(R.id.timebutton3);
        saveButton = (Button) view.findViewById(R.id.save3);
        loadButton = (Button) view.findViewById(R.id.load3);
        s1 = e1.getText().toString();
        s2 = e2.getText().toString();
        s3 = " ";
        s4 = " ";
        handleLoadButton();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSaveButton(e1.getText().toString(), e2.getText().toString(), s3, s4);
            }
        });
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLoadButton();
            }
        });
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });

        return view;
    }

    private void handleLoadButton() {
        d1.clear();
        d2.clear();
        d3.clear();
        d4.clear();
        myData.getAll(d1, d2, d3, d4);
        ad = new MyOwnAdapter(getActivity(), d1, d2, d3, d4, myData);
        r1.setAdapter(ad);
        r1.setLayoutManager(new LinearLayoutManager(getActivity()));
        MainActivity ma1 = (MainActivity) getActivity();
        if (ma1 != null) {
            ma1.f3(d3);
        }
    }

    private void handleSaveButton(String s1, String s2, String s3, String s4) {
        Toast.makeText(getActivity(), "Successfully created", Toast.LENGTH_LONG).show();
        myData.insertData(s1, s2, s3, s4);
        handleLoadButton();
    }


    private void handleDateButton() {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                s3 = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }


    private void handleTimeButton() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(getActivity());

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i("Study_Plan", "onTimeSet: " + hour + minute);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                s4 = DateFormat.format("h:mm a", calendar1).toString();

            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();
    }
}
