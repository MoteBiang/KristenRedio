package pg.org.elcpng.kristenredio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import pg.org.elcpng.kristenredio.utils.DataManager;
import pg.org.elcpng.kristenredio.utils.SessionManager;

public class SplashActivity extends Activity {

	private Context context = SplashActivity.this;
	private boolean mIsBackButtonPressed;
	private static final int SPLASH_DURATION = 2000; // 3 seconds
	SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		overridePendingTransition(R.anim.anim_fadein, R.anim.anim_fadeout);

		session = new SessionManager(this);
		DataManager.username = session.getuserid();

		Handler handler = new Handler();

		// run a thread after 2 seconds to start the home screen
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent i = new Intent(context, MainActivity.class);
				i.setAction("splash");
				finish();
				startActivity(i);
				overridePendingTransition(0, 0);

				/*

				if (!mIsBackButtonPressed) {
					if (session.isLoggedIn()) {

						Intent i = new Intent(context, MainActivity.class);
						i.setAction("splash");
						finish();
						startActivity(i);
						overridePendingTransition(0, 0);

					} else {

						Intent i = new Intent(context, UserActivity.class);
						finish();
						startActivity(i);
						overridePendingTransition(0, 0);
					}
				}

				 */

			}

		}, SPLASH_DURATION);
	}
}
