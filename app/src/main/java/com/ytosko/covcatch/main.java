package com.ytosko.covcatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.joda.time.Days;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class main extends AppCompatActivity {
    TextView name;
    ImageView dp;
    ImageView edit, r;
    FloatingActionButton ab;
    private ProgressDialog pDialog;
    ProgressDialog pDialog2;
    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvAffectedCountries, w;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;
    Switch sw, sm;
    ImageView nav;
    ImageView cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pDialog = new ProgressDialog(main.this);
        pDialog.setTitle("Running");
        pDialog.setMessage("Signing out");

        pDialog2 = new ProgressDialog(main.this);
        pDialog2.setTitle("Please wait");
        pDialog2.setMessage("Refreshing");

        ActionBar a = getSupportActionBar();
        a.hide();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        name = findViewById(R.id.name);
        dp = findViewById(R.id.img);
        ab = findViewById(R.id.fab);
        r = findViewById(R.id.imu);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);

        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.scrollStats);
        pieChart = findViewById(R.id.piechart);
        sw = findViewById(R.id.guard);
        sm = findViewById(R.id.crud);
        nav = findViewById(R.id.navigate);
        w = findViewById(R.id.warn);

        cont = findViewById(R.id.cont);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main.this, traceAct.class));
            }
        });

        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main.this, nearby.class));
            }
        });
        sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sm.isChecked()){
                    FirebaseDatabase.getInstance().getReference("use")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("status").setValue("on");
                    startService(new Intent(getApplicationContext(), traceservice.class));
                }
                else {
                    FirebaseDatabase.getInstance().getReference("use")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("status").setValue("off");
                    stopService(new Intent(getApplicationContext(), traceservice.class));
                }
            }
        });
        DatabaseReference myrefs = FirebaseDatabase.getInstance().getReference("use").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        myrefs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myrefs.keepSynced(true);
                if(snapshot.exists()){
                    guard os = snapshot.getValue(guard.class);
                    String status = os.getStatus();
                    if(status.equals("on")){
                        sm.setChecked(true);
                        startService(new Intent(getApplicationContext(), traceservice.class));
                    }else if(status.equals("off")){
                        sm.setChecked(false);
                        stopService(new Intent(getApplicationContext(), traceservice.class));
                    }else{
                        FirebaseDatabase.getInstance().getReference("use")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("status").setValue("off");
                        stopService(new Intent(getApplicationContext(), traceservice.class));
                    }
                }else if(!snapshot.exists()){
                    FirebaseDatabase.getInstance().getReference("use")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("status").setValue("off");
                    stopService(new Intent(getApplicationContext(), traceservice.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw.isChecked()){
                    FirebaseDatabase.getInstance().getReference("guard")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("status").setValue("on");
                    FirebaseDatabase.getInstance().getReference("date")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("date").setValue(date);
                    FirebaseDatabase.getInstance().getReference("diffdate")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("diff").setValue("1");
                    nav.setVisibility(View.VISIBLE);
                    w.setVisibility(View.GONE);
            }
                else {
                    FirebaseDatabase.getInstance().getReference("guard")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("status").setValue("off");
                    FirebaseDatabase.getInstance().getReference("date")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .removeValue();
                    FirebaseDatabase.getInstance().getReference("diffdate")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                    nav.setVisibility(View.GONE);
                    w.setVisibility(View.VISIBLE);
                }
            }
        });

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("guard").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference nref = FirebaseDatabase.getInstance().getReference("date").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myref.keepSynced(true);
                if(snapshot.exists()){
                    guard os = snapshot.getValue(guard.class);
                    String status = os.getStatus();
                    if(status.equals("on")){
                        sw.setChecked(true);
                        nref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    dateHelper d = snapshot.getValue(dateHelper.class);
                                    String dateStr = d.getDate();
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                    try {
                                        Date date1 = sdf.parse(dateStr);
                                        Date date2 = sdf.parse(date);
                                        long difference = Math.abs(date2.getTime() - date1.getTime());
                                        long differenceDates = (difference / (24 * 60 * 60 * 1000))+1;
                                        String dayDifference = Long.toString(differenceDates);
                                        FirebaseDatabase.getInstance().getReference("diffdate")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .child("diff").setValue(dayDifference);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        nav.setVisibility(View.VISIBLE);
                        w.setVisibility(View.GONE);
                    }else if(status.equals("off")){
                        sw.setChecked(false);
                        nav.setVisibility(View.GONE);
                        w.setVisibility(View.VISIBLE);
                    }else{
                        FirebaseDatabase.getInstance().getReference("guard")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("status").setValue("off");
                        nav.setVisibility(View.GONE);
                        w.setVisibility(View.VISIBLE);
                    }
                }else if(!snapshot.exists()){
                    FirebaseDatabase.getInstance().getReference("guard")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("status").setValue("off");
                    nav.setVisibility(View.GONE);
                    w.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        fetchData();

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog2.show();
                if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                    String mAuth = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    name.setText(mAuth);
                    if (user.getPhotoUrl() != null) {
                        Glide.with(main.this)
                                .load(user.getPhotoUrl())
                                .into(dp);
                    }
                }
                pDialog2.dismiss();
            }
        });
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog.show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(main.this, login.class));
                pDialog.dismiss();
            }
        });
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            String mAuth = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            name.setText(mAuth);
            if (user.getPhotoUrl() != null) {
                Glide.with(main.this)
                        .load(user.getPhotoUrl())
                        .into(dp);
            }
        }

        edit = findViewById(R.id.rdit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.this.finish();
                overridePendingTransition(0, 0);
                startActivity(new Intent(main.this, EditProfile.class));
            }
        });
    }

    private void fetchData() {
        String url  = "https://corona.lmao.ninja/v2/all/";

        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvCritical.setText(jsonObject.getString("critical"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvTodayCases.setText(jsonObject.getString("todayCases"));
                            tvTotalDeaths.setText(jsonObject.getString("deaths"));
                            tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                            tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));


                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
                            pieChart.addPieSlice(new PieModel("Recoverd",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29B6F6")));
                            pieChart.startAnimation();

                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);




                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(main.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        main.this.finish();
        System.exit(0);

    }
    public void goTrackCountries(View view) {

        startActivity(new Intent(getApplicationContext(),AffectedCountries.class));

    }
}