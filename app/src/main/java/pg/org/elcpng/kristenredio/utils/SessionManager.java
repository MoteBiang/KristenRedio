package pg.org.elcpng.kristenredio.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import pg.org.elcpng.kristenredio.MainActivity;
import pg.org.elcpng.kristenredio.UserActivity;
import pg.org.elcpng.kristenredio.models.Customer;

public class SessionManager {
	// Shared Preferences
	SharedPreferences pref;

	// Editor for Shared preferences
	Editor editor;

	// Context
	Context myContext;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "chat";

	// All Shared Preferences Keys
	private static final String IS_LOGIN = "IsLoggedIn";
	private static final String MYUSER_ID = "chatid";
	private static final String FIRSTNAME = "firstname";
	private static final String LASTNAME = "lastname";
	private static final String LOGINTYPE = "logintype";
	private static final String DEVICEID = "deviceid";
	private static final String PROFILEPIC = "profilepic";

	// Constructor
	public SessionManager(Context context) {
		this.myContext = context;
		pref = myContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	public void createLoginSession(String userid, String firstname,
			String lastname, String deviceid) {
		// Storing login value as TRUE

		editor.putBoolean(IS_LOGIN, true);
		// Storing name in pref
		editor.putString(MYUSER_ID, userid);
		editor.putString(FIRSTNAME, firstname);
		editor.putString(LASTNAME, lastname);
		editor.putString(DEVICEID, deviceid);
		// commit changes
		editor.commit();
	}
	
	public void createLoginSession(Customer user) {
		// Storing login value as TRUE

		editor.putBoolean(IS_LOGIN, true);
		editor.putString(MYUSER_ID, user.getCustomerID());
		editor.putString(FIRSTNAME, user.getcustomerName());
		editor.putString(LASTNAME, user.getcustomerSurname());
		editor.putString(DEVICEID, Utils.GetDeviceId(myContext));
		// commit changes
		editor.commit();
	}

	public void setlogintype(String logintype) {
		// Storing login value as TRUE

		// Storing name in pref
		editor.putString(LOGINTYPE, logintype);

		// commit changes
		editor.commit();
	}

	public String getLogintype() {

		String logintype = "";

		logintype = pref.getString(LOGINTYPE, "");

		return logintype;
	}

	public void setProfilepic(String profilepic) {
		// Storing login value as TRUE

		// Storing name in pref
		editor.putString(PROFILEPIC, profilepic);

		// commit changes
		editor.commit();
	}

	public String getProfilePic() {

		String logintype = "";

		logintype = pref.getString(PROFILEPIC, "");

		return logintype;
	}

	/**
	 * Check login method wil check user login status If false it will redirect
	 * user to login page Else won't do anything
	 * */
	public void checkLogin() {
		// Check login status
		if (!this.isLoggedIn()) {
			// user is not logged in redirect him to Login Activity
			Intent i = new Intent(myContext, UserActivity.class);
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Staring Login Activity
			myContext.startActivity(i);

		} else {
			Intent i = new Intent(myContext, MainActivity.class);
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Staring Login Activity
			myContext.startActivity(i);

		}

	}

	public void logoutUser() {
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();

		DataManager.status = "";
		DataManager.message = "";

		// After logout redirect user to Loing Activity
		Intent i = new Intent(myContext, UserActivity.class);
		// Closing all the Activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		System.out.println("utpal testing here....");
		// Add new Flag to start new Activity
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		System.out.println("utpal testing here....2");
		// Staring Login Activity
		myContext.startActivity(i);

	}

	public String getuserid() {
		return pref.getString(MYUSER_ID, "");
	}

	public String getdeviceid() {
		return pref.getString(DEVICEID, "");
	}

	public String firstname() {
		return pref.getString(FIRSTNAME, "");
	}

	public String lastname() {
		return pref.getString(LASTNAME, "");
	}

	/**
	 * Quick check for login
	 * **/
	// Get Login State
	public boolean isLoggedIn() {
		return pref.getBoolean(IS_LOGIN, false);
	}

}
