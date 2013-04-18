package org.julianharty.activitylogutilities;

import android.os.Bundle;
import android.util.Log;

/**
 * Utilities to log various information to the Android log.
 * 
 * These were created to help test and debug Android Activities that use 
 * 'extras' to pass parameters around.
 *  
 * @author Julian Harty
 *
 */
public class LogUtilities {
	
	/**
	 * Log all the extras the activity receives.
	 * 
	 * Typically these are retrieved using getIntent().getExtras();
	 * 
	 * Here is a code sample:
	 * <pre>
	 * {@code
	 * private static final String LOGIN_ACTIVITY_TAG = "LoginActivity";
	 * 
	 * LogUtilities.logExtras(LOGIN_ACTIVITY_TAG, getIntent().getExtras());
	 * }  
	 * </pre>
	 * @param tag to be used in the Android log messages
	 * @param extras passed to this activity
	 */
	public static void logExtras(final String tag, Bundle extras) {
		if (null == extras) {
			Log.d(tag, "Bundle is null, no extras received");
			return;
		}
		
		Log.d(tag, "logExtras starting to list each extra:");
		for (String name: extras.keySet()) {
			String msg = "";
			Object o = extras.get(name);
			// This probably needs refining to show different data types correctly
			// see http://www.javaworld.com/javaworld/javatips/jw-javatip98.html and 
			// http://www.velocityreviews.com/forums/t302491-instanceof-not-always-bad-the-instanceof-myth.html
			if (o instanceof String) {
				msg = String.format("logExtras: %s=%s", name, extras.getString(name)); 
			} else if (o instanceof Integer) {
				msg = String.format("logExtras: %s=%d", name, extras.getInt(name));
			} else {
				try {
					msg = String.format("logExtras: %s=%s", name, extras.get(name).toString());	
				} catch (Exception e) {
					msg = "logExtras: cannot show value for: " + name;
				}
			}
			Log.d(tag, msg);
		}
	}
	

	/**
	 * A utility method to log values we expect to be set.
	 * 
	 * Here is a code sample:
	 * <pre>
	 * {@code
	 * LogUtilities.logWarningIfNullElseInfo(LOGIN_ACTIVITY_TAG, "mEmail", mEmail);
	 * }
	 * </pre>
	 * @param tag the tag to use in the Android log 
	 * @param name the name of the variable we expect to be set
	 * @param value the value it is currently set to
	 */
	public static void logWarningIfNullElseInfo(final String tag, final String name, final String value) {
		if (value != null) {
			String msg = String.format("%s=%s", name, value);
			Log.i(tag, msg);
		} else {
			String msg = name + " is null";
			Log.w(tag, msg);
		}
		
	}

}
