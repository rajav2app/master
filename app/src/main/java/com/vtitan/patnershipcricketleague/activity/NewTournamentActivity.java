package com.vtitan.patnershipcricketleague.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.model.Tournament;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.viewmodel.TournamentViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewTournamentActivity extends AppCompatActivity {
    private TextInputEditText etTStartDate;
    private TextInputEditText etTEndDate;
    final Calendar startCalendar= Calendar.getInstance();
    final Calendar endCalendar= Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tournament);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("  ");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final TournamentViewModel mViewModel = new ViewModelProvider(this).get(TournamentViewModel.class);

        final EditText etTName = findViewById(R.id.etTName);
        final EditText etTLocation = findViewById(R.id.etTLocation);
        etTStartDate = findViewById(R.id.etStartDate);
        etTEndDate = findViewById(R.id.etEndDate);
        final TextInputLayout tilStartDate=findViewById(R.id.tilStartDate);
        final TextInputLayout tilEndDate=findViewById(R.id.tilEndDate);
        final Button btn_CreateTournament = findViewById(R.id.btn_CreateTournament);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                startCalendar.set(Calendar.YEAR, year);
                startCalendar.set(Calendar.MONTH,month);
                startCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        DatePickerDialog.OnDateSetListener enddate =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                endCalendar.set(Calendar.YEAR, year);
                endCalendar.set(Calendar.MONTH,month);
                endCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateendLabel();
            }
        };
        tilStartDate.setEndIconOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {

                new DatePickerDialog(NewTournamentActivity.this,date,startCalendar.get(Calendar.YEAR),startCalendar.get(Calendar.MONTH),startCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tilEndDate.setEndIconOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                new DatePickerDialog(NewTournamentActivity.this,enddate,endCalendar.get(Calendar.YEAR),endCalendar.get(Calendar.MONTH),endCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        btn_CreateTournament.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {

                String tName = etTName.getText().toString().trim();
                if (tName.isEmpty()) {
                    etTName.setError("Tournament name can't be empty.");
                } else {
                    Tournament tournament = new Tournament();
                    tournament.setTournament_name(tName);
                    tournament.setMatch_schedule_state(0);
                    if (etTLocation.getText().toString().trim().isEmpty()) {
                        tournament.setTournament_location("");
                    } else {
                        tournament.setTournament_location(etTLocation.getText().toString().trim());
                    }
                    if (etTStartDate.getText().toString().trim().isEmpty()) {
                        tournament.setTournament_start_time(0);
                    } else {
                        tournament.setTournament_start_time(startCalendar.getTimeInMillis());
                    }
                    if (etTEndDate.getText().toString().isEmpty()) {
                        tournament.setTournament_end_time(0);
                    } else {
                        tournament.setTournament_end_time(endCalendar.getTimeInMillis());
                    }
                  long result=mViewModel.insertTournament(tournament);
                    if(result==-1){
                        Toast.makeText(NewTournamentActivity.this, "Tournament adition failed.", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(NewTournamentActivity.this, "Tournament added successfully.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NewTournamentActivity.this,TournamentSelectionActivity.class));
                        overridePendingTransition(R.anim.enter,R.anim.exit);
                        finish();
                    }
                }


            }


        });




    }
    private void updateLabel(){
        String myFormat="dd MMM yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        etTStartDate.setText(dateFormat.format(startCalendar.getTime()));
    }

    private void updateendLabel(){
        String myFormat="dd MMM yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        etTEndDate.setText(dateFormat.format(endCalendar.getTime()));
    }

}