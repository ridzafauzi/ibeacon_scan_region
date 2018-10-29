package com.example.budakgigibesi.ibeacon_scan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

public class MainActivity extends AppCompatActivity implements BootstrapNotifier {

    private RegionBootstrap regionBootstrap;
    private BackgroundPowerSaver backgroundPowerSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		//enable beacon features///////////////////////////////////////////////////////////////////////
		
		BeaconManager beaconManager = org.altbeacon.beacon.BeaconManager.getInstanceForApplication(this);
		beaconManager.getBeaconParsers().clear();
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
		beaconManager.setEnableScheduledScanJobs(false);	// disable JobScheduler-based scans (used on Android 8+)
        beaconManager.setBackgroundBetweenScanPeriod(0);	// set the time between each scan to be 1 hour (3600 seconds)
        beaconManager.setBackgroundScanPeriod(1100);	// set the duration of the scan to be 1.1 seconds
		
        Region region = new Region("backgroundRegion", null, null, null);
        regionBootstrap = new RegionBootstrap(this, region);	// wake up the app when a beacon is seen
		
		backgroundPowerSaver = new BackgroundPowerSaver(this);	//This reduces bluetooth power usage by about 60%
		
		//////////////////////////////////////////////////////////////////////////////////////////////
    }
	
	@Override
    public void didEnterRegion(Region arg0) {
       Log.i("mybeacon", "i found a beacon");
	   TextView mTextView = (TextView) findViewById(R.id.id_tv);
	   mTextView.setText("i found a beacon");
    }
	
	@Override
    public void didExitRegion(Region region) {

    }
	
	@Override
    public void didDetermineStateForRegion(int state, Region region) {

    }
}
