package pg.org.elcpng.kristenredio.utils;

import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import pg.org.elcpng.kristenredio.R;

public class Utils {

	public static final int TIME_OUT_3S = 3000;
	public static final int TIME_OUT_5S = 5000;
	public static final int TIME_OUT_10S = 10000;
	public static final int TIME_OUT_15S = 15000;
	public static final int TIME_OUT_20S = 20000;
	
	// Time constants
	private static final int SECOND_MILLIS = 1000;
	private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
	private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
	private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

	public static final String FIREBASE_BASE_URL = "https://mititok.firebaseio.com/";
	public static final String FIREBASE_REDIO_URL = "https://mititok.firebaseio.com/redio/";
	public static final String FIREBASE_PROGRAM_URL = "https://mititok.firebaseio.com/program/";
	public static final String FIREBASE_TOKSAVE_URL = "https://mititok.firebaseio.com/toksave/";

	public static final String LOCAL_CUSTOMER_REGISTRATION_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/KinaMax";
	public static final String PHOTO_DIRECTORY = LOCAL_CUSTOMER_REGISTRATION_DIR + "/Photos";
	public static final String BITMAP_DIRECTORY = LOCAL_CUSTOMER_REGISTRATION_DIR + "/Bitmaps";
	public static final String METADATA_DIRECTORY = LOCAL_CUSTOMER_REGISTRATION_DIR + "/MetaData";
	public static final String SIGNDATA_DIRECTORY = LOCAL_CUSTOMER_REGISTRATION_DIR + "/SignDocs";
	public static final String PDFDATA_DIRECTORY = LOCAL_CUSTOMER_REGISTRATION_DIR + "/PDFDocs";
	public static final String CSV_DIRECTORY = LOCAL_CUSTOMER_REGISTRATION_DIR + "/CSVDocs";
	
	public static String GetActivationCode()
	{
		String characters = "1234567890";
		Random rand = new Random();
		
		char text[] = new char[7];
		for(int i=0; i<7; i++)
		{
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}
		
		return new String(text);
	}
	
	public static String GetTimeAgo(long time) {
	    if (time < 1000000000000L) {
	        // if timestamp given in seconds, convert to millis
	        time *= 1000;
	    }

	    long now = getCurrentTimeMillis();
	    if (time > now || time <= 0) {
	        return null;
	    }

	    final long diff = now - time;
	    if (diff < MINUTE_MILLIS) {
	        return "Just now";
	    } else if (diff < 2 * MINUTE_MILLIS) {
	        return "A minute ago";
	    } else if (diff < 50 * MINUTE_MILLIS) {
	        return diff / MINUTE_MILLIS + " minutes ago";
	    } else if (diff < 90 * MINUTE_MILLIS) {
	        return "An hour ago";
	    } else if (diff < 24 * HOUR_MILLIS) {
	        return diff / HOUR_MILLIS + " hours ago";
	    } else if (diff < 48 * HOUR_MILLIS) {
	        return "Yesterday";
	    } else {
	        return diff / DAY_MILLIS + " days ago";
	    }
	}
	
	public static void Toast(String Message, Context context, LayoutInflater inflater, int IconIndex, int DurationType)
	{
		View toastRoot = inflater.inflate(R.layout.toast, null);
		
		TextView lbToastText = (TextView)toastRoot.findViewById(R.id.lbToastText);
		lbToastText.setText(Message);
		ImageView picToastIcon = (ImageView)toastRoot.findViewById(R.id.picToastIcon);
		 
		Toast toast = new Toast(context);
		 
		toast.setView(toastRoot);
		toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
		
		switch(IconIndex)
		{
			case 0:
				//Default Icon
				picToastIcon.setImageResource(R.drawable.ic_launcher);
				break;
			case 1:
				//Question Icon
				picToastIcon.setImageResource(android.R.drawable.ic_menu_help);
				break;
			case 2:
				//Error Icon
				picToastIcon.setImageResource(android.R.drawable.ic_menu_info_details);
				break;				
			default:
				//Default Icon
				picToastIcon.setImageResource(R.drawable.ic_launcher);
				break;
				
		}
		
		switch(DurationType)
		{
			case 0:
				toast.setDuration(Toast.LENGTH_SHORT);
				break;
			case 1:
				toast.setDuration(Toast.LENGTH_LONG);
				break;
			default:
				toast.setDuration(Toast.LENGTH_SHORT);
				break;
		}
		
		toast.show();
		
	}
	
