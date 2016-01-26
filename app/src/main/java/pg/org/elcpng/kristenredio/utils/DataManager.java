package pg.org.elcpng.kristenredio.utils;


import android.os.StrictMode;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import pg.org.elcpng.kristenredio.models.UserPojo;


public class DataManager {

	public static String status = "";
	public static String message = "";
	public static int position = 0;
	public static boolean isindividualopen = false;
	public static String username = "";
	public static String senderid = "";
	public static String profileid = "";
	public static String groupid = "";
	public static String fullname = "";
	public static String url = "http://www.cyberalds.com/chat/";	//"http://swiftomatics.in/ChatServer/";
//	public static String url = "http://192.168.0.103/ChatServer/";
	public static String PROJECT_NUMBER = "549668268833";  // GCM Project number 
	public static ArrayList<UserPojo> alluserlist;

	public static int selectedposition =0;
	
	public static String groupname = "";
	public static String adminid = "";
	
	public static String bannerid = "ca-app-pub-6192865524332826/7091545994";
	
	public static final String FILE_UPLOAD_URL = "http://www.cyberalds.com/chat/fileUpload.php";
    public static final String IMAGE_DIRECTORY_NAME = "YumiChat";
//    public static final String FILE_PATH = "http://swiftomatics.in/ChatServer/uploads/";
    public static final String FILE_PATH = "http://www.cyberalds.com/chat/uploads/";
    public static String action = "";
    
    
	public static String GetDirectURL(String profileid) {

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		String url_send = "http://graph.facebook.com/"+profileid+"/picture?type=large";
		
	    URL url;
	    URL secondURL = null;
	    try {
	        url = new URL(url_send);
	        HttpURLConnection ucon = null;
	        try {
	            ucon = (HttpURLConnection) url.openConnection();
	        } catch (IOException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
	        ucon.setInstanceFollowRedirects(false);
	        secondURL = new URL(ucon.getHeaderField("Location"));
	     
	    } catch (MalformedURLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        return null;
	    }
	    return secondURL.toString();
	}
    
}