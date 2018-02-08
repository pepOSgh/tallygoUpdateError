package com.example.tallygosdkupdate;

//TODO uncomment class to run version 1.2.96

//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import com.tallygo.tallygoandroid.activities.navigation.TGNavigationFragment;


//public class TurnByTurnActivity extends AppCompatActivity implements TGNavigationFragment.Container {
//
//    private SharedPreferences preferences;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_turn_by_turn);
//
//        Intent intent = getIntent();
//        double incidentLat = intent.getDoubleExtra("incidentLatitude",0.0d);
//        double incidentLon = intent.getDoubleExtra("incidentLongitude",0.0d);
//
//        this.preferences = this.getSharedPreferences("com.tallygo.tallygoandroid.PREFERENCE_FILE_KEY", 0);
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        CustomTbTMapFragment mapFragment = CustomTbTMapFragment.newInstance(incidentLat, incidentLon);
//        transaction.add(R.id.turn_by_turn_map, mapFragment);
//        transaction.commit();
//    }
//
//    @Override
//    public void onStart(){
//        super.onStart();
//    }
//
//    @Override
//    public void onStop(){
//        super.onStop();
//    }
//
//    @Override
//    public void onShowNavigationFragment(String s) {
//
//        boolean var2 = this.preferences.getBoolean("SIMULATION_ENABLED", false);
//        boolean var3 = this.preferences.getBoolean("SIMULATION_WRONG_TURN", false);
//        boolean var4 = this.preferences.getBoolean("SIMULATION_CLICK_TO_UPDATE", false);
//        TGNavigationFragment var5 = TGNavigationFragment.newInstance(s, var2, var3, var4);
//        FragmentTransaction var6;
//        var6 = this.getSupportFragmentManager().beginTransaction();
//
//        var6.add(R.id.turn_by_turn_map, var5, TGNavigationFragment.TAG);
//        var6.commit();
//    }
//
//    @Override
//    public void onHideNavigationFragment() {
//
//    }
//}
