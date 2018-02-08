package com.example.tallygosdkupdate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//TODO uncomment to test version 1.3.105
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.tallygo.tallygoandroid.activities.turnbyturn.TGTurnByTurnActivity;
import com.tallygo.tallygoandroid.fragments.navigation.base.TGBaseTurnByTurnFragment;
import com.tallygo.tallygoandroid.sdk.navigation.TGRouteRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.tallygo.tallygoandroid.sdk.TallyGo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        old_version();

        new_version();
    }

//  TODO uncomment content to run version 1.2.96

    private void old_version() {

//        TallyGo.initializeFromMetaData(getApplication(), new TallyGo.InitializeCallback() {
//            @Override
//            public void onSuccess() {
//
//            }
//
//            @Override
//            public void onNoInternetFailure(long l) {
////                Toast.makeText(MainActivity.this, "No internet", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Exception e) {
////                Toast.makeText(MainActivity.this, "FAILURE", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        Intent intent = new Intent(this, TurnByTurnActivity.class);
//        intent.putExtra("incidentLatitude", 40.691335);
//        intent.putExtra("incidentLongitude", -73.9861869);
//        startActivity(intent);
//        finish();
    }

    private void new_version() {
        LatLng currentLocation = new LatLng(32.6809d, -117.1784d); // Hotel Del Coronado
        LatLng destinationCoordinate = new LatLng(32.7306181d,-117.1462286d); // San Diego Zoo
        List waypoints = new ArrayList<>();
        waypoints.add(currentLocation);
        waypoints.add(destinationCoordinate);

// Current date
        Date date = new Date();

// Create the request with the date/time supplied as the departure time
        TGRouteRequest routeRequest = new TGRouteRequest(waypoints, date,
                TGRouteRequest.TGRouteRequestType.DEPARTURE_TIME);

        TGBaseTurnByTurnFragment.Options options = new TGBaseTurnByTurnFragment.Options();
        options.putRouteRequest(routeRequest);

// We assume you'll be testing this at your desk.
// Remove this line when you're ready to try it out on the road.
        options.putSimulated(true);

        Intent intent = new Intent(this, TGTurnByTurnActivity.class);
        intent.putExtra(TGBaseTurnByTurnFragment.Options.TG_BASE_TURN_BY_TURN_OPTIONS_KEY, options);
        startActivity(intent);
    }
}
