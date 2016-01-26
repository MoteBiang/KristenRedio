package pg.org.elcpng.kristenredio.fragments;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.util.AQUtility;
import com.flurry.android.FlurryAgent;

import java.util.ArrayList;
import java.util.Collections;

import pg.org.elcpng.kristenredio.MainActivity;
import pg.org.elcpng.kristenredio.R;
import pg.org.elcpng.kristenredio.models.Stream;
import pg.org.elcpng.kristenredio.player.InterfacePlayer;
import pg.org.elcpng.kristenredio.player.ServicePlayer;
import pg.org.elcpng.kristenredio.playerutils.ClsTrack;
import pg.org.elcpng.kristenredio.playerutils.Constants;
import pg.org.elcpng.kristenredio.playerutils.UpdateUtils;
import pg.org.elcpng.kristenredio.utils.DatabaseHandler;

public class StreamsFragment extends Fragment implements InterfacePlayer {

    private static final String TAG = StreamsFragment.class.getSimpleName();

    private Activity activity;
    private AQuery aq;

    private SeekBar sbPlayer;
    private TextView lbLog;
    private Button btnInitFile, btnInitUrl, btnPlay, btnPause, btnStop;
    private EditText txtURL;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnStreamListener mListener;

    private Context context;
    private DatabaseHandler dbh;


    ///////////////////////////////

    // Bass Service
    private ServicePlayer mBoundService = null;

    // Bass Service Connection
    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder service) {
            mBoundService = ((ServicePlayer.BassServiceBinder) service).getService();

            onBassServiceConnected();
            setShuffleMode(PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean("Shuffle", false));

        }

        public void onServiceDisconnected(ComponentName name) {
            mBoundService = null;
        }

    };

    ///////////////////////////////

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StreamsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static StreamsFragment newInstance(int columnCount) {
        StreamsFragment fragment = new StreamsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AQUtility.setDebug(Constants.DEBUG);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_streamer, container, false);

        context = getActivity();
        activity = getActivity();

        dbh = new DatabaseHandler(context);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new StreamsAdapter(dbh.GetStreamsList(), mListener));
        }

        // Start Service
        context.startService(new Intent(context, ServicePlayer.class));

        // Bind Service
        context.bindService(new Intent(context, ServicePlayer.class), mConnection, Context.BIND_AUTO_CREATE);
        new UpdateUtils(activity);

        return view;
    }

    // onBassServiceConnected: Put some activity stuff here
    public void onBassServiceConnected() {


        // Register Activity
        mBoundService.setActivity(this);

        aq = new AQuery(activity);


        sbPlayer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    final int prgr = progress;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mBoundService.seekTo(prgr);
                        }
                    }).start();

                }
            }
        });

        aq.id(R.id.btnPlay).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBoundService != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (mBoundService.isPlaying()) {
                                mBoundService.pause();
                                //aq.id(R.id.btnPlay).background(R.drawable.player_play_button);
                            } else {
                                if (mBoundService.isPaused()) {
                                    mBoundService.playFromPause();
                                    mBoundService.startVolumeUpFlag = System.currentTimeMillis();
                                    //aq.id(R.id.btnPlay).background(R.drawable.player_pause_button);
                                } else {
                                    mBoundService.play(0);
                                    mBoundService.startVolumeUpFlag = System.currentTimeMillis();
                                }
                            }
                            updatePlayPause();
                        }
                    }).start();

                }

            }
        });

        update();
    }

    private void setShuffleMode(boolean mode) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("Shuffle", mode).commit();
        mBoundService.setShuffle(mode);
        shuffleItemsList();
        updatePlayPause();
    }

    private void shuffleItemsList() {

    }

    public void startPlaylist(int type) {

    }

    private boolean synchronizeTrackList() {

        return false;
    }

    public void update() {

    }

    public void updatePlayPause() {



    }

    @Override
    public void onDestroy() {

        super.onDestroy();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStreamListener) {
            mListener = (OnStreamListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNoticeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPluginsLoaded(String plugins) {

    }

    @Override
    public void onFileLoaded(ClsTrack track, double duration, String artist, String title, int position, int albumId) {

    }

    @Override
    public void onProgressChanged(double progress) {

    }

    @Override
    public void onUpdatePlayPause() {

    }

    public interface OnStreamListener {
        // TODO: Update argument type and name
        void onStreamListener(Stream item);
    }
}
