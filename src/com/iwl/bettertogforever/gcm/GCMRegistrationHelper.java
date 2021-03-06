package com.iwl.bettertogforever.gcm;

import android.app.Activity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class GCMRegistrationHelper {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	/**
	 * Check the device to make sure it has the Google Play Services APK. If
	 * it doesn't, display a dialog that allows users to download the APK from
	 * the Google Play Store or enable it in the device's system settings.
	 */
	public boolean checkPlayServices(Activity activity) {
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, (Activity) activity,
	                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            //finish();
	        }
	        return false;
	    }
	    return true;
	}
}