	public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
    
	public static String CapitaliseFirstStringCharacter(String Value)
    {    	
    	return Value.toUpperCase().replace(Value.substring(1), Value.substring(1).toLowerCase());
    }
	
    public static double GetDistanceInMetres(double Latitude1, double Longitude1, double Latitude2, double Longitude2) {
        
		double lat1Rad = Math.toRadians(Latitude1);
        double lat2Rad = Math.toRadians(Latitude2);
        double deltaLonRad = Math.toRadians(Longitude2 - Longitude1);        
                
        double distance = Math.acos(Math.sin(lat1Rad) * Math.sin(lat2Rad) + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.cos(deltaLonRad))
                * 6371;
        
        distance = ((double)Math.round(distance * 100) / 100) *1000;
        
        return distance;
        
    }
    
    public static double GetDistanceInKiloMetres(double Latitude1, double Longitude1, double Latitude2, double Longitude2) {
        
		double lat1Rad = Math.toRadians(Latitude1);
        double lat2Rad = Math.toRadians(Latitude2);
        double deltaLonRad = Math.toRadians(Longitude2 - Longitude1);        
                
        double distance = Math.acos(Math.sin(lat1Rad) * Math.sin(lat2Rad) + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.cos(deltaLonRad))
                * 6371;
        
        distance = (double)Math.round(distance * 100) / 100;
        
        return distance;
        
    }
    
    public static double GetBearing(double Latitude1, double Longitude1, double Latitude2, double Longitude2)
    {
    	double degToRad = Math.PI / 180.0;
    	double phi1 = Latitude1 * degToRad;
    	double phi2 = Latitude2 * degToRad;
    	double lam1 = Longitude1 * degToRad;
    	double lam2 = Longitude2 * degToRad;
    	double bearing = Math.atan2(Math.sin(lam2-lam1)*Math.cos(phi2), 
    			Math.cos(phi1)*Math.sin(phi2)-Math.sin(phi1)*Math.cos(phi2)*Math.cos(lam2-lam1)) * 180/Math.PI;
    	
    	return (double)Math.round(bearing * 100) / 100;
    }
    
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    public static String GetTimeStamp(int index)
	{
		String timestamp = null;
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df;
		
		switch(index)
		{
			case 0:
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        timestamp = df.format(c.getTime());
		        break;
		        
			case 1:
				df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		        timestamp = df.format(c.getTime());
		        break;
		        
			case 2:
				df = new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss");
		        timestamp = df.format(c.getTime());
		        break;
		}
        
        
        return timestamp;
        
	}
    
    public static String GetSQLiteTimeStampFromMySQL(String Timestamp, int index)
	{
		String timestamp = null;
		
		SimpleDateFormat df;
		
		switch(index)
		{
			case 0:
				
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				try
				{
					Date date = df.parse(Timestamp);
					timestamp = df.format(date);
				}
				catch (ParseException e)
				{
					
				}
		        
		        break;
		        
			case 1:
				
				df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		        
				try
				{
					Date date = df.parse(Timestamp);
					timestamp = df.format(date);
				}
				catch (ParseException e)
				{
					
				}
				
		        break;
		        
			case 2:
				df = new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss");
		        
				try
				{
					Date date = df.parse(Timestamp);
					timestamp = df.format(date);
				}
				catch (ParseException e)
				{
					
				}
				
		        break;
		}
        
        
        return timestamp;
        
	}
    
    public static String GetTimeOfDayContextGreeting()
    {
    	String Greeting = null;
    	int H, M;
    	
    	Time time = new Time();
    	time.setToNow();
    	
		H = time.hour;
		M = time.minute;
		
		if((H >= 0) && (H < 12))
		{
			if((M >= 0) && (M < 60))
			{
				Greeting = "Good Morning";
			}
		}
		else if((H > 11) && (H < 19))
		{
			if((M >= 0) && (M < 60))
			{
				Greeting = "Good Afternoon";
			}
		}
		else if((H > 18) && (H <= 23))
		{
			if((M >= 0) && (M < 60))
			{
				Greeting = "Good Evening";
			}
		}
    	
    	return Greeting;
    }
    
