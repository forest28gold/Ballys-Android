package com.ballyscolombo.ballys;

import com.ballyscolombo.constants.Global;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MapLocationActivity extends FragmentActivity {
	
	Button btn_back;
	Button btn_home;
	Button btn_map;
	Button btn_satellite;
	
	private GoogleMap mMap;
	public LatLng center;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map_location);
        
        btn_back = (Button)findViewById(R.id.button_back);
        btn_home = (Button)findViewById(R.id.button_home);
        btn_map = (Button)findViewById(R.id.button_map);
        btn_satellite = (Button)findViewById(R.id.button_satellite);
        
        setUpMapIfNeeded();
        
        btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
        btn_home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent( MapLocationActivity.this, MainActivity.class );
				intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
				startActivity(intent);
				finish();
			}
		});
        
        btn_map.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				btn_map.setBackgroundResource(R.drawable.map_map_on);
				btn_satellite.setBackgroundResource(R.drawable.map_satellite_off);
				mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			}
		});
        
        btn_satellite.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				btn_map.setBackgroundResource(R.drawable.map_map_off);
				btn_satellite.setBackgroundResource(R.drawable.map_satellite_on);
				mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			}
		});
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap != null) {
            return;
        }
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.clear();
        
        if (mMap == null) {
            return;
        }
        
        mMap.setIndoorEnabled(true);
		// Enable my-location stuff
		mMap.setMyLocationEnabled(true);
        
        // Initialize map options. For example:
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        
        double latitude = Global.BALLYS_LATITUDE;
        double longitude = Global.BALLYS_LONGITUDE;
        
        if (latitude != 0.0 & longitude != 0.0) {
        	
        	MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(Global.BALLYS_TITLE + " : " + Global.BALLYS_SUBTITLE);

            center = new LatLng(latitude, longitude);
            
            // adding marker

            mMap.addMarker(marker);
            
    		mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    		mMap.animateCamera(CameraUpdateFactory.newLatLng(center), 1750, null);
		}
        
		mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			
			@Override
			public void onInfoWindowClick(Marker arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Global.BALLYS_GOOGLE_URL)));
			}
		});
		
    }
    
}
