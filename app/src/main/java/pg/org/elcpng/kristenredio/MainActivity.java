package pg.org.elcpng.kristenredio;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pg.org.elcpng.kristenredio.fragments.NoticesFragment;
import pg.org.elcpng.kristenredio.fragments.StreamsFragment;
import pg.org.elcpng.kristenredio.models.Customer;
import pg.org.elcpng.kristenredio.models.Notice;
import pg.org.elcpng.kristenredio.models.Stream;
import pg.org.elcpng.kristenredio.utils.DataManager;
import pg.org.elcpng.kristenredio.utils.SessionManager;
import pg.org.elcpng.kristenredio.utils.Utils;

public class MainActivity extends AppCompatActivity implements
        StreamsFragment.OnStreamListener,
        NoticesFragment.OnNoticeListener{

    Context context = MainActivity.this;

    // Toolbar scripts
    Toolbar toolbar;
    int statusBarHeight;
    boolean isStatusBarTransparent = false, isGrownAnim = false;
    View statusBarHolderView;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private SessionManager session;
    private Customer customer;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.black_trans80));

            statusBarHeight = Utils.getStatusBarHeight(this);
            isStatusBarTransparent = true;
        }

        setContentView(R.layout.activity_main);

        overridePendingTransition(R.anim.anim_fadein, R.anim.anim_fadeout);

        session = new SessionManager(this);
        DataManager.username = session.getuserid();

        try {
            title = session.firstname() + " " + session.lastname();
            if(title.length()==0)
            {
                title = getResources().getString(R.string.app_name);
            }
        } catch (Exception e) {
            title = getResources().getString(R.string.app_name);
        }


        // TODO Toolbar Styles Begins ////////////////////////////////////////
        toolbar = (Toolbar) findViewById(R.id.toolbar_transparent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        statusBarHolderView = findViewById(R.id.test_statusbar_holder);
        if (!isStatusBarTransparent) {
            statusBarHolderView.setVisibility(View.GONE);
        }

        final Animation animGrow = AnimationUtils.loadAnimation(this, R.anim.grow_color);
        final Animation animShrink = AnimationUtils.loadAnimation(this, R.anim.shrink_color);

        animGrow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isGrownAnim = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animShrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isGrownAnim = false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        // TODO Toolbar Styles Ends ////////////////////////////////////////

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabsMain);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_main, null);
        tabOne.setText("Harim Redio");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_hear, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_main, null);
        tabTwo.setText("Program");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_program, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_main, null);
        tabThree.setText("Toksave");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_schedules, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    /**
     * Adding fragments to ViewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new StreamsFragment(), "Harim Redio");
        adapter.addFrag(new NoticesFragment(), "Program");
        adapter.addFrag(new NoticesFragment(), "Toksave");

        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_translate_in_left_to_right, R.anim.activity_translate_out_left_to_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menu_main_share) {
            return true;
        }
        if (id == R.id.menu_main_info) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStreamListener(Stream item) {

    }

    @Override
    public void onNoticeListener(Notice item) {

    }
}
