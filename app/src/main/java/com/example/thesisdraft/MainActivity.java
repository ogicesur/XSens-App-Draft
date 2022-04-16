package com.example.thesisdraft;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

import com.xsens.dot.android.sdk.events.XsensDotData;
import com.xsens.dot.android.sdk.interfaces.XsensDotDeviceCallback;
import com.xsens.dot.android.sdk.models.FilterProfileInfo;

//import com.example.thesisdraft.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity implements XsensDotDeviceCallback {

    //private ActivityMainBinding mainBinding;
    int[] item_on_the_combination_spinner = {0};
    int[] item_on_the_tasks_spinner = {1};
    Random_Task_Sorter random_task_sorter = new Random_Task_Sorter();
    Next_Previous_Buttons next_previous_buttons = new Next_Previous_Buttons();
    Spinners_Management spinners_management = new Spinners_Management();


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //mainBinding = ActivityMainBinding.DataBindingUtil.inflate(LayoutInflater, R.layout.dropdown_item, viewGroup false);
        //setContentView(mBinding.getRoot());
        setContentView(R.layout.activity_main);

        Spinner participants = (Spinner) findViewById(R.id.part_id);
        Spinner conditions = (Spinner) findViewById(R.id.combinations);
        Spinner tasks = (Spinner) findViewById(R.id.tasks);

        participant_spinner(participants, conditions,tasks);
/*
        XSensDotApplication xsensDotApplication = new XSensDotApplication();


        xsensDotApplication.initXsensDotSdk();

        TextView textView = (TextView) findViewById(R.id.information_area);
        textView.setText(xsensDotApplication.sdk());*/

        }

    public void participant_spinner(Spinner participants, Spinner conditions, Spinner tasks){
        final int[] participant_id_on_array = {0};

        String[] participants_id = getResources().getStringArray(R.array.participants);
        ArrayAdapter itemsAdapter = new ArrayAdapter(this, R.layout.dropdown_item, participants_id);
        participants.setAdapter(itemsAdapter);

        participants.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Selected Participant ID: " + participants_id[position], Toast.LENGTH_SHORT).show();
                participant_id_on_array[0] = position + 1;
                int spinnerets = Integer.parseInt((String) participants.getSelectedItem());
                combination_spinner(participant_id_on_array[0], spinnerets, conditions, tasks);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    public void combination_spinner(int participant_id_on_array, int combination_number, Spinner conditions, Spinner tasks) {
        String[] combination_id = getResources().getStringArray(spinners_management.switch_for_combinations_strings(combination_number));
        ArrayAdapter itemsAdapter_combination = new ArrayAdapter(this, R.layout.dropdown_item, combination_id);
        conditions.setAdapter(itemsAdapter_combination);
        conditions.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                item_on_the_combination_spinner[0] = position;
                //Toast.makeText(getApplicationContext(), "Selected Combination ID: " + position, Toast.LENGTH_SHORT).show();
                task_combination_spinner(participant_id_on_array, item_on_the_combination_spinner[0], conditions, tasks);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void task_combination_spinner(int participant_id, int combination_id, Spinner conditions, Spinner tasks){
        //Spinners_Management spinners_management = new Spinners_Management();
        //int task_id = spinners_management.tasks_sorting(participant_id,combination_id);
        //random_task_sorter.UniqueRandomNumbers(participant_id, combination_id);
        String[] tasks_id = random_task_sorter.UniqueRandomNumbers(participant_id, combination_id);
        //String[] tasks_id = getResources().getStringArray(random_task_sorter.three_dimensional_task_order[participant_id]][combination_id][]);
        ArrayAdapter itemsAdapter_tasks = new ArrayAdapter(this, R.layout.dropdown_item, tasks_id);
        tasks.setAdapter(itemsAdapter_tasks);

        Button next_button = (Button) findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                next_previous_buttons.next_button_method(item_on_the_tasks_spinner,item_on_the_combination_spinner, conditions, tasks );
            }
        });

        Button previous_button = (Button) findViewById(R.id.previous_button);
        previous_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                next_previous_buttons.previous_button_method(item_on_the_tasks_spinner,item_on_the_combination_spinner, conditions, tasks );
            }
        });


        tasks.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                item_on_the_tasks_spinner[0] = position;
                //Toast.makeText(getApplicationContext(), "Selected Combination ID: " + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    @Override
    public void onXsensDotConnectionChanged(String s, int i) {
    }

    @Override
    public void onXsensDotServicesDiscovered(String s, int i) {

    }

    @Override
    public void onXsensDotFirmwareVersionRead(String s, String s1) {

    }

    @Override
    public void onXsensDotTagChanged(String s, String s1) {

    }

    @Override
    public void onXsensDotBatteryChanged(String s, int i, int i1) {

    }

    @Override
    public void onXsensDotDataChanged(String s, XsensDotData xsensDotData) {

    }

    @Override
    public void onXsensDotInitDone(String s) {

    }

    @Override
    public void onXsensDotButtonClicked(String s, long l) {
        //TextView textView = (TextView) findViewById(R.id.information_area);
        //textView.setText("Ahmet");
    }

    @Override
    public void onXsensDotPowerSavingTriggered(String s) {

    }

    @Override
    public void onReadRemoteRssi(String s, int i) {

    }

    @Override
    public void onXsensDotOutputRateUpdate(String s, int i) {

    }

    @Override
    public void onXsensDotFilterProfileUpdate(String s, int i) {

    }

    @Override
    public void onXsensDotGetFilterProfileInfo(String s, ArrayList<FilterProfileInfo> arrayList) {

    }

    @Override
    public void onSyncStatusUpdate(String s, boolean b) {

    }
}