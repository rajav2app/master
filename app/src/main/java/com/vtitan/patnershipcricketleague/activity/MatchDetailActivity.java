package com.vtitan.patnershipcricketleague.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.util.BasicFunction;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.MatchViewModel;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;
import com.vtitan.patnershipcricketleague.viewmodel.TournamentViewModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchDetailActivity extends AppCompatActivity {
    private String teamA;
    private String teamB;
    private int matchNo;
    private int toss=0;
    private int bat=0;
    private int bowl=0;
    private int over=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_start_match));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.enter,R.anim.exit);
            }
        });
        final TeamViewModel mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        final MatchViewModel matchViewModel = new ViewModelProvider(this).get(MatchViewModel.class);
        final SessionManager sessionManager=new SessionManager(this);
        Intent intent=getIntent();
        if(intent!=null){
            teamA=intent.getStringExtra("TA");
            teamB=intent.getStringExtra("TB");
            matchNo=intent.getIntExtra("MNO",0);
        }
        final CircleImageView img_teamA=findViewById(R.id.img_teamA);
        final CircleImageView img_teamB=findViewById(R.id.img_teamB);
        final TextView txtTeamName=findViewById(R.id.txtTeamName);
        final TextView txtTeamNameB=findViewById(R.id.txtTeamNameB);
        final EditText etVenue=findViewById(R.id.etVenue);
        final TextInputLayout til_sch_time=findViewById(R.id.til_sch_time);
        final TextInputEditText edit_sch_time=findViewById(R.id.edit_sch_time);
        final RadioGroup rg_won_toss=findViewById(R.id.rg_won_toss);
        final RadioButton rb_teamA=findViewById(R.id.rb_teamA);
        final RadioButton rb_teamB=findViewById(R.id.rb_teamB);
        final RadioGroup rg_decided_to_bat=findViewById(R.id.rg_decided_to_bat);
        final RadioButton rb_Bat=findViewById(R.id.rb_Bat);
        final RadioButton rb_bowl=findViewById(R.id.rb_bowl);
        final Button btn_save=findViewById(R.id.btn_save);
        final Button btn_start_scoring=findViewById(R.id.btn_start_scoring);
        final RadioGroup rg_over=findViewById(R.id.rg_over);
        final RadioButton rb_over_two=findViewById(R.id.rb_over_two);
       final RadioButton rb_over_three=findViewById(R.id.rb_over_three);
       final RadioButton rb_over_four=findViewById(R.id.rb_over_four);
       final RadioButton rb_over_five=findViewById(R.id.rb_over_five);
       final RadioButton rb_over_six=findViewById(R.id.rb_over_six);

        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy h:mm a");
        final Calendar date = Calendar.getInstance();
        txtTeamName.setText(teamA);
        txtTeamNameB.setText(teamB);
        rb_teamA.setText(teamA);
        rb_teamB.setText(teamB);

        matchViewModel.getMatchDetails(matchNo,Integer.parseInt(sessionManager.getTournamentId())).observe(this, new Observer<Matches>() {
            @Override
            public void onChanged(Matches matches) {
                if(matches!=null){
                    etVenue.setText(matches.getMatch_venue());
                    edit_sch_time.setText(sdf.format(matches.getMatch_date()));
                if(matches.getMatch_won_toss()>0) {
                    if (mViewModel.getTeamName(matches.getMatch_won_toss()).equals(teamA)) {
                        rb_teamA.setChecked(true);
                    } else {
                        rb_teamB.setChecked(true);
                    }
                }
                   if(matches.getDecided_to()==1){
                        rb_Bat.setChecked(true);
                    }else{
                        rb_bowl.setChecked(true);
                    }
                    switch (matches.getMatch_total_over()){
                        case 2:
                            rb_over_two.setChecked(true);
                            break;
                        case 3:
                            rb_over_three.setChecked(true);
                            break;
                        case 4:
                            rb_over_four.setChecked(true);
                            break;
                        case 5:
                            rb_over_five.setChecked(true);
                            break;
                        case 6:
                            rb_over_six.setChecked(true);
                            break;
                    }

                }
            }
        });

        btn_save.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {

                if(etVenue.getText().toString().trim().isEmpty()){
                    etVenue.setError("Please enter the venue details.");
                }else if(edit_sch_time.getText().toString().trim().isEmpty()){
                    edit_sch_time.setError("Please select the match date and time");
                }else if(rg_won_toss.getCheckedRadioButtonId() == -1){
                    rb_teamB.setError("Please select the who one the toss.");
                }else if(rg_decided_to_bat.getCheckedRadioButtonId()==-1){
                    rb_bowl.setError("Please select the batting or bowling");
                }else if(rg_over.getCheckedRadioButtonId()==-1){
                    rb_over_six.setError("Please select the over");
                }else
                {
                 int result=matchViewModel.updateMatchDetails(etVenue.getText().toString().trim(),date.getTimeInMillis(),over,toss,bat,matchNo);
                 if(result>0){
                     Toast.makeText(MatchDetailActivity.this, "Match details updated successfully.", Toast.LENGTH_SHORT).show();
                 }else{
                     Toast.makeText(MatchDetailActivity.this, "Match details updation failed.", Toast.LENGTH_SHORT).show();
                 }
                }

            }
        });

        btn_start_scoring.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {

                if(etVenue.getText().toString().trim().isEmpty()){
                    etVenue.setError("Please enter the venue details.");
                }else if(edit_sch_time.getText().toString().trim().isEmpty()){
                    edit_sch_time.setError("Please select the match date and time");
                }else if(rg_won_toss.getCheckedRadioButtonId() == -1){
                    rb_teamB.setError("Please select the who one the toss.");
                }else if(rg_decided_to_bat.getCheckedRadioButtonId()==-1){
                    rb_bowl.setError("Please select the batting or bowling");
                }else if(rg_over.getCheckedRadioButtonId()==-1){
                    rb_over_six.setError("Please select the over");
                }else
                {
                 int result= matchViewModel.updateMatchDetails(etVenue.getText().toString().trim(),date.getTimeInMillis(),over,toss,bat,matchNo);
                 if(result>0){
                     matchViewModel.updateMatchStatus(1,matchNo,Integer.parseInt(sessionManager.getTournamentId()));
                     Intent intent=new Intent(MatchDetailActivity.this, ScoreActivity.class);
                     intent.putExtra("TA",teamA);
                     intent.putExtra("TB",teamB);
                     intent.putExtra("MNO",matchNo);
                     startActivity(intent);
                 }
                }

            }
        });


        rb_teamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toss =mViewModel.getTeamId(teamA);
            }
        });
        rb_teamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toss =mViewModel.getTeamId(teamB);
            }
        });

        rb_Bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bat =1;
            }
        });
        rb_bowl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bat=0;
            }
        });

        rb_over_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                over=2;
            }
        });

        rb_over_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                over=3;
            }
        });

        rb_over_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                over=4;
            }
        });

        rb_over_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                over=5;
            }
        });

        rb_over_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                over=6;
            }
        });

        edit_sch_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar currentDate = Calendar.getInstance();
                final Calendar tomorrowDate = Calendar.getInstance();
                tomorrowDate.add(Calendar.DATE, 1);

                final DatePickerDialog datePickerDialog = new DatePickerDialog(MatchDetailActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        date.set(year, monthOfYear, dayOfMonth);
                        final TimePickerDialog tDialog = new TimePickerDialog(MatchDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                date.set(Calendar.MINUTE, minute);
                                date.set(Calendar.SECOND, 0);
                                date.set(Calendar.MILLISECOND, 0);
                                edit_sch_time.setError(null);
                                edit_sch_time.setText(sdf.format(date.getTime()));
                            }
                        },0, 0, false);
                        tDialog.show();
                    }
                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
                datePickerDialog.getDatePicker().setMinDate(currentDate.getTimeInMillis());
                DatePicker dPicker = datePickerDialog.getDatePicker();
                //dPicker.setMinDate(tomorrowDate.getTimeInMillis());
                datePickerDialog.show();
            }
        });


    }
}