    public static String GetTimeOfDayContextGreeting_TokPisin()
    {
    	String Greeting = null;
    	int H, M;
    	
    	Time time = new Time();
    	time.setToNow();
    	
		H = time.hour;
		M = time.minute;
		
		if((H >= 0) && (H < 12))
		{
			if((M >= 0) && (M < 60))
			{
				Greeting = "Gutpla Moning";
			}
		}
		else if((H > 11) && (H < 19))
		{
			if((M >= 0) && (M < 60))
			{
				Greeting = "Gutpla Abinun";
			}
		}
		else if((H > 18) && (H <= 23))
		{
			if((M >= 0) && (M < 60))
			{
				Greeting = "Gutpla Nait";
			}
		}
    	
    	return Greeting;
    }
    
    public static double RoundOff2DP(double Value)
    {
    	return (double)Math.round(Value * 100) / 100;
    }
    
    public static String GetICCDSerial(Context context)
    {   	
    	String response = null;
    	
   		try
   		{
   			TelephonyManager mTelephonyMgr = 
   					(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
   			response = mTelephonyMgr.getSimSerialNumber();
   		}
   		catch(Exception e)
   		{
   			//Log.e("Utils.GetICCDSerial()", e.toString());
   		}
   		
   		return response; 
    }
    
    public static String GetDeviceId(Context context)
    {   		
    	String response = null;
   		
    	try
    	{
    		TelephonyManager mTelephonyMgr = 
    				(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
       		response = mTelephonyMgr.getDeviceId(); 
    	}
    	catch(Exception e)
    	{
    		//Log.e("Utils.GetDeviceId()", e.toString());
    	}
   		
   		return response;
    }
    
    public static String GetPhoneType(Context context)
    {
    	
    	TelephonyManager mTelephonyMgr = 
    			(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    	
    	int phoneType = mTelephonyMgr.getPhoneType();
    	
    	switch(phoneType)
    	{
    		case TelephonyManager.PHONE_TYPE_NONE:
    			
    			return "NONE";

    		case TelephonyManager.PHONE_TYPE_GSM:
    			
    			return "GSM";

    		case TelephonyManager.PHONE_TYPE_CDMA:
    			
    			return "CDMA";   
    			
    		default:
    			
    			return "UNKNOWN";
    	}    	
    }
    
    public static String GetSimOperatorName(Context context)
    {   		
    	String response = null;
    	
   		try
   		{
   			TelephonyManager mTelephonyMgr = 
   					(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
   	   		response = mTelephonyMgr.getSimOperatorName(); 
   		}
   		catch(Exception e)
   		{
   			//Log.e("Utils.GetSimOperatorName()", e.toString());
   		}
   		
   		return response;
   		
    }
    
    public static String GetSubscriberId(Context context)
    {   		
   		TelephonyManager mTelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
   		return mTelephonyMgr.getSubscriberId(); 
    }
    
    public static String GetAndroidOSVersion(Context context)
    {
    	String Version = null;
    	
    	int intVersion = Build.VERSION.SDK_INT;
    	
    	switch(intVersion)
    	{
    		case 1:
    			Version = "BASE";
    			break;
    		case 2:
    			Version = "Android 1.1 BASE_1_1";
    			break;
    		case 3:
    			Version = "Android 1.5 CUPCAKE";
    			break;
    		case 4:
    			Version = "Android 1.6 DONUT";
    			break;
    		case 5:
    			Version = "Android 2.0 ECLAIR";
    			break;
    		case 6:
    			Version = "Android 2.0.1 ECLAIR_0_1";
    			break;
    		case 7:
    			Version = "Android 2.1 ECLAIR_MR1";
    			break;
    		case 8:
    			Version = "Android 2.2 FROYO";
    			break;
    		case 9:
    			Version = "Android 2.3 GINGERBREAD";
    			break;
    		case 10:
    			Version = "Android 2.3.3 GINGERBREAD_MR1";
    			break;
    		case 11:
    			Version = "Android 3.0 HONEYCOMB";
    			break;
    		case 12:
    			Version = "Android 3.1 HONEYCOMB_MR1";
    			break;
    		case 13:
    			Version = "Android 3.2 HONEYCOMB_MR2";
    			break;
    		case 14:
    			Version = "Android 4.0 ICE_CREAM_SANDWICH";
    			break;
    		case 15:
    			Version = "Android 4.0.3 ICE_CREAM_SANDWICH_MR1";
    			break;
    		case 16:
    			Version = "Android 4.1 JELLY_BEAN";
    			break;
    		case 17:
    			Version = "Android 4.2 JELLY_BEAN_MR1";
    			break;
    		case 18:
    			Version = "Android 4.3 JELLY_BEAN_MR2";
    			break;
    		case 19:
    			Version = "Android 4.4 KITKAT";
    			break;
    		default:
    			Version = "Unknown";
    			break;
    		
    	}
    	
		return Version;
    	
    }
    
    public static String GetModelInfo() {
    	return String.format("%s", Build.MODEL);
    }
    
    public static String ProcesslMobileNumber(String ContactNumber) {
    	
    	String MobileNumber = ContactNumber.replaceAll("\\s+",""); 
    	MobileNumber = MobileNumber.replace("+", "");
    	int NumChars = MobileNumber.length();
    	
    	// International SMS Reciever
    	if(NumChars > 11)
    	{
    		return MobileNumber;
    	}
    	else // PNG SMS Reciever
    	{	
        	switch (NumChars) {
    			case 7:
    				MobileNumber = null;
    				break;
    			case 8:
    				MobileNumber = "675" + MobileNumber;
    				break;

		    		default:
		    			return MobileNumber;
    		}
    	}
	    
    	return MobileNumber;
    	
    }
    
    public static String ConstructSMSWithID(String SendFromNumber, String MyMobileNumber, String Message) {
    	
    	String result = "";
    	
    	MyMobileNumber = ProcesslMobileNumber(MyMobileNumber);
    	    	
    	if(MyMobileNumber.startsWith("67576"))
    	{
    		// To BMobile Only
    		result = Message;
    	}
    	else
    	{
    		// To Digicel and the rest of the world
    		result = SendFromNumber + ":\n" + Message;
    	}
    		    
    	return result;
    	
    }
    
    public static String GetDigicelMobileNumber(String Message) {
    	
    	String MobileNumber = "";
    	
    	String[] splitArray = Message.split("\\s+");    	    
	    for(int i=0; i < splitArray.length; i++)
	    {	    	
	    	if(splitArray[i].startsWith("675"))
	    	{
	    		MobileNumber = splitArray[i].substring(3);
	    	}
	    }
    	
	    MobileNumber = MobileNumber.replace(".", "");
	    
    	return MobileNumber;
    	
    }
    
    public static String GetCitiFonMobileNumber(String Message)
    {
    	String MobileNumber = "";
    	
    	String[] splitArray = Message.split("\\s+");    	    
	    for(int i=0; i < splitArray.length; i++)
	    {
	    	MobileNumber = splitArray[0];
	    }
    	
    	return MobileNumber;
    }
    
    public static String GetNameFromNumber(Context context, String number)
    {
    	String name = null;
    	
    	String[] projection = new String[]
    	{
    		ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID
    	};
    	
	    Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
	    Cursor cursor = context.getContentResolver().query(contactUri, projection, null, null, null);
	    
	    if(cursor != null)
	    {
	    	if(cursor.moveToFirst())
	    	{
	    		name = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
	    	}
	    	else
	    	{
	    		name=number;
	    	}
	    	cursor.close();
	    }
    	
    	return name;
    }
    
    public static String RemoveSingleQuotes(String value)
    {
    	value = value.replace("'", "");
    	return value;
    }
    
    public static void PlaySMSAlert(Context context)
	{
		Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		MediaPlayer mMediaPlayer = new MediaPlayer();
		
		try
		{
			mMediaPlayer.setDataSource(context, soundUri);
		} 
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
		catch (SecurityException e) {
			e.printStackTrace();
		} 
		catch (IllegalStateException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		
		if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
		mMediaPlayer.setLooping(false);
		
		try
		{
			mMediaPlayer.prepare();
		}
		catch (IllegalStateException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		mMediaPlayer.start();
		}
	}
    
    public static int getToolbarHeight(Context context) {
        int height = (int) context.getResources().getDimension(R.dimen.abc_action_bar_default_height_material);
        return height;
    }

    public static int getStatusBarHeight(Context context) {
        int height = (int) context.getResources().getDimension(R.dimen.statusbar_size);
        return height;
    }
}
