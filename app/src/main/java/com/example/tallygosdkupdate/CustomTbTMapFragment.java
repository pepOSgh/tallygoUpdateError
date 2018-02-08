package com.example.tallygosdkupdate;

//TODO uncomment class to run version 1.2.96

//
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AlertDialog.Builder;
//import android.support.v7.widget.PopupMenu;
//import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.OnClickListener;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.view.animation.Animation.AnimationListener;
//import android.widget.FrameLayout.LayoutParams;
//
//import com.mapbox.mapboxsdk.geometry.LatLng;
//import com.mapbox.mapboxsdk.maps.MapboxMap.OnMapLongClickListener;
//import com.tallygo.tallygoandroid.R.anim;
//import com.tallygo.tallygoandroid.R.dimen;
//import com.tallygo.tallygoandroid.R.id;
//import com.tallygo.tallygoandroid.R.layout;
//import com.tallygo.tallygoandroid.R.menu;
//import com.tallygo.tallygoandroid.R.string;
//import com.tallygo.tallygoandroid.activities.TGBaseActivity;
//import com.tallygo.tallygoandroid.activities.TallyGoActivity;
//import com.tallygo.tallygoandroid.activities.map.TGBaseMapFragment;
//import com.tallygo.tallygoandroid.endpoint.navigation.TGRoute;
//import com.tallygo.tallygoandroid.sdk.TGMapView;
//import com.tallygo.tallygoandroid.sdk.TallyGo;
//import com.tallygo.tallygoandroid.sdk.search.TGReverseGeocodeRequest;
//import com.tallygo.tallygoandroid.sdk.search.TGReverseGeocodeResponse;
//import com.tallygo.tallygoandroid.sdk.search.TGSearchEndpoint.TGReverseGeocodeCallback;
//import com.tallygo.tallygoandroid.ui.views.TGMapSearch;
//import com.tallygo.tallygoandroid.ui.views.TGRouteBar;
//import com.tallygo.tallygoandroid.ui.views.TGMapSearch.Container;
//import com.tallygo.tallygoandroid.utils.TGToastHelper;
//import com.tallygo.tallygoandroid.utils.TGUtils;


