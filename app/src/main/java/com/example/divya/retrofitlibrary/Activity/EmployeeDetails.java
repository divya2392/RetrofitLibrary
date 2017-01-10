package com.example.divya.retrofitlibrary.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.divya.retrofitlibrary.Common.Constant;
import com.example.divya.retrofitlibrary.R;

public class EmployeeDetails extends AppCompatActivity {

    private TextView textViewEmployeeId;
    private TextView textViewEmployeeName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        //Initializing Views
        textViewEmployeeId = (TextView) findViewById(R.id.textViewEmployeeId);
        textViewEmployeeName = (TextView) findViewById(R.id.textViewEmployeeName);

        //Getting intent
        Intent intent = getIntent();

        //Displaying values by fetching from intent
        textViewEmployeeId.setText(String.valueOf(intent.getIntExtra(Constant.KEY_EMPLOYE_ID, 0)));
        textViewEmployeeName.setText(intent.getStringExtra(Constant.KEY_EMPLOYE_NAME));

    }
}