//public class CustomTbTMapFragment extends TGBaseMapFragment implements Container, com.tallygo.tallygoandroid.ui.views.TGRouteBar.Container {
//    public static final String TAG = CustomTbTMapFragment.class.getName();
//
//    private static final String DESTINATION_LAT = "destLat";
//    private static final String DESTINATION_LON = "destLon";
//
//    private TGMapSearch mapSearch;
//    private TGRouteBar routeBar;
//    private TGReverseGeocodeCallback reverseGeocodeCallback;
//
//    private LatLng desinationCoords;
//    public CustomTbTMapFragment() {
//    }
//
//    public static CustomTbTMapFragment newInstance(double lat, double lon) {
//        CustomTbTMapFragment var0 = new CustomTbTMapFragment();
//        Bundle args = new Bundle();
//        args.putDouble(DESTINATION_LAT, lat);
//        args.putDouble(DESTINATION_LON, lon);
//        var0.setArguments(args);
//        return var0;
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            double lat = getArguments().getDouble(DESTINATION_LAT);
//            double lon = getArguments().getDouble(DESTINATION_LON);
//            desinationCoords = new LatLng(lat,lon);
//        }
//
//    }
//
//
//    public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
//        View var4 = var1.inflate(layout.fragment_map, var2, false);
//        this.mapSearch = (TGMapSearch)var4.findViewById(id.ms_map_search);
//        this.mapSearch.setContainer(this);
//        this.mapSearch.setVisibility(View.GONE);
//        this.routeBar = (TGRouteBar)var4.findViewById(id.nb_map_nav_bar);
//        this.routeBar.setVisibility(View.GONE);
//        this.routeBar.setContainer(this);
//        this.setMapHolder((TGMapView)var4.findViewById(id.tgmv_map));
//        super.onCreateView(var1, var2, var3);
//
//        this.mapView.enableMenuButton(false);
//        this.mapView.enableCurrentLocationButton(false);
//
//        return var4;
//    }
//
//    private void requestGeocode(final LatLng var1) {
//        TGReverseGeocodeRequest var2 = new TGReverseGeocodeRequest(var1);
//        this.reverseGeocodeCallback = new TGReverseGeocodeCallback() {
//            public void onSuccess(TGReverseGeocodeResponse var1x) {
//                Log.i("geocodeResponse", var1x.getStringResponse());
//                if(this == CustomTbTMapFragment.this.reverseGeocodeCallback) {
//                    if(var1x.getAddress() == null) {
//                        this.onFailure(new Exception("Null address"));
//                    } else {
//                        CustomTbTMapFragment.this.updateRouteBar(var1, var1x.getAddress().getShortLabel(), var1x.getAddress().getLongLabel());
//                    }
//                }
//            }
//
//            public void onFailure(Exception var1x) {
//                CustomTbTMapFragment.this.routeBar.setAddressText(CustomTbTMapFragment.this.getString(string.unknown), "");
//                CustomTbTMapFragment.this.routeBar.setRouteAvailable(false);
//            }
//        };
//        TallyGo.getTGSearch().reverseGeocode(var2, this.reverseGeocodeCallback);
//    }
//
//    protected void onCurrentLocationEnabled() {
//        this.centerOnCurrentLocation();
//        this.mapView.getCurrentLocationButton().setCentered(true);
//    }
//
//    protected void onTallyGoMapReady() {
//        this.enableCurrentLocation(true);
//        this.mapSearch.setTgMap(this.tgMap);
//        this.routeBar.setTgMap(this.tgMap);
//
//
//        CustomTbTMapFragment.this.showReverseGeocodedPoint(desinationCoords);
//        CustomTbTMapFragment.this.mapView.getCurrentLocationButton().setCentered(false);
//    }
//
//    protected void refreshMapPadding() {
//        if(this.mapSearch != null && this.routeBar != null) {
//            int var1 = ((LayoutParams)this.mapSearch.getLayoutParams()).topMargin + this.mapSearch.getSearchBox().getHeight();
//            int var2 = this.getResources().getDimensionPixelOffset(dimen.compass_margin);
//            this.tgMap.getMap().getUiSettings().setCompassMargins(var2, var2, var2, var2);
//            this.tgMap.getMap().setPadding(0, var1, 0, 0);
//        }
//    }
//
//    protected void onTallyGoMapRendered() {
//    }
//
//    public void onShowRouteOptions() {
//        TGToastHelper.showShort(this.getContext(), "Coming soon");
//    }
//
//    public void onStartNavigation(TGRoute var1) {
//        if(this.getActivity() instanceof com.tallygo.tallygoandroid.activities.navigation.TGNavigationFragment.Container) {
//            ((com.tallygo.tallygoandroid.activities.navigation.TGNavigationFragment.Container)this.getActivity()).onShowNavigationFragment(var1.getJsonString());
//        } else {
//            if(!(this.getParentFragment() instanceof com.tallygo.tallygoandroid.activities.navigation.TGNavigationFragment.Container)) {
//                TGToastHelper.showLong(this.getContext(), "Activity or parent fragment do not extendthe navigation fragment container!");
//                return;
//            }
//
//            ((com.tallygo.tallygoandroid.activities.navigation.TGNavigationFragment.Container)this.getParentFragment()).onShowNavigationFragment(var1.getJsonString());
//        }
//
//        this.hideRouteBar();
//        this.mapSearch.clear();
//    }
//
//    public void onCleared() {
//        this.hideRouteBar();
//    }
//
//    public void onCanceled() {
//        TGUtils.hideKeyboard(this.getActivity());
//    }
//
//    public void onAddressDisplayed(LatLng var1, String var2, String var3) {
//        this.updateRouteBar(var1, var2, var3);
//        TGUtils.hideKeyboard(this.getActivity());
//    }
//
//    public void onNoResults() {
//        this.showNoResultsDialog();
//    }
//
//    private void showReverseGeocodedPoint(LatLng var1) {
//        this.routeBar.clear();
//        this.mapSearch.clear();
//        this.tgMap.mainMarkerDropped(var1);
//        this.mapSearch.setSearchText(TGUtils.getLatLngString(var1));
//        this.showRouteBar();
//        this.requestGeocode(var1);
//    }
//
//    private void showMapMenu() {
//        PopupMenu var1;
//        (var1 = new PopupMenu(this.getContext(), this.mapView.getMenuButton())).setGravity(8388613);
//        var1.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//            public boolean onMenuItemClick(MenuItem var1) {
//                ((TGBaseActivity) CustomTbTMapFragment.this.getActivity()).displayInternalOptionsDialog();
//                return true;
//            }
//        });
//        var1.getMenuInflater().inflate(menu.map_menu, var1.getMenu());
//        var1.show();
//    }
//
//    private void showRouteBar() {
//        if(this.routeBar.getVisibility() != View.VISIBLE) {
//            this.routeBar.setVisibility(View.VISIBLE);
//            Animation var1;
//            (var1 = AnimationUtils.loadAnimation(this.getContext(), anim.slide_up)).setAnimationListener(new AnimationListener() {
//                public void onAnimationStart(Animation var1) {
//                }
//
//                public void onAnimationEnd(Animation var1) {
//                    CustomTbTMapFragment.this.refreshMapPadding();
//                }
//
//                public void onAnimationRepeat(Animation var1) {
//                }
//            });
//            this.routeBar.startAnimation(var1);
//        }
//    }
//
//    private void hideRouteBar() {
//        if(this.routeBar.getVisibility() != View.GONE) {
//            Animation var1;
//            (var1 = AnimationUtils.loadAnimation(this.getContext(), anim.slide_down)).setAnimationListener(new AnimationListener() {
//                public void onAnimationStart(Animation var1) {
//                }
//
//                public void onAnimationEnd(Animation var1) {
//                    CustomTbTMapFragment.this.routeBar.setVisibility(View.GONE);
//                    CustomTbTMapFragment.this.routeBar.clear();
//                    CustomTbTMapFragment.this.refreshMapPadding();
//                }
//
//                public void onAnimationRepeat(Animation var1) {
//                }
//            });
//            this.routeBar.startAnimation(var1);
//        }
//    }
//
//    private void updateRouteBar(LatLng var1, String var2, String var3) {
//        if(this.routeBar.getVisibility() != View.VISIBLE) {
//            this.showRouteBar();
//        }
//
//        this.routeBar.requestRoute(var1);
//        this.routeBar.setAddressText(var2, var3);
//    }
//
//    private void showNoResultsDialog() {
//        (new Builder(this.getContext())).setTitle("No Results").setMessage("I can't find anything right now.").setPositiveButton(string.ok, new android.content.DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface var1, int var2) {
//            }
//        }).setIcon(R.drawable.mapbox_marker_icon_default).show();
//    }
//}
